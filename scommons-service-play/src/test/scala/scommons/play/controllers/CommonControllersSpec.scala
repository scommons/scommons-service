package scommons.play.controllers

import org.scalatest.{Suites, TestSuite}
import org.scalatestplus.play.components.OneAppPerSuiteWithComponents
import play.api._
import play.api.inject.SimpleInjector
import play.api.routing.SimpleRouter

class CommonControllersSpec extends Suites(
  new BasePageControllerSpec,
  new CommonSwaggerControllerSpec
) with TestSuite
  with OneAppPerSuiteWithComponents {

  override def components: BuiltInComponentsFromContext = new BuiltInComponentsFromContext(context)
    with NoHttpFiltersComponents {

    import play.api.mvc.Results
    import play.api.routing.Router
    import play.api.routing.sird._

    lazy val router: Router = SimpleRouter({
      case GET(p"/") => defaultActionBuilder {
        Results.Ok("success!")
      }
    })

    override lazy val application: Application = {
      new DefaultApplication(environment, applicationLifecycle,
        new SimpleInjector(injector) + controllerComponents,
        configuration, requestFactory, httpRequestHandler, httpErrorHandler, actorSystem, materializer)
    }
  }
}
