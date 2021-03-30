package com.sd.laborator.interfaces

interface Chaining {
    fun execute(A: Set<Int>, B: Set<Int>): Set<Pair<Int, Int>>
}