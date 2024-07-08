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
import kotlinx.serialization.json.JsonObject
import org.cufy.json.asJsonObjectOrNull
import org.cufy.json.decodeJsonString
import org.cufy.json.decodeJsonStringOrNull
import org.jose4j.jwk.JsonWebKey
import org.jose4j.lang.JoseException

/* ============= ------------------ ============= */

data class Jose4jJWK(
    val java: JsonWebKey,
    override val parameters: JsonObject,
) : JWK {
    override val kty: String = java.keyType
    override val use: String? = java.use
    override val kid: String? = java.keyId
    override val alg: String? = java.algorithm
    override val keyOps: List<String>? = java.keyOps
}

/* ============= ------------------ ============= */

fun createJose4jJWK(source: JsonObject): Jose4jJWK {
    val parametersJava = source.jose4j_toJavaObject()
    val java = try {
        JsonWebKey.Factory.newJwk(parametersJava)
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
    return Jose4jJWK(
        java = java,
        parameters = source,
    )
}

fun createJose4jJWKOrNull(source: JsonObject): Jose4jJWK? {
    val parametersJava = source.jose4j_toJavaObject()
    val java = try {
        JsonWebKey.Factory.newJwk(parametersJava)
    } catch (_: JoseException) {
        return null
    }
    return Jose4jJWK(
        java = java,
        parameters = source,
    )
}

/* ============= ------------------ ============= */

fun String.decodeJose4jJWK(): Jose4jJWK {
    val parameters = try {
        decodeJsonString()
    } catch (e: SerializationException) {
        throw IllegalArgumentException(e)
    }

    if (parameters !is JsonObject)
        throw IllegalArgumentException("Bad JWK. Expected JsonObject")

    val parametersJava = parameters.jose4j_toJavaObject()
    val java = try {
        JsonWebKey.Factory.newJwk(parametersJava)
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }

    return Jose4jJWK(
        java = java,
        parameters = parameters,
    )
}

fun String.decodeJose4jJWKOrNull(): JWK? {
    val parameters = decodeJsonStringOrNull()
        ?.asJsonObjectOrNull
        ?: return null
    val parametersJava = parameters.jose4j_toJavaObject()
    val java = try {
        JsonWebKey.Factory.newJwk(parametersJava)
    } catch (_: JoseException) {
        return null
    }
    return Jose4jJWK(
        java = java,
        parameters = parameters,
    )
}

/* ============= ------------------ ============= */
