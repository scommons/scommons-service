package scommons.play.controllers

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.time.{Millis, Seconds, Span}

abstract class BaseControllerSpec extends FlatSpec
  with Matchers
  with BeforeAndAfterEach
  with BeforeAndAfterAll
  with MockitoSugar
  with ScalaFutures {

  implicit val defaultPatience: PatienceConfig = PatienceConfig(
    timeout = Span(5, Seconds),
    interval = Span(100, Millis)
  )

  protected implicit val system: ActorSystem = ActorSystem(getClass.getSimpleName)
  protected implicit val mat: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(system))

  override protected def afterAll(): Unit = {
    system.terminate().futureValue
  }
}
