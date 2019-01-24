package scommons.service.util

class HashUtilsSpec extends BaseUtilSpec {

  it should "calculate sha-1 hash" in {
    //when & then
    HashUtils.sha1("") shouldBe "da39a3ee5e6b4b0d3255bfef95601890afd80709"
    HashUtils.sha1("test") shouldBe "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"
    HashUtils.sha1("12345") shouldBe "8cb2237d0679ca88db6464eac60da96345513964"
  }
}
