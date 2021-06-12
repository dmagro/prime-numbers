package proxy.controllers

import com.twitter.finatra.http.Controller
import com.twitter.inject.Logging

class PrimeNumberController  extends Controller with Logging {

  get("/prime/:number") { request: PrimeNumberRequest =>
    info(s"Get prime numbers until ${request.number}")
    response.ok.json(
      s"""
        |[${request.number}]
        |""".stripMargin
    )
  }
}
