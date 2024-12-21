package org.cufy.compression

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.Inflater
import java.util.zip.InflaterInputStream
import kotlin.coroutines.cancellation.CancellationException

actual fun ByteArray.deflate(level: Int): ByteArray {
    val baos = ByteArrayOutputStream()
    val deflater = Deflater(level)
    val deflate = DeflaterOutputStream(baos, deflater)
    deflate.use { it.write(this) }
    return baos.toByteArray()
}

actual fun String.deflate(level: Int): ByteArray {
    val baos = ByteArrayOutputStream()
    val deflater = Deflater(level)
    val deflate = DeflaterOutputStream(baos, deflater)
    val osw = OutputStreamWriter(deflate)
    osw.use { it.write(this) }
    return baos.toByteArray()
}

actual fun ByteArray.inflate(): ByteArray {
    val bais = ByteArrayInputStream(this)
    val inflater = Inflater()
    val inflate = InflaterInputStream(bais, inflater)
    return inflate.use { it.readBytes() }
}

actual fun ByteArray.inflateOrNull(): ByteArray? {
    return try {
        inflate()
    } catch (e: CancellationException) {
        throw e
    } catch (_: RuntimeException) {
        null
    }
}

actual fun ByteArray.inflateToString(): String {
    val bais = ByteArrayInputStream(this)
    val inflater = Inflater()
    val inflate = InflaterInputStream(bais, inflater)
    val isr = InputStreamReader(inflate)
    return isr.use { it.readText() }
}

actual fun ByteArray.inflateToStringOrNull(): String? {
    return try {
        inflateToString()
    } catch (e: CancellationException) {
        throw e
    } catch (_: RuntimeException) {
        null
    }
}
