/*
 *	Copyright 2024 cufy.org and meemer.com
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

inline val JsonPrimitive.isBoolean get() = content.isJsonBoolean()

inline val JsonPrimitive.isNumber get() = content.isJsonNumber()

inline val JsonPrimitive.isDouble get() = content.isJsonDouble()

inline val JsonPrimitive.isFloat get() = content.isJsonFloat()

inline val JsonPrimitive.isLong get() = content.isJsonLong()

inline val JsonPrimitive.isInt get() = content.isJsonInt()

inline val JsonPrimitive.isNull get() = this is JsonNull

//

inline val JsonElement.isString get() = this is JsonPrimitive && isString

inline val JsonElement.isBoolean get() = this is JsonPrimitive && isBoolean

inline val JsonElement.isNumber get() = this is JsonPrimitive && isNumber

inline val JsonElement.isDouble get() = this is JsonPrimitive && isDouble

inline val JsonElement.isFloat get() = this is JsonPrimitive && isFloat

inline val JsonElement.isLong get() = this is JsonPrimitive && isLong

inline val JsonElement.isInt get() = this is JsonPrimitive && isInt

inline val JsonElement.isNull get() = this is JsonNull

//

inline val JsonElement.isJsonObject get() = this is JsonObject

inline val JsonElement.isJsonArray get() = this is JsonArray
