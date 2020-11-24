package com.kotlin.cccandroidtest.data.repository

import com.kotlin.cccandroidtest.data.db.dao.EstimateDao
import com.kotlin.cccandroidtest.data.db.dao.PersonDao
import com.kotlin.cccandroidtest.data.db.model.EstimateWithPerson
import com.kotlin.cccandroidtest.data.db.model.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppRepository(private val estimateDao: EstimateDao, private val personDao: PersonDao) {

    //kotlin coroutines, retrieve an object for the estimate and link the person object to the created_by, requested_by and contact fields.
    fun getEstimate(): EstimateWithPerson? {
        var estimateWithPerson: EstimateWithPerson? = null
        GlobalScope.launch {
            estimateWithPerson = estimateDao.getAll().map {
                val person: Person? = personDao.getAll().firstOrNull()
                EstimateWithPerson(
                    it.id,
                    it.company,
                    it.address,
                    it.number,
                    it.revisionNumber,
                    it.createdDate,
                    person,
                    person,
                    person
                )
            }.firstOrNull()
        }
        return estimateWithPerson
    }
}