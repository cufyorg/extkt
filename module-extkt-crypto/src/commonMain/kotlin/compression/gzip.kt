package org.cufy.compression

// byte[] => gzip

expect fun ByteArray.gzip(): ByteArray

// string => gzip

expect fun String.gzip(): ByteArray

// gzip => byte[]

expect fun ByteArray.gunzip(): ByteArray

expect fun ByteArray.gunzipOrNull(): ByteArray?

// gzip => string

expect fun ByteArray.gunzipToString(): String

expect fun ByteArray.gunzipToStringOrNull(): String?
