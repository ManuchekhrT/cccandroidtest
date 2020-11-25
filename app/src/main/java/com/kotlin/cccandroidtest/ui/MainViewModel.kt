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
    val fullName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private var disposable: Disposable? = null

    fun getEstimateWithPerson() {
        disposable?.dispose()
        disposable = Flowable.zip(
            estimateRepository.getEstimate(),
            personRepository.getPerson(),
            BiFunction<Estimate, Person, EstimateWithPerson> { t1: Estimate, t2: Person ->
                EstimateWithPerson(
                    t1.id,
                    t1.company,
                    t1.address,
                    t1.number,
                    t1.revisionNumber,
                    t1.createdDate,
                    t2,
                    t2,
                    t2
                )
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                company.value = it.company
                address.value = it.address
                number.value = it.number
                revisionNumber.value = it.revisionNumber
                createdDate.value = it.createdDate
                fullName.value = it.createdBy?.firstName + " " + it.createdBy?.lastName
            }, {
                it.printStackTrace()
            })
    }

    override fun onCleared() {
        disposable?.dispose()
    }


}