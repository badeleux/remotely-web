name := "picknplay"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "fr.greweb" %% "playcli" % "0.1" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("com.jsuereth", "scala-arm_2.10.0-RC1") exclude("com.github.scala-incubator.io", "scala-io-core_2.10.0-RC1") exclude("com.github.scala-incubator.io", "scala-io-file_2.10.0-RC1")
)     

play.Project.playScalaSettings
