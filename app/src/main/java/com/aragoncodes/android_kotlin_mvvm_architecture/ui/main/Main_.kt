package com.aragoncodes.android_kotlin_mvvm_architecture.ui.main

class Main_ {

    interface Navigator {

        fun handleError(throwable: Throwable)

        fun logout()

        fun openLoginActivity()

    }

    interface ViewModel {

        fun logout()

    }

}