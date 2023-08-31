package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoroutineContextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_context)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }

    }

    // we can launch a coroutine inside a coroutine

    fun execute()
    {
        // this parent job wont finish until all its child jobs gets completed
        // when the parent job get cancelled, all the child jobs also gets cancelled
        
        var parentJob = GlobalScope.launch(Dispatchers.Main){
                Log.d(TAG,"Parent - $coroutineContext")

            //if we want to run the coroutine on the same thread as parent
            // we dont have to mention the coroutine context

            var childJob1 = launch {
                Log.d(TAG,"Child1 - $coroutineContext")    // this will run on the main thread only as parent
            }
            var childJob2 = async {
                Log.d(TAG,"Child2 - $coroutineContext")   // this will run on the main thread only as parent
            }

            var childJob3 = launch(Dispatchers.IO) {
                Log.d(TAG,"Child3 - $coroutineContext")   // this will run on the IO thread only as we have explicitly mentioned 
            }
        }
    }
}