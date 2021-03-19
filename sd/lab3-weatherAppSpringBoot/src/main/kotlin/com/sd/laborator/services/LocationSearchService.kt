package com.sd.laborator.services

import com.sd.laborator.abstractapi.AbstractWeatherAPI
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class LocationSearchService : AbstractWeatherAPI() {
    override fun getAPIInfo(location: String): String {
        val encodedLocationName = URLEncoder.encode(location, StandardCharsets.UTF_8.toString())
        val locationSearchURL = URL("https://www.metaweather.com/api/location/search/?query=$encodedLocationName")

        val rawResponse: String = locationSearchURL.readText()
        val responseRootObject = JSONObject("{\"data\": ${rawResponse}}")
        val responseContentObject = responseRootObject.getJSONArray("data").takeUnless { it.isEmpty } ?.getJSONObject(0)

        return responseContentObject?.getInt("woeid").toString()
    }
}