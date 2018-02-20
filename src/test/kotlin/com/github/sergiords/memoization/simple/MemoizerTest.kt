package com.github.sergiords.memoization.simple

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.min

class MemoizationTests {

    @Test
    fun `memoized square`() {

        fun square(n: Int): Int = n * n

        listOf(1, 2, 3, 4, 1, 2, 3, 4).forEach {

            val result = memoizedSquare(it)

            assertEquals(square(it), result)

        }

    }

    @Test
    fun `simple memoizer`() {

        var count = 0
        val memoized = memoizer { it -> ++count; it }

        listOf(1, 2, 3, 4, 1, 2, 3, 4).forEachIndexed { index, n ->

            val result: Any = memoized(n)
            val expectedCount = min(index + 1, 4) // count should only be able to reach 4

            assertEquals(n, result)
            assertEquals(expectedCount, count)
        }

    }

}
