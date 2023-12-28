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

import org.cufy.bson.BsonElement
import org.cufy.bson.MutableBsonMapField

/* ============= ------------------ ============= */

/**
 * A bson variant of [FieldCodec] enabling extra
 * features that can be achieved only when the
 * target output is known to be bson.
 *
 * This implements [MutableBsonMapField] to enable the following syntax:
 * ```kotlin
 * document {
 *      MyField by myValue
 * }
 * ```
 *
 * This interface will be useless after context
 * receivers is released for production.
 * This interface will be removed gradually after
 * context receivers is released for production.
 *
 * @param I the type of the decoded value.
 * @param O the type of the encoded value.
 * @author LSafer
 * @since 2.0.0
 */
interface BsonNullableFieldCodec<I, O : BsonElement> : NullableFieldCodec<I, O>, BsonFieldCodec<I?, O>

/**
 * Create a new field codec with the given [name]
 * and backed by the given [codec].
 */
@Suppress("FunctionName")
fun <I, O : BsonElement> FieldCodec(name: String, codec: NullableCodec<I, O>): BsonNullableFieldCodec<I, O> {
    return object : BsonNullableFieldCodec<I, O>, NullableCodec<I, O> by codec {
        override val name = name
    }
}

/* ============= ------------------ ============= */

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when decoding fails.
 */
@PedMarker3
infix fun <I, O : BsonElement> BsonNullableFieldCodec<I, O>.defaultIn(defaultValue: I): BsonNullableFieldCodec<I, O> {
    val codec = this as NullableCodec<I, O>
    return FieldCodec(name, codec defaultIn defaultValue)
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when decoding fails.
 */
@PedMarker3
infix fun <I, O : BsonElement> BsonNullableFieldCodec<I, O>.catchIn(block: (Throwable) -> I): BsonNullableFieldCodec<I, O> {
    val codec = this as NullableCodec<I, O>
    return FieldCodec(name, codec catchIn block)
}

/**
 * Return a new codec backed by this codec that returns the given [defaultValue] when encoding fails.
 */
@PedMarker3
infix fun <I, O : BsonElement> BsonNullableFieldCodec<I, O>.defaultOut(defaultValue: O): BsonNullableFieldCodec<I, O> {
    val codec = this as NullableCodec<I, O>
    return FieldCodec(name, codec defaultOut defaultValue)
}

/**
 * Return a new codec backed by this codec that returns the result of invoking the given [block] when encoding fails.
 */
@PedMarker3
infix fun <I, O : BsonElement> BsonNullableFieldCodec<I, O>.catchOut(block: (Throwable) -> O): BsonNullableFieldCodec<I, O> {
    val codec = this as NullableCodec<I, O>
    return FieldCodec(name, codec catchOut block)
}

/* ============= ------------------ ============= */

/**
 * Return a field codec derived from this one with
 * its name tagged with the given language [tag].
 */
@PedMarker3
infix fun <I, O : BsonElement> BsonNullableFieldCodec<I, O>.lang(tag: String): BsonNullableFieldCodec<I, O> {
    if (tag.isEmpty()) return this
    return FieldCodec("$name#$tag", this)
}

/**
 * Create a new field codec with the given [name]
 * and backed by [this] codec.
 */
@PedMarker3
infix fun <I, O : BsonElement> NullableCodec<I, O>.at(name: String): BsonNullableFieldCodec<I, O> {
    return FieldCodec(name, this)
}

/**
 * Create a new field codec with the receiver name
 * and backed by the given [codec].
 *
 * Example:
 *
 * ```
 * object User {
 *      val Name = "name" be Codecs.String
 *      val Age = "age" be Codecs.Int64.Nullable
 * }
 * ```
 */
@PedMarker3
infix fun <I, O : BsonElement> String.be(codec: NullableCodec<I, O>): BsonNullableFieldCodec<I, O> {
    return FieldCodec(this, codec)
}

/* ============= ------------------ ============= */
