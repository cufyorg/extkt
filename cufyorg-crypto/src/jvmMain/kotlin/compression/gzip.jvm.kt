package org.cufy.compression

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

actual fun ByteArray.gzip(): ByteArray {
    val baos = ByteArrayOutputStream()
    val gzip = GZIPOutputStream(baos)
    gzip.use { it.write(this) }
    return baos.toByteArray()
}

actual fun String.gzip(): ByteArray {
    val baos = ByteArrayOutputStream()
    val gzip = GZIPOutputStream(baos)
    val osw = OutputStreamWriter(gzip)
    osw.use { it.write(this) }
    return baos.toByteArray()
}

actual fun ByteArray.gunzip(): ByteArray {
    val bais = ByteArrayInputStream(this)
    val gzip = GZIPInputStream(bais)
    return gzip.use { it.readBytes() }
}

actual fun ByteArray.gunzipToString(): String {
    val bais = ByteArrayInputStream(this)
    val gzip = GZIPInputStream(bais)
    val isr = InputStreamReader(gzip)
    return isr.use { it.readText() }
}
