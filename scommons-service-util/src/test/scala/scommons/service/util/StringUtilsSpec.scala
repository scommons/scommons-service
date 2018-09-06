package scommons.service.util

import scommons.service.util.StringUtils._

class StringUtilsSpec extends BaseUtilSpec {

  it should "convert bytes to hex string" in {
    //when & then
    byteArray2Hex(Array()) shouldBe ""
    byteArray2Hex(Array(1, 2, 0xa.toByte, 0xf.toByte)) shouldBe "01020a0f"
    
    byteArray2Hex(Array(), upperCase = true) shouldBe ""
    byteArray2Hex(Array(1, 2, 0xa.toByte, 0xf.toByte), upperCase = true) shouldBe "01020A0F"
  }
}
