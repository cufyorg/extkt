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

import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.jvm.JvmName

//

/** return a copy of [this] with [block] applied to it. */
fun JsonObjectLike.copy(block: MutableJsonObjectLike.() -> Unit): JsonObject {
    return JsonObject(buildMap { putAll(this); block() })
}

//

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringMapPair")
operator fun JsonObjectLike.plus(pair: Pair<String, JsonObjectLike?>): JsonObject {
    val (name, value) = pair
    val element = value?.toJsonObject().orJsonNull()
    return JsonObject(this + (name to element))
}

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringListPair")
operator fun JsonObjectLike.plus(pair: Pair<String, JsonArrayLike?>): JsonObject {
    val (name, value) = pair
    val element = value?.toJsonArray().orJsonNull()
    return JsonObject(this + (name to element))
}

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringStringPair")
operator fun JsonObjectLike.plus(pair: Pair<String, String?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringNumberPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Number?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringBooleanPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Boolean?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

/** return the combination of [this] and the given key-value [pair] */
@JvmName("plusStringNothingPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Nothing?>): JsonObject {
    val (name, _) = pair
    val element = JsonNull
    return JsonObject(this + (name to element))
}

//

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this[name] = element
}

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this[name] = element
}

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: String?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: Number?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: Boolean?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

/** set the given [name] to the given [value] or to [JsonNull] if [value] is null. */
operator fun MutableJsonObjectLike.set(name: String, value: Nothing?) {
    val element = JsonNull
    this[name] = element
}
