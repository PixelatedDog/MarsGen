package uk.co.tomsturgeon.marsgen
package httpClient

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import org.http4s.Status.Ok
import org.http4s.implicits.http4sLiteralsSyntax
import org.http4s.{Method, Request, Uri}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class HttpClientTests extends AnyFlatSpec with should.Matchers {

  val API_KEY = "DEMO_KEY"
  val ROOT =
    uri"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos"

  "a Http Client" should "make a basic request out to the nasa example endpoint and receive a 200" in {
    val res = HttpClient.createClient().use { client =>
      val uriString =
        ROOT
          .withQueryParam("sol", 1200)
          .withQueryParam("api_key", API_KEY)

      val req = Request[IO](
        method = Method.GET,
        uriString
      )
      client.run(req).use(IO(_))
    }
    res.unsafeRunSync().status shouldBe Ok
  }
}
