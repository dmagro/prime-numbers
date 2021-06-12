package proxy.exceptions.mappers

import com.google.inject.Inject
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.finatra.jackson.caseclass.exceptions.CaseClassFieldMappingException


class BadInputExceptionMapper @Inject()(response: ResponseBuilder) extends ExceptionMapper[CaseClassFieldMappingException] {

   def toResponse(request: Request, exception: CaseClassFieldMappingException): Response = {
    response.badRequest(exception.getMessage)
  }
}