package com.github.sergiords.memoization.simple

import org.junit.Assert.assertEquals
import org.junit.Test

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

            assertEquals(n, result)
            assertEquals(n, count)
        }

    }

}
