package com.st.wte.ui.base.model

import androidx.annotation.Keep

@Keep
data class ErrorBody(
        var status: Boolean? = null,
        var statusCode: Int? = null,
        var message: String? = null,
)