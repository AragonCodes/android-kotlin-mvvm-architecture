package com.aragoncodes.android_kotlin_mvvm_architecture.data

import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.DbHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.pref.PreferencesHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.ApiHelper
import io.reactivex.Observable

interface DataManager : DbHelper, PreferencesHelper, ApiHelper {

    fun seedDatabaseCustoms(): Observable<Boolean>

    fun updateApiHeader(userId: String?, accessToken: String?)

    fun updateUserInfo(
        userId: String?,
        userName: String,
        email: String,
        profilePicPath: String
    )

}