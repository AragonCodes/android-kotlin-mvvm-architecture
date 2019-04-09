package com.aragoncodes.android_kotlin_mvvm_architecture.di.builder

import com.aragoncodes.android_kotlin_mvvm_architecture.ui.login.LoginActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.main.MainActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity
}