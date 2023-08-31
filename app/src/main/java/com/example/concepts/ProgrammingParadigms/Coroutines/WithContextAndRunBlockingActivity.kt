package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class WithContextAndRunBlockingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_context_and_run_blocking)

//        GlobalScope.launch {
//            execute()
//        }

        // runBlock , blocks the thread until the all coroutines gets executed

        runBlocking {     // this keep the main thread alives until the block get executed

            launch {
                delay(2000)
                Log.d(TAG,"World")
            }
              execute()
            Log.d(TAG,"Hello ")
        }
    }

    // lets say you want to run some code inside a coroutine on a different thread, then we
    // use withcontext to change the context(thread) , and until the code inside the withcontext
    // is not finished executing , the further execution will be blocked
    suspend fun execute()
    {
        Log.d(TAG,"Before")
        withContext(Dispatchers.IO)      // this is used to change the context inside a coroutine
        {
            delay(1000)
            Log.d(TAG,"Inside")
        }
        Log.d(TAG,"outside")


        // output order --  Before Inside outside
    }
}