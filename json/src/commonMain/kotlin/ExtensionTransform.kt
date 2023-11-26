package org.cufy.json

import kotlinx.serialization.json.*

//

inline val JsonPrimitive.string get() = if (isString) content else error("$content is not a String")
inline val JsonPrimitive.stringOrNull get() = if (isString) content else null

//

inline val JsonElement.jsonPrimitiveOrNull get() = this as? JsonPrimitive
inline val JsonElement.jsonObjectOrNull get() = this as? JsonObject
inline val JsonElement.jsonArrayOrNull get() = this as? JsonArray

//

inline val JsonElement.jsonPrimitiveString get() = jsonPrimitive.string
inline val JsonElement.jsonPrimitiveStringOrNull get() = jsonPrimitiveOrNull?.stringOrNull

inline val JsonElement.jsonPrimitiveInt get() = jsonPrimitive.int
inline val JsonElement.jsonPrimitiveIntOrNull get() = jsonPrimitiveOrNull?.intOrNull

inline val JsonElement.jsonPrimitiveLong get() = jsonPrimitive.long
inline val JsonElement.jsonPrimitiveLongOrNull get() = jsonPrimitiveOrNull?.longOrNull

inline val JsonElement.jsonPrimitiveDouble get() = jsonPrimitive.double
inline val JsonElement.jsonPrimitiveDoubleOrNull get() = jsonPrimitiveOrNull?.doubleOrNull

inline val JsonElement.jsonPrimitiveFloat get() = jsonPrimitive.float
inline val JsonElement.jsonPrimitiveFloatOrNull get() = jsonPrimitiveOrNull?.floatOrNull

inline val JsonElement.jsonPrimitiveBoolean get() = jsonPrimitive.boolean
inline val JsonElement.jsonPrimitiveBooleanOrNull get() = jsonPrimitiveOrNull?.booleanOrNull

inline val JsonElement.jsonPrimitiveContent get() = jsonPrimitive.content
inline val JsonElement.jsonPrimitiveContentOrNull get() = jsonPrimitiveOrNull?.contentOrNull

//

fun JsonPrimitive?.orJsonNull(): JsonPrimitive {
    return this ?: JsonNull
}

fun JsonElement?.orJsonNull(): JsonElement {
    return this ?: JsonNull
}

//

fun JsonArrayLike.toJsonArray(): JsonArray {
    return JsonArray(toList())
}

fun JsonObjectLike.toJsonObject(): JsonObject {
    return JsonObject(toMap())
}

//

inline val String?.json: JsonPrimitive get() = JsonPrimitive(this)

inline val Number?.json: JsonPrimitive get() = JsonPrimitive(this)

inline val Boolean?.json: JsonPrimitive get() = JsonPrimitive(this)

inline val Nothing?.json: JsonPrimitive get() = JsonNull
