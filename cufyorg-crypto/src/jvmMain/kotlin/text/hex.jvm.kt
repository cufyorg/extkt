package org.cufy.text

actual fun ByteArray.encodeHex(): String {
    return com.google.crypto.tink.subtle.Hex.encode(this)
}

actual fun String.decodeHex(): ByteArray {
    return com.google.crypto.tink.subtle.Hex.decode(this)
}

actual fun String.encodeHex(): String {
    return encodeToByteArray().encodeHex()
}

actual fun String.decodeHexToString(): String {
    return decodeHex().decodeToString()
}
