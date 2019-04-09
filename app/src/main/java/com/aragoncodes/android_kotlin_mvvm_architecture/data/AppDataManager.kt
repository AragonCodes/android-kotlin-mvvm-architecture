package com.aragoncodes.android_kotlin_mvvm_architecture.data

import android.content.Context
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.DbHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.pref.PreferencesHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.api.CustomObjectResponse
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db.Custom
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.ApiHeader
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.ApiHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.util.AppConstants
import com.aragoncodes.android_kotlin_mvvm_architecture.util.CommonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject
constructor(
    private val mContext: Context,
    private val mDbHelper: DbHelper,
    private val mPreferencesHelper: PreferencesHelper,
    private val mApiHelper: ApiHelper,
    private val mGson: Gson
) : DataManager {

    override fun getAllCustoms(): Observable<List<Custom>> {
        return mDbHelper.getAllCustoms()
    }

    override fun isCustomEmpty(): Observable<Boolean> {
        return mDbHelper.isCustomEmpty()
    }

    override fun saveCustom(custom: Custom): Observable<Boolean> {
        return mDbHelper.saveCustom(custom)
    }

    override fun saveCustomList(customList: List<Custom>): Observable<Boolean> {
        return mDbHelper.saveCustomList(customList)
    }

    override var currentUserEmail: String?
        get() = mPreferencesHelper.currentUserEmail
        set(currentUserEmail) {mPreferencesHelper.currentUserEmail = currentUserEmail}
    override var currentUserId: String?
        get() = mPreferencesHelper.currentUserId
        set(currentUserId) {mPreferencesHelper.currentUserId = currentUserId}
    override var currentUserName: String?
        get() = mPreferencesHelper.currentUserName
        set(currentUserName) {mPreferencesHelper.currentUserName = currentUserName}
    override var currentUserProfilePicUrl: String?
        get() = mPreferencesHelper.currentUserProfilePicUrl
        set(currentUserProfilePicUrl) {mPreferencesHelper.currentUserProfilePicUrl = currentUserProfilePicUrl}
    override val apiHeader: ApiHeader
        get() = mApiHelper.apiHeader
    override val customApiCall: Single<CustomObjectResponse>
        get() = mApiHelper.customApiCall

    // **********

    override fun seedDatabaseCustoms(): Observable<Boolean> {
        return mDbHelper.isCustomEmpty()
            .concatMap { isEmpty ->
                if (isEmpty) {
                    val type = object : TypeToken<List<Custom>>(){}.type
                    val customList: List<Custom> = mGson.fromJson(
                        CommonUtils.loadJSONFromAsset(mContext, AppConstants.SEED_DATABASE_CUSTOMS),
                        type
                    )
                    saveCustomList(customList)
                }
                Observable.just(false)
            }
    }

    override fun updateApiHeader(userId: String?, accessToken: String?) {
        mApiHelper.apiHeader.protectedApiHeader.userId = userId
    }

    override fun updateUserInfo(
        userId: String?,
        userName: String,
        email: String,
        profilePicPath: String
    ) {

        mPreferencesHelper.currentUserId = userId
        mPreferencesHelper.currentUserName = userName
        mPreferencesHelper.currentUserEmail = email
        mPreferencesHelper.currentUserProfilePicUrl = profilePicPath

    }
}
