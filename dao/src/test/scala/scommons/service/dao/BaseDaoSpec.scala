package scommons.service.dao

import org.scalatest._
import org.scalatest.concurrent.{Eventually, ScalaFutures}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.time.{Millis, Seconds, Span}

trait BaseDaoSpec extends FlatSpec
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
