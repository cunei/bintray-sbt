lazy val commonSettings: Seq[Setting[_]] = Seq(
  git.baseVersion in ThisBuild := "0.3.0",
  organization in ThisBuild := "com.eed3si9n"
)

lazy val root = (project in file(".")).
  enablePlugins(GitVersioning).
  settings(
    commonSettings,
    // organization := "me.lessis",
    // version := "0.3.0-SNAPSHOT",
    name := "bintray-sbt",
    description := "package publisher for bintray.com",
    homepage := Some(url(s"https://github.com/eed3si9n/${name.value}#readme")),
    sbtPlugin := true,
    libraryDependencies ++= Seq(
      "me.lessis" %% "bintry" % "0.4.0",
      "org.slf4j" % "slf4j-nop" % "1.7.7"), // https://github.com/softprops/bintray-sbt/issues/26
    scalacOptions ++= Seq(Opts.compile.deprecation, "-feature"),
    resolvers += Resolver.sonatypeRepo("releases"),
    licenses ++= Seq("MIT" -> url(
      s"https://github.com/eed3si9n/${name.value}/blob/${version.value}/LICENSE")),
    publishArtifact in Test := false,
    pomExtra := (
      <scm>
        <url>git@github.com:eed3si9n/{name.value}.git</url>
        <connection>scm:git:git@github.com:eed3si9n/{name.value}.git</connection>
      </scm>
      <developers>
        <developer>
          <id>softprops</id>
          <name>Doug Tangren</name>
          <url>https://github.com/softprops</url>
        </developer>
      </developers>
    ),
    lsSettings,
    externalResolvers in LsKeys.lsync := (resolvers in bintray.Keys.bintray).value
  )
