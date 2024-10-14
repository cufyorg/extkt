/*
 *	Copyright 2023 cufy.org and meemer.com
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	    http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package org.cufy.json

import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

// JsonElement <= String

@Deprecated("Use the new name", ReplaceWith("decodeJson(json)"))
fun String.decodeJsonString(json: Json = Json): JsonElement = decodeJson(json)

@Deprecated("Use the new name", ReplaceWith("decodeJsonOrNull(json)"))
fun String.decodeJsonStringOrNull(json: Json = Json): JsonElement? = decodeJsonOrNull(json)

/**
 * Deserializes [this] JSON string into a corresponding [JsonElement] representation.
 *
 * @throws [SerializationException] if the given string is not a valid JSON
 */
fun String.decodeJson(json: Json = Json): JsonElement {
    return json.parseToJsonElement(this)
}

/**
 * Tries to deserialize [this] JSON string into a corresponding [JsonElement] representation.
 * Returns `null` when on failure.
 */
fun String.decodeJsonOrNull(json: Json = Json): JsonElement? {
    return try {
        json.parseToJsonElement(this)
    } catch (_: SerializationException) {
        return null
    }
}

/**
 * Tries to deserialize [this] JSON string into a corresponding [JsonElement] representation.
 */
fun String.decodeJsonCatching(json: Json = Json): Result<JsonElement> {
    return try {
        success(json.parseToJsonElement(this))
    } catch (e: SerializationException) {
        failure(e)
    }
}

// JsonElement => String

@Deprecated("Use the new name", ReplaceWith("encodeToString(json)"))
fun JsonElement.encodeToJsonString(json: Json = Json): String = encodeToString(json)

/**
 * Serializes [this] [JsonElement] representation into JSON string.
 */
fun JsonElement.encodeToString(json: Json = Json): String {
    return json.encodeToString(this)
}

// T <= String

@Deprecated("Use the new name", ReplaceWith("deserializeJson<T>(json)"))
inline fun <reified T> String.deserializeJsonString(json: Json = Json): T = deserializeJson(json)

@Deprecated("Use the new name", ReplaceWith("deserializeJsonOrNull<T>(json)"))
inline fun <reified T> String.deserializeJsonStringOrNull(json: Json = Json): T? = deserializeJsonOrNull(json)

/**
 * Decodes and deserializes [this] string to the value of type [T] using deserializer
 * retrieved from the reified type parameter.
 *
 * @throws SerializationException in case of any decoding-specific error
 * @throws IllegalArgumentException if the decoded input is not a valid instance of [T]
 */
inline fun <reified T> String.deserializeJson(json: Json = Json): T {
    return json.decodeFromString(this)
}

/**
 * Decodes and deserializes [this] string to the value of type [T] using deserializer
 * retrieved from the reified type parameter.
 * Returns `null` when on failure.
 */
