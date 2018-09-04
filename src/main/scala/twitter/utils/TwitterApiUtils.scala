package twitter.utils

import twitter.data.AccessData
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

class TwitterApiUtils {

  var twitter: Twitter = _

  def createTwitterInstance: Unit = {
    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(AccessData.ConsumerKey)
      .setOAuthConsumerSecret(AccessData.ConsumerSecret)
      .setOAuthAccessToken(AccessData.AccessToken)
      .setOAuthAccessTokenSecret(AccessData.AccessSecret)
    val tf = new TwitterFactory(cb.build())
    twitter = tf.getInstance()
  }


}
