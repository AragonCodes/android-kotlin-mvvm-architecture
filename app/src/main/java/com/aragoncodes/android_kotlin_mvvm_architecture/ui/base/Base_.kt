package com.aragoncodes.android_kotlin_mvvm_architecture.ui.base

import android.content.Context

class Base_ {

    interface View {

        val isNetworkConnected: Boolean

        fun showLoading()

        fun hideLoading()

        fun hideKeyboard()

        fun showError(message: String?)

        fun showMessage(message: String?)

    }

    interface ViewModel {

        fun login(username: String?, password: String?)

        fun logout()

    }
}