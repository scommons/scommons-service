package scommons.service.util

import java.util.Formatter

object StringUtils {

  def byteArray2Hex(hash: Array[Byte]): String = byteArray2Hex(hash, upperCase = false)

  def byteArray2Hex(hash: Array[Byte], upperCase: Boolean): String = {
    val formatter = new Formatter
    val format = if (upperCase) "%02X" else "%02x"
    for (b <- hash) {
      formatter.format(format, Byte.box(b))
    }
    
    formatter.toString
  }
}
