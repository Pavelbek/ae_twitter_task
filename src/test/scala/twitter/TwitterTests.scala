package twitter

import models.UpdateTweetRequest
import org.testng.annotations.{BeforeMethod, Test}
import org.testng.Assert
import twitter4j.{StatusUpdate, Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder


class TwitterTests(var cb: ConfigurationBuilder, var tf: TwitterFactory, var twitter: Twitter){

  @BeforeMethod
  def init() = {
    cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(Access.ConsumerKey)
      .setOAuthConsumerSecret(Access.ConsumerSecret)
      .setOAuthAccessToken(Access.AccessToken)
      .setOAuthAccessTokenSecret(Access.AccessSecret)
      tf = new TwitterFactory(cb.build())
    twitter = tf.getInstance()
  }

  @Test
  def createTweetTest() = {
    val r = scala.util.Random
    // get initial number of tweets
    var statuses = twitter.getHomeTimeline
    var initialNumberOfTweets = statuses.size()
    var expectedNumberOfTweets = initialNumberOfTweets + 1
    // post new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // get current number of tweets
    var numberOfTweetsAfterUpdate = twitter.getHomeTimeline
    // check that current number of tweet is 1 more that initial
    Assert.assertEquals(expectedNumberOfTweets, numberOfTweetsAfterUpdate, s"Expected number is: $expectedNumberOfTweets, but actual number is: $numberOfTweetsAfterUpdate")
  }

  @Test
  def updateTweetTest() = {
    val r = scala.util.Random
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    var statusUpdate = new StatusUpdate("UpdateTweet " + r.nextInt(9999999))
    twitter.updateStatus(statusUpdate)
  }

  @Test
  def deleteTweetTest() = {

  }

  @Test
  def retweetUpdatedTweet() = {

  }

}

object Access{
  val AccessToken = "token"
  val AccessSecret = "access secret"
  val ConsumerKey = "key"
  val ConsumerSecret = "consumer secret"
}
