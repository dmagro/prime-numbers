package proxy.modules

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import proxy.clients.ClientsFactory

object ClientsFactoryModule extends TwitterModule {

  @Provides
  def provideClientsFactory: ClientsFactory = {
    new ClientsFactory()
  }
}
