package proxy

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.ExceptionMappingFilter
import com.twitter.finatra.http.routing.HttpRouter
import proxy.controllers.PrimeNumberController
import proxy.exceptions.mappers.BadInputExceptionMapper
import proxy.modules.ClientsFactoryModule

object ProxyServerMain extends ProxyServer

class ProxyServer extends HttpServer {

  override val modules = Seq(ClientsFactoryModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[ExceptionMappingFilter[Request]]
      .exceptionMapper[BadInputExceptionMapper]
      .add[PrimeNumberController]
  }
}
