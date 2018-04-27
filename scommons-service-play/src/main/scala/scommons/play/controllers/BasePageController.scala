package scommons.play.controllers

import controllers.AssetsFinder
import play.api.mvc._
import play.twirl.api.StringInterpolation

abstract class BasePageController(components: ControllerComponents,
                                  finder: AssetsFinder,
                                  prefix: String,
                                  project: String) extends AbstractController(components) {

  lazy val index: Action[AnyContent] = {
    val assetsBase = finder.assetsBasePath
    val assetsUrl = s"$prefix${finder.assetsUrlPrefix}"

    def findFirst(prefix: String, suffix: String): String = {
      val name = project.toLowerCase
      val resources = List(
        s"$prefix$name-opt$suffix",
        s"$prefix$name-fastopt$suffix"
      )

      resources.collectFirst {
        case res if getClass.getResource(s"$assetsBase/$res") != null =>
          s"$assetsUrl/$res"
      }.getOrElse(throw new IllegalArgumentException(
        s"Could not find resource, project: $project, searched for: $resources"
      ))
    }

    def scriptUrl(suffix: String): String = findFirst("", suffix)
    def styleUrl(): String = findFirst("styles/", ".css")

    val mainStyle = styleUrl()
    val mainScript = scriptUrl(".js")
    val loaderScript = scriptUrl("-loader.js")
    val libraryScript = scriptUrl("-library.js")
    val commonAssetsUrl = s"$assetsUrl/lib/scommons-client-assets"

    Action(Ok(
      html"""<!doctype html>
        <html>
          <head>
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />

            <link rel="stylesheet" href="$commonAssetsUrl/css/bootstrap.min.css" />
            <link rel="stylesheet" href="$commonAssetsUrl/css/bootstrap-responsive.min.css" />
            <link rel="stylesheet" href="$commonAssetsUrl/css/custom.css" />

            <link rel="stylesheet" href="$mainStyle" />
          </head>
          <body>
            <div id="root">Loading, please, wait...</div>

            <script src="$libraryScript"></script>
            <script src="$loaderScript"></script>
            <script src="$mainScript"></script>
          </body>
        </html>
        """
    ))
  }
}
