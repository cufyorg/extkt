/*
 *	Copyright 2022-2023 cufy.org and meemer.com
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
package org.cufy.bson

import java.math.BigDecimal

/* ============= ------------------ ============= */

/**
 * Return the java version of this bson element.
 */
val BsonInt32.java: JavaBsonInt32
    get() = JavaBsonInt32(value)

/**
 * Return the kotlin version of this bson element.
 */
val JavaBsonInt32.kt: BsonInt32
    get() = BsonInt32(value)

/**
 * Return the java version of this bson element.
 */
val BsonInt64.java: JavaBsonInt64
    get() = JavaBsonInt64(value)

/**
 * Return the kotlin version of this bson element.
 */
val JavaBsonInt64.kt: BsonInt64
    get() = BsonInt64(value)

/**
 * Return the java version of this bson element.
 */
val BsonDouble.java: JavaBsonDouble
    get() = JavaBsonDouble(value)

/**
 * Return the kotlin version of this bson element.
 */
val JavaBsonDouble.kt: BsonDouble
    get() = BsonDouble(value)

/**
 * Return the java version of this bson element.
 */
val BsonDecimal128.java: JavaBsonDecimal128
    get() = JavaBsonDecimal128(value.java)

/**
 * Return the kotlin version of this bson element.
 */
val JavaBsonDecimal128.kt: BsonDecimal128
    get() = BsonDecimal128(value.kt)

/* ============= ------------------ ============= */

/**
 * Return a [BsonDecimal128] with the value of this.
 */
val BigDecimal.bson: BsonDecimal128 get() = toDecimal128().bson

/**
 * Return a [BsonDecimal128] with the value of this or [BsonNull] if this is `null`.
 */
val BigDecimal?.bson: BsonElement get() = this?.let { bson } ?: null.bson

/* ============= ------------------ ============= */
