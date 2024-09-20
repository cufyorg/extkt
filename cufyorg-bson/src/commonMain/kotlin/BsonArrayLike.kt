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
import kotlin.jvm.JvmName

/* ============= ------------------ ============= */

/**
 * A list containing only items of type [BsonElement].
 *
 * @since 2.0.0
 */
typealias BsonArrayLike = List<BsonElement>

/**
 * A mutable list containing only items of type [BsonElement].
 *
 * TODO: change to MutableList<BsonDocument> when context receivers are stable.
 *
 * @since 2.0.0
 */
typealias MutableBsonArrayLike = IMutableBsonArrayLike

/**
 * A block of code building a bson array.
 *
 * @since 2.0.0
 */
typealias BsonArrayBlock = MutableBsonArrayLike.() -> Unit

/* ============= ------------------ ============= */

/**
 * Construct a new mutable bson list from this list.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see toMutableList
 * @since 2.0.0
 */
fun Iterable<BsonElement>.toMutableBsonArray(): MutableBsonArrayLike {
    return toMutableList().asMutableBsonArray()
}

/**
 * Obtain a mutable bson list backed by this list.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @since 2.0.0
 */
fun MutableList<BsonElement>.asMutableBsonArray(): MutableBsonArrayLike {
    val content = this
    return object : MutableBsonArrayLike, MutableList<BsonElement> by content {
        override fun equals(other: Any?) =
            content == other

        override fun hashCode() =
            content.hashCode()

        override fun toString() =
            content.joinToString(",", "[", "]")
    }
}

/* ============= ------------------ ============= */

/**
 * Return an empty new [MutableBsonArrayLike].
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see mutableListOf
 * @since 2.0.0
 */
fun mutableBsonArrayOf(): MutableBsonArrayLike {
    return mutableListOf<BsonElement>()
        .asMutableBsonArray()
}

/**
 * Returns a new [MutableBsonArrayLike] with the given elements.
 *
 * This function will be obsolete once kotlin
 * context receivers is stable.
 *
 * @see mutableListOf
 * @since 2.0.0
 */
fun mutableBsonArrayOf(vararg elements: BsonElement): MutableBsonArrayLike {
    return mutableListOf(*elements)
        .asMutableBsonArray()
}

/* ============= ------------------ ============= */

/**
 * A mutable list containing only items of type [BsonElement].
 *
 * This interface will be removed and its functions will be
 * extensions of [MutableBsonArrayLike] once kotlin context
 * receivers is stable.
 *
 * @since 2.0.0
 */
@BsonMarker2
@DeprecatedWithContextParameters
interface IMutableBsonArrayLike : BsonArrayLike, MutableList<BsonElement> {
    /* ============= ------------------ ============= */

    /**
     * Static (pure) utility function to prettify
     * creating arrays within the dsl.
     *
     * Usage:
     * ```
     * BsonArray {
     *     by(array {
     *         by(100L)
     *         by("item")
     *         /* ... */
     *     })
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
     * BsonArray {
     *     by(array(
     *         100L.bson,
     *         "item".bson,
     *         /* ... */
     *     ))
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
     * BsonArray {
     *     by(document {
     *         "a" by 100L
     *         "b" by "item"
     *         /* ... */
     *     })
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
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    fun by(value: BsonElement?) {
        value ?: return run { this += null.bson }
        this += value
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    fun by(value: BsonDocument?) {
        value ?: return run { this += null.bson }
        this += value
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     */
    @BsonMarker2
    fun by(value: BsonArray?) {
        value ?: return run { this += null.bson }
        this.add(value)
    }

    /* ============= ------------------ ============= */

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDocument].
     */
    @BsonMarker2
    fun by(value: Map<String, BsonElement>?) {
        value ?: return run { this += null.bson }
        this += value.toBsonDocument()
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonArray].
     */
    @BsonMarker2
    fun by(value: List<BsonElement>?) {
        value ?: return run { this += null.bson }
        this.add(value.toBsonArray())
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonString].
     */
    @BsonMarker2
    fun by(value: String?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonObjectId].
     */
    @BsonMarker2
    fun by(value: ObjectId?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [ID.bson].
     */
    @BsonMarker2
    fun by(value: AnyID?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped using [BsonArray] and [ID.bson].
     */
    @Suppress("INAPPLICABLE_JVM_NAME")
    @JvmName("byIDList")
    @BsonMarker2
    fun by(value: List<AnyID>?) {
        value ?: return run { this += null.bson }
        this.add(value.map { it.bson }.toBsonArray())
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDecimal128].
     */
    @BsonMarker2
    fun by(value: Decimal128?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

//    /**
//     * Add the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDecimal128].
//     */
//    @BsonConstructorMarker
//    fun by(value: BigDecimal?) {
//        value ?: return run { this += null.bson }
//        this += value.bson
//    }
//
//    /**
//     * Add the given [value].
//     *
//     * If [value] is null then [BsonNull] will be set instead.
//     *
//     * The given [value] will be wrapped with [BsonDateTime].
//     */
//    @BsonConstructorMarker
//    fun by(value: Date?) {
//        value ?: return run { this += null.bson }
//        this += value.bson
//    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDateTime].
     */
    @BsonMarker2
    fun by(value: Instant?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /* ============= ------------------ ============= */

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonBoolean].
     */
    @BsonMarker2
    fun by(value: Boolean?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt32].
     */
    @BsonMarker2
    fun by(value: Int?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonInt64].
     */
    @BsonMarker2
    fun by(value: Long?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /**
     * Add the given [value].
     *
     * If [value] is null then [BsonNull] will be set instead.
     *
     * The given [value] will be wrapped with [BsonDouble].
     */
    @BsonMarker2
    fun by(value: Double?) {
        value ?: return run { this += null.bson }
        this += value.bson
    }

    /* ============= ------------------ ============= */

    /**
     * Add the value from invoking the given [block].
     */
    @BsonMarker2
    fun by(block: BsonDocumentBlock) {
        this += BsonDocument(block)
    }

    /* ============= ------------------ ============= */

    /**
     * Put all the items in the given [list].
     */
    @BsonMarker2
    fun byAll(list: List<BsonElement>) {
        this += list
    }

    /* ============= ------------------ ============= */
}

/* ============= ------------------ ============= */
