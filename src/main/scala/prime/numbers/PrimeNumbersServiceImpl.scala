package prime.numbers

import com.prime.numbers.thrift._
import com.twitter.inject.Logging
import com.twitter.util.Future

class PrimeNumbersServiceImpl extends PrimeNumbersService.MethodPerEndpoint with Logging {
  override def primeNumbersUntil(number: Int): Future[collection.Seq[Int]] = {
    info(s"Request for: $number")
      Future.value(calculatePrimeNumbers(number))
  }

  /**
   * Sieve of Eratosthenes https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
   */
  private def calculatePrimeNumbers(number: Int) : Seq[Int] = {
    var primes: Array[Boolean] = Array.fill(number + 1)(true)
    var current : Int = 2

    while (current * current <= number) {
      if (primes(current))
        primes = markNonPrimesAsFalse(primes, current, number)

      current += 1
    }

    (2 to number).filter(i => primes(i))
  }

  private def markNonPrimesAsFalse(markers: Array[Boolean], n: Int, limit: Int) : Array[Boolean] = {
    val markersCopy = markers.clone()
    (n * n to limit by n).toList.foreach(i => markersCopy(i) = false)
    markersCopy
  }
}
