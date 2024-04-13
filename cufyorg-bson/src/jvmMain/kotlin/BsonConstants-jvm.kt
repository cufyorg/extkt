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

/* ============= ------------------ ============= */

/**
 * Return the java version of this bson element.
 */
inline val BsonBoolean.java: JavaBsonBoolean
    get() = when (this) {
        is BsonBoolean.True -> JavaBsonBoolean.TRUE
        is BsonBoolean.False -> JavaBsonBoolean.FALSE
    }

/**
 * Return the kotlin version of this bson element.
 */
inline val JavaBsonBoolean.kt: BsonBoolean
    get() = BsonBoolean(value)

/**
 * Return the java version of this bson element.
 */
@Suppress("UnusedReceiverParameter")
inline val BsonUndefined.java: JavaBsonUndefined
    get() = JavaBsonUndefined()

/**
 * Return the kotlin version of this bson element.
 */
@Suppress("UnusedReceiverParameter")
inline val JavaBsonUndefined.kt: BsonUndefined
    get() = BsonUndefined

/**
 * Return the java version of this bson element.
 */
@Suppress("UnusedReceiverParameter")
inline val BsonNull.java: JavaBsonNull
    get() = JavaBsonNull.VALUE

/**
 * Return the kotlin version of this bson element.
 */
@Suppress("UnusedReceiverParameter")
inline val JavaBsonNull.kt: BsonNull
    get() = BsonNull

/* ============= ------------------ ============= */
