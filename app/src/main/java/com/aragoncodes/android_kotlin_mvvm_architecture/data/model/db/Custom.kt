package com.aragoncodes.android_kotlin_mvvm_architecture.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "customs")
class Custom {

    @Expose
    @PrimaryKey
    var id: Long? = null

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAt: String? = null

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = null

    @Expose
    @SerializedName("custom_text")
    @ColumnInfo(name = "custom_text")
    var customText: String? = null

}