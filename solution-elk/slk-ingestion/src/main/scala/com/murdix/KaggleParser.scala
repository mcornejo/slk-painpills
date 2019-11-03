package com.murdix

import scala.util.Try

object KaggleParser {

  val cleanNull: Option[String] => Option[String] = {
    case Some("null") => None
    case x => x
  }
  /*
   * A function literal (also known as an anonymous function)
   * that takes each line of the Kaggle TSV file and transforms
   * it into a Option[PainPills]
   */
  val parser: Array[String] => Option[PainPills] = (line: Array[String]) => {
    Try {
      val tokenizedLined: Array[String] = line.map(_.trim)

      PainPills(noReporterDEA = cleanNull(Try(tokenizedLined(0)).toOption),
        reporterBusAct = cleanNull(Try(tokenizedLined(1)).toOption),
        reporterName = cleanNull(Try(tokenizedLined(2)).toOption),
        reporterAddlCoInfo = cleanNull(Try(tokenizedLined(3)).toOption),
        reporterAddress1 = cleanNull(Try(tokenizedLined(4)).toOption),
        reporterAddress2 = cleanNull(Try(tokenizedLined(5)).toOption),
        reporterCity = cleanNull(Try(tokenizedLined(6)).toOption),
        reporterState = cleanNull(Try(tokenizedLined(7)).toOption),
        reporterZip = Try(tokenizedLined(8).toInt).toOption,
        reporterCounty = cleanNull(Try(tokenizedLined(9)).toOption),
        buyerDEA = cleanNull(Try(tokenizedLined(10)).toOption),
        buyerBusAct = cleanNull(Try(tokenizedLined(11)).toOption),
        buyerName = cleanNull(Try(tokenizedLined(12)).toOption),
        buyerAddlCoInfo = cleanNull(Try(tokenizedLined(13)).toOption),
        buyerAddress1 = cleanNull(Try(tokenizedLined(14)).toOption),
        buyerAddress2 = cleanNull(Try(tokenizedLined(15)).toOption),
        buyerCity = cleanNull(Try(tokenizedLined(16)).toOption),
        buyerState = cleanNull(Try(tokenizedLined(17)).toOption),
        buyerZip = Try(tokenizedLined(18).toInt).toOption,
        buyerCounty = cleanNull(Try(tokenizedLined(19)).toOption),
        transactionCode = cleanNull(Try(tokenizedLined(20)).toOption),
        drugCode = cleanNull(Try(tokenizedLined(21)).toOption),
        NDC = Try(tokenizedLined(22).toLong).toOption,
        drugName = cleanNull(Try(tokenizedLined(23)).toOption),
        quantity = Try(tokenizedLined(24).toDouble).toOption,
        unit = cleanNull(Try(tokenizedLined(25)).toOption),
        actionIndicator = cleanNull(Try(tokenizedLined(26)).toOption),
        orderForm = cleanNull(Try(tokenizedLined(27)).toOption),
        correction = cleanNull(Try(tokenizedLined(28)).toOption),
        strength = cleanNull(Try(tokenizedLined(29)).toOption),
        transactionDate = cleanNull(Try(tokenizedLined(30)).toOption),
        transactionDay = cleanNull(Try(tokenizedLined(30).slice(2, 4)).toOption),
        transactionMonth = cleanNull(Try(tokenizedLined(30).take(2)).toOption),
        transactionYear = cleanNull(Try(tokenizedLined(30).slice(4, 8)).toOption),
        calcBaseWTInGm = Try(tokenizedLined(31).toDouble).toOption,
        dosageUnit = Try(tokenizedLined(32).toDouble).toOption,
        transactionID = Try(tokenizedLined(33).toLong).toOption,
        productName = cleanNull(Try(tokenizedLined(34)).toOption),
        ingredientName = cleanNull(Try(tokenizedLined(35)).toOption),
        measure = cleanNull(Try(tokenizedLined(36)).toOption),
        MMEConversionFactor = Try(tokenizedLined(37).toDouble).toOption,
        combinedLabelerName = cleanNull(Try(tokenizedLined(38)).toOption),
        revisedCompanyName = cleanNull(Try(tokenizedLined(39)).toOption),
        reporterFamily = cleanNull(Try(tokenizedLined(40)).toOption),
        dos = Try(tokenizedLined(41).toDouble).toOption
      )
    }.toOption
  }
}
