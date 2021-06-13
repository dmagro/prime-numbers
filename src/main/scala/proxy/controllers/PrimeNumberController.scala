package proxy.controllers

import com.twitter.finatra.http.Controller
import com.twitter.inject.Logging
import com.prime.numbers.thrift._
import proxy.clients.ClientsFactory
import proxy.exceptions.ServiceFailureException

class PrimeNumberController extends Controller with Logging {

  get("/prime/:number") { request: PrimeNumberRequest =>
    info(s"Get prime numbers until ${request.number}")

    val client: PrimeNumbersService.MethodPerEndpoint = ClientsFactory.primeNumbersServiceClient()

    client.primeNumbersUntil(request.number)  onSuccess { serviceResponse =>
      response.ok.json(serviceResponse)
    } onFailure { _ =>
      throw ServiceFailureException(s"Failed to retrieve prime numbers until ${request.number}")
    }
  }
}
