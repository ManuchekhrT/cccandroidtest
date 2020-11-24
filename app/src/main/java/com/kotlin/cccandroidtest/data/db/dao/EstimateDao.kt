package com.kotlin.cccandroidtest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.cccandroidtest.data.db.model.Estimate
import com.kotlin.cccandroidtest.data.db.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface EstimateDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg estimates: Estimate)

    @Query("SELECT * FROM estimate")
    fun getAll(): List<Estimate>

    @Query("SELECT * FROM estimate WHERE id = :id")
    fun getEstimateById(id: Int): Estimate
}