package org.cufy.test

import kotlinx.coroutines.test.runTest
import org.cufy.bcrypt.bcryptCheck
import org.cufy.bcrypt.bcryptHash
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BcryptTest {
    @Test
    fun hash() = runTest {
        val password = "Hello World"
        val salt = byteArrayOf(52, 74, -66, -18, 76, -60, 42, -91, 60, 51, 53, -70, -82, -70, -42, -1)
        val hash = "\$2a\$10\$LCo85ixCIoS6KxU4pppU9uRZBHut.S/unz55OOpSsBFosiGF8IN2S"
        val actualHash = bcryptHash(password, salt, cost = 10)

        assertEquals(hash, actualHash)
        assertTrue(bcryptCheck(password, hash))
    }
}
