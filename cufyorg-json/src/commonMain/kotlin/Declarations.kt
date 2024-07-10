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

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

private val EMPTY_JSON_OBJECT = JsonObject(emptyMap())
private val EMPTY_JSON_ARRAY = JsonArray(emptyList())

/**
 * A map containing only items of type [JsonElement].
 *
 * @since 2.0.0
 */
typealias JsonObjectLike = Map<String, JsonElement>

/**
 * A mutable map containing only items of type [JsonElement].
 *
 * @since 2.0.0
 */
typealias MutableJsonObjectLike = MutableMap<String, JsonElement>

/**
 * A list containing only items of type [JsonElement].
 *
 * @since 2.0.0
 */
typealias JsonArrayLike = Iterable<JsonElement>

/**
 * A mutable list containing only items of type [JsonElement].
 *
 * @since 2.0.0
 */
typealias MutableJsonArrayLike = MutableList<JsonElement>

/**
 * Return an empty [JsonObject].
 */
fun JsonObject(): JsonObject {
    return EMPTY_JSON_OBJECT
}

/**
 * Return an empty [JsonArray].
 */
fun JsonArray(): JsonArray {
    return EMPTY_JSON_ARRAY
}

/**
 * Construct a new json object using the given [block].
 *
 * **Warning: mutating the instance provided by the
 * given [block] after the execution of this
 * function will result to an undefined behaviour.**
 */
fun JsonObject(block: MutableJsonObjectLike.() -> Unit): JsonObject {
    return JsonObject(buildMap(block))
}

/**
 * Construct a new json array using the given [block].
 *
 * **Warning: mutating the instance provided by the
 * given [block] after the execution of this
 * function will result to an undefined behaviour.**
 */
fun JsonArray(block: MutableJsonArrayLike.() -> Unit): JsonArray {
    return JsonArray(buildList(block))
}
