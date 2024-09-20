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

import kotlinx.datetime.Instant
import kotlin.reflect.KCallable

/* ============= ------------------ ============= */

/**
 * A map containing only items of type [BsonElement].
 *
 * @since 2.0.0
 */
typealias BsonDocumentLike = Map<String, BsonElement>

/**
 * A mutable map containing only items of type [BsonElement].
 *
 * TODO: change to MutableMap<String, BsonElement> when context receivers are stable.
 *
 * @since 2.0.0
 */
typealias MutableBsonDocumentLike = IMutableBsonDocumentLike

/**
 * A block of code building a bson document.
 *
 * @since 2.0.0
 */
typealias BsonDocumentBlock = MutableBsonDocumentLike.() -> Unit

/* ============= ------------------ ============= */

/**
 * Construct a new mutable bson map from this map.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see toMutableMap
 * @since 2.0.0
 */
fun Map<String, BsonElement>.toMutableBsonDocument(): MutableBsonDocumentLike {
    return toMutableMap().asMutableBsonDocument()
}

/**
 * Obtain a mutable bson map backed by this map.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @since 2.0.0
 */
fun MutableMap<String, BsonElement>.asMutableBsonDocument(): MutableBsonDocumentLike {
    val content = this
    return object : MutableBsonDocumentLike, MutableMap<String, BsonElement> by content {
        override fun equals(other: Any?) =
            content == other

        override fun hashCode() =
            content.hashCode()

        override fun toString() =
            content.entries.joinToString(",", "{", "}") {
                """"${it.key}":${it.value}"""
            }
    }
}

/* ============= ------------------ ============= */

/**
 * Return an empty new [MutableBsonDocumentLike].
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see mutableMapOf
 * @since 2.0.0
 */
fun mutableBsonDocumentOf(): MutableBsonDocumentLike {
    return mutableMapOf<String, BsonElement>()
        .asMutableBsonDocument()
}

/**
 * Returns a new [MutableBsonDocumentLike] with the given pairs.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see mutableMapOf
 * @since 2.0.0
 */
fun mutableBsonDocumentOf(vararg pairs: Pair<String, BsonElement>): MutableBsonDocumentLike {
    return mutableMapOf(*pairs)
        .asMutableBsonDocument()
}

/* ============= ------------------ ============= */

/**
 * An interface allowing custom receivers for
 * [by][IMutableBsonDocumentLike.by].
 *
 * This interface will be useless after context
 * receivers is released for production.
 * This interface will be removed gradually after
 * context receivers is released for production.
 *
 * @author LSafer
 * @since 2.0.0
 */
interface MutableBsonMapField<T> {
    /**
     * The name of the field.
     */
    val name: String

    /**
     * Encode the given [value] to a bson value.
     */
    fun encode(value: T): BsonElement?
}

/**
 * A builder building a [BsonDocument].
 *
 * This interface will be removed and its functions will be
 * extensions of [MutableBsonDocumentLike] once kotlin context
 * receivers is stable.
 *
 * @author LSafer
 * @since 2.0.0
 */
@BsonMarker2
@DeprecatedWithContextParameters
interface IMutableBsonDocumentLike : BsonDocumentLike, MutableMap<String, BsonElement> {
    /* ============= ------------------ ============= */

