package org.cufy.bcrypt

import kotlin.random.Random

expect fun bcryptSaltGen(): ByteArray

expect fun bcryptSaltGen(random: Random): ByteArray

/**
 * Hashes given password with the OpenBSD bcrypt schema.
 * The cost factor will define how expensive the hash will
 * be to generate.
 */
expect fun bcryptHash(password: String, cost: Int): String

/**
 * Hashes given password with the OpenBSD bcrypt schema.
 * The cost factor will define how expensive the hash will
 * be to generate.
 */
expect fun bcryptHash(password: String, salt: ByteArray, cost: Int): String

/**
 * Verify given bcrypt hash, which includes salt and cost
 * factor with given raw password.
 */
expect fun bcryptCheck(password: String, hash: String): Boolean
