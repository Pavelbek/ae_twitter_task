package twitter.utils

import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

class TwitterApiUtils {

  var twitter: Twitter = _

  def createTwitterInstance: Unit = {
    val cb = new ConfigurationBuilder()
    object Access{
      val AccessToken = "token"
      val AccessSecret = "access secret"
      val ConsumerKey = "key"
      val ConsumerSecret = "consumer secret"
    }
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(Access.ConsumerKey)
      .setOAuthConsumerSecret(Access.ConsumerSecret)
      .setOAuthAccessToken(Access.AccessToken)
      .setOAuthAccessTokenSecret(Access.AccessSecret)
    val tf = new TwitterFactory(cb.build())
    twitter = tf.getInstance()
  }


}
