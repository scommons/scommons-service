package definitions

import common.TestLibs
import sbt.Keys._
import sbt._

object ServiceUtil extends ServiceModule {

  override val id: String = "scommons-service-util"

  override val base: File = file("util")

  override def definition: Project = super.definition
    .settings(
      description := "Common service utilities"
    )

  override val internalDependencies: Seq[ClasspathDep[ProjectReference]] = Nil

  override val runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
  ))

  override val testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    TestLibs.scalaTest.value,
    TestLibs.mockito.value
  ).map(_ % "test"))
}
