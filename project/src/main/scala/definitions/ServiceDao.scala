package definitions

import common.TestLibs
import sbt.Keys._
import sbt._

object ServiceDao extends ServiceModule {

  override val id: String = "scommons-service-dao"

  override val base: File = file("dao")

  override def definition: Project = super.definition
    .settings(
      description := "Common Data Access Object (DAO) utilities and components"
    )

  override val internalDependencies: Seq[ClasspathDep[ProjectReference]] = Nil

  override val runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
  ))

  override val testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    TestLibs.scalaTest.value,
    TestLibs.scalaTestPlusMockito.value
  ).map(_ % "test"))
}
