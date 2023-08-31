package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Coroutine Builders are of two types , Launch and Async
// Launch is used to launch a coroutine, when we do not care about the result
// Async is also used to launch a coroutine, and to get some result  in return from the coroutine

// Job are like handles for coroutines , we can handle coroutine with Jobs
// we can start the coroutine, cancel  the coroutine, join  the coroutine etc with the help of Job

class CoroutineBuilderAndJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_builder_and_job)

        CoroutineScope(Dispatchers.IO).launch {
            printFBandInstaFollowerswithAsync()
        }
    }

    suspend fun printFBfollowerswithLaunch()              // Here we are getting fbfollowers using launch builer and using join()
    {                                         // a better way is to use async() builder
        var fbFollowers = 0
        val job = CoroutineScope(Dispatchers.IO).launch {
            fbFollowers = getFbFollowers()
        }

        job.join()    // this joins tells that execute the below code lines
        // when this coroutine job is finished
        Log.d(TAG, fbFollowers.toString())
    }

    suspend fun printFBfollowerswithAsync() {
        val job = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()         // this last line is the Deferred return type of the job
        }

        // the job is type deferred meaning, we will get the result in future, that is
        // why we are doing await() , to wait till we get the answer

        Log.d(TAG, job.await().toString())
    }

    suspend fun printFBandInstaFollowerswithLaunch() {
        var fb = 0
        var insta = 0

        var job1 = CoroutineScope(Dispatchers.IO).launch {
            fb = getFbFollowers()
        }
        var job2 = CoroutineScope(Dispatchers.IO).launch {
            insta = getInstaFollowers()
        }

        job1.join()
        job2.join()

        // we used join on both the jobs to make sure that , result have been calculated

        Log.d(TAG, "FB followers - ${fb}, Insta followers - ${insta}")

    }

    suspend fun printFBandInstaFollowerswithAsync() {

        var fb = CoroutineScope(Dispatchers.IO).async {
            getFbFollowers()
        }
        var insta = CoroutineScope(Dispatchers.IO).async {
            getInstaFollowers()
        }

        Log.d(TAG, "FB followers - ${fb.await()}, Insta followers - ${insta.await()}")
    }

    suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }

    suspend fun getInstaFollowers(): Int {
        delay(1000)
        return 113
    }
}