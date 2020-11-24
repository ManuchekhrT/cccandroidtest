package com.kotlin.cccandroidtest

import android.app.Application
import com.kotlin.cccandroidtest.data.db.AppDatabase
import com.kotlin.cccandroidtest.data.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {

    private val appScope = CoroutineScope(SupervisorJob())
    val appDatabase by lazy { AppDatabase.getAppDatabase(this, appScope) }
    val appRepository by lazy { AppRepository(appDatabase.estimateDao(), appDatabase.personDao()) }
}