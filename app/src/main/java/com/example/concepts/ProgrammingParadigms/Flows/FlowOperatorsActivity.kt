package com.example.flows

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FlowOperatorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_operators)


//      Terminal functions are all suspend functions , a flow can only be started by Terminal operator like here collect
//      example of TERMINAL OPERATORS (onStart,onCompletion, onEach, collect,first, toList )

//        GlobalScope.launch {
//            producer()
//                .onStart { // will execute when we start consuming
////                    emit(-1)       // we can emit something in onStart and onCompletion
//                    Log.d("TAG", "started consuming")
//                 }
//                .onCompletion {// will execute when we finish consuming
////                    emit(-10)       // we can emit something in onStart and onCompletion
//                    Lo g.d("TAG", "completed consuming")
//                }
//                .onEach {//onEach will be called when an element will be just about to emit
//                    Log.d("TAG", "about to emit - $it")
//                }
//                .collect{ // with this we are consuming the element
//                    Log.d("TAG", "$it")
//                }
//        }

        GlobalScope.launch(Dispatchers.Main) {

//            var firstElement = producer().first()    // returns the first element of the flow
//            Log.d(TAG,"$firstElement")
//
//            var resultList = producer().toList() // convert the flow elements into a list and returns it
//            Log.d(TAG,"$resultList")

            producer()
                .buffer(3)
                .map { it * 2 }          // mutliplies eache element with 2
                .filter { it < 5 }       // will only collect elements less than 5
                .collect { Log.d(TAG, "$it") }
        }

    }

    private fun producer(): Flow<Int> {
        return flow<Int> {
            var list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                emit(it)
            }
        }
    }
}