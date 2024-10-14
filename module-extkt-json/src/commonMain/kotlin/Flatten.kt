package org.cufy.json

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

fun JsonObject.flatten(): Map<String, String> {
    return buildMap { flattenTo("", this) }
}

private fun JsonObject.flattenTo(path: String, out: MutableMap<String, String>) {
    val prefix = if (path.isEmpty()) "" else "$path."
    for ((name, value) in this) when (value) {
        is JsonObject -> value.flattenTo("$prefix$name", out)
        is JsonPrimitive -> out["$prefix$name"] = Json.encodeToString(value)
        is JsonArray -> out["$prefix$name"] = Json.encodeToString(value)
    }
}

fun Map<String, String>.unflattenToJsonObject(): JsonObject {
    fun mergeToJsonObject(path: String): JsonObject {
        val prefix = if (path.isEmpty()) "" else "$path."
        val names = this.entries.asSequence()
            .filter { it.key.startsWith(prefix) }
            .groupBy {
                val i = it.key.indexOf('.', prefix.length)
                if (i == -1) it.key.substring(prefix.length)
                else it.key.substring(prefix.length, i)
            }

        return buildJsonObject {
            for ((name, items) in names) {
                val (lastPath, last) = items.last()
                val lastRelativeDotPeriodIndex = lastPath.indexOf('.', prefix.length)

                if (lastRelativeDotPeriodIndex == -1) {
                    put(name, LenientJson.parseToJsonElement(last))
                } else {
                    put(name, mergeToJsonObject("$prefix$name"))
                }
            }
        }
    }

    return mergeToJsonObject("")
}
