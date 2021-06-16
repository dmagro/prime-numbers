package proxy

import com.google.inject.Provides
import com.prime.numbers.thrift.PrimeNumbersService
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.TwitterModule
import com.twitter.inject.server.FeatureTest
import com.twitter.util.Future
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar.mock
import proxy.clients.ClientsFactory

class PrimeNumbersFeatureTest extends FeatureTest {

  override protected val server = new EmbeddedHttpServer(
    new ProxyServer {
      override def overrideModules = Seq(MockModule)
    }
  )

  test("200 OK") {
    server.httpGet("/prime/1", andExpect = Status.Ok)
  }

  test("400 BadRequest") {
    server.httpGet("/prime/0", andExpect = Status.BadRequest)
  }

  test("500 InternalServerError") {
    // Value defined for testing purposes and simulate the case where the underlying service failed for some reason
    server.httpGet("/prime/42", andExpect = Status.InternalServerError)
  }
}

object MockModule extends TwitterModule {

  @Provides
  def provideClientsFactory: ClientsFactory = {
    val client = mock[PrimeNumbersService.MethodPerEndpoint]
    // Value defined for testing purposes and simulate the case where the underlying service failed for some reason reas
    when(client.primeNumbersUntil(ArgumentMatchers.eq(42))) thenReturn(Future.???)
    when(client.primeNumbersUntil(ArgumentMatchers.eq(1))) thenReturn(Future.value(Seq()))
    val factory = mock[ClientsFactory]
    when(factory.primeNumbersServiceClient()) thenReturn(client)
    factory
  }
}
