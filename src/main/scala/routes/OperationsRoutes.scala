package uk.co.tomsturgeon.marsgen
package routes

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

object OperationsRoutes {
  def apply(): HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "ping" => Ok()
  }
}
