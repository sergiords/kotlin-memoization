package com.github.sergiords.memoization.recursive

typealias F = (Int) -> Int

interface Handler : (Handler) -> F

fun memoizer(f: (F) -> F): F {

    val cache = mutableMapOf<Int, Int>()

    fun combiner(handlerCombiner: (Handler) -> F): Handler = object : Handler {

        override fun invoke(handler: Handler): F {

            val combined = handlerCombiner(handler)

            return { x -> cache.computeIfAbsent(x, combined) }
        }

    }

    val g = combiner { h -> f { n -> cache.computeIfAbsent(n, h(h)) } }

    return g(g)
}

val memoizedFactorial = memoizer { h -> { n -> if (n < 2) 1 else n * h(n - 1) } }
