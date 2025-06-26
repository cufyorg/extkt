package org.cufy.bcrypt

import kotlin.random.Random

// stupid library that works; salt= salt + rounds
@JsModule("bcryptjs")
private external val bcryptjs: dynamic

actual fun bcryptSaltGen(): ByteArray {
    return Random.nextBytes(16)
}

actual fun bcryptSaltGen(random: Random): ByteArray {
    return random.nextBytes(16)
}

actual fun bcryptHash(password: String, cost: Int): String {
    val config = bcryptjs.genSaltSync(cost)
    return bcryptjs.hashSync(password, config, cost)
}

actual fun bcryptHash(password: String, salt: ByteArray, cost: Int): String {
    val costString = cost.toString().padStart(2, '0')
    val saltString = encodeRadix64(salt).decodeToString()
    val config = "$2a$${costString}$${saltString}"
    return bcryptjs.hashSync(password, config) as String
}

actual fun bcryptCheck(password: String, hash: String): Boolean {
    return bcryptjs.compareSync(password, hash)
}
