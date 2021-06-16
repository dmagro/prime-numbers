package prime.numbers

import com.twitter.util.Await
import org.scalatest.funsuite.AnyFunSuite

class PrimeNumbersServiceImplTest extends AnyFunSuite {

  private val service = new PrimeNumbersServiceImpl

  test("when the service is called with -1 it should return an empty sequence") {
    val result = Await.result(service.primeNumbersUntil(-1))
    assert(result.isEmpty)
  }

  test("when the service is called with 0 it should return an empty sequence") {
    val result = Await.result(service.primeNumbersUntil(0))
    assert(result.isEmpty)
  }

  test("when the service is called with 1 it should return an empty sequence") {
    val result = Await.result(service.primeNumbersUntil(1))
    assert(result.isEmpty)
  }

  test("when the service is called with 50 it should return an sequence containing all the prime numbers until 50") {
    val result = Await.result(service.primeNumbersUntil(50))
    assert(result.containsSlice(Seq(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47)))
  }
}
