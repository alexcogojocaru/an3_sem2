package com.sd.laborator.services

import com.sd.laborator.abstractapi.AbstractChaining
import com.sd.laborator.abstractapi.PayloadReceiver
import com.sd.laborator.interfaces.UnionOperation
import org.springframework.stereotype.Service

@Service
class UnionService: AbstractChaining() {
    override fun executeOperation() {
        val res: Set<Pair<Int, Int>> = PayloadReceiver.list[0] union PayloadReceiver.list[1]

        PayloadReceiver.list.clear()
        PayloadReceiver.list.add(res)

        if (link != null) {
            link?.executeOperation()
        }
    }
}