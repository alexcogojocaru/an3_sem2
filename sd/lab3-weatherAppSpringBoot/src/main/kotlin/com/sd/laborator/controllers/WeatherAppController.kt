package com.sd.laborator.controllers

import com.sd.laborator.abstractapi.AbstractWeatherAPI
import com.sd.laborator.interfaces.OrchestratorInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherAppController {
//    @Autowired
//    private lateinit var locationSearchService: AbstractWeatherAPI
//
//    @Autowired
//    private lateinit var weatherForecastService: AbstractWeatherAPI

    @Autowired
    private lateinit var orchestrator: OrchestratorInterface

    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
//        locationSearchService.setNextLink(weatherForecastService)
//        return locationSearchService.getAPIResult(location)
        return orchestrator.orchestrate(location)
    }
}