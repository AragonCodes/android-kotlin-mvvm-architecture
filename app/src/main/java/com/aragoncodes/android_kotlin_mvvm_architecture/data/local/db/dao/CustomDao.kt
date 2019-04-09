package com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db.Custom

@Dao
interface CustomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(custom: Custom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(customs: List<Custom>)

    @Query("SELECT * FROM customs")
    fun loadAll(): List<Custom>
}
