package org.cufy.compression

// byte[] => deflate

expect fun ByteArray.deflate(level: Int /* 0-9 */): ByteArray

// string => deflate

expect fun String.deflate(level: Int /* 0-9 */): ByteArray

// deflate => byte[]

expect fun ByteArray.dedeflate(level: Int /* 0-9 */): ByteArray

// deflate => string

expect fun ByteArray.dedeflateToString(level: Int /* 0-9 */): String
