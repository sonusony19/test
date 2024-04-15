package com.st.wte.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.MutableLiveData

class NetworkManager(private val context: Context) : ConnectivityManager.NetworkCallback() {

    private val networkStatus = MutableLiveData<Boolean?>(null)
    private var isFirstCheck = true

    fun register(): MutableLiveData<Boolean?> {
        (context.getSystemService(Activity.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.registerDefaultNetworkCallback(this)
        return networkStatus
    }

    fun unRegister() {
        (context.getSystemService(Activity.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        if (isFirstCheck) isFirstCheck = false
        else networkStatus.postValue(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkStatus.postValue(false)
    }

    override fun onUnavailable() {
        super.onUnavailable()
        if (networkStatus.value != false) networkStatus.postValue(false)
    }
}