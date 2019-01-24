package scommons.play.controllers

import controllers.AssetsFinder
import org.mockito.Mockito._
import org.scalatest.DoNotDiscover
import org.scalatestplus.play.ConfiguredApp
import play.api.mvc.ControllerComponents
import play.api.test.FakeRequest
import play.api.test.Helpers._

@DoNotDiscover
class BasePageControllerSpec extends BaseControllerSpec
  with ConfiguredApp {

  private lazy val controllerComponents = app.injector.instanceOf[ControllerComponents]

  private val finder = mock[AssetsFinder]

  override protected def beforeEach(): Unit = {
    reset(finder)

    when(finder.assetsBasePath).thenReturn("/public")
    when(finder.assetsUrlPrefix).thenReturn("/assets")
  }

  override protected def afterEach(): Unit = {
    verify(finder).assetsBasePath
    verify(finder).assetsUrlPrefix

    verifyNoMoreInteractions(finder)
  }

  it should "fail if cannot find js" in {
    //given
    val prefix = "/app-prefix"
    val project = "app-client3"
    val controller = new BasePageController(controllerComponents, finder, prefix, project) {}

    //when
    val e = the[IllegalArgumentException] thrownBy {
      controller.index.apply(FakeRequest()).futureValue
    }

    //then
    e.getMessage shouldBe s"Could not find resource, project: $project" +
      s", searched for: List($project-opt.js, $project-fastopt.js)"
  }

  it should "not fail if cannot find css" in {
    //given
    val prefix = "/app-prefix"
    val project = "app-client2"
    val controller = new BasePageController(controllerComponents, finder, prefix, project) {}

    //when
    val result = controller.index.apply(FakeRequest()).futureValue

    //then
    result.header.status shouldBe OK
    result.body.consumeData.futureValue.utf8String shouldBe
      s"""<!doctype html>
         |        <html>
         |          <head>
         |            <meta charset="UTF-8" />
         |            <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
         |
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/bootstrap.min.css" />
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/bootstrap-responsive.min.css" />
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/custom.css" />
         |            
         |          </head>
         |          <body>
         |            <div id="root">Loading, please, wait...</div>
         |
         |            <script src="$prefix/assets/$project-opt-library.js"></script>
         |            <script src="$prefix/assets/$project-opt-loader.js"></script>
         |            <script src="$prefix/assets/$project-opt.js"></script>
         |          </body>
         |        </html>
         |        """.stripMargin
  }

  it should "return html page with client js and css" in {
    //given
    val prefix = "/app-prefix"
    val project = "app-client"
    val controller = new BasePageController(controllerComponents, finder, prefix, project) {}

    //when
    val result = controller.index.apply(FakeRequest()).futureValue

    //then
    result.header.status shouldBe OK
    result.body.consumeData.futureValue.utf8String shouldBe
      s"""<!doctype html>
         |        <html>
         |          <head>
         |            <meta charset="UTF-8" />
         |            <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
         |
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/bootstrap.min.css" />
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/bootstrap-responsive.min.css" />
         |            <link rel="stylesheet" href="$prefix/assets/lib/scommons-client-assets/css/custom.css" />
         |            <link rel="stylesheet" href="$prefix/assets/styles/$project-opt.css" />
         |          </head>
         |          <body>
         |            <div id="root">Loading, please, wait...</div>
         |
         |            <script src="$prefix/assets/$project-opt-library.js"></script>
         |            <script src="$prefix/assets/$project-opt-loader.js"></script>
         |            <script src="$prefix/assets/$project-opt.js"></script>
         |          </body>
         |        </html>
         |        """.stripMargin
  }
}
