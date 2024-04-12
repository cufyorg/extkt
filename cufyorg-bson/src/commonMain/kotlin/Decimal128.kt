/*
 *	Copyright 2022-2023 cufy.org
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
 * A binary integer decimal representation of a 128-bit decimal value, supporting 34 decimal digits of significand and an exponent range
 * of -6143 to +6144.
 *
 * @see <a href="https://github.com/mongodb/specifications/blob/master/source/bson-decimal128/decimal128.rst">BSON Decimal128
 * specification</a>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_Integer_Decimal">binary integer decimal</a>
 * @see <a href="https://en.wikipedia.org/wiki/Decimal128_floating-point_format">decimal128 floating-point format</a>
 * @see <a href="http://ieeexplore.ieee.org/document/4610935/">754-2008 - IEEE Standard for Floating-Point Arithmetic</a>
 */
@Suppress("ConvertSecondaryConstructorToPrimary")
expect class Decimal128 : Number, Comparable<Decimal128> {
    /**
     * Constructs a Decimal128 value representing the given long.
     *
     * @param value the Decimal128 value represented as a long
     */
    constructor(value: Long)

    companion object {
        /**
         * A constant holding the positive infinity of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.valueOf("Infinity")`.
         */
        val POSITIVE_INFINITY: Decimal128

        /**
         * A constant holding the negative infinity of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.parse("-Infinity")`.
         */
        val NEGATIVE_INFINITY: Decimal128

        /**
         * A constant holding a negative Not-a-Number (-NaN) value of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.parse("-NaN")`.
         */
        val NEGATIVE_NaN: Decimal128

        /**
         * A constant holding a Not-a-Number (NaN) value of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.parse("NaN")`.
         */
        val NaN: Decimal128

        /**
         * A constant holding a positive zero value of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.parse("0")`.
         */
        val POSITIVE_ZERO: Decimal128

        /**
         * A constant holding a negative zero value of type [Decimal128].
         *
         * It is equal to the value return by `Decimal128.parse("-0")`.
         */
        val NEGATIVE_ZERO: Decimal128

        /**
         * Returns a Decimal128 value representing the given String.
         *
         * @param value the Decimal128 value represented as a String
         * @return the Decimal128 value representing the given String
         * @throws NumberFormatException if the value is out of the Decimal128 range
         * @see
         * <a href="https://github.com/mongodb/specifications/blob/master/source/bson-decimal128/decimal128.rst#from-string-representation">
         *     From-String Specification</a>
         */
        fun parse(value: String): Decimal128

        /**
         * Create an instance with the given high and low order
         * bits representing this Decimal128 as an IEEE 754-2008
         * 128-bit decimal floating point using the BID encoding
         * scheme.
         *
         * @param high the high-order 64 bits
         * @param low  the low-order 64 bits
         * @return the Decimal128 value representing the given high and low order bits
         */
        fun fromIEEE754BIDEncoding(high: Long, low: Long): Decimal128
    }

    /**
     * Gets the high-order 64 bits of the IEEE 754-2008 128-bit
     * decimal floating point encoding for this Decimal128,
     * using the BID encoding
     * scheme.
     */
    val high: Long

    /**
     * Gets the low-order 64 bits of the IEEE 754-2008 128-bit
     * decimal floating point encoding for this Decimal128,
     * using the BID encoding
     * scheme.
     */
    val low: Long

    /**
     * Returns true if this Decimal128 is negative.
     *
     * @return true if this Decimal128 is negative
     */
    val isNegative: Boolean

    /**
     * Returns true if this Decimal128 is infinite.
     */
    val isInfinite: Boolean

    /**
     * Returns true if this Decimal128 is finite.
     */
    val isFinite: Boolean

    /**
     * Returns true if this Decimal128 is Not-A-Number (NaN).
     */
    val isNaN: Boolean
}

/* ============= ------------------ ============= */

/**
 * Return a [Decimal128] with the value of this.
 */
expect fun Int.toDecimal128(): Decimal128

/**
 * Return a [Decimal128] with the value of this.
 */
expect fun Long.toDecimal128(): Decimal128

/**
 * Return a [Decimal128] with the value of this.
 */
expect fun Double.toDecimal128(): Decimal128

/* ============= ------------------ ============= */
