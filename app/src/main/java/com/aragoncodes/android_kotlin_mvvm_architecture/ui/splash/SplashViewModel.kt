package com.aragoncodes.android_kotlin_mvvm_architecture.ui.splash

import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider

class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<Splash_.Navigator>(dataManager, schedulerProvider), Splash_.ViewModel {

    // VARIABLES
    // ====================================================

    // FUNCTIONS
    // ====================================================
    // => Primary
    override fun startSeeding() {
        compositeDisposable.add(dataManager
            .seedDatabaseCustoms()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ decideNextActivity() }, { throwable -> decideNextActivity() })
        )
    }

    private fun decideNextActivity() {
        if (dataManager.currentUserId == null) {
            navigator!!.openLoginActivity()
        } else {
            navigator!!.openMainActivity()
        }
    }
}