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

    def getResources(prefix: String, suffix: String): List[String] = {
      val name = project.toLowerCase
      List(
        s"$prefix$name-opt$suffix",
        s"$prefix$name-fastopt$suffix"
      )
    }

    def findFirst(resources: List[String]): Option[String] = {
      resources.collectFirst {
        case res if getClass.getResource(s"$assetsBase/$res") != null =>
          s"$assetsUrl/$res"
      }
    }

    def scriptUrl(suffix: String): String = {
      val resources = getResources("", suffix)

      findFirst(resources).getOrElse(throw new IllegalArgumentException(
        s"Could not find resource, project: $project, searched for: $resources"
      ))
    }

    def styleUrl(): Option[String] = findFirst(getResources("styles/", ".css"))

    val mainStyle = styleUrl().map { url =>
      html"""<link rel="stylesheet" href="$url" />"""
    }.getOrElse("")

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
            $mainStyle

            <script type="text/javascript">
              scommons = {
                UiSettings: {
                  imgClearCacheUrl: "$commonAssetsUrl/img/clear.cache.gif"
                }
              };
            </script>
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
