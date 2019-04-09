package com.aragoncodes.android_kotlin_mvvm_architecture

import android.app.Activity
import android.app.Application
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse.Address
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse.ParseClass
import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse.User
import com.aragoncodes.android_kotlin_mvvm_architecture.di.component.DaggerAppComponent
import com.google.android.libraries.places.api.Places
import com.parse.Parse
import com.parse.ParseInstallation
import com.parse.ParseObject
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mCalligraphyConfig: CalligraphyConfig

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        // Dependency Injection
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        // Logger
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Network
        AndroidNetworking.initialize(applicationContext)
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }

        // Fonts
        CalligraphyConfig.initDefault(mCalligraphyConfig)

        // Google Places
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, getString(R.string.places_api_key))
        }

        // Parse Android SDK
        ParseObject.registerSubclass(Address::class.java)
        ParseObject.registerSubclass(User::class.java)
        ParseObject.registerSubclass(ParseClass::class.java)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.parse_app_id))
                .clientKey(getString(R.string.parse_client_key))
                .server(getString(R.string.parse_server_url))
                .build()
        )

        ParseInstallation.getCurrentInstallation().saveInBackground()
    }
}