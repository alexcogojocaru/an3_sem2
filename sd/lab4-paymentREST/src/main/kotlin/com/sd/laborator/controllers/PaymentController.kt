package com.sd.laborator.controllers

import com.sd.laborator.interfaces.PaymentInterface
import com.sd.laborator.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PaymentController {
    @Autowired
    private lateinit var paymentService: PaymentInterface

    @Autowired
    private lateinit var personService: PersonService

    @RequestMapping(value = ["/pay/{id}/{sum}"], method = [RequestMethod.POST])
    fun addPayment(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        if (!personService.personExists(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        personService.readPerson(id)?.let {
            paymentService.add(it, sum)
        }?.let {
            personService.updatePerson(id, it)
        }

        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/pay/{id}/{sum}"], method = [RequestMethod.DELETE])
    fun subtractPayment(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        if (!personService.personExists(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        personService.readPerson(id)?.let {
            paymentService.subtract(it, sum)
        }?.let {
            personService.updatePerson(id, it)
        }

        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/pay/{id}/{sum}"], method = [RequestMethod.PUT])
    fun updatePayment(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        if (!personService.personExists(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        personService.readPerson(id)?.let {
            paymentService.update(it, sum)
        }?.let {
            personService.updatePerson(id, it)
        }

        return ResponseEntity(HttpStatus.OK)
    }
}