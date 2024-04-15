package com.st.wte.ui.main.model
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName


@Keep
data class Post(
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)