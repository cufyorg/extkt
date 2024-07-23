package org.cufy.hash

// byte[] => md5

expect fun ByteArray.md5(): ByteArray

// string => md5

expect fun String.md5(): ByteArray

// byte[] => md5(hex)

expect fun ByteArray.md5ToString(): String

// string => md5(hex)

expect fun String.md5ToString(): String
