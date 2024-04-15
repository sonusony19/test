package com.st.wte.repositories

import android.accounts.NetworkErrorException
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.st.wte.R
import com.st.wte.application.ThisApplication
import com.st.wte.callbacks.ApiListener
import com.st.wte.ui.base.model.ErrorBody
import com.st.wte.utils.getStringResource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException

class Repository {

    private var errorToast: Toast? = null

    fun <T> makeRequest(api: Call<T>, listener: ApiListener<T>) {
        api.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200 && response.body() != null) {
                    listener.onSuccess(response.body()!!)
                } else {
                    handleError(response.code(), response.errorBody())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(getErrorOrShowToast(t, false))
            }

            private fun handleError(code: Int, errorBody: ResponseBody?) {
                try {
                    Gson().fromJson(errorBody?.string(), ErrorBody::class.java)?.let {
                        listener.onError(it.apply { statusCode = code })
                    }
                } catch (e: Exception) {
                    listener.onFailure(getErrorOrShowToast(e, false))
                }
            }
        })
    }

    private fun getErrorOrShowToast(error: Throwable, show: Boolean = true): String {
        Log.e(getStringResource(R.string.app_name), error.localizedMessage, error)
        val message = when (error) {
            is NetworkErrorException -> getStringResource(R.string.no_int_connection)
            is ParseException -> getStringResource(R.string.parsing_error)
            is TimeoutException -> getStringResource(R.string.slow_internet)
            is SocketTimeoutException -> getStringResource(R.string.could_not_reach_server)
            is UnknownHostException -> getStringResource(R.string.could_not_reach_server)
            is ConnectException -> getStringResource(R.string.no_int_connection)
            else -> getStringResource(R.string.api_error)
        }
        if (show && message.isNotEmpty()) {
            errorToast = Toast.makeText(ThisApplication.getContext(), message, Toast.LENGTH_SHORT).also { it.show() }
        }
        return message
    }
}