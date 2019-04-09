package com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.util.*

@ParseClassName("_User")
class User : ParseUser() {

    var name: String?
        get() = getString("name")
        set(name) = put("name", name)

    var phone: String?
        get() = getString("phone")
        set(phone) = put("phone", phone)

    var address: Address?
        get() = getParseObject("address") as Address
        set(address) = put("address", address)

    var status: Boolean?
        get() = getBoolean("status")
        set(status) = put("status", status)

    var datetimeAt: Date?
        get() = getDate("datetime")
        set(date) = put("datetime", date)

    var arrayField: List<String>
        get() = getList("arrayField")
        set(arrayField) = put("arrayField", arrayField)

    var img: ParseFile?
        get() = getParseFile("img")
        set(img) = put("img", img)

    fun getProfilePicUrl(): String? {
        return if (img == null) null
        else
            img!!.url
    }

}