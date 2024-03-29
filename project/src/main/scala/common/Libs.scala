package common

import sbt._
import scommons.sbtplugin.project.CommonLibs

object Libs extends CommonLibs {

  private val scommonsApiVersion = "1.0.0"

  lazy val scommonsApiCore = Def.setting("org.scommons.api" %% "scommons-api-core" % scommonsApiVersion)
}
