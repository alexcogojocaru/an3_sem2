package com.sd.laborator.controllers

import com.sd.laborator.interfaces.PaymentInterface
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

    @RequestMapping(value = ["/person/{id}/add/{sum}"], method = [RequestMethod.POST])
    fun add(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        paymentService.add(id, sum)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/payment/{id}/sub/{sum}"], method = [RequestMethod.POST])
    fun subtract(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        paymentService.substract(id, sum)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/payment/{id}/update/{sum}"], method = [RequestMethod.POST])
    fun update(@PathVariable id: Int, @PathVariable sum: Int): ResponseEntity<Unit> {
        paymentService.update(id, sum)
        return ResponseEntity(HttpStatus.OK)
    }
}