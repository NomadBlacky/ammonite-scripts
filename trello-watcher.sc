import $ivy.`org.scalaj::scalaj-http:2.3.0`
import scalaj.http._
import $ivy.`org.scala-lang.modules::scala-swing:2.0.3`

import scala.swing._
import $ivy.`com.lihaoyi::ujson:0.6.5`

import scala.swing.BorderPanel.Position

val key   = sys.env("TRELLO_API_KEY")
val token = sys.env("TRELLO_API_TOKEN")
val card  = sys.env("TRELLO_CARD_ID")

val req =
  Http("https://api.trello.com/1/cards/yRmXONbq")
    .params(
      ("fields", "all"),
      ("key", "83ad6e2e455269bf7c65131797b373e1"),
      ("token", "23ab69f3231a939ae8b8ab453d23b1a440c53ea64021b17941f5861ea95eca5f")
    )

val panel = new BorderPanel {
  add(new Label("foo"), Position.North)
}

val frame = new MainFrame {
  title = "Trello"
  contents = panel
  visible = true
}

@main def main(): Unit = {
  println(ujson.read(req.asString.body).render(2))
}