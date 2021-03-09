package com.kurnavova.foodapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.kurnavova.foodapp.R

/**
 * Utils for helping with the network state check and triggering of network error dialog.
 */
object NetworkUtils {

    /**
     * @return true if device is connected to internet (data or wifi), false otherwise
     */
    fun isConnected(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null && (capabilities.hasTransport(TRANSPORT_CELLULAR) || capabilities.hasTransport
                    (TRANSPORT_WIFI))
            ) return true
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    /**
     * Triggers a "network error" snackbar using given [view].
     */
    fun showNetworkErrorDialog(view: View) {
        Snackbar.make(view, R.string.dialog_network_error_title, Snackbar.LENGTH_SHORT)
            .show()
    }
}
