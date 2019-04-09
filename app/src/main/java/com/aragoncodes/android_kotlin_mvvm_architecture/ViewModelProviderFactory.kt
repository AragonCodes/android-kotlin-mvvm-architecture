package com.aragoncodes.android_kotlin_mvvm_architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.login.LoginViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.main.MainViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.splash.SplashViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject
constructor(
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(dataManager, schedulerProvider) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataManager, schedulerProvider) as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(dataManager, schedulerProvider) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}