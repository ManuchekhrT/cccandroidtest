package com.kotlin.cccandroidtest

import android.app.Application
import com.kotlin.cccandroidtest.data.db.AppDatabase
import com.kotlin.cccandroidtest.data.repository.EstimateRepository
import com.kotlin.cccandroidtest.data.repository.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    private val appScope = CoroutineScope(SupervisorJob())
    private val appDatabase by lazy { AppDatabase.getAppDatabase(this, appScope) }
    val personRepository by lazy { PersonRepository(appDatabase.personDao()) }
    val estimateRepository by lazy { EstimateRepository(appDatabase.estimateDao()) }
}