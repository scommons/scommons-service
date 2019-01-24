package scommons.service.util

import org.scalatest._
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.time.{Millis, Seconds, Span}

trait BaseUtilSpec extends FlatSpec
  with Matchers
  with MockitoSugar
  with BeforeAndAfterAll
  with BeforeAndAfterEach
  with Eventually
  with ScalaFutures
  with Inside {

  implicit val defaultPatience: PatienceConfig = PatienceConfig(
    timeout = Span(5, Seconds),
    interval = Span(50, Millis)
  )

}
