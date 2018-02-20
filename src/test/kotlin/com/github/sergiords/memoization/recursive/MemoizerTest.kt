package com.github.sergiords.memoization.recursive

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoizationTests {

    private fun factorial(n: Int): Int = if (n < 2) 1 else n * factorial(n - 1)

    @Test
    fun `memoized factorial`() {

        listOf(1, 2, 3, 4, 1, 2, 3, 4, 5).forEach {

            val result = memoizedFactorial(it)
            val expected = factorial(it)

            assertEquals(expected, result)

        }

    }

    @Test
    fun `recursive memoizer`() {

        var count = 0
        val memoized = memoizer { h -> { n -> count++; if (n < 2) 1 else n * h(n - 1) } }

        listOf(
            Pair(1, 1), Pair(2, 2), Pair(3, 3), Pair(4, 4),
            Pair(1, 4), Pair(2, 4), Pair(3, 4), Pair(4, 4), Pair(5, 5)
        ).forEach { (n, expectedCount) ->

            val result = memoized(n)

            assertEquals(factorial(n), result)
            assertEquals(expectedCount, count)
        }

    }

}
