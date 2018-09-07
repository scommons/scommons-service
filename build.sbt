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
  `scommons-service-dao`,
  `scommons-service-util`,
  `scommons-service-test`,
  `scommons-service-play`
)

lazy val `scommons-service-dao` = ServiceDao.definition
lazy val `scommons-service-util` = ServiceUtil.definition
lazy val `scommons-service-test` = ServiceTest.definition
lazy val `scommons-service-play` = ServicePlay.definition
