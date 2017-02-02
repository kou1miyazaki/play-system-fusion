name := """play-system-fusion2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, JavaAppPackaging, PlayEnhancer)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  filters,
  javaWs
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

scalacOptions ++= Seq("-encoding", "UTF-8")

javacOptions ++= Seq("-encoding", "UTF-8")

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// Add
// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)

// Java project. Don't expect Scala IDE
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
// Use .class files instead of generated .scala files for views and routes
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)

ivyConfiguration ~= { originalIvyConfiguration =>
  val config = originalIvyConfiguration.asInstanceOf[InlineIvyConfiguration]
  val ivyHome = file("./.ivy2")
  val ivyPaths = new IvyPaths(config.paths.baseDirectory, Some(ivyHome))
  new InlineIvyConfiguration(ivyPaths, config.resolvers, config.otherResolvers,
    config.moduleConfigurations, config.localOnly, config.lock,
    config.checksums, config.resolutionCacheDir, config.log)
}
