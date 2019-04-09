package com.aragoncodes.android_kotlin_mvvm_architecture.data.remote

import com.aragoncodes.android_kotlin_mvvm_architecture.di.annotation.ApiInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHeader @Inject
constructor(val publicApiHeader: PublicApiHeader, val protectedApiHeader: ProtectedApiHeader) {

    class ProtectedApiHeader(
        @field:Expose
        @field:SerializedName("api_key")
        var apiKey: String?,
        @field:Expose
        @field:SerializedName("user_id")
        var userId: String?
    )

    class PublicApiHeader @Inject
    constructor(
        @param:ApiInfo
        @field:Expose
        @field:SerializedName("api_key")
        var apiKey: String?
    )

}