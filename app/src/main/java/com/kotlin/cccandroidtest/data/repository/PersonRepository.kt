package com.kotlin.cccandroidtest.data.repository

import com.kotlin.cccandroidtest.data.db.dao.PersonDao
import com.kotlin.cccandroidtest.data.db.model.Person
import io.reactivex.Flowable

class PersonRepository(private val personDao: PersonDao) {
    fun getPerson(id: String?): Person = personDao.getPerson(id)
}