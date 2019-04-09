package com.aragoncodes.android_kotlin_mvvm_architecture.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.aragoncodes.android_kotlin_mvvm_architecture.BuildConfig
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.aragoncodes.android_kotlin_mvvm_architecture.data.AppDataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.data.DataManager
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.AppDatabase
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.AppDbHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.db.DbHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.pref.AppPreferencesHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.local.pref.PreferencesHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.ApiHeader
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.ApiHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.data.remote.AppApiHelper
import com.aragoncodes.android_kotlin_mvvm_architecture.di.annotation.ApiInfo
import com.aragoncodes.android_kotlin_mvvm_architecture.di.annotation.DatabaseInfo
import com.aragoncodes.android_kotlin_mvvm_architecture.di.annotation.PreferenceInfo
import com.aragoncodes.android_kotlin_mvvm_architecture.util.AppConstants
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.AppSchedulerProvider
import com.aragoncodes.android_kotlin_mvvm_architecture.util.rx.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @ApiInfo
    internal fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Provides
    @Singleton
    internal fun provideProtectedApiHeader(
        @ApiInfo apiKey: String,
        preferencesHelper: PreferencesHelper
    ): ApiHeader.ProtectedApiHeader {
        return ApiHeader.ProtectedApiHeader(
            apiKey,
            preferencesHelper.currentUserId
        )
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

}