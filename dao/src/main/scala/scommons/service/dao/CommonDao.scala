package scommons.service.dao

import scala.concurrent.{ExecutionContext, Future}

trait CommonDao {

  def getOne[T](queryName: String, futureResults: Future[Seq[T]])(implicit ec: ExecutionContext): Future[Option[T]] = {
    futureResults.map { results =>
      val size = results.size
      if (size > 1) {
        val context = s"${getClass.getSimpleName}.$queryName"
        throw new IllegalStateException(s"Expected only one result, but actual size is $size in $context")
      }

      results.headOption
    }
  }

  def isUpdated[T](futureResult: Future[Long])(implicit ec: ExecutionContext): Future[Boolean] = {
    futureResult.map(_ > 0)
  }
}
