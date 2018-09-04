name := "ae_twitter_task"

version := "1.0"

scalaVersion := "2.12.1"


libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-core" % "4.0.7",
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test",
  "org.scalactic" %% "scalactic" % "3.0.5"
)
