package com.murdix.painpills.jobs

import com.murdix.painpills.entities.PainPills
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object AggregateJob extends App{
  implicit val spark: SparkSession = SparkSession.builder
    .appName("AggegateJob")
    .getOrCreate()

  import spark.implicits._

  /* We open the files in ORC format */
  val allDrugs: Dataset[PainPills] = spark.read.orc("s3://pain-pills-raw/").as[PainPills]

  /* We save it in different locations depending on their aggregations.
  * They can be stored in HDFS, S3 or they can be sent directly to Elasticsearch.
  * */
  allDrugs
    .groupBy( "drugName","transactionYear", "transactionMonth", "transactionDay", "transactionDate")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("s3://pain-pills-processed/day/")

  allDrugs
    .groupBy( "drugName","transactionYear", "transactionMonth", "transactionDate")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("s3://pain-pills-processed/month/")

  allDrugs
    .groupBy( "drugName","buyerCity", "transactionDate")
    .agg(count("drugName") as "count")
    .coalesce(1)
    .write
    .format("json")
    .mode(SaveMode.Append)
    .save("s3://pain-pills-processed/city/")
}
