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
}

/* ============= ------------------ ============= */

/**
 * Split this string into JWS Compact Serialization Components.
 *
 * If decode fails, throw an [IllegalArgumentException].
 */
fun String.decodeCompactJWS(): CompactJWS {
    return decodeCompactJWSOrNull()
        ?: throw IllegalArgumentException("Malformed JWS was presented")
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
 *
 * If signing fails, throw an [IllegalArgumentException].
 */
expect fun JWT.sign(jwks: JWKSet): CompactJWS

/**
 * Find suitable key in [jwks], sign JWT components
 * and return JWS components.
 *
 * If signing fails, return `null`.
 */
fun JWT.signOrNull(jwks: JWKSet): CompactJWS? {
    return try {
        sign(jwks)
    } catch (_: IllegalArgumentException) {
        return null
    }
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, throw an [IllegalArgumentException].
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
expect fun CompactJWS.verify(jwks: JWKSet): JWT

/**
 * Decode JWS components, find matching key in [jwks],
 * verify signature and return JWT components.
 *
 * If verification fails, return `null`.
 *
 * > If the algorithm is set to `none`, no verification will be done.
 */
fun CompactJWS.verifyOrNull(jwks: JWKSet): JWT? {
    return try {
        verify(jwks)
    } catch (_: IllegalArgumentException) {
        return null
    }
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, throw an [IllegalArgumentException].
 */
expect fun CompactJWS.decode(): JWT

/**
 * Decode JWS components, without signature verification.
 *
 * If decoding fails, return `null`.
 */
fun CompactJWS.decodeOrNull(): JWT? {
    return try {
        decode()
    } catch (_: IllegalArgumentException) {
        return null
    }
}

/* ============= ------------------ ============= */
