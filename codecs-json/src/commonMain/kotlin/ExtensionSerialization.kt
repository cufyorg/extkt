package org.cufy.json

import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

// JsonElement <= String

fun String.decodeJsonString(json: Json = Json): JsonElement {
    return json.parseToJsonElement(this)
}

fun String.decodeJsonStringOrNull(json: Json = Json): JsonElement? {
    return try {
        json.parseToJsonElement(this)
    } catch (_: SerializationException) {
        return null
    }
}

// JsonElement => String

fun JsonElement.encodeToJsonString(json: Json = Json): String {
    return json.encodeToString(this)
}

// T <= String

inline fun <reified T> String.deserializeJsonString(json: Json = Json): T {
    return json.decodeFromString(this)
}

inline fun <reified T> String.deserializeJsonStringOrNull(json: Json = Json): T? {
    return try {
        json.decodeFromString(this)
    } catch (_: SerializationException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}

// T <= JsonElement

inline fun <reified T> JsonElement.deserializeJsonElement(json: Json = Json): T {
    return json.decodeFromJsonElement(this)
}

inline fun <reified T> JsonElement.deserializeJsonElementOrNull(json: Json = Json): T? {
    return try {
        json.decodeFromJsonElement(this)
    } catch (_: SerializationException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}

// T => String

inline fun <reified T> T.serializeToJsonString(json: Json = Json): String {
    return json.encodeToString(this)
}

// T => JsonElement

inline fun <reified T> T.serializeToJsonElement(json: Json = Json): JsonElement {
    return json.encodeToJsonElement(this)
}
