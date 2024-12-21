package org.cufy.text

// byte[] => base64

expect fun ByteArray.encodeBase64(): String

expect fun ByteArray.encodeBase64(offset: Int, length: Int = size): String

// byte[] => base64url

expect fun ByteArray.encodeBase64UrlSafe(): String

expect fun ByteArray.encodeBase64UrlSafe(offset: Int, length: Int = size): String

// string => base64

expect fun String.encodeBase64(): String

expect fun String.encodeBase64(offset: Int, length: Int = this.length): String

// string => base64url

expect fun String.encodeBase64UrlSafe(): String

expect fun String.encodeBase64UrlSafe(offset: Int, length: Int = this.length): String

// base64 => byte[]

expect fun String.decodeBase64(): ByteArray

expect fun String.decodeBase64OrNull(): ByteArray?

// base64url => byte[]

expect fun String.decodeBase64UrlSafe(): ByteArray

expect fun String.decodeBase64UrlSafeOrNull(): ByteArray?

// base64 => string

expect fun String.decodeBase64ToString(): String

expect fun String.decodeBase64ToStringOrNull(): String?

// base64url => string

expect fun String.decodeBase64UrlSafeToString(): String

expect fun String.decodeBase64UrlSafeToStringOrNull(): String?
