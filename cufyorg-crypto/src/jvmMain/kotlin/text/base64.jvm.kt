package org.cufy.text

import com.google.crypto.tink.subtle.Base64

actual fun ByteArray.encodeBase64(): String {
    return Base64.encode(this)
}

actual fun ByteArray.encodeBase64(offset: Int, length: Int): String {
    val flags = Base64.DEFAULT or Base64.NO_WRAP
    return Base64.encodeToString(this, offset, length, flags)
}

actual fun ByteArray.encodeBase64UrlSafe(): String {
    return Base64.urlSafeEncode(this)
}

actual fun ByteArray.encodeBase64UrlSafe(offset: Int, length: Int): String {
    val flags = Base64.DEFAULT or Base64.NO_PADDING or Base64.NO_WRAP or Base64.URL_SAFE
    return Base64.encodeToString(this, offset, length, flags)
}

actual fun String.encodeBase64(): String {
    return encodeToByteArray().encodeBase64()
}

actual fun String.encodeBase64(offset: Int, length: Int): String {
    return encodeToByteArray(offset, length).encodeBase64()
}

actual fun String.encodeBase64UrlSafe(): String {
    return encodeToByteArray().encodeBase64UrlSafe()
}

actual fun String.encodeBase64UrlSafe(offset: Int, length: Int): String {
    return encodeToByteArray(offset, length).encodeBase64UrlSafe()
}

actual fun String.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

actual fun String.decodeBase64UrlSafe(): ByteArray {
    return Base64.urlSafeDecode(this)
}

actual fun String.decodeBase64ToString(): String {
    return decodeBase64().decodeToString()
}

actual fun String.decodeBase64UrlSafeToString(): String {
    return decodeBase64UrlSafe().decodeToString()
}
