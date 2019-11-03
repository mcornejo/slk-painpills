package com.murdix.painpills.jobs

import com.murdix.painpills.entities.PainPills
import com.murdix.painpills.parser.KaggleParser
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object KaggleJob extends App {
  implicit val spark: SparkSession = SparkSession.builder
    .appName("KaggleJob")
    .getOrCreate()

  import spark.implicits._

  /* We read the dump (or raw) files in external format (csv, tsv, json etc) */
  val rawFile: Dataset[String] = spark.read.textFile("s3://pain-pills-dump/*")

  /* We parse every line */
  val allDrugs: Dataset[PainPills] = rawFile.flatMap(line => KaggleParser.parser(line))

  /* We partition the data, select the format and save it */
  allDrugs
    .write
    .partitionBy(
      "drugName",
      "transactionYear",
      "transactionMonth",
      "transactionDay"
    )
    .format("orc")
    .option("compression", "zlib")
    .mode(SaveMode.Append)
    .save("s3://pain-pills-raw")
}
