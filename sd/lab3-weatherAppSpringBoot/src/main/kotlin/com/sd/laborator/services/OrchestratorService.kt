package com.sd.laborator.services

import com.sd.laborator.interfaces.OrchestratorInterface
import com.sd.laborator.abstractapi.AbstractWeatherAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestratorService : OrchestratorInterface {
    @Autowired
    private lateinit var locationSearchService: AbstractWeatherAPI

    @Autowired
    private lateinit var weatherForecastService: AbstractWeatherAPI

    override fun orchestrate(location: String): String {
        val id = locationSearchService.getAPIResult(location)
        return weatherForecastService.getAPIResult(id)
    }
}