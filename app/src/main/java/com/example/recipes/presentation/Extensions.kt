package com.example.recipes.presentation

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.StringRes
import com.example.recipes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object InternetConnection {
    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                true
            } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }
}

object MaterialDialog {
    fun showDialog(
        @StringRes title: Int,
        @StringRes message: Int,
        context: Context,
        setCancel: Boolean,
        action: () -> Unit
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                R.string.retry
            ) { _, _ -> action.invoke() }
            .setCancelable(setCancel)
            .show()
    }
}
