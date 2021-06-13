package prime.numbers

import com.prime.numbers.thrift._
import com.twitter.inject.Logging
import com.twitter.util.Future

class PrimeNumbersServiceImpl extends PrimeNumbersService.MethodPerEndpoint  with Logging{
  override def primeNumbersUntil(number: Long): Future[collection.Seq[Long]] = {
    info(s"Request for: $number")
    Future.value(Seq(number))
  }
}
