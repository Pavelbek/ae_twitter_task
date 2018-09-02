package twitter

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
    // creating new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // to update tweet we need to create this object
    var textForTweetToUpdate = "UpdateTweet " + r.nextInt(9999999)
    var statusUpdate = new StatusUpdate(textForTweetToUpdate)
    // and here we updating last added tweet
    var updatedTweet = twitter.updateStatus(statusUpdate)
    var updatedTweetText = updatedTweet.getText
    // now we need to get updated tweet text and check it against our variable
    Assert.assertEquals(textForTweetToUpdate, updatedTweetText, s"Expected text is: $textForTweetToUpdate, but actual text is: $updatedTweetText")
  }

  @Test
  def deleteTweetTest() = {
    val r = scala.util.Random
    // get current number of tweets
    var initialNumberOfTweets = twitter.getHomeTimeline
    // creating new tweet
    val status = twitter.updateStatus("TestTweet " + r.nextInt(9999999))
    // delete created tweet
    twitter.destroyStatus(status.getId)
    // number of tweets after we delete one
    var currentNumberOfTweets = twitter.getHomeTimeline
    Assert.assertEquals(initialNumberOfTweets, currentNumberOfTweets, s"Expected numdeb of tweets is: $initialNumberOfTweets, actual number of tweets is: $currentNumberOfTweets")
  }

  @Test
  def retweetUpdatedTweetTest() = {
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
    Assert.assertTrue(numOfRetweets.size() == 1)
  }

}

object Access{
  val AccessToken = "token"
  val AccessSecret = "access secret"
  val ConsumerKey = "key"
  val ConsumerSecret = "consumer secret"
}
