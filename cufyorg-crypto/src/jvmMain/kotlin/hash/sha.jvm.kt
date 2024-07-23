package org.cufy.hash

import org.cufy.text.encodeHex
import java.security.MessageDigest

actual fun ByteArray.sha256(): ByteArray {
    val digest = MessageDigest.getInstance("SHA-256")
    return digest.digest(this)
}

actual fun ByteArray.sha384(): ByteArray {
    val digest = MessageDigest.getInstance("SHA-384")
    return digest.digest(this)
}

actual fun ByteArray.sha512(): ByteArray {
    val digest = MessageDigest.getInstance("SHA-512")
    return digest.digest(this)
}

actual fun String.sha256(): ByteArray {
    return encodeToByteArray().sha256()
}

actual fun String.sha384(): ByteArray {
    return encodeToByteArray().sha384()
}

actual fun String.sha512(): ByteArray {
    return encodeToByteArray().sha512()
}

actual fun ByteArray.sha256ToString(): String {
    return sha256().encodeHex()
}

actual fun ByteArray.sha384ToString(): String {
    return sha384().encodeHex()
}

actual fun ByteArray.sha512ToString(): String {
    return sha512().encodeHex()
}

actual fun String.sha256ToString(): String {
    return sha256().encodeHex()
}

actual fun String.sha384ToString(): String {
    return sha384().encodeHex()
}

actual fun String.sha512ToString(): String {
    return sha512().encodeHex()
}
