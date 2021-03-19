package com.sd.laborator.abstractapi

import com.sd.laborator.interfaces.WeatherAPIInterface

abstract class AbstractWeatherAPI : WeatherAPIInterface {
    private var nextLink: AbstractWeatherAPI? = null

    fun setNextLink(nextLink: AbstractWeatherAPI) {
        this.nextLink = nextLink
    }

    fun getAPIResult(location: String): String {
        val result = getAPIInfo(location)

        val linkResponse: String = nextLink?.getAPIInfo(result).toString()
        if (nextLink != null) {
            return linkResponse
        }

        return result
    }
}