import $ivy.`com.danielasfregola::twitter4s:5.3`
import ammonite.ops._
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.Tweet
import scalaj.http.Http

import scala.concurrent.Await
import scala.concurrent.duration.Duration

val restClient = TwitterRestClient()

val mediaExtensionRegex = """^.+\.(\w+)$""".r

@main def favorites(screenName: String, count: Int, outDir: Path) = {
  require(outDir.isDir, s"$outDir is not a directory.")
  val ratedResult = Await.result(restClient.favoriteStatusesForUser(screenName, count), Duration.Inf)
  for {
    tweet          <- ratedResult.data
    entity         <- {println(describeTweet(tweet)); tweet.extended_entities.toSeq}
    (media, index) <- entity.media.zipWithIndex
    url            =  {println(media.media_url_https); media.media_url_https}
    extension      =  mediaExtensionRegex.findFirstMatchIn(url).map(_.group(1)).getOrElse("")
    outputFileName =  s"${tweet.id}-$index.$extension"
    outputPath     =  outDir/outputFileName if !exists(outputPath)
    body           =  Http(url).asBytes.body
  } write.over(outputPath, body)
}

private def describeTweet(tweet: Tweet): String = {
  val id = tweet.id
  val screenName = tweet.user.map(_.screen_name).getOrElse("(none)")
  val text = tweet.text.replaceAll("\n", " ")
  s"[$id] @$screenName: $text"
}