package com.example.concepts.ProgrammingParadigms.MultiThreading

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class JobCancellationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_cancellation)

        GlobalScope.launch(Dispatchers.Main) {
            execute()
        }


    }

    private suspend fun execute() {
        var parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {  // even after cancelling the parentJob ,this loops gets executed because
                if (isActive) {             // it gets busy in executing the  loop and forgets to check
                    executelongrunningTask()    // if the job is cancelled or not for that we add isActive check
                    Log.d(TAG, "$i")
                }
            }
        }

        delay(1000)
        Log.d(TAG, "Cancel called")
        parentJob.cancel()
        parentJob.join()
        Log.d(TAG, "Complete parent job")
    }

    private fun executelongrunningTask() {
        for (i in 1..10000000000) {
        }
    }
}