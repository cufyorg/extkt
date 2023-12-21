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
inline val JsonElement.jsonPrimitiveOrNull get() = this as? JsonPrimitive

/** if this json element is a json object, return it. Otherwise, return `null`. */
inline val JsonElement.jsonObjectOrNull get() = this as? JsonObject

/** if this json element is a json array, return it. Otherwise, return `null`. */
inline val JsonElement.jsonArrayOrNull get() = this as? JsonArray

//

/** if this json element is a json string, return its content. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveString get() = jsonPrimitive.string

/** if this json element is a json string, return its content. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveStringOrNull get() = jsonPrimitiveOrNull?.stringOrNull

/** if this json element is a json number, return its content as [Int]. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveInt get() = jsonPrimitive.int

/** if this json element is a json number, return its content as [Int]. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveIntOrNull get() = jsonPrimitiveOrNull?.intOrNull

/** if this json element is a json number, return its content as [Long]. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveLong get() = jsonPrimitive.long

/** if this json element is a json number, return its content as [Long]. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveLongOrNull get() = jsonPrimitiveOrNull?.longOrNull

/** if this json element is a json number, return its content as [Double]. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveDouble get() = jsonPrimitive.double

/** if this json element is a json number, return its content as [Double]. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveDoubleOrNull get() = jsonPrimitiveOrNull?.doubleOrNull

/** if this json element is a json number, return its content as [Float]. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveFloat get() = jsonPrimitive.float

/** if this json element is a json number, return its content as [Float]. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveFloatOrNull get() = jsonPrimitiveOrNull?.floatOrNull

/** if this json element is a json boolean, return its content. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveBoolean get() = jsonPrimitive.boolean

/** if this json element is a json boolean, return its content. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveBooleanOrNull get() = jsonPrimitiveOrNull?.booleanOrNull

/** if this json element is a json primitive, return its raw content. Otherwise, fail. */
inline val JsonElement.jsonPrimitiveContent get() = jsonPrimitive.content

/** if this json element is a json primitive, return its raw content. Otherwise, return `null`. */
inline val JsonElement.jsonPrimitiveContentOrNull get() = jsonPrimitiveOrNull?.contentOrNull

//

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
fun JsonPrimitive?.orJsonNull(): JsonPrimitive {
    return this ?: JsonNull
}

/** if this json element reference is not null, return it. Otherwise, return [JsonNull]. */
fun JsonElement?.orJsonNull(): JsonElement {
    return this ?: JsonNull
}

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
inline val Nothing?.json: JsonPrimitive get() = JsonNull
