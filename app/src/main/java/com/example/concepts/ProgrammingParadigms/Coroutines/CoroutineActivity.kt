package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        CoroutineScope(Dispatchers.Main).launch {
            task1()           // this arrows shows that here this task will be suspended , meaning will be paused as it is a IO operation
                              // and until IO finishes , other coroutine which is also running on main thread will be executed
        }

        CoroutineScope(Dispatchers.Main).launch {
            task2()
        }
    }

    suspend fun task1()
    {
        Log.d(TAG,"task 1 started")
        yield()                             // yield is a inbuilt suspend function
        Log.d(TAG,"task 1 ended")
    }

    suspend fun task2()
    {
        Log.d(TAG,"task 2 started")
        yield()                             // yield is a inbuilt suspend function
        Log.d(TAG,"task 2 ended")
    }
}