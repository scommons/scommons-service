package scommons.service.util

import org.scalatest._
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatestplus.mockito.MockitoSugar

trait BaseUtilSpec extends AnyFlatSpec
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
