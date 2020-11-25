package com.kotlin.cccandroidtest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.cccandroidtest.data.db.model.Person
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg person: Person)

    @Query("SELECT * from person LIMIT 1")
    fun getPerson(): Flowable<Person>

    @Query("DELETE FROM person")
    suspend fun deleteAll()
}