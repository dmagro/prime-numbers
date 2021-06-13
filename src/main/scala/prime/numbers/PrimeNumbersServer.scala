package prime.numbers

import com.twitter.finagle.Thrift
import com.twitter.util.Await

object PrimeNumbersServer extends App {
  val server = Thrift.server.serveIface(":8080", new PrimeNumbersServiceImpl)
  Await.ready(server)
}
