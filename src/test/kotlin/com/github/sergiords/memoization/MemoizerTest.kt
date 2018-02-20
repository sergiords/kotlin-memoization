package com.github.sergiords.memoization

import org.junit.Assert.assertEquals
import org.junit.Test

class MemoizationTests {

    @Test
    fun `square function`() {

        listOf(1, 2, 3, 4, 10).forEach {
            assertEquals(it * it, square(it))
        }

    }

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
        val memoized = memoizer { it -> ++count; it }

        val sum = listOf(1, 2, 3, 4, 1, 2, 3, 4).map(memoized).sum()

        assertEquals(20, sum)
        assertEquals(4, count)

    }

}
