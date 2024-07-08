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
import org.jose4j.jwk.PublicJsonWebKey
import org.jose4j.jws.JsonWebSignature

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun JWT.jose4j_sign(jwk: Jose4jJWK): CompactJWS {
    if (jwk.java !is PublicJsonWebKey)
        throw IllegalArgumentException("jwt signing failed: key is not an asymmetric key")

    val alg = header["alg"]?.asStringOrNull
        ?: defaultSigAlg(jwk.kty, jwk.use, jwk.alg)

    val jose4j = JsonWebSignature()
    jose4j.apply(this)
    jose4j.key = jwk.java.privateKey
    jose4j.setHeader("kid", jwk.kid)
    jose4j.setHeader("alg", alg)

    return jose4j.signToCompactJWS()
}

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun CompactJWS.jose4j_verify(jwk: Jose4jJWK): JWT {
    val jose4j = JsonWebSignature()
    jose4j.apply(this)

    jose4j.key = jwk.java.key

    if (!jose4j.verifySignature())
        throw IllegalArgumentException("jws verification failed: invalid signature")

    return jose4j.toJWT()
}

/* ============= ------------------ ============= */

@Suppress("FunctionName")
internal fun CompactJWS.jose4j_decode(): JWT {
    val jose4j = JsonWebSignature()
    jose4j.apply(this)
    return jose4j.toJWT()
}

/* ============= ------------------ ============= */
