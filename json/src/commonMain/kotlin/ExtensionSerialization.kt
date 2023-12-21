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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

// JsonElement <= String

/**
 * Deserializes [this] JSON string into a corresponding [JsonElement] representation.
 *
 * @throws [SerializationException] if the given string is not a valid JSON
 */
fun String.decodeJsonString(json: Json = Json): JsonElement {
    return json.parseToJsonElement(this)
}

/**
 * Tries to deserialize [this] JSON string into a corresponding [JsonElement] representation.
 * Returns `null` when on failure.
 */
fun String.decodeJsonStringOrNull(json: Json = Json): JsonElement? {
    return try {
        json.parseToJsonElement(this)
    } catch (_: SerializationException) {
        return null
    }
}

// JsonElement => String

/**
 * Serializes [this] [JsonElement] representation into JSON string.
 */
fun JsonElement.encodeToJsonString(json: Json = Json): String {
    return json.encodeToString(this)
}

// T <= String

/**
 * Decodes and deserializes [this] string to the value of type [T] using deserializer
 * retrieved from the reified type parameter.
 *
 * @throws SerializationException in case of any decoding-specific error
 * @throws IllegalArgumentException if the decoded input is not a valid instance of [T]
 */
inline fun <reified T> String.deserializeJsonString(json: Json = Json): T {
    return json.decodeFromString(this)
}

/**
 * Decodes and deserializes [this] string to the value of type [T] using deserializer
 * retrieved from the reified type parameter.
 * Returns `null` when on failure.
 */
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

/**
 * Deserializes [this] json element into a value of type [T] using a deserializer retrieved
 * from reified type parameter.
 *
 * @throws [SerializationException] if the given JSON element is not a valid JSON input for the type [T]
 * @throws [IllegalArgumentException] if the decoded input cannot be represented as a valid instance of type [T]
 */
inline fun <reified T> JsonElement.deserializeJsonElement(json: Json = Json): T {
    return json.decodeFromJsonElement(this)
}

/**
 * Deserializes [this] json element into a value of type [T] using a deserializer retrieved
 * from reified type parameter.
 * Returns `null` when on failure.
 */
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

/**
 * Serializes and encodes [this] to string using serializer retrieved from the reified type parameter.
 *
 * @throws SerializationException in case of any encoding-specific error
 * @throws IllegalArgumentException if the encoded input does not comply format's specification
 */
inline fun <reified T> T.serializeToJsonString(json: Json = Json): String {
    return json.encodeToString(this)
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
