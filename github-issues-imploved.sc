import $ivy.`org.scalaj::scalaj-http:2.3.0`
import $ivy.`com.typesafe.play::play-json:2.6.7`

import ammonite.ops._
import scalaj.http._
import play.api.libs.json._

def issueUrl(owner: String, repo: String) = s"https://api.github.com/repos/$owner/$repo/issues"
def gitUserName = %%('git, 'config, "--get", "user.name")(pwd).out.lines.head

@main def main(owner: String, repo: String) = {
  val response = Http(issueUrl(owner, repo)).asString
  val json     = Json.parse(response.body)

  println(Json.stringify(json))
}

@main def main2(repo: String, owner: String = gitUserName) = {
  val response = Http(issueUrl(owner, repo)).asString
  val json     = Json.parse(response.body)

  println(Json.stringify(json))
}

@main def getFullName(repo: String) = {
  val regex = """(\w+)/(\w+)""".r
  repo match {
    case regex(owner, repo) =>
      main(owner, repo)
    case _ =>
      println(s"Invalid Repository: $repo")
      exit(1)
  }
}
