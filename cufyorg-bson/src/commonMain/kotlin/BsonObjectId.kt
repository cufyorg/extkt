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
 * A representation of the BSON ObjectId type.
 *
 * @see org.bson.BsonObjectId
 * @since 2.0.0
 */
data class BsonObjectId(val value: ObjectId = ObjectId()) : BsonElement {
    override val type: BsonType get() = BsonType.ObjectId

    override fun equals(other: Any?) =
        other is BsonObjectId && other.value == value

    override fun hashCode(): Int =
        value.hashCode()

    override fun toString(): String =
        """ObjectId("$value")"""
}

/* ============= ------------------ ============= */

/**
 * Return a [BsonObjectId] with the value of this.
 */
inline val ObjectId.bson: BsonObjectId get() = BsonObjectId(this)

/**
 * Return a [BsonObjectId] with the value of this or [BsonNull] if this is `null`.
 */
val ObjectId?.bson: BsonElement get() = this?.let { bson } ?: null.bson

/* ============= ------------------ ============= */
