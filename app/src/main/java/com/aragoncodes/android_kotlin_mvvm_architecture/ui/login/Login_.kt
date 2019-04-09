package com.aragoncodes.android_kotlin_mvvm_architecture.ui.login

class Login_ {

    interface Navigator {

        fun handleError(throwable: Throwable)

        fun login()

        fun openMainActivity()

    }

    interface ViewModel {

        fun login(email: String, password: String)

    }

}