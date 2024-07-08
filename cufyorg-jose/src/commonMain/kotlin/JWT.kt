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
package org.cufy.jose

import kotlinx.serialization.json.JsonObject
import org.cufy.json.MutableJsonObjectLike
import org.cufy.json.toJsonObject

/* ============= ------------------ ============= */

/**
 * The components of a decoded JWT.
 */
data class JWT(
    /**
     * The header claims.
     */
    val header: JsonObject,
    /**
     * The payload claims.
     */
    val payload: JsonObject,
)

/* ============= ------------------ ============= */

open class JWTBuilder {
    val header: MutableJsonObjectLike = mutableMapOf()
    val payload: MutableJsonObjectLike = mutableMapOf()

    fun build(): JWT {
        return JWT(
            header = this.header.toJsonObject(),
            payload = this.payload.toJsonObject(),
        )
    }
}

/**
 * Construct a new [JWT] using the given builder [block].
 */
fun JWT(block: JWTBuilder.() -> Unit): JWT {
    return JWTBuilder().apply(block).build()
}

/* ============= ------------------ ============= */
