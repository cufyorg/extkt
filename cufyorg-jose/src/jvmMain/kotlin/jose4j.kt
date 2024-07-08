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
import kotlinx.serialization.json.*
import org.cufy.json.decodeJsonString
import org.cufy.json.encodeToJsonString
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.JsonWebKeySet
import org.jose4j.jws.JsonWebSignature
import org.jose4j.jwx.Headers
import org.jose4j.jwx.JsonWebStructure
import org.jose4j.lang.JoseException
import java.math.BigDecimal
import java.math.BigInteger

/* ============= ------------------ ============= */

fun JsonWebStructure.apply(jwt: JWT) {
    this.headers.apply(jwt.header)
    this.payload = jwt.payload.encodeToJsonString()
}

fun JsonWebSignature.apply(compact: CompactJWS) {
    try {
        this.compactSerialization = compact.value
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
}

fun JsonWebEncryption.apply(compact: CompactJWE) {
    try {
        this.compactSerialization = compact.value
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
}

fun JsonWebStructure.toJWT(): JWT {
    val header = this.headers.toJsonObject()
    val payload = try {
        this.payload.decodeJsonString()
    } catch (e: SerializationException) {
        throw IllegalArgumentException(e)
    }

    if (payload !is JsonObject)
        throw IllegalArgumentException("Bad JWT payload. Expected JsonObject")

    // return
    return JWT(
        header = header,
        payload = payload,
    )
}

fun JsonWebSignature.signToCompactJWS(): CompactJWS {
    val iterator = try {
        this.compactSerialization.split(".").iterator()
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
    return CompactJWS(
        header = iterator.next(),
        payload = iterator.next(),
        signature = iterator.next(),
    )
}

fun JsonWebEncryption.encryptToCompactJWE(): CompactJWE {
    val iterator = try {
        this.compactSerialization.split(".").iterator()
    } catch (e: JoseException) {
        throw IllegalArgumentException(e)
    }
    return CompactJWE(
        header = iterator.next(),
        encryptedKey = iterator.next(),
        initializationVector = iterator.next(),
        ciphertext = iterator.next(),
        authenticationTag = iterator.next(),
    )
}

fun JsonWebKey.toJWK(): JWK {
    val level = JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE
    val parameters = toParams(level).jose4j_toJsonElement()
    return Jose4jJWK(this, parameters)
}

fun JsonWebKeySet.toJWKSet(): JWKSet {
    return jsonWebKeys.mapTo(mutableSetOf()) { it.toJWK() }
}

/* ============= ------------------ ============= */

fun Headers.toJsonObject(): JsonObject {
    return fullHeaderAsJsonString
        .decodeJsonString()
        .jsonObject
}

fun Headers.apply(element: JsonObject) {
    for ((name, value) in element) {
        setObjectHeaderValue(name, value.jose4j_toJavaObject())
    }
}

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun JsonObject.jose4j_toJavaObject(): Map<String, *> {
    return mapValues { it.value.jose4j_toJavaObject() }
}

@Suppress("FunctionName")
internal fun JsonArray.jose4j_toJavaObject(): List<*> {
    return map { it.jose4j_toJavaObject() }
}

@Suppress("FunctionName")
internal fun JsonElement.jose4j_toJavaObject(): Any? {
    return when (this) {
        is JsonNull -> null
        is JsonPrimitive -> when {
            isString -> content
            content == "true" -> true
            content == "false" -> false
            '.' in content -> doubleOrNull ?: BigDecimal(content)
            else -> longOrNull ?: BigInteger(content)
        }

        is JsonArray -> jose4j_toJavaObject()
        is JsonObject -> jose4j_toJavaObject()
    }
}

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun Map<*, *>.jose4j_toJsonElement(): JsonObject {
    return JsonObject(entries.associate { "$it" to it.value.jose4j_toJsonElement() })
}

@Suppress("FunctionName")
internal fun List<*>.jose4j_toJsonElement(): JsonArray {
    return JsonArray(map { it.jose4j_toJsonElement() })
}

@Suppress("FunctionName")
internal fun Any?.jose4j_toJsonElement(): JsonElement {
    return when (this) {
        null -> JsonNull
        is CharSequence -> JsonPrimitive(toString())
        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is List<*> -> jose4j_toJsonElement()
        is Map<*, *> -> jose4j_toJsonElement()
        else -> error("Couldn't convert ${this::class} to JsonElement")
    }
}

/* ============= ------------------ ============= */
