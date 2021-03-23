package com.sd.laborator.services

import com.sd.laborator.interfaces.PaymentInterface
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PaymentService : PaymentInterface {
    companion object {
        private var sumRegistry = ConcurrentHashMap<Int, Int>()
    }

    override fun add(id: Int, amount: Int) {
        sumRegistry[id] = amount
    }

    override fun update(id: Int, amount: Int) {
        sumRegistry[id] = amount
    }

    override fun substract(id: Int, amount: Int) {
        sumRegistry[id] = amount
    }

    override fun getPayments(): ConcurrentHashMap<Int, Int> {
        return sumRegistry
    }
}