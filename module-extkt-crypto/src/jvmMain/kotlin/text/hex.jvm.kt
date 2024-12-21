package org.cufy.text

actual fun ByteArray.encodeHex(): String {
    return com.google.crypto.tink.subtle.Hex.encode(this)
}

actual fun String.decodeHex(): ByteArray {
    return com.google.crypto.tink.subtle.Hex.decode(this)
}

actual fun String.decodeHexOrNull(): ByteArray? {
    return try {
        com.google.crypto.tink.subtle.Hex.decode(this)
    } catch (_: IllegalArgumentException) {
        null
    }
}

actual fun String.encodeHex(): String {
    return encodeToByteArray().encodeHex()
}

actual fun String.decodeHexToString(): String {
    return decodeHex().decodeToString()
}

actual fun String.decodeHexToStringOrNull(): String? {
    return decodeHexOrNull()?.decodeToString()
}
