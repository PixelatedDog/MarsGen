ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "mars-photo-gen",
    idePackagePrefix := Some("uk.co.tomsturgeon.marsgen")
  )

val catsCoreVersion = "2.3.0"
val catsEffectVersion = "3.3.5"
val http4sVersion =  "0.23.9"
val cirisVersion = "2.3.2"
val typesafeConfigVersion = "1.4.1"

val scalaTestVersion = "3.2.11"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsCoreVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "is.cir" %% "ciris" % cirisVersion,
  "org.scalactic" %% "scalactic" % scalaTestVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.10",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",


  // Testing
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
