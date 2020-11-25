package com.kotlin.cccandroidtest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.cccandroidtest.data.db.model.Estimate
import io.reactivex.Flowable

@Dao
interface EstimateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg estimate: Estimate)

    @Query("SELECT * from estimate LIMIT 1")
    fun getEstimate(): Flowable<Estimate>

    @Query("DELETE FROM estimate")
    suspend fun deleteAll()

}