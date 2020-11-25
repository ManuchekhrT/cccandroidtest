package com.kotlin.cccandroidtest.data.repository

import com.kotlin.cccandroidtest.data.db.dao.EstimateDao
import com.kotlin.cccandroidtest.data.db.model.Estimate
import io.reactivex.Flowable

class EstimateRepository(private val estimateDao: EstimateDao) {
    fun getEstimate(): Flowable<Estimate> = estimateDao.getEstimate()
}