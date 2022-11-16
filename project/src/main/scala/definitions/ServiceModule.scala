package definitions

import org.scoverage.coveralls.Imports.CoverallsKeys._
import sbt.Keys._
import sbt._
import scommons.sbtplugin.project.CommonModule
import xerial.sbt.Sonatype.autoImport._

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
    
    coverallsService := GitHubActionsCI.jobId.map(_ => GitHubActionsCI),

    //
    // publish/release related settings:
    //
    sonatypeProfileName := "org.scommons",
    publishMavenStyle := true,
    Test / publishArtifact := false,
    publishTo := sonatypePublishToBundle.value,
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
