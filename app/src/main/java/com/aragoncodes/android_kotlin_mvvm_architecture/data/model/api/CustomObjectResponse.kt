package com.aragoncodes.android_kotlin_mvvm_architecture.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CustomObjectResponse {

    @Expose
    @SerializedName("data")
    val data: List<CustomObject>? = null

    @Expose
    @SerializedName("message")
    val message: String? = null

    @Expose
    @SerializedName("status_code")
    val statusCode: String? = null

    override fun equals(other: Any?): Boolean {

        if (this === other) {
            return true
        }
        if (other !is CustomObjectResponse) {
            return false
        }

        val that = other as CustomObjectResponse?

        if (statusCode != that!!.statusCode) {
            return false
        }
        return if (message != that.message) {
            false
        } else data == that.data

    }

    override fun hashCode(): Int {
        var result = statusCode!!.hashCode()
        result = 31 * result + message!!.hashCode()
        result = 31 * result + data!!.hashCode()
        return result
    }

    class CustomObject {

        @Expose
        @SerializedName("id")
        val id: String? = null

        @Expose
        @SerializedName("customObject_url")
        val customObjectUrl: String? = null

        @Expose
        @SerializedName("img_url")
        val coverImgUrl: String? = null

        @Expose
        @SerializedName("created_at")
        val createdAt: String? = null

        @Expose
        @SerializedName("description")
        val description: String? = null

        override fun equals(other: Any?): Boolean {

            if (this === other) {
                return true
            }
            if (other !is CustomObject) {
                return false
            }

            val customObject = other as CustomObject

            return (id == customObject.id)

        }

        override fun hashCode(): Int {
            var result = customObjectUrl!!.hashCode()
            result = 31 * result + id.hashCode()
            return result
        }
    }
}