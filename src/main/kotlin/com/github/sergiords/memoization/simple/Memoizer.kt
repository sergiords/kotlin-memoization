package com.github.sergiords.memoization.simple

fun square(x: Int) = x * x

fun memoizer(f: (Int) -> Int): (Int) -> Int {

    val cache = mutableMapOf<Int, Int>()

    return { n -> cache.computeIfAbsent(n, f) }
}

val memoizedSquare = memoizer(::square)
