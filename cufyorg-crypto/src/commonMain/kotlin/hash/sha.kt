package org.cufy.hash

// byte[] => sha256

expect fun ByteArray.sha256(): ByteArray

// byte[] => sha384

expect fun ByteArray.sha384(): ByteArray

// byte[] => sha512

expect fun ByteArray.sha512(): ByteArray

// string => sha256

expect fun String.sha256(): ByteArray

// string => sha384

expect fun String.sha384(): ByteArray

// string => sha512

expect fun String.sha512(): ByteArray

// byte[] => sha256(hex)

expect fun ByteArray.sha256ToString(): String

// byte[] => sha384(hex)

expect fun ByteArray.sha384ToString(): String

// byte[] => sha512(hex)

expect fun ByteArray.sha512ToString(): String

// string => sha256(hex)

expect fun String.sha256ToString(): String

// string => sha384(hex)

expect fun String.sha384ToString(): String

// string => sha512(hex)

expect fun String.sha512ToString(): String
