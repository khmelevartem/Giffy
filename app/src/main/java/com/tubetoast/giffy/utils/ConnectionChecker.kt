package com.tubetoast.giffy.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectionChecker(context: Context) {

    val status get() = _status.asStateFlow()
    private val _status: MutableStateFlow<Boolean>

    init {
        _status = MutableStateFlow(false)
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    _status.value = true
                }

                override fun onLost(network: Network) {
                    _status.value = false
                }

                override fun onUnavailable() {
                    _status.value = false
                }
            }
        )
    }

    fun isConnected() = status.value

}