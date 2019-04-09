package com.aragoncodes.android_kotlin_mvvm_architecture.data.model.parse

import com.parse.ParseClassName
import com.parse.ParseObject
import java.util.*

@ParseClassName("ParseClass")
class ParseClass : ParseObject() {

    var name: String?
        get() = getString("name")
        set(name) = put("name", name)

    var status: Boolean?
        get() = getBoolean("status")
        set(status) = put("status", status)

    var datetimeAt: Date?
        get() = getDate("datetime")
        set(date) = put("datetime", date)

    var arrayField: List<String>
        get() = getList("arrayField")
        set(arrayField) = put("arrayField", arrayField)

}