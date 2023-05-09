package com.ianslane.mathfacts
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class MathFactRetriever {

    private val service: MathFactService

    companion object {
        private const val BASE_URL = "http://numbersapi.com/"
    }

    init {

        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(MathFactService::class.java)
    }

    suspend fun getMathFact() : MathFact {
        return service.getMathFact()
    }

}