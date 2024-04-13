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

import java.math.BigDecimal

/* ============= ------------------ ============= */

@Suppress("NOTHING_TO_INLINE", "OVERRIDE_BY_INLINE")
actual class Decimal128(val java: JavaDecimal128) : Number(), Comparable<Decimal128> {
    //

    actual constructor(value: Long) :
            this(JavaDecimal128(value))

    //

    /**
     * Constructs a Decimal128 value representing the given BigDecimal.
     *
     * @param value the Decimal128 value represented as a BigDecimal
     * @throws NumberFormatException if the value is out of the Decimal128 range
     */
    constructor(value: BigDecimal) :
            this(JavaDecimal128(value))

    //

    actual companion object {
        @Suppress("ConstPropertyName")
        private const val serialVersionUID: Long = 4570973266503637887L

        actual val POSITIVE_INFINITY: Decimal128 =
            JavaDecimal128.POSITIVE_INFINITY.kt
        actual val NEGATIVE_INFINITY: Decimal128 =
            JavaDecimal128.NEGATIVE_INFINITY.kt
        actual val NEGATIVE_NaN: Decimal128 =
            JavaDecimal128.NEGATIVE_NaN.kt
        actual val NaN: Decimal128 =
            JavaDecimal128.NaN.kt
        actual val POSITIVE_ZERO: Decimal128 =
            JavaDecimal128.POSITIVE_ZERO.kt
        actual val NEGATIVE_ZERO: Decimal128 =
            JavaDecimal128.NEGATIVE_ZERO.kt

        actual inline fun parse(value: String): Decimal128 =
            JavaDecimal128.parse(value).kt

        actual inline fun fromIEEE754BIDEncoding(high: Long, low: Long): Decimal128 =
            JavaDecimal128.fromIEEE754BIDEncoding(high, low).kt
    }

    //

    actual inline val high: Long get() = java.high

    actual inline val low: Long get() = java.low

    actual inline val isNegative: Boolean get() = java.isNegative

    actual inline val isInfinite: Boolean get() = java.isInfinite

    actual inline val isFinite: Boolean get() = java.isFinite

    actual inline val isNaN: Boolean get() = java.isNaN

    //

    /**
     * Return a [BigDecimal] with the value of this.
     */
    inline fun toBigDecimal(): BigDecimal =
        java.bigDecimalValue()

    //

    override inline fun toByte(): Byte =
        java.toByte()

    override inline fun toInt(): Int =
        java.toInt()

    override inline fun toFloat(): Float =
        java.toFloat()

    override inline fun toDouble(): Double =
        java.toDouble()

    override inline fun toLong(): Long =
        java.toLong()

    override inline fun toShort(): Short =
        java.toShort()

    override inline fun compareTo(other: Decimal128): Int =
        java.compareTo(other.java)

    override inline fun equals(other: Any?): Boolean =
        other is Decimal128 && java == other.java

    override inline fun hashCode(): Int =
        java.hashCode()

    override inline fun toString(): String =
        java.toString()
}

/* ============= ------------------ ============= */

/**
 * Return the kotlin version of this decimal128.
 */
val JavaDecimal128.kt: Decimal128
    get() = Decimal128(this)

/* ============= ------------------ ============= */

/**
 * Return a [Decimal128] with the value of this.
 */
fun BigDecimal.toDecimal128(): Decimal128 {
    return Decimal128(this)
}

actual fun Int.toDecimal128(): Decimal128 {
    return toLong().toDecimal128()
}

actual fun Long.toDecimal128(): Decimal128 {
    return Decimal128(this)
}

actual fun Double.toDecimal128(): Decimal128 {
    return when {
        isNaN() -> Decimal128.NaN
        isInfinite() -> when {
            this > 0 -> Decimal128.POSITIVE_INFINITY
            else -> Decimal128.NEGATIVE_INFINITY
        }

        else -> toBigDecimal().toDecimal128()
    }
}

/* ============= ------------------ ============= */
