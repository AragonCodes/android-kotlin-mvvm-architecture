package com.aragoncodes.android_kotlin_mvvm_architecture.ui.main

import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider
import com.parse.ParseInstallation
import com.parse.ParseUser

class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<Main_.Navigator>(dataManager, schedulerProvider), Main_.ViewModel {

    // VARIABLES
    // ====================================================

    // FUNCTIONS
    // ====================================================
    // => Primary
    override fun logout() {

        setIsLoading(true)

        // Update SharedPreferences
        dataManager.currentUserId = null
        dataManager.currentUserName = null
        dataManager.currentUserEmail = null
        dataManager.currentUserProfilePicUrl = null

        // Unsubscribe device from user notifications channel
        val pinInstallation = ArrayList<String>()
        val installation  = ParseInstallation.getCurrentInstallation()
        pinInstallation.add("")
        installation.put("channels",pinInstallation)
        installation.save()

        // Logout user
        ParseUser.logOut()

        setIsLoading(false)

        navigator!!.openLoginActivity()

    }
}