import common.Common
import definitions._

lazy val `scommons-service` = (project in file("."))
  .settings(Common.settings)
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

lazy val `scommons-service-play` = ScommonsServicePlay.definition
