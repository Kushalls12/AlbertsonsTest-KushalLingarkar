package com.company.albertsonstest_kushallingarkar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.albertsonstest_kushallingarkar.model.data.Acronym
import com.company.albertsonstest_kushallingarkar.model.repository.AcronymRepository
import kotlinx.coroutines.launch
/**

The AcronymViewModel class provides the necessary data and functionality required by the UI for the AcronymSearch feature
 */
class AcronymViewModel : ViewModel() {

    private val acronymRepository = AcronymRepository()

    private val _acronymMeanings = MutableLiveData<List<Acronym>>()
    val acronymMeanings: LiveData<List<Acronym>>
        get() = _acronymMeanings

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    /**
    Search for the meanings of an acronym
    @param acronym the acronym to search for
     */
    fun searchAcronym(acronym: String) {
        viewModelScope.launch {
            try {
                val result = acronymRepository.getAcronymMeanings(acronym)
                _acronymMeanings.value = result
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
