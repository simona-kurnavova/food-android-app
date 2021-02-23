package com.kurnavova.foodapp.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.kurnavova.foodapp.utils.NetworkUtils
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Abstract class for ViewModel with implementation of exception handler.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e(TAG, "Cause: ${throwable.cause}, Message: ${throwable.message}")
    }

    companion object {
        const val TAG = "ViewModel"
    }
}