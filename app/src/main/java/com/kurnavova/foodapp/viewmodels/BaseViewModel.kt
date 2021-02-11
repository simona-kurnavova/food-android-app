package com.kurnavova.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Abstract class for ViewModel with implementation of exception handler.
 */
abstract class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHanlder = CoroutineExceptionHandler{ _, throwable ->
        Log.e(TAG, "Cause: ${throwable.cause}, Message: ${throwable.message}")
    }

    companion object {
        const val TAG = "ViewModel"
    }
}