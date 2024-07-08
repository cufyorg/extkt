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

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import org.cufy.json.asJsonArrayOrNull
import org.cufy.json.asJsonObjectOrNull
import org.cufy.json.decodeJsonString
import org.cufy.json.decodeJsonStringOrNull
import org.jose4j.jwk.JsonWebKeySet
import org.jose4j.lang.JoseException

/* ============= ------------------ ============= */

typealias Jose4jJWKSet = Set<Jose4jJWK>

/* ============= ------------------ ============= */

fun String.decodeJose4jJWKSet(): Jose4jJWKSet {
    val jose4jSet = try {
        JsonWebKeySet(this).jsonWebKeys
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
    val parametersSet = try {
        decodeJsonString()
    } catch (e: SerializationException) {
        throw IllegalArgumentException(e)
    }

    if (parametersSet !is JsonObject)
        throw IllegalArgumentException("Bad JWKSet. Expected JsonObject")

    val parametersSetKeys = parametersSet["keys"]

    if (parametersSetKeys !is JsonArray)
        throw IllegalArgumentException("Bad JWKSet keys. Expected JsonArray")

    val count = parametersSetKeys.size
    return buildSet(count) {
        for (i in 0..<count) {
            val jose4j = jose4jSet[i]
            val parameters = parametersSetKeys[i]

            if (parameters !is JsonObject)
                throw IllegalArgumentException("Bad JWK. Expected JsonObject")

            this += Jose4jJWK(
                java = jose4j,
                parameters = parameters,
            )
        }
    }
}

fun String.decodeJose4jJWKSetOrNull(): Jose4jJWKSet? {
    val jose4jSet = try {
        JsonWebKeySet(this).jsonWebKeys
    } catch (e: JoseException) {
        return null
    }
    val parametersSet = decodeJsonStringOrNull()
        ?.asJsonObjectOrNull
        ?: return null

    val parametersSetKeys = parametersSet["keys"]
        ?.asJsonArrayOrNull
        ?: return null

    val count = parametersSetKeys.size
    return buildSet(count) {
        for (i in 0..<count) {
            val jose4j = jose4jSet[i]
            val parameters = parametersSetKeys[i]
                .asJsonObjectOrNull
                ?: return null

            this += Jose4jJWK(
                java = jose4j,
                parameters = parameters,
            )
        }
    }
}

/* ============= ------------------ ============= */
