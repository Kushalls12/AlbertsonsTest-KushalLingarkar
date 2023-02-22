package com.company.albertsonstest_kushallingarkar.model.repository

import com.company.albertsonstest_kushallingarkar.model.data.Acronym
import com.company.albertsonstest_kushallingarkar.AcronymService
import com.company.albertsonstest_kushallingarkar.RetrofitClient
import com.company.albertsonstest_kushallingarkar.model.data.LongForm

class AcronymRepository {

    private val acronymService: AcronymService = RetrofitClient.create()

    suspend fun getAcronymMeanings(acronym: String): List<Acronym> {
        return acronymService.getAcronymMeanings(acronym)
            .map { response ->
                Acronym(
                    response.sf,
                    response.lfs.map { longForm ->
                        LongForm(
                            longForm.lf,
                            longForm.freq,
                            longForm.since)
                    }
                )
            }
    }
}