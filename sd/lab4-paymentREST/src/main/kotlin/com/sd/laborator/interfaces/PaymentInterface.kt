package com.sd.laborator.interfaces

import com.sd.laborator.pojo.Person

interface PaymentInterface {
    fun add(person: Person, amount: Int): Person
    fun subtract(person: Person, amount: Int): Person
    fun update(person: Person, amount: Int): Person
}