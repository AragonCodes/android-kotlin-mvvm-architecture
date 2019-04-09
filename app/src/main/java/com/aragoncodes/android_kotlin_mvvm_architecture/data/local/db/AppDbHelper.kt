package com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db

import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db.Custom
import io.reactivex.Observable
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject
    constructor(
        private val mAppDatabase: AppDatabase
    )
    : DbHelper {

    override fun getAllCustoms(): Observable<List<Custom>> {
        return Observable.fromCallable<List<Custom>> { mAppDatabase.customDao().loadAll() }
    }

    override fun isCustomEmpty(): Observable<Boolean> {
        return Observable.fromCallable { mAppDatabase.customDao().loadAll().isEmpty() }
    }

    override fun saveCustom(custom: Custom): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.customDao().insert(custom)
            true
        }
    }

    override fun saveCustomList(customList: List<Custom>): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.customDao().insertAll(customList)
            true
        }
    }

}