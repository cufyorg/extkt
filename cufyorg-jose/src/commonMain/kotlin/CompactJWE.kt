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
 * The components of a JWE Compact Serialization.
 *
 * [RFC7516-7.1](https://www.rfc-editor.org/rfc/rfc7516#section-7.1)
 */
data class CompactJWE(
    /**
     * `BASE64URL(UTF8(JWE Protected Header))`
     */
    override val header: String,
    /**
     * `BASE64URL(JWE Encrypted Key)`
     */
    val encryptedKey: String,
    /**
     * `BASE64URL(JWE Initialization Vector)`
     */
    val initializationVector: String,
    /**
     * `BASE64URL(JWE Ciphertext)`
     */
    val ciphertext: String,
    /**
     * `BASE64URL(JWE Authentication Tag)`
     */
    val authenticationTag: String,
) : CompactJWT() {
    /**
     * The components seperated with periods ('.').
     *
     * ```
     * BASE64URL(UTF8(JWE Protected Header)) || '.' ||
     * BASE64URL(JWE Encrypted Key) || '.' ||
     * BASE64URL(JWE Initialization Vector) || '.' ||
     * BASE64URL(JWE Ciphertext) || '.' ||
     * BASE64URL(JWE Authentication Tag)
     * ```
     */
    override val value by lazy {
        buildString {
            append(header)
            append('.')
            append(encryptedKey)
            append('.')
            append(initializationVector)
            append('.')
            append(ciphertext)
            append('.')
            append(authenticationTag)
        }
    }
}

/* ============= ------------------ ============= */

/**
 * Split this string into JWE Compact Serialization Components.
 *
 * If decode fails, throw an [IllegalArgumentException].
 */
fun String.decodeCompactJWE(): CompactJWE {
    return decodeCompactJWEOrNull()
        ?: throw IllegalArgumentException("Malformed JWE was presented")
}

/**
 * Split this string into JWE Compact Serialization Components.
 *
 * If decode fails, return `null`.
 */
fun String.decodeCompactJWEOrNull(): CompactJWE? {
    val segments = splitToSequence('.').iterator()
    return CompactJWE(
        header = if (segments.hasNext()) segments.next() else return null,
        encryptedKey = if (segments.hasNext()) segments.next() else return null,
        initializationVector = if (segments.hasNext()) segments.next() else return null,
        ciphertext = if (segments.hasNext()) segments.next() else return null,
        authenticationTag = if (segments.hasNext()) segments.next() else return null,
    )
}

/**
 * Using the number of periods ('.') in this string,
 * determine if this string is JWE Compact Serialization.
 */
fun String.isCompactJWEQuick(): Boolean {
    return 4 == count { it == '.' }
}

/* ============= ------------------ ============= */

/**
 * Find suitable key in [jwks], encrypt JWT components
 * and return JWE components.
 *
 * If signing fails, throw an [IllegalArgumentException].
 */
expect fun JWT.encrypt(jwks: JWKSet): CompactJWE

/**
 * Find suitable key in [jwks], encrypt JWT components
 * and return JWE components.
 *
 * If signing fails, return `null`.
 */
fun JWT.encryptOrNull(jwks: JWKSet): CompactJWE? {
    return try {
        encrypt(jwks)
    } catch (_: IllegalArgumentException) {
        return null
    }
}

/* ============= ------------------ ============= */

/**
 * Decode JWS components, find matching key in [jwks],
 * decode payload and return JWT components.
 *
 * If decryption fails, throw an [IllegalArgumentException].
 */
expect fun CompactJWE.decrypt(jwks: JWKSet): JWT

/**
 * Decode JWS components, find matching key in [jwks],
 * decode payload and return JWT components.
 *
 * If decryption fails, return `null`.
 */
fun CompactJWE.decryptOrNull(jwks: JWKSet): JWT? {
    return try {
        decrypt(jwks)
    } catch (_: IllegalArgumentException) {
        return null
    }
}

/* ============= ------------------ ============= */