    /**
     * Static (pure) utility function to prettify
     * creating arrays within the dsl.
     *
     * Usage:
     * ```
     * BsonDocument {
     *     "name" by array {
     *         by(100L)
     *         by("item")
     *         /* ... */
     *     }
     * }
     * ```
     *
     * @return an array built with the given [block].
     * @since 2.0.0
     */
    @BsonMarker2
    fun array(block: BsonArrayBlock) = BsonArray(block)

    /**
     * Static (pure) utility function to prettify
     * creating arrays within the dsl.
     *
     * Usage:
     * ```
     * BsonDocument {
     *     "name" by array(
     *         100L.bson,
     *         "item".bson,
     *         /* ... */
     *     )
     * }
     * ```
     *
     * @return an array with the given [elements].
     * @since 2.0.0
     */
    @BsonMarker2
    fun array(vararg elements: BsonElement) = BsonArray(*elements)

    /**
     * Static (pure) utility function to prettify
     * creating documents within the dsl.
     *
     * Usage:
     * ```
     * BsonDocument {
     *     "a" by document {
     *         "a" by 100L
     *         "b" by "item"
     *         /* ... */
     *     }
     * }
     * ```
     *
     * @return a document built with the given [block].
     * @since 2.0.0
     */
    @BsonMarker2
    fun document(block: BsonDocumentBlock) = BsonDocument(block)

    /* ============= ------------------ ============= */

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun String.by(value: BsonElement?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: BsonElement?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: BsonElement?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun String.by(value: BsonDocument?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: BsonDocument?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: BsonDocument?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun String.by(value: BsonArray?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: BsonArray?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: BsonArray?) {
        name by value
    }

    /* ============= ------------------ ============= */

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDocument].
     */
    @BsonMarker2
    infix fun String.by(value: Map<String, BsonElement>?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.toBsonDocument()
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDocument].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Map<String, BsonElement>?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDocument].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Map<String, BsonElement>?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonArray].
     */
    @BsonMarker2
    infix fun String.by(value: List<BsonElement>?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.toBsonArray()
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonArray].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: List<BsonElement>?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonArray].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: List<BsonElement>?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonString].
     */
    @BsonMarker2
    infix fun String.by(value: String?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonString].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: String?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonString].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: String?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonObjectId].
     */
    @BsonMarker2
    infix fun String.by(value: ObjectId?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonObjectId].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: ObjectId?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonObjectId].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: ObjectId?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [ID.bson].
     *
     */
    @BsonMarker2
    infix fun String.by(value: AnyID?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [ID.bson].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: AnyID?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [ID.bson].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: AnyID?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [BsonArray] and [ID.bson].
     */
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("byIDList")
    @BsonMarker2
    infix fun String.by(value: List<AnyID>?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.map { it.bson }.toBsonArray()
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [BsonArray] and [ID.bson].
     */
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("byIDList")
    @BsonMarker2
    infix fun KCallable<*>.by(value: List<AnyID>?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [BsonArray] and [ID.bson].
     */
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("byIDList")
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: List<AnyID>?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDecimal128].
     */
    @BsonMarker2
    infix fun String.by(value: Decimal128?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDecimal128].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Decimal128?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDecimal128].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Decimal128?) {
        name by value
    }

//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDecimal128].
//     */
//    @BsonConstructorMarker
//    infix fun String.by(value: BigDecimal?) {
//        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
//        this@IMutableBsonDocumentLike[this] = value.bson
//    }
//
//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDecimal128].
//     */
//    @BsonConstructorMarker
//    infix fun KCallable<*>.by(value: BigDecimal?) {
//        name by value
//    }
//
//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDecimal128].
//     */
//    @BsonConstructorMarker
//    infix fun MutableBsonMapField<*>.byname(value: BigDecimal?) {
//        name by value
//    }

//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDateTime].
//     */
//    @BsonConstructorMarker
//    infix fun String.by(value: Date?) {
//        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
//        this@IMutableBsonDocumentLike[this] = value.bson
//    }
//
//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDateTime].
//     */
//    @BsonConstructorMarker
//    infix fun KCallable<*>.by(value: Date?) {
//        name by value
//    }
//
//    /**
//     * Set the field with the name [this] to the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDateTime].
//     */
//    @BsonConstructorMarker
//    infix fun MutableBsonMapField<*>.byname(value: Date?) {
//        name by value
//    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDateTime].
     */
    @BsonMarker2
    infix fun String.by(value: Instant?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDateTime].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Instant?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDateTime].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Instant?) {
        name by value
    }

    /* ============= ------------------ ============= */

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonBoolean].
     */
    @BsonMarker2
    infix fun String.by(value: Boolean?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonBoolean].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Boolean?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonBoolean].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Boolean?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt32].
     */
    @BsonMarker2
    infix fun String.by(value: Int?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt32].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Int?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt32].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Int?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt64].
     */
    @BsonMarker2
    infix fun String.by(value: Long?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt64].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Long?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt64].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Long?) {
        name by value
    }

    //

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDouble].
     */
    @BsonMarker2
    infix fun String.by(value: Double?) {
        value ?: return run { this@IMutableBsonDocumentLike[this] = null.bson }
        this@IMutableBsonDocumentLike[this] = value.bson
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDouble].
     */
    @BsonMarker2
    infix fun KCallable<*>.by(value: Double?) {
        name by value
    }

    /**
     * Set the field with the name [this] to the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDouble].
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(value: Double?) {
        name by value
    }

    /* ============= ------------------ ============= */

    /**
     * Set the field with the name [this] to the value from invoking the given [block]
     */
    @BsonMarker2
    infix fun String.by(block: BsonDocumentBlock) {
        this@IMutableBsonDocumentLike[this] = BsonDocument(block)
    }

    /**
     * Set the field with the name [this] to the value from invoking the given [block]
     */
    @BsonMarker2
    infix fun KCallable<*>.by(block: BsonDocumentBlock) {
        name by block
    }

    /**
     * Set the field with the name [this] to the value from invoking the given [block]
     */
    @BsonMarker2
    infix fun MutableBsonMapField<*>.byname(block: BsonDocumentBlock) {
        name by block
    }

    /* ============= ------------------ ============= */

    /**
     * Set the field represented by the [receiver][this] to the given [value].
     */
    @BsonMarker2
    infix fun <T> MutableBsonMapField<T>.by(value: T) {
        this@IMutableBsonDocumentLike[name] = encode(value) ?: null.bson
    }

    /* ============= ------------------ ============= */

    /**
     * Put all the mappings in the given [map].
     */
    @BsonMarker2
    fun byAll(map: Map<String, BsonElement>) {
        this@IMutableBsonDocumentLike += map
    }

    /* ============= ------------------ ============= */
}

/* ============= ------------------ ============= */
