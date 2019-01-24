package definitions

import common.{Libs, TestLibs}
import sbt.Keys._
import sbt._

object ServicePlay extends ServiceModule {

  override val id: String = "scommons-service-play"

  override val base: File = file("play")

  override def definition: Project = super.definition
    .settings(
      description := "Common Scala Play! utilities and components"
    )

  override val internalDependencies: Seq[ClasspathDep[ProjectReference]] = Nil

  override val superRepoProjectsDependencies: Seq[(String, String, Option[String])] = Seq(
    ("scommons-api", "scommons-api-coreJVM", None)
  )

  override val runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    Libs.scommonsApiCore.value,
    
    Libs.play.value
  ))

  override val testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    TestLibs.scalaTestPlusPlay.value,
    TestLibs.mockito.value
  ).map(_ % "test"))
}
