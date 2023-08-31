package com.example.concepts.ProgrammingParadigms.Flows

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception

// initially the producer runs on the same thread on which the consumer is running=

// when we want the consumer thread to run on different thread and the producer thread to run
// on different thread then we use flowOn

class FlowOnAndExceptionHandlingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_on_and_exception_handling)

 //        --  FLOWON EXAMPLE  --

//        GlobalScope.launch(Dispatchers.Main) {  // collector run on this thread (Main)
//
//                producer()   // this producer runs on IO thread because of the flowOn mentioned below
//                    .map {
//                        delay(1000)
//                        Log.d("map thread - ", "${Thread.currentThread().name}")
//                        it * 2     // last line is the return value
//                    }
//                    .flowOn(Dispatchers.IO)    // the code above this line (starting from producer) will run on IO thread
//                    .filter {
//                        delay(500)
//                        Log.d("filter thread - ", "${Thread.currentThread().name}")
//                        it < 8
//                    }
//                    .flowOn(Dispatchers.Main)    // the code above this line (filter lambda function ) and below the upper flowOn will run on  Main thread
//                    .collect {
//                        Log.d("collector thread - ", "${Thread.currentThread().name}")
//                    }  // this collect will run on Main thread as the consumer coroutine is launched on Main thread
//
//        }

        //  --- EXCEPTION HANDLING  ---

        GlobalScope.launch(Dispatchers.Main) {

            try {     // this try will catch error of both Emitter and Collector

                producer()
                    .collect {
                        Log.d("collector thread - ", "${Thread.currentThread().name}")
                    }

            }catch (e : Exception)   // this catch block catches error of both collector and emitter
            {
                Log.d(TAG,"Collector Error Catcher block, msg -" + e.message.toString())
            }
        }
    }

    private fun producer() : Flow<Int> {        // this producer will run on the same thread as where consumer thread is running
        return flow<Int> {
            val list = listOf<Int>(1,2,3,4,5,6,7,8,9,0)
            list.forEach{
                delay(1000)
                emit(it)
                Log.d("emitter thread - ","${Thread.currentThread().name}")
                throw Exception("Error in Emitter")
            }
        }.catch {  // when we want to handle the producer error we use catch block here
            // the advantage of this catch block in producer is that it can return some callback values
            Log.d(TAG,"Emitter Error Catcher block , msg - ${it.message}")
            emit(-1)
        }
    }
}