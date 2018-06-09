import $ivy.`com.danielasfregola::twitter4s:5.3`
import ammonite.ops._
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet
import scalaj.http.Http

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

val restClient = TwitterRestClient()

val mediaExtensionRegex = """^.+\.(\w+)$""".r

@main def favorites(screenName: String, count: Int, outDir: Path) = {
  require(outDir.isDir, s"$outDir is not a directory.")
  val ratedResult = Await.result(restClient.favoriteStatusesForUser(screenName), Duration.Inf)
  for {
    tweet          <- ratedResult.data
    entity         <- {println(describeTweet(tweet)); tweet.extended_entities.toSeq}
    (media, index) <- entity.media.zipWithIndex
    url            =  {println(media.media_url_https); media.media_url_https}
    extension      =  mediaExtensionRegex.findFirstMatchIn(url).map(_.group(1)).getOrElse("")
    body           =  Http(url).asBytes.body
    fileName       =  s"${tweet.id}-$index.$extension"
  } write.over(outDir/fileName, body)
}

private def describeTweet(tweet: Tweet): String = {
  val id = tweet.id
  val screenName = tweet.user.map(_.screen_name).getOrElse("(none)")
  val text = tweet.text.replaceAll("\n", " ")
  s"[$id] @$screenName: $text"
}