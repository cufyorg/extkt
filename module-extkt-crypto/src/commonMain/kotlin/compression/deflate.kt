package org.cufy.compression

// byte[] => deflate

expect fun ByteArray.deflate(level: Int /* 0-9 */): ByteArray

// string => deflate

expect fun String.deflate(level: Int /* 0-9 */): ByteArray

// deflate => byte[]

expect fun ByteArray.inflate(): ByteArray

expect fun ByteArray.inflateOrNull(): ByteArray?

// deflate => string

expect fun ByteArray.inflateToString(): String

expect fun ByteArray.inflateToStringOrNull(): String?
