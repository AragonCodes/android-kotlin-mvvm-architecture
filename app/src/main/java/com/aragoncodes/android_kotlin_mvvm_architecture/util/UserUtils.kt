package com.aragoncodes.android_kotlin_mvvm_architecture.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.data.AppDataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse.User
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseActivity
import com.parse.ParseInstallation
import com.parse.ParseObject
import com.parse.ParseUser

object UserUtils {

    val currentUser: User?
        get() = ParseUser.getCurrentUser() as User

//
//    fun logout(appDataManager: AppDataManager, onCompleteCallback: () -> Unit ) {
//
//        // Update SharedPreferences
//        appDataManager.currentUserId = null
//        appDataManager.currentUserName = null
//        appDataManager.currentUserEmail = null
//        appDataManager.currentUserProfilePicUrl = null
//
//        // Unsubscribe device from user notifications channel
//        val pinInstallation = ArrayList<String>()
//        val installation  = ParseInstallation.getCurrentInstallation()
//        pinInstallation.add("")
//        installation.put("channels",pinInstallation)
//        installation.save()
//
//        // Logout user
//        ParseUser.logOut()
//
//        onCompleteCallback()
//    }

}