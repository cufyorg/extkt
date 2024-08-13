package org.cufy.compression

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.zip.Deflater
import java.util.zip.DeflaterInputStream
import java.util.zip.DeflaterOutputStream

actual fun ByteArray.deflate(level: Int): ByteArray {
    val deflater = Deflater(level)
    val baos = ByteArrayOutputStream()
    val deflate = DeflaterOutputStream(baos, deflater)
    deflate.use { it.write(this) }
    return baos.toByteArray()
}

actual fun String.deflate(level: Int): ByteArray {
    val deflater = Deflater(level)
    val baos = ByteArrayOutputStream()
    val deflate = DeflaterOutputStream(baos, deflater)
    val osw = OutputStreamWriter(deflate)
    osw.use { it.write(this) }
    return baos.toByteArray()
}

actual fun ByteArray.dedeflate(level: Int): ByteArray {
    val deflater = Deflater(level)
    val bais = ByteArrayInputStream(this)
    val deflate = DeflaterInputStream(bais, deflater)
    return deflate.use { it.readBytes() }
}

actual fun ByteArray.dedeflateToString(level: Int): String {
    val deflater = Deflater(level)
    val bais = ByteArrayInputStream(this)
    val deflate = DeflaterInputStream(bais, deflater)
    val isr = InputStreamReader(deflate)
    return isr.use { it.readText() }
}
