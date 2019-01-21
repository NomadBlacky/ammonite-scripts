import mill._
import mill.scalalib._

trait CommonModule extends ScalaModule {
  def scalaVersion = "2.12.8"
  override def ivyDeps = Agg(
    ivy"com.lihaoyi:::ammonite:1.6.2"
  )
}

object bin extends CommonModule {
  override def ivyDeps = super.ivyDeps() ++ Agg(
    ivy"com.typesafe.play::play-json:2.6.7",
    ivy"com.lihaoyi::ujson:0.6.5",
    ivy"org.scala-lang.modules::scala-swing:2.0.3",
    ivy"com.danielasfregola::twitter4s:5.3"
  )
}

object worker extends CommonModule {
  override def ivyDeps = super.ivyDeps() ++ Agg(

  )
}
