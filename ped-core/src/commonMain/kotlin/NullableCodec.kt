/*
 *	Copyright 2023 cufy.org and meemer.com
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
package org.cufy.ped

import kotlin.js.JsName
import kotlin.jvm.JvmName

/* ============= ------------------ ============= */

/**
 * A codec that handles nullable elements.
 *
 * Nullable Codecs MUST handle `null` input and output.
 *
 * @author LSafer
 * @since 2.0.0
 */
interface NullableCodec<I, O> : Codec<I?, O>

/* ============= ------------------ ============= */

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when decoding fails.
 */
@KpedMarker4
infix fun <I, O> NullableCodec<I, O>.defaultIn(defaultValue: I): NullableCodec<I, O> {
    val codec = this
    return object : NullableCodec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I?> {
            return runCatching {
                codec.decode(value).getOrDefault(defaultValue)
            }
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when decoding fails.
 */
@KpedMarker4
infix fun <I, O> NullableCodec<I, O>.catchIn(block: (Throwable) -> I): NullableCodec<I, O> {
    val codec = this
    return object : NullableCodec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return codec.encode(value)
        }

        override fun decode(value: Any?): Result<I?> {
            return runCatching {
                codec.decode(value).getOrElse(block)
            }
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when encoding fails.
 */
@KpedMarker4
infix fun <I, O> NullableCodec<I, O>.defaultOut(defaultValue: O): NullableCodec<I, O> {
    val codec = this
    return object : NullableCodec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrDefault(defaultValue)
            }
        }

        override fun decode(value: Any?): Result<I?> {
            return codec.decode(value)
        }
    }
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when encoding fails.
 */
@KpedMarker4
infix fun <I, O> NullableCodec<I, O>.catchOut(block: (Throwable) -> O): NullableCodec<I, O> {
    val codec = this
    return object : NullableCodec<I, O> {
        override fun encode(value: Any?): Result<O> {
            return runCatching {
                codec.encode(value).getOrElse(block)
            }
        }

        override fun decode(value: Any?): Result<I?> {
            return codec.decode(value)
        }
    }
}

/* ============= ------------------ ============= */

/**
 * Decode [this] value to [I] using the given [codec].
 *
 * @receiver the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@JvmName("decodeInfixNullish")
@JsName("decodeInfixNullish")
@KpedMarker4
infix fun <I, O> O?.decode(codec: NullableCodec<I, O>): I? {
    return decode(this, codec)
}

/**
 * Decode [this] value to [I] using the given [codec].
 *
 * @receiver the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@JvmName("decodeInfixNullable")
@JsName("decodeInfixNullable")
@KpedMarker4
infix fun <I, O> O.decode(codec: NullableCodec<I, O>): I? {
    return decode(this, codec)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@JvmName("tryDecodeNullish")
@JsName("tryDecodeNullish")
@KpedMarker3
fun <I, O> tryDecode(value: O?, codec: NullableCodec<I, O>): Result<I?> {
    return tryDecodeAny(value, codec)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoding result.
 * @since 2.0.0
 */
@JvmName("tryDecodeNullable")
@JsName("tryDecodeNullable")
@KpedMarker3
fun <I, O> tryDecode(value: O, codec: NullableCodec<I, O>): Result<I?> {
    return tryDecodeAny(value, codec)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@JvmName("decodeNullish")
@JsName("decodeNullish")
@KpedMarker3
fun <I, O> decode(value: O?, codec: NullableCodec<I, O>): I? {
    return decodeAny(value, codec)
}

/**
 * Decode the given [value] to [O] using the given [codec].
 *
 * @param value the value to decode.
 * @param codec the codec to be used.
 * @return the decoded value.
 * @throws CodecException if decoding failed.
 * @since 2.0.0
 */
@JvmName("decodeNullable")
@JsName("decodeNullable")
@KpedMarker3
fun <I, O> decode(value: O, codec: NullableCodec<I, O>): I? {
    return decodeAny(value, codec)
}

/* ============= ------------------ ============= */
