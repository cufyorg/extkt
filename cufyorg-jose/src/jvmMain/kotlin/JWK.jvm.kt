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

/* ============= ------------------ ============= */

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual sealed interface JWK {
    actual val parameters: JsonObject

    actual val kty: String
    actual val use: String?
    actual val kid: String?
    actual val alg: String?
    actual val keyOps: List<String>?
}

/* ============= ------------------ ============= */

actual fun createJWK(parameters: JsonObject): JWK {
    return createJose4jJWK(parameters)
}

actual fun createJWKOrNull(parameters: JsonObject): JWK? {
    return createJose4jJWKOrNull(parameters)
}

/* ============= ------------------ ============= */

actual fun String.decodeJWK(): JWK {
    return decodeJose4jJWK()
}

actual fun String.decodeJWKOrNull(): JWK? {
    return decodeJose4jJWKOrNull()
}

/* ============= ------------------ ============= */
