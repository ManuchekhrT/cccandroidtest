package com.kotlin.cccandroidtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.cccandroidtest.data.db.dao.EstimateDao
import com.kotlin.cccandroidtest.data.db.dao.PersonDao
import com.kotlin.cccandroidtest.data.db.model.Estimate
import com.kotlin.cccandroidtest.data.db.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Estimate::class, Person::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun estimateDao(): EstimateDao
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private const val DB_NAME = "cccandroidtest_db"

        fun getAppDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val estimateDao = database.estimateDao()
                    val personDao = database.personDao()

                    estimateDao.deleteAll()
                    personDao.deleteAll()

                    /*
                          "estimate": {
                                       "id": "c574b0b4-bdef-4b92-a8f0-608a86ccf5ab",
                                       "company": "Placebo Secondary School",
                                       "address": "32 Commissioners Road East",
                                       "number": 32,
                                       "revision_number": 3,
                                       "created_date": "2020-08-22 15:23:54",
                                       "created_by": "85a57f85-a52d-4137-a0d1-62e61362f716",
                                       "requested_by": "85a57f85-a52d-4137-a0d1-62e61362f716",
                                       "contact": "85a57f85-a52d-4137-a0d1-62e61362f716"
                             },
                           "person": {
                                       "id": "85a57f85-a52d-4137-a0d1-62e61362f716",
                                       "first_name": "Joseph",
                                       "last_name": "Sham",
                                       "email": "joseph.sham@fake.fake",
                                       "phone_number": "123-456-7890"
                           }
                    */
                    val estimate = Estimate(
                        "c574b0b4-bdef-4b92-a8f0-608a86ccf5ab",
                        "Placebo Secondary School",
                        "32 Commissioners Road East",
                        32,
                        3,
                        "2020-08-22 15:23:54",
                        "85a57f85-a52d-4137-a0d1-62e61362f716",
                        "85a57f85-a52d-4137-a0d1-62e61362f716",
                        "85a57f85-a52d-4137-a0d1-62e61362f716"
                    )
                    estimateDao.insert(estimate)

                    val person = Person(
                        "85a57f85-a52d-4137-a0d1-62e61362f716",
                        "Joseph",
                        "Sham",
                        "joseph.sham@fake.fake",
                        "123-456-7890"
                    )
                    personDao.insert(person)
                }
            }
        }
    }
}