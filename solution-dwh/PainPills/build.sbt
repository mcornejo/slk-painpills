import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.murdix",
      scalaVersion := "2.11.12",
      version      := "0.1"
    )),
    name := "painpills",
    libraryDependencies += scalaTest % Test,

    libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4",

    libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4",

    libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.663",

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8",

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

  )

enablePlugins(JavaAppPackaging)

