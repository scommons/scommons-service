package common

import sbt._

object Libs {

  private val scommonsApiVersion = "0.1.0-SNAPSHOT"
  private val playVer = "2.6.7"

  lazy val scommonsApiCore = Def.setting("org.scommons.api" %% "scommons-api-core" % scommonsApiVersion)

  lazy val play = Def.setting("com.typesafe.play" %% "play" % playVer)
}
