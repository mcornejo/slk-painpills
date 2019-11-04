package com.murdix

import org.scalatest._

class KaggleParserTest extends FlatSpec with Matchers {
  "The parser" should "parse 5 random lines correctly" in {
    val line1: String = "PA0006836\tDISTRIBUTOR\tACE SURGICAL SUPPLY CO INC\tnull\t1034 PEARL STREET\tnull\tBROCKTON\tMA\t2301\tPLYMOUTH\tBT3484653\tPRACTITIONER\tTABRIZI, HAMID R DMD\tnull\t389 MAIN STREET, SUITE 404\tnull\tMALDEN\tMA\t2148\tMIDDLESEX\tS\t9193\t00406036301\tHYDROCODONE\t1.0\tnull\tnull\tnull\tnull\tnull\t12262012\t0.6054\t100.0\t64\tHYDROCODONE BIT/ACETA 10MG/500MG USP\tHYDROCODONE BITARTRATE HEMIPENTAHYDRATE\tTAB\t1.0\tSpecGx LLC\tMallinckrodt\tACE Surgical Supply Co Inc\t10.0"
    val line2: String = "PB0020052\tDISTRIBUTOR\tKPH HEALTHCARE SERVICES, INC.\tKINNEY DRUGS WAREHOUSE\t520 EAST MAIN ST.\tnull\tGOUVERNEUR\tNY\t13642\tSAINT LAWRENCE\tAK0563723\tCHAIN PHARMACY\tKPH HEALTHCARE SERVICES, INC.\tKINNEY DRUGS #13\t1729 STATE STREET\tnull\tWATERTOWN\tNY\t13601\tJEFFERSON\tS\t9193\t00591034905\tHYDROCODONE\t3.0\tnull\tnull\tnull\tnull\t0000\t03012007\t4.5405\t1500.0\t15\tHYDROCODONE BIT 5MG/ACETAMINOPHEN 50\tHYDROCODONE BITARTRATE HEMIPENTAHYDRATE\tTAB\t1.0\tActavis Pharma, Inc.\tAllergan, Inc.\tKPH Healthcare Services, Inc.\t5.0"
    val line3: String = "PG0149650\tDISTRIBUTOR\tAMERICAN SALES COMPANY\tnull\t7110 AMBASSADOR ROAD\tnull\tBALTIMORE\tMD\t21244\tBALTIMORE\tBM9728291\tCHAIN PHARMACY\tMARTIN'S PHARMACY #6283\tnull\t400 GATEWAY DRIVE\tnull\tWINCHESTER\tVA\t22603\tFREDERICK\tS\t9193\t00603388421\tHYDROCODONE\t1.0\tnull\tnull\tnull\tnull\tnull\t12292011\t0.45405\t100.0\t10757\tHYDROCODO.BIT 7.5MG TAB\tHYDROCODONE BITARTRATE HEMIPENTAHYDRATE\tTAB\t1.0\tPar Pharmaceutical\tEndo Pharmaceuticals, Inc.\tAmerican Sales Company\t7.5"
    val line4: String = "PK0070297\tDISTRIBUTOR\tKINRAY INC\tnull\t152-35 10TH AVE.\tnull\tWHITESTONE\tNY\t11357\tQUEENS\tB99619745\tRETAIL PHARMACY\t1491 LEX AVE PHARMACY INC\tnull\t1491 LEXINGTON AVENUE\tnull\tNEW YORK\tNY\t10029\tNEW YORK\tS\t9193\t53746010901\tHYDROCODONE\t1.0\tnull\tnull\tnull\tnull\tnull\t07062007\t0.3027\t100.0\t51894\tHYDROCODONE BIT./ACETAMINOPHEN TABS.\tHYDROCODONE BITARTRATE HEMIPENTAHYDRATE\tTAB\t1.0\tAmneal Pharmaceuticals LLC\tAmneal Pharmaceuticals, Inc.\tKinray Inc\t5.0"
    val line5: String = "PK0132706\tDISTRIBUTOR\tKAISER FOUNDATION HOSPITALS\tnull\t9521 DALEN ST\tnull\tDOWNEY\tCA\t90242\tLOS ANGELES\tFK3105017\tRETAIL PHARMACY\tKAISER PERMANENTE PHARMACY #363\tnull\t3430 E LA PALMA AVE, BLDG 2, FL 1\tnull\tANAHEIM\tCA\t92806\tORANGE\tS\t9193\t00179011230\tHYDROCODONE\t48.0\tnull\tnull\tnull\tnull\tnull\t12262012\t4.35888\t1440.0\t4415\tHYDROCODONE BITARTRATE/ACETA 5MG/325\tHYDROCODONE BITARTRATE HEMIPENTAHYDRATE\tTAB\t1.0\tKaiser Foundation Hospitals\tKaiser Foundation Hospitals\tKaiser Permanente\t5.0"

    val maybePainPills1: Option[PainPills] = KaggleParser.parser(line1.split("\t"))
    val maybePainPills2: Option[PainPills] = KaggleParser.parser(line2.split("\t"))
    val maybePainPills3: Option[PainPills] = KaggleParser.parser(line3.split("\t"))
    val maybePainPills4: Option[PainPills] = KaggleParser.parser(line4.split("\t"))
    val maybePainPills5: Option[PainPills] = KaggleParser.parser(line5.split("\t"))

    println(maybePainPills1)
    println(maybePainPills2)
    println(maybePainPills3)
    println(maybePainPills4)
    println(maybePainPills5)

    maybePainPills1.isDefined shouldBe true
    maybePainPills1.get.noReporterDEA shouldBe Some("PA0006836")

    maybePainPills2.isDefined shouldBe true
    maybePainPills2.get.noReporterDEA shouldBe Some("PB0020052")

    maybePainPills3.isDefined shouldBe true
    maybePainPills3.get.noReporterDEA shouldBe Some("PG0149650")

    maybePainPills4.isDefined shouldBe true
    maybePainPills4.get.noReporterDEA shouldBe Some("PK0070297")

    maybePainPills5.isDefined shouldBe true
    maybePainPills5.get.noReporterDEA shouldBe Some("PK0132706")
  }

}
