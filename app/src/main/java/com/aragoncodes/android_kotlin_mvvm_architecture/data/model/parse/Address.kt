package com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse

import com.parse.ParseClassName
import com.parse.ParseGeoPoint
import com.parse.ParseObject

@ParseClassName("Address")
class Address : ParseObject() {

    var completeAddress: String?
        get() = getString("completeAddress")
        set(completeAddress) = put("completeAddress", completeAddress)

    var location: ParseGeoPoint?
        get() = getParseGeoPoint("location")
        set(location) = put("location", location)

    fun getLatitude(): Double? {
        return if (location == null) null
        else
            location!!.latitude
    }

    fun getLongitude(): Double? {
        return if (location == null) null
        else
            location!!.longitude
    }

}