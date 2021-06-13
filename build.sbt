name := "prime-numbers"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.twitter" %% "finatra-http" % "21.2.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % Runtime
libraryDependencies += "com.twitter" %% "scrooge-core" % "21.2.0"
libraryDependencies += "com.twitter" %% "finagle-thrift" % "21.2.0"