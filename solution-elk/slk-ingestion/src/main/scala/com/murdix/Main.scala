package com.murdix

import java.io.File
import java.time.LocalDateTime

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{FileIO, Framing, Sink}
import akka.util.ByteString
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContextExecutor
import scala.util._

object Main extends App {

  // Implicits of akka, Actor System and Execution Context
  implicit val system: ActorSystem = ActorSystem("PainPills")
  implicit val mat: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  // HTTP Connection pool
  val pool = Http().cachedHostConnectionPool[Int]("localhost", 9200)

  val fullPipeline = FileIO  
    .fromPath(new File("src/main/resources/kaggle/sample10k.tsv").toPath)   // Open the file
    .via(Framing.delimiter(ByteString("\n"), 1024))   // Split by line
    .map(_.utf8String.split('\t'))                    // Split by token tab-separated
    .map(KaggleParser.parser)                         // Convert the tokens into a object
    .map(_.asJson.noSpaces)                           // Convert the object in json
    .map( data =>                                     // Create the HTTP Request with the json payload
      (
        HttpRequest(
        HttpMethods.POST,
        "http://localhost:9200/painpills/_doc",
        entity = HttpEntity(ContentTypes.`application/json`, data.getBytes())
      ), 1)
    )
    .via(pool)                                       // Executes the HTTP Request within a pool.
    .runWith(Sink.foreach {
      case (Success(s), i) => println(s" $s succeeded")
      case (Failure(e), i) => println(s"[${LocalDateTime.now}] $i failed: $e")
    })
}
