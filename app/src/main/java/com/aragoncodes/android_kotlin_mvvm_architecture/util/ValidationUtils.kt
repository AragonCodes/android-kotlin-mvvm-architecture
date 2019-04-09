package com.aragoncodes.android_kotlin_mvvm_architecture.util

import android.util.Patterns

object ValidationUtils {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}