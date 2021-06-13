package proxy.controllers

import com.twitter.finatra.http.annotations.RouteParam
import com.twitter.finatra.validation.constraints.Min


case class PrimeNumberRequest(@Min(1) @RouteParam number: Int)