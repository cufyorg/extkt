package org.cufy.bcrypt

import at.favre.lib.bytes.Bytes
import at.favre.lib.crypto.bcrypt.BCrypt
import kotlin.random.Random
import kotlin.random.asJavaRandom

private val BCRYPT_HASHER: BCrypt.Hasher = BCrypt.withDefaults()
private val BCRYPT_VERIFIER: BCrypt.Verifyer = BCrypt.verifyer()

actual fun bcryptSaltGen(): ByteArray {
    return Bytes.random(BCrypt.SALT_LENGTH).array()
}

actual fun bcryptSaltGen(random: Random): ByteArray {
    return Bytes.random(BCrypt.SALT_LENGTH, random.asJavaRandom()).array()
}

actual fun bcryptHash(password: String, cost: Int): String {
    val pwdBytes = password.toByteArray()
    val out = BCRYPT_HASHER.hash(cost, pwdBytes)
    return out.decodeToString()
}

actual fun bcryptHash(password: String, salt: ByteArray, cost: Int): String {
    val pwdBytes = password.toByteArray()
    val out = BCRYPT_HASHER.hash(cost, salt, pwdBytes)
    return out.decodeToString()
}

actual fun bcryptCheck(password: String, hash: String): Boolean {
    val pwdBytes = password.toByteArray()
    val hashBytes = hash.toByteArray()
    val out = BCRYPT_VERIFIER.verify(pwdBytes, hashBytes)
    return out.verified
}
