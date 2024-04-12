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
@file:Suppress("NOTHING_TO_INLINE")

package org.cufy.bson

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/* ============= ------------------ ============= */

/**
 * An id wrapper.
 *
 * @author LSafer
 * @since 2.0.0
 */
@Suppress("unused")
@Serializable(IDSerializer::class)
data class ID<T>(val value: String) : CharSequence by value {
    constructor() : this(ObjectId())
    constructor(value: ObjectId) : this(value.toHexString())
    constructor(value: ID<*>) : this(value.value)

    override fun toString(): String = value
}

/**
 * A typealias for generic-less [ID].
 *
 * @author LSafer
 * @since 2.0.0
 */
typealias AnyID = ID<out Any?>

/* ============= ------------------ ============= */

/**
 * Return the best fitting native wrapper for
 * this id.
 * A [BsonObjectId] if it is a valid object id
 * and a [BsonString] if it is not.
 *
 * @since 2.0.0
 */
val AnyID.bson: BsonElement
    get() {
        if (ObjectId.isValid(value))
            return BsonObjectId(ObjectId(value))

        return BsonString(value)
    }

/**
 * Return the best fitting native wrapper for
 * this id.
 * A [BsonObjectId] if it is a valid object id
 * and a [BsonString] if it is not
 * or [BsonNull] if this is `null`.
 *
 * @since 2.0.0
 */
@get:JvmName("bson_nullable")
val AnyID?.bson: BsonElement get() = this?.let { bson } ?: null.bson

/* ============= ------------------ ============= */

/**
 * Return an [ObjectId] with the value of this.
 *
 * @throws IllegalArgumentException if the string is not a valid hex string representation of an ObjectId.
 * @since 2.0.0
 */
fun AnyID.toObjectId(): ObjectId {
    return ObjectId(value)
}

/**
 * Return an [ObjectId] with the value of this.
 * Or `null` if the value is not a valid [ObjectId].
 *
 * @since 2.0.0
 */
fun AnyID.toObjectIdOrNull(): ObjectId? {
    if (ObjectId.isValid(value))
        return ObjectId(value)

    return null
}

/**
 * Return an [ID] instance from the value of this.
 */
inline fun <T> String.toID(): ID<T> {
    return ID(this)
}

/**
 * Return an [AnyId] instance from the value of this.
 */
@JvmName("toAnyId")
inline fun String.toID(): AnyID {
    return AnyID(this)
}

/**
 * Return an [ID] instance from the value of this.
 */
inline fun <T> ObjectId.toID(): ID<T> {
    return ID(this)
}

/**
 * Return an [AnyId] instance from the value of this.
 */
@JvmName("toAnyId")
inline fun ObjectId.toID(): AnyID {
    return AnyID(this)
}

/**
 * Cast this [ID] to an id of [T].
 */
inline fun <T> AnyID.toID(): ID<T> {
    return ID(this)
}

/**
 * Cast this [ID] to [AnyId].
 */
@JvmName("toAnyId")
inline fun AnyID.toID(): AnyID {
    return AnyID(this)
}

/* ============= ------------------ ============= */

/**
 * The serializer for [ID].
 *
 * @author LSafer
 * @since 2.0.0
 */
internal object IDSerializer : KSerializer<ID<*>> {
    override val descriptor = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: ID<*>) {
        encoder.encodeInline(descriptor).encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): ID<*> {
        return ID<Any?>(decoder.decodeInline(descriptor).decodeString())
    }
}

/* ============= ------------------ ============= */
