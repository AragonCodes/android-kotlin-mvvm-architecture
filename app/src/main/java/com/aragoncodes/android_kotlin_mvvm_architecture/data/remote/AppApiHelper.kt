package com.aragoncodes.android_kotlin_mvvm_architecture.data.remote

import com.aragoncodes.android_kotlin_mvvm_architecture.data.model.api.CustomObjectResponse
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor(
        override val apiHeader: ApiHeader
    ): ApiHelper {

    override val customApiCall: Single<CustomObjectResponse>
    get() = Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_API_WEBSITE)
        .addHeaders(apiHeader.protectedApiHeader)
        .build()
        .getObjectSingle<CustomObjectResponse>(CustomObjectResponse::class.java)

}