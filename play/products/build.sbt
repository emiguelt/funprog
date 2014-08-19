name := """products"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "net.sf.barcode4j" % "barcode4j" % "2.0",
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7"
)
