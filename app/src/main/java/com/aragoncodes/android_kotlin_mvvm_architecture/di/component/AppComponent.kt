package com.aragoncodes.android_kotlin_mvvm_architecture.di.component

import android.app.Application
import com.aragoncodes.android_kotlin_mvvm_architecture.App
import com.aragoncodes.android_kotlin_mvvm_architecture.di.builder.ActivityBuilder
import com.aragoncodes.android_kotlin_mvvm_architecture.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}