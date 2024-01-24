package com.hgshkt.androidtask4.api

import com.hgshkt.androidtask4.api.model.Weather
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    @GET("")
    fun getWeather(): Single<Weather>
}