package com.aragoncodes.android_kotlin_mvvm_architecture.data.remote

import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.api.CustomObjectResponse
import io.reactivex.Single

interface ApiHelper {

    val apiHeader: ApiHeader

    val customApiCall: Single<CustomObjectResponse>

}