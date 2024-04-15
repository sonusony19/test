package com.st.wte.ui.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.st.wte.network.ApiService
import com.st.wte.repositories.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseViewModel : ViewModel(), KoinComponent {
    protected val TAG = "LOGGER"
    internal val repository: Repository by inject()
    internal val apiService: ApiService by inject()
    internal val loading = MutableLiveData<Boolean?>(null)
   /* internal var error = MutableLiveData<ErrorBody?>(null)
    internal var apiResponse = MutableLiveData<BaseResponse<*>>()

    init {
        repository.setHandlers(apiResponse, error, loading)
    }

    protected fun setApiID(apiId: String) = repository.setApiId(apiId)*/

    fun toggleLoader(show: Boolean) {
        loading.value = show
    }
}