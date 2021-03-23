package com.sd.laborator.controllers

import com.sd.laborator.interfaces.PersonInterface
import com.sd.laborator.pojo.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class PersonController {
    @Autowired
    private lateinit var paymentService: PersonInterface

    @RequestMapping(value = ["/person"], method = [RequestMethod.POST])
    fun createPerson(@RequestBody person: Person): ResponseEntity<Unit> {
        paymentService.createPerson(person)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/person/{id}"], method = [RequestMethod.GET])
    fun readPerson(@PathVariable id: Int): ResponseEntity<Person?> {
        val person = paymentService.readPerson(id)
        val status = if (person == null) {
            HttpStatus.NOT_FOUND
        }
        else {
            HttpStatus.OK
        }

        return ResponseEntity(person, status)
    }

    @RequestMapping(value = ["/person/{id}"], method = [RequestMethod.PUT])
    fun updatePerson(@PathVariable id: Int, @RequestBody person: Person): ResponseEntity<Unit> {
        paymentService.readPerson(id)?.let {
            paymentService.updatePerson(it.id, person)
            return ResponseEntity(HttpStatus.ACCEPTED)
        } ?: return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @RequestMapping(value = ["/person/{id}"], method = [RequestMethod.DELETE])
    fun deletePerson(@PathVariable id: Int): ResponseEntity<Unit> {
        paymentService.readPerson(id)?.let {
            paymentService.deletePerson(it.id)
            return ResponseEntity(HttpStatus.OK)
        } ?: return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}