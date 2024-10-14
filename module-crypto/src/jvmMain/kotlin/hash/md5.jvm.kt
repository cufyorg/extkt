package org.cufy.hash

import org.cufy.text.encodeHex
import java.security.MessageDigest

actual fun ByteArray.md5(): ByteArray {
    val digest = MessageDigest.getInstance("MD5")
    return digest.digest(this)
}

actual fun String.md5(): ByteArray {
    return encodeToByteArray().md5()
}

actual fun ByteArray.md5ToString(): String {
    return md5().encodeHex()
}

actual fun String.md5ToString(): String {
    return md5().encodeHex()
}
