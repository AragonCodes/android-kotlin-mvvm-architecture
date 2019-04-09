package com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db

import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db.Custom
import io.reactivex.Observable

interface DbHelper {

    fun getAllCustoms(): Observable<List<Custom>>

    fun isCustomEmpty(): Observable<Boolean>

    fun saveCustom(custom: Custom): Observable<Boolean>

    fun saveCustomList(customList: List<Custom>): Observable<Boolean>

}