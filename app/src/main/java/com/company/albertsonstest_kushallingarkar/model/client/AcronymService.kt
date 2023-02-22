package com.company.albertsonstest_kushallingarkar

import com.company.albertsonstest_kushallingarkar.model.data.Acronym
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("dictionary.py")
    suspend fun getAcronymMeanings(@Query("sf") acronym: String): List<Acronym>
}

    object RetrofitClient {

    private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): AcronymService {
        return retrofit.create(AcronymService::class.java)
    }
}