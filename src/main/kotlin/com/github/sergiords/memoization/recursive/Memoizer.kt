package com.github.sergiords.memoization.recursive

typealias F = (Int) -> Int

interface Handler : (Handler) -> F

object H : Handler {

    override fun invoke(h: Handler): F = { n -> if (n < 2) 1 else n * h(h)(n - 1) }

}

val factorialOf5 = H(H)(5)

fun uncachedMemoizer(f2f: (F) -> F): F {

    val h = object : Handler {
        override fun invoke(h: Handler): F = f2f { n -> h(h)(n) }
    }

    return h(h)
}

fun memoizer(f2f: (F) -> F): F {

    val cache = mutableMapOf<Int, Int>()

    val h = object : Handler {
        override fun invoke(h: Handler): F = f2f { n -> cache.computeIfAbsent(n, h(h)) }
    }

    return { n -> cache.computeIfAbsent(n, h(h)) }
}

val memoizedFactorial = memoizer { f -> { n -> if (n < 2) 1 else n * f(n - 1) } }
