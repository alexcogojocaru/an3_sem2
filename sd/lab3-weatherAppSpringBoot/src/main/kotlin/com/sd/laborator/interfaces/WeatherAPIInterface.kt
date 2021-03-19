package com.sd.laborator.interfaces

interface WeatherAPIInterface {
    fun getAPIInfo(location: String): String
}