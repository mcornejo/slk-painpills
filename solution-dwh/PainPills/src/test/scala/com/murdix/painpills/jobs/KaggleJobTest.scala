package com.murdix.painpills.jobs

import com.murdix.painpills.entities.PainPills
import com.murdix.painpills.parser.KaggleParser
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object KaggleJobTest extends App {
  implicit val spark: SparkSession = SparkSession.builder
    .appName("KaggleJobTest")
    .config("spark.master", "local").config("spark.sql.orc.impl", "native")
    .getOrCreate()

  import spark.implicits._

  val rawFile: Dataset[String] = spark.read.textFile("src/test/resources/kaggle/sample10k.tsv")
  val allDrugs: Dataset[PainPills] = rawFile.flatMap(line => KaggleParser.parser(line))

  allDrugs
    .write
    .partitionBy(
      "drugName"
    )
    .format("orc")
    .option("compression", "zlib")
    .mode(SaveMode.Append)
    .save("src/test/resources/kaggle/processed/")  // the path could be a S3 bucket "s3://pain-pills-raw"
}
