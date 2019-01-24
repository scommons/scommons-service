package definitions

import common.TestLibs
import sbt.Keys._
import sbt._

object ServiceTest extends ServiceModule {

  override val id: String = "scommons-service-test"

  override val base: File = file("test")

  override def definition: Project = super.definition
    .settings(
      description := "Common service integration tests utilities"
    )

  override val internalDependencies: Seq[ClasspathDep[ProjectReference]] = Nil

  override val runtimeDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    TestLibs.scalaTestPlusPlay.value,
    TestLibs.akkaStreamTestKit.value,
    TestLibs.dockerTestkitScalatest.value,
    TestLibs.dockerTestkitImpl.value,
    TestLibs.mockito.value
  ))

  override val testDependencies: Def.Initialize[Seq[ModuleID]] = Def.setting(Seq(
    TestLibs.scalaTest.value,
    TestLibs.mockito.value
  ).map(_ % "test"))
}
