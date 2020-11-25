package com.kotlin.cccandroidtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.cccandroidtest.data.repository.EstimateRepository
import com.kotlin.cccandroidtest.data.repository.PersonRepository

class MainViewModelFactory(
    private val personRepository: PersonRepository,
    private val estimateRepository: EstimateRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(personRepository, estimateRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}