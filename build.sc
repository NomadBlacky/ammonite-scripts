import mill._, scalalib._

object bin extends ScalaModule {
  def scalaVersion = "2.12.8"
  def ivyDeps = Agg(
    ivy"com.lihaoyi:::ammonite:1.6.2",
    ivy"com.typesafe.play::play-json:2.6.7",
    ivy"com.lihaoyi::ujson:0.6.5",
    ivy"org.scala-lang.modules::scala-swing:2.0.3",
    ivy"com.danielasfregola::twitter4s:5.3"
  )
}
