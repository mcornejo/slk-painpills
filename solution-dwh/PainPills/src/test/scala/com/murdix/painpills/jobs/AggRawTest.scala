package com.murdix.painpills.jobs

import com.murdix.painpills.entities.PainPills
import com.murdix.painpills.parser.KaggleParser
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object AggRawTest extends App{
  implicit val spark: SparkSession = SparkSession.builder
    .appName("AggRawTest")
    .config("spark.master", "local").config("spark.sql.orc.impl", "native")
    .getOrCreate()

  import spark.implicits._

  val rawFile: Dataset[String] = spark.read.textFile("src/test/resources/kaggle/sample10k.tsv")
  val allDrugs: Dataset[PainPills] = rawFile.flatMap(line => KaggleParser.parser(line))

  allDrugs
    .groupBy( "drugName","transactionYear", "transactionMonth", "transactionDay", "transactionDate")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("src/test/resources/kaggle/agg/day/")

  allDrugs
    .groupBy( "drugName","transactionYear", "transactionMonth", "transactionDate")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("src/test/resources/kaggle/agg/month/")

  allDrugs
    .groupBy( "drugName","buyerCity")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("src/test/resources/kaggle/agg/city/")
}
