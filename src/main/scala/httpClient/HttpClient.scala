package uk.co.tomsturgeon.marsgen
package httpClient

import cats.effect.unsafe.IORuntime.global
import cats.effect.{IO, Resource}
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client

object HttpClient {

  def createClient(): Resource[IO, Client[IO]] = {
    BlazeClientBuilder[IO].resource
  }
}
