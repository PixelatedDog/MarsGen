package uk.co.tomsturgeon.marsgen
package config

import com.typesafe.config.{Config, ConfigFactory}

case object EnvConfig {
  val env: String =
    if (System.getenv("SCALA_ENV") == null) "dev" else System.getenv("SCALA_ENV")
  val conf: Config = ConfigFactory.load()
  def apply(): Config = conf.getConfig(env)
}
