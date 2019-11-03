package com.murdix.painpills.jobs

import com.murdix.painpills.entities.PainPills
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object AggregateTest extends App{
  implicit val spark: SparkSession = SparkSession.builder
    .appName("AggregateTest")
    .config("spark.master", "local").config("spark.sql.orc.impl", "native")
    .getOrCreate()

  import spark.implicits._

  val allDrugs: Dataset[PainPills] = spark.read.orc("src/test/resources/kaggle/processed/").as[PainPills]

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
