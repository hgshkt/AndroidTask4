package com.hgshkt.androidtask4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.hgshkt.androidtask4.api.ApiClient
import com.hgshkt.androidtask4.api.ApiInterface
import com.hgshkt.androidtask4.model.DisplayWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var requestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        requestButton.setOnClickListener {
            request { weather, error ->
                updateUI(weather)
                showErrorMessage(error)
            }
        }
    }

    private fun updateUI(weather: DisplayWeather) {
        textView.text = weather.temperature.toString()
    }

    private fun init() {
        textView = findViewById(R.id.textView)
        requestButton = findViewById(R.id.requestButton)
    }

    private fun request(handleResponse: (weather: DisplayWeather, error: Throwable) -> Unit) {
        val client = ApiClient.client.create(ApiInterface::class.java)
        client.getWeather()
            .subscribeOn(Schedulers.io())
            .map { it.toDisplay() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { weather, error ->
                handleResponse(weather, error)
            }
    }

    private fun showErrorMessage(error: Throwable?) {
        Toast.makeText(this, error?.message, Toast.LENGTH_SHORT).show()
    }
}