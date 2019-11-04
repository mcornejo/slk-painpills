name := "painpills-elk"

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.26"
lazy val circeVerison = "0.11.1"
lazy val akkaHttpVersion = "10.1.10"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http"   % akkaHttpVersion,

  "io.circe" %% "circe-core" % circeVerison,
  "io.circe" %% "circe-generic" % circeVerison,
  "io.circe" %% "circe-parser" %circeVerison,

  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
