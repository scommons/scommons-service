package common

import sbt._
import scommons.sbtplugin.project.CommonLibs

object Libs extends CommonLibs {

  private val scommonsApiVersion = "0.8.1"

  lazy val scommonsApiCore = Def.setting("org.scommons.api" %% "scommons-api-core" % scommonsApiVersion)
}
