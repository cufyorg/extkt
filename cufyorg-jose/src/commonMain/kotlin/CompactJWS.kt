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

import org.cufy.json.asJsonObjectOrNull
import org.cufy.json.decodeJsonOrNull
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/* ============= ------------------ ============= */

/**
 * The components of a JWS Compact Serialization.
 *
 * [RFC7515-7.1](https://datatracker.ietf.org/doc/html/rfc7515#section-7.1)
 */
data class CompactJWS(
    /**
     * `BASE64URL(UTF8(JWS Protected Header))`
     */
    override val header: String,
    /**
     * `BASE64URL(JWS Payload)`
     */
    val payload: String,
    /**
     * `BASE64URL(JWS Signature)`
     */
    val signature: String,
) : CompactJWT() {
    /**
     * The components seperated with periods ('.').
     *
     * ```
     * BASE64URL(UTF8(JWS Protected Header)) || '.' ||
     * BASE64URL(JWS Payload) || '.' ||
     * BASE64URL(JWS Signature)
     * ```
     */
    override val value by lazy {
        buildString {
            append(header)
            append('.')
            append(payload)
            append('.')
            append(signature)
        }
    }

    /**
     * The decoded payload of the jwt.
     */
    @OptIn(ExperimentalEncodingApi::class)
    val decodedPayloadOrNull by lazy {
        Base64.UrlSafe.decode(payload)
            .decodeToString()
            .decodeJsonOrNull()
            ?.asJsonObjectOrNull
    }
}

/* ============= ------------------ ============= */

/**
 * Split this string into JWS Compact Serialization Components.
 */
fun String.decodeCompactJWSCatching(): Result<CompactJWS> {
    return decodeCompactJWSOrNull()
        ?.let { success(it) }
        ?: failure(IllegalArgumentException("Malformed JWS was presented"))
}

/**
 * Split this string into JWS Compact Serialization Components.
 *
 * If decode fails, throw an [IllegalArgumentException].
 */
fun String.decodeCompactJWS(): CompactJWS {
    return decodeCompactJWSCatching().getOrThrow()
}

/**
 * Split this string into JWS Compact Serialization Components.
 *
 * If decode fails, return `null`.
 */
fun String.decodeCompactJWSOrNull(): CompactJWS? {
    val segments = splitToSequence('.').iterator()
    return CompactJWS(
        header = if (segments.hasNext()) segments.next() else return null,
        payload = if (segments.hasNext()) segments.next() else return null,
        signature = if (segments.hasNext()) segments.next() else return null,
    )
}

/**
 * Using the number of periods ('.') in this string,
 * determine if this string is JWS Compact Serialization.
 */
fun String.isCompactJWSQuick(): Boolean {
    return 2 == count { it == '.' }
}

/* ============= ------------------ ============= */

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 */
expect fun JWT.signCatching(jwks: JWKSet): Result<CompactJWS>

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 *
 * If signing fails, throw an [IllegalArgumentException].
 */
fun JWT.sign(jwks: JWKSet): CompactJWS {
    return signCatching(jwks).getOrThrow()
}

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 *
 * If signing fails, return `null`.
 */
fun JWT.signOrNull(jwks: JWKSet): CompactJWS? {
    return signCatching(jwks).getOrNull()
}

/* ============= ------------------ ============= */

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 */
fun JWT.signToStringCatching(jwks: JWKSet): Result<String> {
    return signCatching(jwks).map { it.value }
}

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 *
 * If signing fails, throw an [IllegalArgumentException].
 */
fun JWT.signToString(jwks: JWKSet): String {
    return sign(jwks).value
}

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 *
 * If signing fails, return `null`.
 */
fun JWT.signToStringOrNull(jwks: JWKSet): String? {
    return signOrNull(jwks)?.value
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
expect fun CompactJWS.verifyCatching(jwks: JWKSet): Result<JWT>

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, throw an [IllegalArgumentException].
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun CompactJWS.verify(jwks: JWKSet): JWT {
    return verifyCatching(jwks).getOrThrow()
}

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, return `null`.
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun CompactJWS.verifyOrNull(jwks: JWKSet): JWT? {
    return verifyCatching(jwks).getOrNull()
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun String.verifyCompactJWSCatching(jwks: JWKSet): Result<JWT> {
    return decodeCompactJWSCatching().fold(
        { it.verifyCatching(jwks) },
        { failure(it) }
    )
}

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, throw an [IllegalArgumentException].
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun String.verifyCompactJWS(jwks: JWKSet): JWT {
    return decodeCompactJWS().verify(jwks)
}

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, return `null`.
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun String.verifyCompactJWSOrNull(jwks: JWKSet): JWT? {
    return decodeCompactJWSOrNull()?.verifyOrNull(jwks)
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, without signature verification.
 */
expect fun CompactJWS.unverifiedCatching(): Result<JWT>

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, throw an [IllegalArgumentException].
 */
fun CompactJWS.unverified(): JWT {
    return unverifiedCatching().getOrThrow()
}

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, return `null`.
 */
fun CompactJWS.unverifiedOrNull(): JWT? {
    return unverifiedCatching().getOrNull()
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, throw an [IllegalArgumentException].
 */
fun String.unverifiedCompactJWSCatching(): Result<JWT> {
    return decodeCompactJWSCatching().fold(
        { it.unverifiedCatching() },
        { failure(it) }
    )
}

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, throw an [IllegalArgumentException].
 */
fun String.unverifiedCompactJWS(): JWT {
    return decodeCompactJWS().unverified()
}

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, return `null`.
 */
fun String.unverifiedCompactJWSOrNull(): JWT? {
    return decodeCompactJWSOrNull()?.unverifiedOrNull()
}

/* ============= ------------------ ============= */
