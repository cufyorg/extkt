package org.cufy.json.test

import kotlinx.serialization.Serializable
import org.cufy.json.deserialize
import org.cufy.json.flatten
import org.cufy.json.serializeToJsonObject
import org.cufy.json.unflattenToJsonObject
import kotlin.test.Test
import kotlin.test.assertEquals

class FlattenTest {
    @Test
    fun `simple example`() {
        val data = SomeData(
            a = "H",
            b = SomeOtherData(
                a = "E",
                b = "L",
                c = SomeOtherOtherData(
                    a = "L",
                    b = "O",
                )
            )
        )

        val json = data.serializeToJsonObject()
        val flattened = json.flatten()

        val flattenedExpected = mapOf(
            "a" to "\"H\"",
            "b.a" to "\"E\"",
            "b.b" to "\"L\"",
            "b.c.a" to "\"L\"",
            "b.c.b" to "\"O\"",
        )

        assertEquals(flattenedExpected, flattened)

        val unflattened = flattened.unflattenToJsonObject()

        assertEquals(json, unflattened)

        val deserialized = unflattened.deserialize<SomeData>()

        assertEquals(data, deserialized)
    }
}

@Serializable
private data class SomeData(
    val a: String,
    val b: SomeOtherData,
)

@Serializable
private data class SomeOtherData(
    val a: String,
    val b: String,
    val c: SomeOtherOtherData,
)

@Serializable
private data class SomeOtherOtherData(
    val a: String,
    val b: String,
)
