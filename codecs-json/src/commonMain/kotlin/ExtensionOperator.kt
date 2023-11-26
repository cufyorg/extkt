package org.cufy.json

import kotlinx.serialization.json.*
import kotlin.jvm.JvmName

//

operator fun MutableJsonObjectLike.set(name: String, value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this[name] = element
}

operator fun MutableJsonObjectLike.set(name: String, value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this[name] = element
}

operator fun MutableJsonObjectLike.set(name: String, value: String?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

operator fun MutableJsonObjectLike.set(name: String, value: Number?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

operator fun MutableJsonObjectLike.set(name: String, value: Boolean?) {
    val element = JsonPrimitive(value)
    this[name] = element
}

operator fun MutableJsonObjectLike.set(name: String, value: Nothing?) {
    val element = JsonNull
    this[name] = element
}

//

operator fun JsonObjectBuilder.set(name: String, value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    put(name, element)
}

operator fun JsonObjectBuilder.set(name: String, value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    put(name, element)
}

operator fun JsonObjectBuilder.set(name: String, value: String?) {
    val element = JsonPrimitive(value)
    put(name, element)
}

operator fun JsonObjectBuilder.set(name: String, value: Number?) {
    val element = JsonPrimitive(value)
    put(name, element)
}

operator fun JsonObjectBuilder.set(name: String, value: Boolean?) {
    val element = JsonPrimitive(value)
    put(name, element)
}

operator fun JsonObjectBuilder.set(name: String, value: Nothing?) {
    val element = JsonNull
    put(name, element)
}

//

@JvmName("plusStringMapPair")
operator fun JsonObjectLike.plus(pair: Pair<String, JsonObjectLike?>): JsonObjectLike {
    val (name, value) = pair
    val element = value?.toJsonObject().orJsonNull()
    return this + (name to element)
}

@JvmName("plusStringListPair")
operator fun JsonObjectLike.plus(pair: Pair<String, JsonArrayLike?>): JsonObjectLike {
    val (name, value) = pair
    val element = value?.toJsonArray().orJsonNull()
    return this + (name to element)
}

@JvmName("plusStringStringPair")
operator fun JsonObjectLike.plus(pair: Pair<String, String?>): JsonObjectLike {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return this + (name to element)
}

@JvmName("plusStringNumberPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Number?>): JsonObjectLike {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return this + (name to element)
}

@JvmName("plusStringBooleanPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Boolean?>): JsonObjectLike {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return this + (name to element)
}

@JvmName("plusStringNothingPair")
operator fun JsonObjectLike.plus(pair: Pair<String, Nothing?>): JsonObjectLike {
    val (name, _) = pair
    val element = JsonNull
    return this + (name to element)
}

//

@JvmName("plusStringMapPair")
operator fun JsonObject.plus(pair: Pair<String, JsonObjectLike?>): JsonObject {
    val (name, value) = pair
    val element = value?.toJsonObject().orJsonNull()
    return JsonObject(this + (name to element))
}

@JvmName("plusStringListPair")
operator fun JsonObject.plus(pair: Pair<String, JsonArrayLike?>): JsonObject {
    val (name, value) = pair
    val element = value?.toJsonArray().orJsonNull()
    return JsonObject(this + (name to element))
}

@JvmName("plusStringStringPair")
operator fun JsonObject.plus(pair: Pair<String, String?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

@JvmName("plusStringNumberPair")
operator fun JsonObject.plus(pair: Pair<String, Number?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

@JvmName("plusStringBooleanPair")
operator fun JsonObject.plus(pair: Pair<String, Boolean?>): JsonObject {
    val (name, value) = pair
    val element = JsonPrimitive(value)
    return JsonObject(this + (name to element))
}

@JvmName("plusStringNothingPair")
operator fun JsonObject.plus(pair: Pair<String, Nothing?>): JsonObject {
    val (name, _) = pair
    val element = JsonNull
    return JsonObject(this + (name to element))
}

//

operator fun MutableJsonArrayLike.plusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this += element
}

operator fun MutableJsonArrayLike.plusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this += element
}

operator fun MutableJsonArrayLike.plusAssign(value: String?) {
    val element = JsonPrimitive(value)
    this += element
}

operator fun MutableJsonArrayLike.plusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    this += element
}

operator fun MutableJsonArrayLike.plusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    this += element
}

