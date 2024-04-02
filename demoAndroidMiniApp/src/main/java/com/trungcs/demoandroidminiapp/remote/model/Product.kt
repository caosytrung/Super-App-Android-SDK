package com.trungcs.demoandroidminiapp.remote.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("errorDescription")
    @Expose
    val errorDescription: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("sku")
    @Expose
    val sku: String,
    @SerializedName("image")
    @Expose
    val image: String? = null,

    )