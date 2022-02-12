package uk.co.tomsturgeon.marsgen
package routes

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.http4s.implicits.http4sLiteralsSyntax
import org.http4s.{Method, Request}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class GetImageRouteTests extends AnyFlatSpec with should.Matchers {

  "Get image route" should "return 200 when a correct request is sent" in {
    val route = GetImageRoute.apply().run(Request[IO](Method.GET, uri"sol/200"))

  }
}
