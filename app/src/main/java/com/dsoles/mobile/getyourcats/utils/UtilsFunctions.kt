package com.dsoles.mobile.getyourcats.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket


fun hasInternetConnection(): Boolean {
    return try {
        // Attempt to open a socket to a reliable server
        Socket().use { socket ->
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            true
        }
    } catch (e: IOException) {
        false
    }
}

fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    // First, check for connectivity using the method described previously
    if (!isConnected(context)) return false

    // Then, check for actual internet access
    return hasInternetConnection()
}