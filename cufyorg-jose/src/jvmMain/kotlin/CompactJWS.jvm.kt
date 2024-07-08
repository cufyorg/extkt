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

import org.cufy.json.asStringOrNull

/* ============= ------------------ ============= */

actual fun JWT.sign(jwks: JWKSet): CompactJWS {
    val kid = header["kid"]?.asStringOrNull
    val alg = header["alg"]?.asStringOrNull

    val jwk = jwks.findSign(kid, alg)
    jwk ?: throw IllegalArgumentException("jws signing failed: no matching key: kid=$kid; alg=$alg")

    return when (jwk) {
        is Jose4jJWK -> jose4j_sign(jwk)
    }
}

/* ============= ------------------ ============= */

actual fun CompactJWS.verify(jwks: Set<JWK>): JWT {
    val h = this.decodedHeaderOrNull

    val kid = h?.get("kid")?.asStringOrNull
    val alg = h?.get("alg")?.asStringOrNull

    if (alg == "none")
        return decode()

    val jwk = jwks.findVerify(kid, alg)
    jwk ?: throw IllegalArgumentException("jws verification failed: no matching key: kid=$kid; alg=$alg")

    return when (jwk) {
        is Jose4jJWK -> jose4j_verify(jwk)
    }
}

/* ============= ------------------ ============= */

actual fun CompactJWS.decode(): JWT {
    return jose4j_decode()
}

/* ============= ------------------ ============= */
