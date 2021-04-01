package com.sd.laborator.services

import com.sd.laborator.abstractapi.AbstractChaining
import com.sd.laborator.abstractapi.PayloadReceiver
import com.sd.laborator.interfaces.CartesianProductOperation
import org.springframework.stereotype.Service

@Service
class CartesianProductService: AbstractChaining() {
    override fun executeOperation() {
        val result: MutableSet<Pair<Int, Int>> = mutableSetOf()

        setList[0]?.forEach {
            a -> setList[1]?.forEach {
                b -> result.add(Pair(a.first, b.first))
            }
        }

        PayloadReceiver.list.add(result)
        println(PayloadReceiver.list)

        if (link != null) {
            link?.executeOperation()
        }
    }
}