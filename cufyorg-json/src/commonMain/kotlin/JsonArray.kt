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

import kotlinx.serialization.json.*

//

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: Map<String, JsonElement>?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: List<JsonElement>?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArray.plus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this + element)
}

//

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: Map<String, JsonElement>?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: List<JsonElement>?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArray.minus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this - element)
}

//

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    add(element)
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    add(element)
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: String?) {
    val element = JsonPrimitive(value)
    add(element)
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    add(element)
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    add(element)
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun JsonArrayBuilder.plusAssign(value: Nothing?) {
    val element = JsonNull
    add(element)
}
