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
package org.cufy.bson

/* ============= ------------------ ============= */

/**
 * A data class encapsulated an element with its language.
 */
data class Localized<T>(
    val element: T,
    val lang: String,
)

/* ============= ------------------ ============= */

/**
 * Return a string derived from this one with
 * its content tagged with the given language [tag].
 */
@BsonMarker1
infix fun String.lang(tag: String): String {
    if (tag.isEmpty()) return this
    return "$this#$tag"
}

/**
 * Select the element with the perfect tag for the given [lang] preference.
 *
 * @param lang a list of comma-separated language ranges or a list of language
 *                 ranges in the form of the "Accept-Language" header defined in RFC 2616
 * @throws IllegalArgumentException if a language range or a weight found in the
 *                                  given ranges is ill-formed
 * @see Locale.LanguageRange.parse
 */
@ExperimentalBsonApi
expect operator fun BsonDocumentLike.get(name: String, lang: String): Localized<BsonElement?>

/**
 * Select the element with the perfect tag for the given [lang] preference.
 *
 * @param lang the languages ordered by preference. (e.g. `["en-US", "ar-SA"]`)
 * @throws IllegalArgumentException if the given range does not comply with the
 *                                  syntax of the language range mentioned
 *                                  in RFC 4647
 * @see Locale.LanguageRange
 */
@ExperimentalBsonApi
expect operator fun BsonDocumentLike.get(name: String, lang: List<String>): Localized<BsonElement?>

/**
 * Return the tags of the fields that has the given [name].
 */
@ExperimentalBsonApi
expect fun BsonDocumentLike.getLangList(name: String): List<String>

/* ============= ------------------ ============= */
