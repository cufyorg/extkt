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
import org.jose4j.jwe.JsonWebEncryption

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun JWT.jose4j_encrypt(jwk: Jose4jJWK): CompactJWE {
    val alg = header["alg"]?.asStringOrNull
        ?: defaultEncAlg(jwk.kty, jwk.use, jwk.alg)
    val enc = header["enc"]?.asStringOrNull
        ?: defaultEncEnc(jwk.kty, jwk.use, jwk.alg)

    val jose4j = JsonWebEncryption()
    jose4j.apply(this)
    jose4j.key = jwk.java.key
    jose4j.setHeader("kid", jwk.kid)
    jose4j.setHeader("alg", alg)
    jose4j.setHeader("enc", enc)

    return jose4j.encryptToCompactJWE()
}

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun CompactJWE.jose4j_decrypt(jwk: Jose4jJWK): JWT {
    val jose4j = JsonWebEncryption()
    jose4j.apply(this)
    jose4j.key = jwk.java.key

    return jose4j.toJWT()
}

/* ============= ------------------ ============= */
