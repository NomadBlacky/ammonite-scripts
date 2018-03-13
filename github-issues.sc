import $ivy.`org.scalaj::scalaj-http:2.3.0`
import $ivy.`com.typesafe.play::play-json:2.6.7`

import scalaj.http._
import play.api.libs.json._

val repoRegex = """(\w+)/(\w+)""".r
def issueUrl(repo: String) = s"https://api.github.com/repos/$repo/issues"

@main def main(repo: String) {
  if (repoRegex.findFirstIn(repo).isEmpty) {
    println(s"Invalid repository: $repo")
    System.exit(1)
  }

  val response = Http(issueUrl(repo)).asString
  val json     = Json.parse(response.body)

  println(Json.stringify(json))
}
