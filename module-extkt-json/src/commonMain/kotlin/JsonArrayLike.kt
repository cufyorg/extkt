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
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive

//

/** return a copy of [this] with [block] applied to it. */
fun JsonArrayLike.copy(block: MutableJsonArrayLike.() -> Unit): JsonArray {
    return JsonArray(buildList { addAll(this@copy); block() })
}

//

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: JsonObjectLike?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: JsonArrayLike?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

/** return the combination of [this] and the given [value] */
operator fun JsonArrayLike.plus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this + element)
}

//

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: JsonObjectLike?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: JsonArrayLike?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

/** return the combination of [this] excluding the first element equivalent to [value] */
operator fun JsonArrayLike.minus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this - element)
}

//

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this += element
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this += element
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: String?) {
    val element = JsonPrimitive(value)
    this += element
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    this += element
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    this += element
}

/** add the given [value] to the end of the list. Or add [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.plusAssign(value: Nothing?) {
    val element = JsonNull
    this += element
}

//

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this -= element
}

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this -= element
}

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: String?) {
    val element = JsonPrimitive(value)
    this -= element
}

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    this -= element
}

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    this -= element
}

/** remove the first element equivalent to [value] from the list. Or remove [JsonNull] if [value] is null. */
operator fun MutableJsonArrayLike.minusAssign(value: Nothing?) {
    val element = JsonNull
    this -= element
}
