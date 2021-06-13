package proxy.exceptions

case class ServiceFailureException(msg: String) extends Exception(msg)
