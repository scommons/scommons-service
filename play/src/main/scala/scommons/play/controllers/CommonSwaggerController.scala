package scommons.play.controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class CommonSwaggerController @Inject() (components: ControllerComponents)
  extends AbstractController(components) {

  def swaggerUi(prefix: String): Action[AnyContent] = Action {
    Redirect(
      url = s"$prefix/assets/lib/swagger-ui/index.html",
      queryStringParams = Map("url" -> Seq(s"$prefix/api-docs"))
    )
  }
}
