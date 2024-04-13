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

import java.nio.ByteBuffer
import java.util.*

/* ============= ------------------ ============= */

@Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")
actual class ObjectId(val java: JavaObjectId) : Comparable<ObjectId> {
    //

    actual constructor() :
            this(JavaObjectId())

    actual constructor(timestamp: Int, counter: Int) :
            this(JavaObjectId(timestamp, counter))

    actual constructor(hexString: String) :
            this(JavaObjectId(hexString))

    actual constructor(bytes: ByteArray) :
            this(JavaObjectId(bytes))

    //

    /**
     * Constructs a new instance using the given date.
     *
     * @param date the date
     */
    constructor(date: Date) :
            this(JavaObjectId(date))

    /**
     * Constructs a new instances using the given date and counter.
     *
     * @param date    the date
     * @param counter the counter
     * @throws IllegalArgumentException if the high order byte of counter is not zero
     */
    constructor(date: Date, counter: Int) :
            this(JavaObjectId(date, counter))

    /**
     * Constructs a new instance from the given ByteBuffer
     *
     * @param buffer the ByteBuffer
     * @throws IllegalArgumentException if the buffer is null or does not have at least 12 bytes remaining
     * @since 3.4
     */
    constructor(buffer: ByteBuffer) :
            this(JavaObjectId(buffer))

    //

    actual companion object {
        actual inline fun get(): ObjectId =
            JavaObjectId.get().kt

        actual inline fun isValid(hexString: String): Boolean =
            JavaObjectId.isValid(hexString)

        //

        /**
         * Gets a new object id with the given date value and all other bits zeroed.
         *
         * The returned object id will compare as less than or equal to any other
         * object id within the same second as the given date, and less than any
         * later date.
         */
        inline fun getSmallestWithDate(date: Date): ObjectId =
            JavaObjectId.getSmallestWithDate(date).kt
    }

    //

    actual inline val timestamp: Int get() = java.timestamp

    actual fun toByteArray(): ByteArray =
        java.toByteArray()

    actual fun toHexString(): String =
        java.toHexString()

    //

    /**
     * Gets the timestamp as a [Date] instance.
     */
    inline val date: Date get() = java.date

    /**
     * Convert to a byte array.  Note that the numbers are stored in big-endian order.
     *
     * @return the byte array
     */
    fun putToByteBuffer(buffer: ByteBuffer): Unit =
        java.putToByteBuffer(buffer)

    //

    override inline fun compareTo(other: ObjectId): Int =
        java.compareTo(other.java)

    override inline fun equals(other: Any?): Boolean =
        other is ObjectId && java == other.java

    override inline fun hashCode(): Int =
        java.hashCode()

    override inline fun toString(): String =
        java.toString()
}

/* ============= ------------------ ============= */

/**
 * Return the kotlin version of this objectId.
 */
val JavaObjectId.kt: ObjectId
    get() = ObjectId(this)

/* ============= ------------------ ============= */