inline fun <reified T> String.deserializeJsonOrNull(json: Json = Json): T? {
    return try {
        json.decodeFromString(this)
    } catch (_: SerializationException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}

/**
 * Decodes and deserializes [this] string to the value of type [T] using deserializer
 * retrieved from the reified type parameter.
 */
inline fun <reified T> String.deserializeJsonCatching(json: Json = Json): Result<T> {
    return try {
        success(json.decodeFromString<T>(this))
    } catch (e: SerializationException) {
        failure(e)
    } catch (e: IllegalArgumentException) {
        failure(e)
    }
}

// T <= JsonElement

@Deprecated("Use the new name", ReplaceWith("deserialize<T>(json)"))
inline fun <reified T> JsonElement.deserializeJsonElement(json: Json = Json): T = deserialize(json)

@Deprecated("Use the new name", ReplaceWith("deserializeOrNull<T>(json)"))
inline fun <reified T> JsonElement.deserializeJsonElementOrNull(json: Json = Json): T? = deserializeOrNull(json)

/**
 * Deserializes [this] json element into a value of type [T] using a deserializer retrieved
 * from reified type parameter.
 *
 * @throws [SerializationException] if the given JSON element is not a valid JSON input for the type [T]
 * @throws [IllegalArgumentException] if the decoded input cannot be represented as a valid instance of type [T]
 */
inline fun <reified T> JsonElement.deserialize(json: Json = Json): T {
    return json.decodeFromJsonElement(this)
}

/**
 * Deserializes [this] json element into a value of type [T] using a deserializer retrieved
 * from reified type parameter.
 * Returns `null` when on failure.
 */
inline fun <reified T> JsonElement.deserializeOrNull(json: Json = Json): T? {
    return try {
        json.decodeFromJsonElement(this)
    } catch (_: SerializationException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}

/**
 * Deserializes [this] json element into a value of type [T] using a deserializer retrieved
 * from reified type parameter.
 */
inline fun <reified T> JsonElement.deserializeCatching(json: Json = Json): Result<T> {
    return try {
        success(json.decodeFromJsonElement<T>(this))
    } catch (e: SerializationException) {
        failure(e)
    } catch (e: IllegalArgumentException) {
        failure(e)
    }
}

// T => String

/**
 * Serializes and encodes [this] to string using serializer retrieved from the reified type parameter.
 *
 * @throws SerializationException in case of any encoding-specific error
 * @throws IllegalArgumentException if the encoded input does not comply format's specification
 */
inline fun <reified T> T.serializeToJsonString(json: Json = Json): String {
    return json.encodeToString(this)
}

/**
 * Serializes and encodes [this] to string using serializer retrieved from the reified type parameter.
 * Returns `null` when on failure.
 */
inline fun <reified T> T.serializeToJsonStringOrNull(json: Json = Json): String? {
    return try {
        json.encodeToString(this)
    } catch (_: SerializationException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}

/**
 * Serializes and encodes [this] to string using serializer retrieved from the reified type parameter.
 */
inline fun <reified T> T.serializeToJsonStringCatching(json: Json = Json): Result<String> {
    return try {
        success(json.encodeToString(this))
    } catch (e: SerializationException) {
        failure(e)
    } catch (e: IllegalArgumentException) {
        failure(e)
    }
}

// T => JsonElement

/**
 * Serializes the [this] into an equivalent [JsonElement] using a serializer retrieved
 * from reified type parameter.
 *
 * @throws [SerializationException] if the given value cannot be serialized to JSON.
 */
inline fun <reified T> T.serializeToJsonElement(json: Json = Json): JsonElement {
    return json.encodeToJsonElement(this)
}

/**
 * Serializes the [this] into an equivalent [JsonElement] using a serializer retrieved
 * from reified type parameter.
 * Returns `null` when on failure.
 */
inline fun <reified T> T.serializeToJsonElementOrNull(json: Json = Json): JsonElement? {
    return try {
        return json.encodeToJsonElement(this)
    } catch (_: SerializationException) {
        null
    }
}

/**
 * Serializes the [this] into an equivalent [JsonElement] using a serializer retrieved
 * from reified type parameter.
 */
inline fun <reified T> T.serializeToJsonElementCatching(json: Json = Json): Result<JsonElement> {
    return try {
        success(json.encodeToJsonElement(this))
    } catch (e: SerializationException) {
        failure(e)
    }
}

// T => JsonObject

/**
 * Serializes the [this] into an equivalent [JsonObject] using a serializer retrieved
 * from reified type parameter.
 *
 * @throws [SerializationException] if the given value cannot be serialized to JSON.
 * @throws [ClassCastException] if the given value was not serialized to JsonObject.
 */
inline fun <reified T> T.serializeToJsonObject(json: Json = Json): JsonObject {
    return serializeToJsonElement(json) as JsonObject
}

/**
 * Serializes the [this] into an equivalent [JsonObject] using a serializer retrieved
 * from reified type parameter.
 * Returns `null` when on failure.
 */
inline fun <reified T> T.serializeToJsonObjectOrNull(json: Json = Json): JsonObject? {
    return serializeToJsonElementOrNull(json) as? JsonObject
}

/**
 * Serializes the [this] into an equivalent [JsonObject] using a serializer retrieved
 * from reified type parameter.
 */
inline fun <reified T> T.serializeToJsonObjectCatching(json: Json = Json): Result<JsonObject> {
    return serializeToJsonElementCatching(json)
        .mapCatching { it as JsonObject }
}
