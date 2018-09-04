package twitter.utils

import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

object TwitterApiUtils {
  def createTwitterInstance: Twitter = {
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
    return tf.getInstance()
  }

  var twitter: Twitter = createTwitterInstance
}
