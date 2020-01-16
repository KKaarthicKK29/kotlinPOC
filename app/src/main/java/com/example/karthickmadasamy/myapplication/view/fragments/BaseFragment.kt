package com.example.karthickmadasamy.myapplication.view.fragments

import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

import com.example.karthickmadasamy.myapplication.R


/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

open class BaseFragment : Fragment() {

    /**
     * checking network connection. if connection is available, it will return true else false
     */
    // return true if network connection available
    val isNetworkAvailable: Boolean
        get() {
            Log.d(TAG, "isNetworkAvailable")
            val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

    /**
     * display error message if no network connection
     */
    fun errorDialog(msg: Int, exitapp: Boolean) {
        Log.d(TAG, "errorDialog")
        try {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok) { dialog, id ->
                        dialog.cancel()
                        if (exitapp)
                            activity!!.finish()
                    }
            val alert = builder.create()
            alert.show()
        } catch (e: Exception) {
            Log.e(TAG, "Error in showing error dialog box : " + e.message)
        }

    }

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
    }
}
