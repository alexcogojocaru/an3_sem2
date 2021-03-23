package com.sd.laborator.interfaces

import java.util.concurrent.ConcurrentHashMap

interface PaymentInterface {
    fun add(id: Int, amount: Int)
    fun substract(id: Int, amount: Int)
    fun update(id: Int, amount: Int)
    fun getPayments(): ConcurrentHashMap<Int, Int>
}