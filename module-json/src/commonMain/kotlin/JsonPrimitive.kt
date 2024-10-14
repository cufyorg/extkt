package org.cufy.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.*

// https://stackoverflow.com/a/13340826/22235255
private val NUMBER_REGEXP = Regex("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")

@OptIn(ExperimentalSerializationApi::class)
fun String.coerceJsonPrimitive(): JsonPrimitive {
    return when {
        this == "null" -> JsonNull
        this == "true" -> JsonPrimitive(true)
        this == "false" -> JsonPrimitive(false)
        this matches NUMBER_REGEXP -> JsonUnquotedLiteral(this)
        else -> JsonPrimitive(this)
    }
}

fun String.isJsonNull(): Boolean {
    return this == "null"
}

fun String.isJsonBoolean(): Boolean {
    return this == "true" || this == "false"
}

fun String.isJsonNumber(): Boolean {
    return this matches NUMBER_REGEXP
}

fun String.isJsonDouble(): Boolean {
    return toDoubleOrNull() != null
}

fun String.isJsonFloat(): Boolean {
    return toFloatOrNull() != null
}

fun String.isJsonLong(): Boolean {
    return JsonPrimitive(this).longOrNull != null
}

fun String.isJsonInt(): Boolean {
    return JsonPrimitive(this).intOrNull != null
}

fun String.isJsonObjectQuick(): Boolean {
    for (c in 0..lastIndex) {
        when (this[c]) {
            '{' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    for (c in lastIndex downTo 0) {
        when (this[c]) {
            '}' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    return true
}

fun String.isJsonArrayQuick(): Boolean {
    for (c in 0..lastIndex) {
        when (this[c]) {
            '[' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    for (c in lastIndex downTo 0) {
        when (this[c]) {
            ']' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    return true
}

fun String.isJsonStringQuick(): Boolean {
    for (c in 0..lastIndex) {
        when (this[c]) {
            '\"' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    for (c in lastIndex downTo 0) {
        when (this[c]) {
            '\"' -> break
            ' ', '\t', '\n', '\r' -> continue
            else -> return false
        }
    }

    return true
}
