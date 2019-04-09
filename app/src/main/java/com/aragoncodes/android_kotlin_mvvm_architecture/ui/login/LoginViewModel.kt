package com.aragoncodes.android_kotlin_mvvm_architecture.ui.login

import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse.User
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseViewModel

import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider
import com.parse.ParseInstallation
import com.parse.ParseUser
import timber.log.Timber

class LoginViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<Login_.Navigator>(dataManager, schedulerProvider), Login_.ViewModel {

    // VARIABLES
    // ====================================================

    // FUNCTIONS
    // ====================================================
    // => Primary
    override fun login(email: String, password: String) {

        setIsLoading(true)

        ParseUser.logInInBackground(email, password) { user, e ->

            setIsLoading(false)

            if (user != null) {

                Timber.d("Succes on: userLogin")
                val loggedUser: User = (user as User)

                // Update SharedPreferences
                dataManager.currentUserId = loggedUser.objectId
                dataManager.currentUserName = loggedUser.name
                dataManager.currentUserEmail = loggedUser.email
                dataManager.currentUserProfilePicUrl = loggedUser.getProfilePicUrl()

                // Subscribe device from user notifications channel
                val pinInstallation = ArrayList<String>()
                val installation  = ParseInstallation.getCurrentInstallation()
                pinInstallation.add("user_${loggedUser.objectId}")
                installation.put("channels",pinInstallation)
                installation.save()

                navigator!!.openMainActivity()

            } else {
                Timber.e("Fail on: userLogin", e)
                navigator!!.handleError(e)
            }
        }

    }

}