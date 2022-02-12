package uk.co.tomsturgeon.marsgen

import config.EnvConfig

import cats.data.Kleisli
import cats.effect.unsafe.implicits.global
import cats.effect.{Async, ExitCode, IO, IOApp, Resource}
import cats.implicits.toSemigroupKOps
import com.typesafe.scalalogging.Logger
import org.http4s.{HttpApp, Request, Response}
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.{Router, Server}
import uk.co.tomsturgeon.marsgen.routes.{GetImageRoute, OperationsRoutes}

object MarsGenApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val logger = Logger("main")
    logger.info("Starting service")
    MarsGen.resource[IO].use(_ => IO.never).as(ExitCode.Success)
  }
}

object MarsGen {
  val router: Kleisli[IO, Request[IO], Response[IO]] = (GetImageRoute() <+> OperationsRoutes()).orNotFound

  def resource[F[_]: Async]: Resource[IO, Server] = {
    BlazeServerBuilder[IO]
      .bindHttp(
        EnvConfig().getInt("HTTP_PORT"),
        EnvConfig().getString("HTTP_HOST")
      )
      .withHttpApp(router)
      .resource
  }
}
