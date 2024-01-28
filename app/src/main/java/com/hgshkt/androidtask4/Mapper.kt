package com.hgshkt.androidtask4

import com.hgshkt.androidtask4.api.model.Weather
import com.hgshkt.androidtask4.model.DisplayWeather

fun Weather.toDisplay(): DisplayWeather {
    return DisplayWeather(
        temperature = temperature,
        wind = wind,
        description = description
    )
}