package com.github.sergiords.memoization.recursive

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoizationTests {

    private fun factorial(n: Int): Int = if (n < 2) 1 else n * factorial(n - 1)

    @Test
    fun `H combinator`() {

        listOf(1, 2, 3, 4, 1, 2, 3, 4, 5).forEach {

            val result = H(H)(it)
            val expected = factorial(it)

            assertEquals(expected, result)

        }

    }

    @Test
    fun `factorial of 5`() {

        val expected = factorial(5)

        assertEquals(expected, factorialOf5)

    }

    @Test
    fun `uncached factorial`() {

        var count = 0
        val uncachedFactorial = uncachedMemoizer { f -> { n -> ++count; if (n < 2) 1 else n * f(n - 1) } }

        listOf(
            (1 to 1), (2 to 3), (3 to 6), (4 to 10),
            (1 to 11), (2 to 13), (3 to 16), (4 to 20), (5 to 25)
        ).forEach { (n, expectedCount) ->

            val result = uncachedFactorial(n)
            val expected = factorial(n)

            assertEquals(expected, result)
            assertEquals(expectedCount, count)

        }

    }

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
        val memoized = memoizer { f -> { n -> count++; if (n < 2) 1 else n * f(n - 1) } }

        listOf(
            (1 to 1), (2 to 2), (3 to 3), (4 to 4),
            (1 to 4), (2 to 4), (3 to 4), (4 to 4), (5 to 5)
        ).forEach { (n, expectedCount) ->

            val result = memoized(n)

            assertEquals(factorial(n), result)
            assertEquals(expectedCount, count)
        }

    }

}
