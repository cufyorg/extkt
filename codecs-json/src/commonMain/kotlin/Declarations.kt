package org.cufy.json

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

typealias JsonObjectLike = Map<String, JsonElement>
typealias MutableJsonObjectLike = MutableMap<String, JsonElement>

typealias JsonArrayLike = Iterable<JsonElement>
typealias MutableJsonArrayLike = MutableList<JsonElement>

fun JsonObject(block: MutableJsonObjectLike.() -> Unit): JsonObject {
    return JsonObject(buildMap(block))
}

fun JsonArray(block: MutableJsonArrayLike.() -> Unit): JsonArray {
    return JsonArray(buildList(block))
}
