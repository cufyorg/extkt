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

import java.util.*

/* ============= ------------------ ============= */

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
actual operator fun BsonDocumentLike.get(name: String, lang: String): Localized<BsonElement?> {
    val rangeList = Locale.LanguageRange.parse(lang)
    val langTagList = getLangList(name)
    val langTag = Locale.lookupTag(rangeList, langTagList)
    langTag ?: return Localized(get(name), lang = "")
    if (langTag.isEmpty()) return Localized(get(name), lang = "")
    return Localized(get("$name#$langTag"), lang = langTag)
}

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
actual operator fun BsonDocumentLike.get(name: String, lang: List<String>): Localized<BsonElement?> {
    val rangeList = lang.map { Locale.LanguageRange(it) }
    val langTagList = getLangList(name)
    val langTag = Locale.lookupTag(rangeList, langTagList)
    langTag ?: return Localized(get(name), lang = "")
    if (langTag.isEmpty()) return Localized(get(name), lang = "")
    return Localized(get("$name#$langTag"), lang = langTag)
}

/**
 * Return the tags of the fields that has the given [name].
 */
@ExperimentalBsonApi
actual fun BsonDocumentLike.getLangList(name: String): List<String> {
    return buildList {
        for (nt in keys) {
            val nts = nt.split("#", limit = 2)
            val n = nts.first()
            val t = nts.getOrNull(1)

            if (n == name)
                add(t ?: "")
        }
    }
}

/* ============= ------------------ ============= */
