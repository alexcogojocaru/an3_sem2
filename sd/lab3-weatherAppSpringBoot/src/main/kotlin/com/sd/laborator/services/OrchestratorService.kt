package com.sd.laborator.services

import com.sd.laborator.interfaces.OrchestratorInterface
import com.sd.laborator.abstractapi.AbstractWeatherAPI
import com.sd.laborator.interfaces.WeatherAPIInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrchestratorService : OrchestratorInterface {
    @Autowired
    private lateinit var locationSearchService: WeatherAPIInterface

    @Autowired
    private lateinit var weatherForecastService: WeatherAPIInterface

    override fun orchestrate(location: String): String {
        val id = locationSearchService.getAPIInfo(location)
        return weatherForecastService.getAPIInfo(id)
    }
}