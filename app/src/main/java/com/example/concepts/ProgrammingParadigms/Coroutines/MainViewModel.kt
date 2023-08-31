package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel()  {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true)
            {
                delay(500)
                Log.d(TAG,"Hello")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"View Model Destroyed")
    }
}