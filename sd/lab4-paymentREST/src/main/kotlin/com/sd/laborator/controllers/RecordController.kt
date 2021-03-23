package com.sd.laborator.controllers

import com.sd.laborator.interfaces.PaymentInterface
import com.sd.laborator.interfaces.PersonInterface
import com.sd.laborator.pojo.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class RecordController {
    @Autowired
    private lateinit var personService: PersonInterface

    @Autowired
    private lateinit var paymentService: PaymentInterface

    @RequestMapping(value = ["/records"], method = [RequestMethod.GET])
    fun get(): ResponseEntity<HashMap<Person, Int>> {
        val recordsMap = hashMapOf<Person, Int>()

        for (person in personService.getPersons()) {
            recordsMap[person] = 1
        }

        return ResponseEntity(HttpStatus.OK)
    }
}