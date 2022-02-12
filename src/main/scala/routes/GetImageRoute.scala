package uk.co.tomsturgeon.marsgen
package routes

import config.EnvConfig
import httpClient.HttpClient
import routes.SolResponseADT.{SolResponseFailure, SolResponseSuccess}

import cats.effect.IO
import org.http4s.dsl.io._
import org.http4s.{HttpRoutes, Method, Request, Uri}

object GetImageRoute {

  def apply(): HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "sol" / sol => {
      sendRequest(sol).flatMap {
        case SolResponseSuccess => Ok("yay")
        case SolResponseFailure => BadRequest("Something went wrong sending the request to NASA")
      }
    }
  }

  def sendRequest(solDate: String): IO[SolResponseADT] = {
    HttpClient
      .createClient()
      .use(httpClient => {
        for {
          uri <- SolQueryBuilder.withSolDate(solDate)
          req <- IO(Request[IO](Method.GET, uri))
          res <- httpClient
            .run(req)
            .use(response =>
              response.status match {
                case Ok => IO.pure[SolResponseADT](SolResponseSuccess)
                case _  => IO.pure[SolResponseADT](SolResponseFailure)
              }
            )
        } yield res
      })
  }
}

sealed trait SolResponseADT
object SolResponseADT {
  case object SolResponseSuccess extends SolResponseADT
  case object SolResponseFailure extends SolResponseADT
}

object SolQueryBuilder {
  val ROOT: String = EnvConfig().getString("NASA_ROOT_URL")
  val API_KEY: String = EnvConfig().getString("NASA_API_KEY")

  def withSolDate(sol: String): IO[Uri] = {
    Uri.fromString(ROOT) match {
      case Right(value) => IO(value.withQueryParam("sol", sol).withQueryParam("api_key", API_KEY))
      case Left(err) => IO.raiseError(err)
    }
  }
}
