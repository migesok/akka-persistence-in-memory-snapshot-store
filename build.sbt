organization := "com.migesok"

name := "akka-persistence-in-memory-snapshot-store"

version := "0.1.0"

licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

bintrayPublishSettings

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.2")

resolvers += "krasserm at bintray" at "http://dl.bintray.com/krasserm/maven"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence-experimental" % "2.3.6",
  "org.scala-stm" %% "scala-stm" % "0.7",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.github.krasserm" %% "akka-persistence-testkit" % "0.3.4" % "test",
  "com.github.michaelpisula" %% "akka-persistence-inmemory" % "0.2.1" % "test"
)
