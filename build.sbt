name := "Statsd client for Akka"

version := "0.0"

organization := ""

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies <<= scalaVersion {
  scala_version =>
    val akkaVersion  = "2.2.0"
    Seq(
      "com.typesafe.akka" %% "akka-actor"  % akkaVersion,
      "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
      "org.scalatest" %% "scalatest" % "2.0.M6-SNAP8" % "test")
}

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M6-SNAP8" % "test"
