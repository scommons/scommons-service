import definitions._
import scommons.sbtplugin.project.CommonModule

lazy val `scommons-service` = (project in file("."))
  .settings(CommonModule.settings: _*)
  .settings(ServiceModule.settings: _*)
  .settings(
    skip in publish := true,
    publish := (),
    publishM2 := ()
  )
  .settings(
    ideaExcludeFolders += s"${baseDirectory.value}/docs/_site"
  )
  .aggregate(
  `scommons-service-play`
)

lazy val `scommons-service-play` = ServicePlay.definition
