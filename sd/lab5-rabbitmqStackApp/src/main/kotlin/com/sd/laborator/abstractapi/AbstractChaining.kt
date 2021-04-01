package com.sd.laborator.abstractapi

import com.sd.laborator.interfaces.Chaining

abstract class AbstractChaining : Chaining {
    protected var link: AbstractChaining? = null
    protected val setList: MutableList<Set<Pair<Int, Int>>?> = mutableListOf()

    @JvmName("setLink1")
    fun setLink(link: AbstractChaining?) {
        this.link = link
    }

    override fun loadSet(data: Set<Pair<Int, Int>>) {
        setList.add(data)
    }
}