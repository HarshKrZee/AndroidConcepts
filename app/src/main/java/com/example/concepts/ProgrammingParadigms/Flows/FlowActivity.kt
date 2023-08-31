 package com.example.flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

 class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        // consumer 1

        var job = GlobalScope.launch {
            val data = producer()   //this flow will be cancelled by the below coroutine after 35.second
            data.collect{
                Log.d("data",it.toString())
            }
        }

        // consumer 2

        var job2 = GlobalScope.launch {
            val data = producer()
            delay(2500)
            data.collect{
                Log.d("data 2",it.toString())
            }
        }

//        GlobalScope.launch {
//            delay(3500)
//            job.cancel()
//        }

    }

     // producer

    fun producer() = flow<Int>{  // this internally launches a coroutine
        val list = listOf<Int>(1,2,3,4,5,6,7,8,9,10)
        list.forEach{
            delay(1000)
            emit(it)
        }

    }
}