operator fun MutableJsonArrayLike.plusAssign(value: Nothing?) {
    val element = JsonNull
    this += element
}

//

operator fun JsonArrayBuilder.plusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    add(element)
}

operator fun JsonArrayBuilder.plusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    add(element)
}

operator fun JsonArrayBuilder.plusAssign(value: String?) {
    val element = JsonPrimitive(value)
    add(element)
}

operator fun JsonArrayBuilder.plusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    add(element)
}

operator fun JsonArrayBuilder.plusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    add(element)
}

operator fun JsonArrayBuilder.plusAssign(value: Nothing?) {
    val element = JsonNull
    add(element)
}

//

operator fun MutableJsonArrayLike.minusAssign(value: JsonObjectLike?) {
    val element = value?.toJsonObject().orJsonNull()
    this -= element
}

operator fun MutableJsonArrayLike.minusAssign(value: JsonArrayLike?) {
    val element = value?.toJsonArray().orJsonNull()
    this -= element
}

operator fun MutableJsonArrayLike.minusAssign(value: String?) {
    val element = JsonPrimitive(value)
    this -= element
}

operator fun MutableJsonArrayLike.minusAssign(value: Number?) {
    val element = JsonPrimitive(value)
    this -= element
}

operator fun MutableJsonArrayLike.minusAssign(value: Boolean?) {
    val element = JsonPrimitive(value)
    this -= element
}

operator fun MutableJsonArrayLike.minusAssign(value: Nothing?) {
    val element = JsonNull
    this -= element
}

//

operator fun JsonArrayLike.plus(value: JsonObjectLike?): JsonArrayLike {
    val element = value?.toJsonObject().orJsonNull()
    return this + element
}

operator fun JsonArrayLike.plus(value: JsonArrayLike?): JsonArrayLike {
    val element = value?.toJsonArray().orJsonNull()
    return this + element
}

operator fun JsonArrayLike.plus(value: String?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this + element
}

operator fun JsonArrayLike.plus(value: Number?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this + element
}

operator fun JsonArrayLike.plus(value: Boolean?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this + element
}

operator fun JsonArrayLike.plus(value: Nothing?): JsonArrayLike {
    val element = JsonNull
    return this + element
}

//

operator fun JsonArrayLike.minus(value: JsonObjectLike?): JsonArrayLike {
    val element = value?.toJsonObject().orJsonNull()
    return this - element
}

operator fun JsonArrayLike.minus(value: JsonArrayLike?): JsonArrayLike {
    val element = value?.toJsonArray().orJsonNull()
    return this - element
}

operator fun JsonArrayLike.minus(value: String?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this - element
}

operator fun JsonArrayLike.minus(value: Number?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this - element
}

operator fun JsonArrayLike.minus(value: Boolean?): JsonArrayLike {
    val element = JsonPrimitive(value)
    return this - element
}

operator fun JsonArrayLike.minus(value: Nothing?): JsonArrayLike {
    val element = JsonNull
    return this - element
}

//

operator fun JsonArray.plus(value: Map<String, JsonElement>?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this + element)
}

operator fun JsonArray.plus(value: List<JsonElement>?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this + element)
}

operator fun JsonArray.plus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

operator fun JsonArray.plus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

operator fun JsonArray.plus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this + element)
}

operator fun JsonArray.plus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this + element)
}

//

operator fun JsonArray.minus(value: Map<String, JsonElement>?): JsonArray {
    val element = value?.toJsonObject().orJsonNull()
    return JsonArray(this - element)
}

operator fun JsonArray.minus(value: List<JsonElement>?): JsonArray {
    val element = value?.toJsonArray().orJsonNull()
    return JsonArray(this - element)
}

operator fun JsonArray.minus(value: String?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

operator fun JsonArray.minus(value: Number?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

operator fun JsonArray.minus(value: Boolean?): JsonArray {
    val element = JsonPrimitive(value)
    return JsonArray(this - element)
}

operator fun JsonArray.minus(value: Nothing?): JsonArray {
    val element = JsonNull
    return JsonArray(this - element)
}

//
