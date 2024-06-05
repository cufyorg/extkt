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

/** if this json element is a json string, return its content. Otherwise, fail. */
inline val JsonPrimitive.string get() = if (isString) content else error("$content is not a String")

/** if this json element is a json string, return its content. Otherwise, return `null`. */
inline val JsonPrimitive.stringOrNull get() = if (isString) content else null

//

/** if this json element is a json primitive, return it. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asPrimitiveOrNull"))
inline val JsonElement.jsonPrimitiveOrNull get() = asJsonPrimitiveOrNull

/** if this json element is a json object, return it. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asObjectOrNull"))
inline val JsonElement.jsonObjectOrNull get() = asJsonObjectOrNull

/** if this json element is a json array, return it. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asJsonArrayOrNull"))
inline val JsonElement.jsonArrayOrNull get() = asJsonArrayOrNull

//

/** if this json element is a json primitive, return it. Otherwise, fail. */
inline val JsonElement.asJsonPrimitive: JsonPrimitive get() = this as JsonPrimitive

/** if this json element is a json primitive, return it. Otherwise, return `null`. */
inline val JsonElement.asJsonPrimitiveOrNull: JsonPrimitive? get() = this as? JsonPrimitive

/** if this json element is a json object, return it. Otherwise, fail. */
inline val JsonElement.asJsonObject: JsonObject get() = this as JsonObject

/** if this json element is a json object, return it. Otherwise, return `null`. */
inline val JsonElement.asJsonObjectOrNull: JsonObject? get() = this as? JsonObject

/** if this json element is a json array, return it. Otherwise, fail. */
inline val JsonElement.asJsonArray: JsonArray get() = this as JsonArray

/** if this json element is a json array, return it. Otherwise, return `null`. */
inline val JsonElement.asJsonArrayOrNull: JsonArray? get() = this as? JsonArray

//

/** if this json element is a json string, return its content. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asString"))
inline val JsonElement.jsonPrimitiveString get() = asString

/** if this json element is a json string, return its content. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asStringOrNull"))
inline val JsonElement.jsonPrimitiveStringOrNull get() = asStringOrNull

/** if this json element is a json number, return its content as [Int]. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asInt"))
inline val JsonElement.jsonPrimitiveInt get() = asInt

/** if this json element is a json number, return its content as [Int]. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asIntOrNull"))
inline val JsonElement.jsonPrimitiveIntOrNull get() = asIntOrNull

/** if this json element is a json number, return its content as [Long]. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asLong"))
inline val JsonElement.jsonPrimitiveLong get() = asLong

/** if this json element is a json number, return its content as [Long]. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asLongOrNull"))
inline val JsonElement.jsonPrimitiveLongOrNull get() = asLongOrNull

/** if this json element is a json number, return its content as [Double]. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asDouble"))
inline val JsonElement.jsonPrimitiveDouble get() = asDouble

/** if this json element is a json number, return its content as [Double]. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asDoubleOrNull"))
inline val JsonElement.jsonPrimitiveDoubleOrNull get() = asDoubleOrNull

/** if this json element is a json number, return its content as [Float]. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asFloat"))
inline val JsonElement.jsonPrimitiveFloat get() = asFloat

/** if this json element is a json number, return its content as [Float]. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asFloatOrNull"))
inline val JsonElement.jsonPrimitiveFloatOrNull get() = asFloatOrNull

/** if this json element is a json boolean, return its content. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asBoolean"))
inline val JsonElement.jsonPrimitiveBoolean get() = asBoolean

/** if this json element is a json boolean, return its content. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asBooleanOrNull"))
inline val JsonElement.jsonPrimitiveBooleanOrNull get() = asBooleanOrNull

/** if this json element is a json primitive, return its raw content. Otherwise, fail. */
@Deprecated("Use new API", ReplaceWith("asContentString"))
inline val JsonElement.jsonPrimitiveContent get() = asContentString

