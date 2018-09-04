package twitter

import org.scalatest.{BeforeAndAfterAll, FunSuite, Matchers}
import twitter.utils.TwitterApiUtils
import twitter4j.StatusUpdate

class ApiTwitterTests extends FunSuite with Matchers with BeforeAndAfterAll{

  override def beforeAll() {
    TwitterApiUtils.createTwitterInstance
  }

  test("create tweet test"){
    var twitter = TwitterApiUtils.twitter
    val r = scala.util.Random
    // get initial number of tweets
    var statuses = twitter.getHomeTimeline()
    var initialNumberOfTweets = statuses.size()
    var expectedNumberOfTweets = initialNumberOfTweets + 1
    // post new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // get current number of tweets
    var numberOfTweetsAfterUpdate = twitter.getHomeTimeline.size()
    // check that current number of tweet is 1 more that initial
    assert(expectedNumberOfTweets.equals(numberOfTweetsAfterUpdate), s"Expected number is: $expectedNumberOfTweets, but actual number is: $numberOfTweetsAfterUpdate")
  }

  test("update tweet test") {
    var twitter = TwitterApiUtils.twitter
    val r = scala.util.Random
    // creating new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // to update tweet we need to create this object
    var textForTweetToUpdate = "UpdateTweet " + r.nextInt(9999999)
    var statusUpdate = new StatusUpdate(textForTweetToUpdate)
    // and here we updating last added tweet
    var updatedTweet = twitter.updateStatus(statusUpdate)
    var updatedTweetText = updatedTweet.getText
    // now we need to get updated tweet text and check it against our variable
    assert(textForTweetToUpdate.equals(updatedTweetText), s"Expected text is: $textForTweetToUpdate, but actual text is: $updatedTweetText")
  }

  test("delete tweet test"){
    var twitter = TwitterApiUtils.twitter
    val r = scala.util.Random
    // get current number of tweets
    var initialNumberOfTweets = twitter.getHomeTimeline.size()
    // creating new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // delete created tweet
    twitter.destroyStatus(status.getId)
    // number of tweets after we delete one
    var currentNumberOfTweets = twitter.getHomeTimeline.size()
    assert(initialNumberOfTweets.equals(currentNumberOfTweets), s"Expected number of tweets is: $initialNumberOfTweets, actual number of tweets is: $currentNumberOfTweets")
  }

  test("retweet updated tweet test"){
    var twitter = TwitterApiUtils.twitter
    val r = scala.util.Random
    // creating new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // to update tweet we need to create this object
    var textForTweetToUpdate = "UpdateTweet " + r.nextInt(9999999)
    var statusUpdate = new StatusUpdate(textForTweetToUpdate)
    // updating the latest tweet
    var updatedTweet = twitter.updateStatus(statusUpdate)
    // retweeting updated tweet
    twitter.retweetStatus(updatedTweet.getId)
    var numOfRetweets = twitter.getRetweets(updatedTweet.getId)
    assert(numOfRetweets.size().equals(1), "Retweets count isn't correct")
  }
}