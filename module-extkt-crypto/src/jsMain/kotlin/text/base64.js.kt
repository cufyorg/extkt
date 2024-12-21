package org.cufy.text

import kotlin.coroutines.cancellation.CancellationException
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

private external fun atob(input: String): String
private external fun btoa(input: String): String

private fun atobUrlSafe(input: String): String {
    val base64 = base64UrlSafeToBase64(input)
    return atob(base64)
}

private fun btoaUrlSafe(input: String): String {
    val base64 = btoa(input)
    return base64ToBase64UrlSafe(base64)
}

private fun base64UrlSafeToBase64(base64UrlSafe: String): String {
    return base64UrlSafe.replace("-", "+").replace("_", "/")
}

private fun base64ToBase64UrlSafe(base64: String): String {
    return base64.replace("+", "-").replace("/", "_")
}

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.encodeBase64(): String {
    return Base64.encode(this)
}

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.encodeBase64(offset: Int, length: Int): String {
    return Base64.encode(this, offset, length)
}

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.encodeBase64UrlSafe(): String {
    return Base64.UrlSafe.encode(this)
}

@OptIn(ExperimentalEncodingApi::class)
actual fun ByteArray.encodeBase64UrlSafe(offset: Int, length: Int): String {
    return Base64.UrlSafe.encode(this, offset, length)
}

actual fun String.encodeBase64(): String {
    return btoa(this)
}

actual fun String.encodeBase64(offset: Int, length: Int): String {
    return encodeToByteArray(offset, length).encodeBase64()
}

actual fun String.encodeBase64UrlSafe(): String {
    return btoaUrlSafe(this)
}

actual fun String.encodeBase64UrlSafe(offset: Int, length: Int): String {
    return encodeToByteArray(offset, length).encodeBase64UrlSafe()
}

@OptIn(ExperimentalEncodingApi::class)
actual fun String.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

@OptIn(ExperimentalEncodingApi::class)
actual fun String.decodeBase64OrNull(): ByteArray? {
    return try {
        Base64.decode(this)
    } catch (_: IllegalArgumentException) {
        null
    }
}

@OptIn(ExperimentalEncodingApi::class)
actual fun String.decodeBase64UrlSafe(): ByteArray {
    val base64 = base64UrlSafeToBase64(this)
    return Base64.decode(base64)
}

@OptIn(ExperimentalEncodingApi::class)
actual fun String.decodeBase64UrlSafeOrNull(): ByteArray? {
    return try {
        val base64 = base64UrlSafeToBase64(this)
        Base64.decode(base64)
    } catch (_: IllegalArgumentException) {
        null
    }
}

actual fun String.decodeBase64ToString(): String {
    return atob(this)
}

actual fun String.decodeBase64ToStringOrNull(): String? {
    return try {
        atob(this)
    } catch (e: CancellationException) {
        throw e
    } catch (_: Throwable) {
        null
    }
}

actual fun String.decodeBase64UrlSafeToString(): String {
    return atobUrlSafe(this)
}

actual fun String.decodeBase64UrlSafeToStringOrNull(): String? {
    return try {
        atobUrlSafe(this)
    } catch (e: CancellationException) {
        throw e
    } catch (_: Throwable) {
        null
    }
}
