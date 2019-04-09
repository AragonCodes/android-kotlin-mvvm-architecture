package com.aragoncodes.android_kotlin_mvvm_architecture.ui.splash

class Splash_ {

    interface Navigator {

        fun openLoginActivity()

        fun openMainActivity()

    }

    interface ViewModel {

        fun startSeeding()

    }

}