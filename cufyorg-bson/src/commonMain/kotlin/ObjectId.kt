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
@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.cufy.bson

/* ============= ------------------ ============= */

/**
 * A globally unique identifier for objects.
 */
expect class ObjectId : Comparable<ObjectId> {
    /**
     * Create a new object id.
     */
    constructor()

    /**
     * Creates an ObjectId using the given time and counter.
     *
     * @param timestamp the time in seconds
     * @param counter   the counter
     * @throws IllegalArgumentException if the high order byte of counter is not zero
     */
    constructor(timestamp: Int, counter: Int)

    /**
     * Constructs a new instance from a 24-byte hexadecimal string representation.
     *
     * @param hexString the string to convert
     * @throws IllegalArgumentException if the string is not a valid hex string representation of an ObjectId
     */
    constructor(hexString: String)

    /**
     * Constructs a new instance from the given byte array
     *
     * @param bytes the byte array
     * @throws IllegalArgumentException if array is null or not of length 12
     */
    constructor(bytes: ByteArray)

    companion object {
        /**
         * Gets a new object id.
         */
        fun get(): ObjectId

        /**
         * Checks if a string could be an {@code ObjectId}.
         *
         * @param hexString a potential ObjectId as a String.
         * @return whether the string could be an object id
         * @throws IllegalArgumentException if hexString is null
         */
        fun isValid(hexString: String): Boolean
    }

    /**
     * Gets the timestamp (number of seconds since the Unix epoch).
     */
    val timestamp: Int

    /**
     * Convert to a byte array.
     *
     * Note that the numbers are stored in big-endian order.
     *
     * @return the byte array
     */
    fun toByteArray(): ByteArray

    /**
     * Converts this instance into a 24-byte hexadecimal string representation.
     */
    fun toHexString(): String
}

/* ============= ------------------ ============= */

/**
 * Return an [ObjectId] with the value of this.
 *
 * @throws IllegalArgumentException if the string is not a valid hex string representation of an ObjectId.
 * @since 2.0.0
 */
fun String.toObjectId(): ObjectId {
    return ObjectId(this)
}

/**
 * Return an [ObjectId] with the value of this.
 * Or `null` if the value is not a valid [ObjectId].
 *
 * @since 2.0.0
 */
fun String.toObjectIdOrNull(): ObjectId? {
    if (ObjectId.isValid(this))
        return ObjectId(this)

    return null
}

/* ============= ------------------ ============= */
