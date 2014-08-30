name := """PlayChat"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "jquery" % "2.1.1"
)
