package scommons.service.util

import java.security.MessageDigest

object HashUtils {

  def sha1(value: String): String = {
    val sha1Digest = MessageDigest.getInstance("SHA-1")
    StringUtils.byteArray2Hex(sha1Digest.digest(value.getBytes("UTF-8")))
  }
}
