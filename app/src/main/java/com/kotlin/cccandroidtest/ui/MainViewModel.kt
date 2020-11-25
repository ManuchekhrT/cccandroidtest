package com.kotlin.cccandroidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.cccandroidtest.data.db.model.Estimate
import com.kotlin.cccandroidtest.data.db.model.EstimateWithPerson
import com.kotlin.cccandroidtest.data.db.model.Person
import com.kotlin.cccandroidtest.data.repository.EstimateRepository
import com.kotlin.cccandroidtest.data.repository.PersonRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val personRepository: PersonRepository,
    private val estimateRepository: EstimateRepository
) : ViewModel() {

    val company: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val address: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val number: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val revisionNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val createdDate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val createdBy: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val requester: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val contact: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private var disposable: Disposable? = null

    fun getEstimateWithPerson() {
        disposable?.dispose()
        disposable = estimateRepository.getEstimate()
            .subscribeOn(Schedulers.io())
            .map {
                EstimateWithPerson(
                    it.id,
                    it.company,
                    it.address,
                    it.number,
                    it.revisionNumber,
                    it.createdDate,
                    personRepository.getPerson(it.createdBy),
                    personRepository.getPerson(it.requestedBy),
                    personRepository.getPerson(it.contact)
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                company.value = it.company
                address.value = it.address
                number.value = it.number
                revisionNumber.value = it.revisionNumber
                createdDate.value = it.createdDate
                createdBy.value = it.personConnWithCreatedBy?.firstName + " " + it.personConnWithCreatedBy?.lastName
                requester.value = it.personConnWithRequestedBy?.firstName + " " + it.personConnWithRequestedBy?.lastName
                contact.value = it.personConnWithContact?.firstName + " " + it.personConnWithContact?.lastName
            }, {
                it.printStackTrace()
            })

    }

    override fun onCleared() {
        disposable?.dispose()
    }


}