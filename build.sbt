ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.4"

val mainClassPath = "org.tinhn.scala.main"
maintainer := "tinhn@gmail.com"

lazy val root = (project in file("."))
  .settings(
    name := "KafkaScalaDemo",
    idePackagePrefix := Some("org.tinhn.scala"),
    assembly / mainClass := Some(mainClassPath),
    Compile / mainClass := Some(mainClassPath)
  )

// using 'provided' to exclude JAR files
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.8.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.9" % "provided"

// sbt native packager settings
enablePlugins(JavaAppPackaging)
enablePlugins(JDKPackagerPlugin)
enablePlugins(JlinkPlugin)

jlinkIgnoreMissingDependency := JlinkIgnore.only(
  "scala.quoted" -> "scala",
  "scala.quoted.runtime" -> "scala"
)

//SBT Proguard plugin
enablePlugins(SbtProguard)
Proguard / proguardOptions ++= Seq(
  "-dontoptimize",
  "-dontnote",
  "-dontwarn",
  "-ignorewarnings"
)

Proguard / proguardOptions += ProguardOptions.keepMain(mainClassPath)

Proguard / proguardInputs := (Compile / dependencyClasspath).value.files
Proguard / proguardFilteredInputs ++= ProguardOptions.noFilter(
  (Compile / packageBin).value
)