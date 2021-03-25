package com.sd.laborator.services

import com.sd.laborator.interfaces.PaymentInterface
import com.sd.laborator.pojo.Person
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PaymentService : PaymentInterface {
    override fun add(person: Person, amount: Int): Person {
        person.sum += amount
        return person
    }

    override fun subtract(person: Person, amount: Int): Person {
        person.sum -= amount
        return person
    }

    override fun update(person: Person, amount: Int): Person {
        person.sum = amount
        return person
    }
}