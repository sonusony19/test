package com.st.wte.callbacks

import com.st.wte.ui.base.model.ErrorBody

interface ApiListener<T> {
    fun onSuccess(response: T)
    fun onError(error: ErrorBody)
    fun onFailure(errorMessage: String)
}