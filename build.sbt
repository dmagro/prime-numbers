name := "prime-numbers"

version := "0.1"

scalaVersion := "2.13.6"

lazy val versions = new {
  val finatra = "21.2.0"
  val logback = "1.2.3"
  val scrooge = "21.2.0"
  val finagle = "21.2.0"
  val scalaTest = "3.2.8"
}

libraryDependencies += "com.twitter" %% "finatra-http" % versions.finatra
libraryDependencies += "ch.qos.logback" % "logback-classic" % versions.logback % Runtime
libraryDependencies += "com.twitter" %% "scrooge-core" % versions.scrooge
libraryDependencies += "com.twitter" %% "finagle-thrift" % versions.finagle

libraryDependencies += "com.twitter" %% "finatra-http" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "finatra-jackson" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-server" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-app" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-core" % versions.finatra % "test" classifier "tests"
libraryDependencies += "com.twitter" %% "inject-modules" % versions.finatra % "test" classifier "tests"
libraryDependencies += "org.scalatest" %% "scalatest" % versions.scalaTest % Test
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % "test"