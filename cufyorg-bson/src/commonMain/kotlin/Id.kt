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

@Deprecated("use ID instead", ReplaceWith("ID<T>"))
typealias Id<T> = ID<T>

/**
 * A typealias for generic-less [ID].
 *
 * @author LSafer
 * @since 2.0.0
 */
@Deprecated("use AnyID instead", ReplaceWith("AnyID"))
typealias AnyId = ID<out Any?>

/**
 * Return an [ID] instance from the value of this.
 */
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
fun <T> String.toId(): ID<T> {
    return ID(this)
}

/**
 * Return an [AnyId] instance from the value of this.
 */
@JvmName("toAnyID")
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
fun String.toId(): AnyID {
    return AnyID(this)
}

/**
 * Return an [ID] instance from the value of this.
 */
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
fun <T> ObjectId.toId(): ID<T> {
    return ID(this)
}

/**
 * Return an [AnyId] instance from the value of this.
 */
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
@JvmName("toAnyId")
fun ObjectId.toId(): AnyID {
    return AnyID(this)
}

/**
 * Cast this [ID] to an id of [T].
 */
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
fun <T> AnyID.toId(): ID<T> {
    return ID(this)
}

/**
 * Cast this [ID] to [AnyId].
 */
@Deprecated("use toID instead", ReplaceWith("this.toID()"))
@JvmName("toAnyId")
fun AnyID.toId(): AnyID {
    return AnyID(this)
}