/** if this json element is a json primitive, return its raw content. Otherwise, return `null`. */
@Deprecated("Use new API", ReplaceWith("asContentStringOrNull"))
inline val JsonElement.jsonPrimitiveContentOrNull get() = asContentStringOrNull

//

/** if this json element is a json string, return its content. Otherwise, fail. */
inline val JsonElement.asString: String get() = asJsonPrimitive.string

/** if this json element is a json string, return its content. Otherwise, return `null`. */
inline val JsonElement.asStringOrNull: String? get() = asJsonPrimitiveOrNull?.stringOrNull

/** if this json element is a json number, return its content as [Int]. Otherwise, fail. */
inline val JsonElement.asInt: Int get() = asJsonPrimitive.int

/** if this json element is a json number, return its content as [Int]. Otherwise, return `null`. */
inline val JsonElement.asIntOrNull: Int? get() = asJsonPrimitiveOrNull?.intOrNull

/** if this json element is a json number, return its content as [Long]. Otherwise, fail. */
inline val JsonElement.asLong: Long get() = asJsonPrimitive.long

/** if this json element is a json number, return its content as [Long]. Otherwise, return `null`. */
inline val JsonElement.asLongOrNull: Long? get() = asJsonPrimitiveOrNull?.longOrNull

/** if this json element is a json number, return its content as [Double]. Otherwise, fail. */
inline val JsonElement.asDouble: Double get() = asJsonPrimitive.double

/** if this json element is a json number, return its content as [Double]. Otherwise, return `null`. */
inline val JsonElement.asDoubleOrNull: Double? get() = asJsonPrimitiveOrNull?.doubleOrNull

/** if this json element is a json number, return its content as [Float]. Otherwise, fail. */
inline val JsonElement.asFloat: Float get() = asJsonPrimitive.float

/** if this json element is a json number, return its content as [Float]. Otherwise, return `null`. */
inline val JsonElement.asFloatOrNull: Float? get() = asJsonPrimitiveOrNull?.floatOrNull

/** if this json element is a json boolean, return its content. Otherwise, fail. */
inline val JsonElement.asBoolean: Boolean get() = asJsonPrimitive.boolean

/** if this json element is a json boolean, return its content. Otherwise, return `null`. */
inline val JsonElement.asBooleanOrNull: Boolean? get() = asJsonPrimitiveOrNull?.booleanOrNull

/** if this json element is a json primitive, return its raw content. Otherwise, fail. */
inline val JsonElement.asContentString: String get() = asJsonPrimitive.content

/** if this json element is a json primitive, return its raw content. Otherwise, return `null`. */
inline val JsonElement.asContentStringOrNull: String? get() = asJsonPrimitiveOrNull?.contentOrNull

//

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
fun JsonPrimitive?.orJsonNull(): JsonPrimitive {
    return this ?: JsonNull
}

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
fun JsonElement?.orJsonNull(): JsonElement {
    return this ?: JsonNull
}

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
inline val JsonPrimitive?.orJsonNull get() = this ?: JsonNull

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
inline val JsonElement?.orJsonNull get() = this ?: JsonNull

//

/** Return a [JsonArray] instance by copying [this] list */
fun JsonArrayLike.toJsonArray(): JsonArray {
    return JsonArray(toList())
}

/** Return a [JsonObject] instance by copying [this] map */
fun JsonObjectLike.toJsonObject(): JsonObject {
    return JsonObject(toMap())
}

//

/** return a [JsonPrimitive] instance from the content of this. */
inline val String?.json: JsonPrimitive get() = JsonPrimitive(this)

/** return a [JsonPrimitive] instance from the content of this. */
inline val Number?.json: JsonPrimitive get() = JsonPrimitive(this)

/** return a [JsonPrimitive] instance from the content of this. */
inline val Boolean?.json: JsonPrimitive get() = JsonPrimitive(this)

/** return a [JsonPrimitive] instance from the content of this. */
@Suppress("UnusedReceiverParameter")
inline val Nothing?.json: JsonPrimitive get() = JsonNull
