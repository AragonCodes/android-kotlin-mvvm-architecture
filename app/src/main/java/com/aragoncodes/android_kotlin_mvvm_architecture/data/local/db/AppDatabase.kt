package com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.dao.CustomDao
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db.Custom

@Database(entities = [Custom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customDao(): CustomDao

}