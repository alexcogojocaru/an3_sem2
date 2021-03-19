package com.sd.laborator.controllers

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CustomController : ErrorController {

    @RequestMapping(value = [path])
    @ResponseBody
    fun handleError(): String {
        return "Could not find the specified location"
    }

    override fun getErrorPath(): String {
        return path
    }

    companion object {
        private const val path = "/error"
    }
}