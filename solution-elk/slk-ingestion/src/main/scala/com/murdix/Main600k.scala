package com.murdix

import java.io.File
import java.time.LocalDateTime

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}
import akka.stream.scaladsl.Compression.gunzip
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{FileIO, Flow, Framing, Sink, Source}
import akka.util.ByteString
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util._

// To make it runnable, extends App should be appended.
object Main600k {

  // Implicits of akka, Actor System and Execution Context
  implicit val system: ActorSystem = ActorSystem("PainPills")
  implicit val mat: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val pool = Http().cachedHostConnectionPool[Int]("localhost", 9200)

  // This file does not exists in the repo. It is a big file that can be created from the full dataset.
  val fullPipeline: Future[Done] = FileIO
    .fromPath(new File("src/main/resources/kaggle/x01.gz").toPath)
    .via(gunzip())
    .via(Framing.delimiter(ByteString("\n"), 1024))
    .map(_.utf8String.split('\t'))
    .map(KaggleParser.parser)
    .map(_.asJson.noSpaces)
    .map( data =>
      (
        HttpRequest(
        HttpMethods.POST,
        "http://localhost:9200/painpills/_doc",
        entity = HttpEntity(ContentTypes.`application/json`, data.getBytes())
      ), 1)
    )
    .via(pool)
    .runWith(Sink.foreach {
      case (Success(s), i) => println(s" $s succeeded")
      case (Failure(e), i) => println(s"[${LocalDateTime.now}] $i failed: $e")
    })

  fullPipeline.onComplete {
    case Success(_) =>
      system.terminate()
    case Failure(e) =>
      println(s"Failure: ${e.getMessage}")
      system.terminate()
  }

}
