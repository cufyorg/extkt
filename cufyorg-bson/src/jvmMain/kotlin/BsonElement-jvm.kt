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
val BsonElement.java: JavaBsonElement
    get() = when (this) {
        is BsonDocument -> java
        is BsonArray -> java
        is BsonString -> java
        is BsonRegExp -> java
        is BsonObjectId -> java
        is BsonInt32 -> java
        is BsonInt64 -> java
        is BsonDouble -> java
        is BsonDecimal128 -> java
        is BsonDateTime -> java
        is BsonBoolean -> java
        is BsonUndefined -> java
        is BsonNull -> java
    }

/**
 * Return the kotlin version of this bson element.
 */
val JavaBsonElement.kt: BsonElement
    get() = when (this) {
        is JavaBsonDocument -> kt
        is JavaBsonArray -> kt
        is JavaBsonString -> kt
        is JavaBsonRegExp -> kt
        is JavaBsonObjectId -> kt
        is JavaBsonInt32 -> kt
        is JavaBsonInt64 -> kt
        is JavaBsonDouble -> kt
        is JavaBsonDecimal128 -> kt
        is JavaBsonDateTime -> kt
        is JavaBsonBoolean -> kt
        is JavaBsonUndefined -> kt
        is JavaBsonNull -> kt
        else -> error("Unsupported bson element: $bsonType")
    }

/* ============= ------------------ ============= */
