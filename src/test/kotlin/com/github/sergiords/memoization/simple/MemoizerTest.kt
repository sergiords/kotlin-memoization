package com.github.sergiords.memoization.simple

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoizationTests {

    @Test
    fun `memoized square`() {

        listOf(1, 2, 3, 4, 1, 2, 3, 4).forEach {

            val result = memoizedSquare(it)

            assertEquals(it * it, result)

        }

    }

    @Test
    fun `simple memoizer`() {

        var count = 0
        val memoized = memoizer { it -> ++count; it * it }

        listOf(
            (1 to 1), (2 to 2), (3 to 3), (4 to 4),
            (1 to 4), (2 to 4), (3 to 4), (4 to 4), (5 to 5)
        ).forEach { (n, expectedCount) ->

            val result = memoized(n)

            assertEquals(n * n, result)
            assertEquals(expectedCount, count)
        }

    }

}
