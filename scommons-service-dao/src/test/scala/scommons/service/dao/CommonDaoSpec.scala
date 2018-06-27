package scommons.service.dao

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Failure

class CommonDaoSpec extends BaseDaoSpec {

  private val commonDao = new SomeTestDao

  private val queryName = "getSomething"

  "getOne" should "fail if there are more than 1 elements" in {
    //given
    val futureResults = Future.successful(List(1, 2))

    //when
    val result = commonDao.getOne(queryName, futureResults)

    //then
    val context = s"SomeTestDao.$queryName"
    val Some(Failure(exception)) = Await.ready(result, 5.seconds).value
    exception.getMessage shouldBe s"Expected only one result, but actual size is 2 in $context"
  }

  it should "not fail if there are 0 elements" in {
    //given
    val futureResults = Future.successful(List[Int]())

    //when & then
    commonDao.getOne(queryName, futureResults).futureValue shouldBe None
  }

  it should "return single element" in {
    //given
    val futureResults = Future.successful(List(1))

    //when & then
    commonDao.getOne(queryName, futureResults).futureValue shouldBe Some(1)
  }

  "isUpdated" should "return true if updateCount > 0" in {
    //when & then
    commonDao.isUpdated(Future.successful(-1)).futureValue shouldBe false
    commonDao.isUpdated(Future.successful(0)).futureValue shouldBe false
    commonDao.isUpdated(Future.successful(1)).futureValue shouldBe true
    commonDao.isUpdated(Future.successful(2)).futureValue shouldBe true
  }
}

class SomeTestDao extends CommonDao {

  def getSomething[T](results: Future[List[T]]): Future[Option[T]] = {
    getOne("getSomething", results)
  }
}
