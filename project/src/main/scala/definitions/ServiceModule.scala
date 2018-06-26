package definitions

import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule

trait ServiceModule extends CommonModule {

  override val repoName = "scommons-service"

  override def definition: Project = {
    super.definition
      .settings(ServiceModule.settings: _*)
  }
}

object ServiceModule {

  val settings: Seq[Setting[_]] = Seq(
    organization := "org.scommons.service",
    
    //
    // publish/release related settings:
    //
    publishMavenStyle := true,
    publishArtifact in Test := false,
    publishTo := {
      if (isSnapshot.value)
        Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
      else
        Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
    },
    pomExtra := {
      <url>https://github.com/scommons/scommons-service</url>
        <licenses>
          <license>
            <name>Apache 2</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:scommons/scommons-service.git</url>
          <connection>scm:git@github.com:scommons/scommons-service.git</connection>
        </scm>
        <developers>
          <developer>
            <id>viktorp</id>
            <name>Viktor Podzigun</name>
            <url>https://github.com/viktor-podzigun</url>
          </developer>
        </developers>
    },
    pomIncludeRepository := {
      _ => false
    }
  )
}
