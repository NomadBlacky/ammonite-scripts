import $ivy.`org.scalaj::scalaj-http:2.3.0`, scalaj.http._
import $ivy.`com.typesafe.play::play-json:2.6.7`, play.api.libs.json._

val key   = sys.env("TRELLO_API_KEY")
val token = sys.env("TRELLO_API_TOKEN")
val card  = sys.env("TRELLO_CARD_ID")

val res =
  Http("https://api.trello.com/1/cards/yRmXONbq")
    .params(
      ("fields", "all"),
      ("key", "83ad6e2e455269bf7c65131797b373e1"),
      ("token", "23ab69f3231a939ae8b8ab453d23b1a440c53ea64021b17941f5861ea95eca5f")
    )
    .asString

println(Json.prettyPrint(Json.parse(res.body)))
