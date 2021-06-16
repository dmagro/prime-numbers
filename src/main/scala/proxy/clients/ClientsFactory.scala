package proxy.clients

import com.prime.numbers.thrift.PrimeNumbersService
import com.twitter.finagle.Thrift

import scala.util.Properties

class ClientsFactory {

  def primeNumbersServiceClient(): PrimeNumbersService.MethodPerEndpoint =
    Thrift.client.build[PrimeNumbersService.MethodPerEndpoint](s"${Properties.envOrElse("PRIME_NUMBERS_HOST", "localhost")}:8080")

}
