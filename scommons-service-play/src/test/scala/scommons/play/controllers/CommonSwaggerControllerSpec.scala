package scommons.play.controllers

import java.net.URLEncoder

import org.scalatest.DoNotDiscover
import org.scalatestplus.play.ConfiguredApp
import play.api.mvc.ControllerComponents
import play.api.test.FakeRequest
import play.api.test.Helpers._

@DoNotDiscover
class CommonSwaggerControllerSpec extends BaseControllerSpec
  with ConfiguredApp {

  private lazy val controller = new CommonSwaggerController(app.injector.instanceOf[ControllerComponents])

  it should "redirect to swagger ui html page in assets" in {
    //given
    val prefix = "/app-prefix"

    //when
    val result = controller.swaggerUi(prefix).apply(FakeRequest()).futureValue

    //then
    result.header.status shouldBe SEE_OTHER
    result.header.headers(LOCATION) shouldBe {
      s"$prefix/assets/lib/swagger-ui/index.html?url=${URLEncoder.encode(s"$prefix/api-docs", "utf-8")}"
    }
  }
}
