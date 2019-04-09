package com.aragoncodes.android_kotlin_mvvm_architecture.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.aragoncodes.android_kotlin_mvvm_architecture.data.AppDataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.util.UserUtils
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider
import com.parse.ParseInstallation
import com.parse.ParseUser
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {

    val isLoading = ObservableBoolean()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() = mNavigator!!.get()
        set(navigator) {
            this.mNavigator = WeakReference<N>(navigator)
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

}