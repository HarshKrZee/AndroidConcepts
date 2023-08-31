package com.example.concepts.ProgrammingParadigms.Flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


// shared flows
// if there is a flow and 2 consumers, then these consumers will get the data from starting
// independently

// BUT,in shared flows , lets say the first consumers start consuming first from the flow
// lets say the first consumer has consumed 1,2,3 ... then the next element to be consumed is 4
// and now if the second consumer comes , so it will NOT get the data from the start instead
// it will start consuming from 4 only

class SharedFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_flow)

        GlobalScope.launch(Dispatchers.Main){
            var result = producer()
            result.collect{
                Log.d("data1","item - $it")
            }
        }

        GlobalScope.launch(Dispatchers.Main){
            var result = producer()
            delay(2500)
            result.collect{
                Log.d("data2","item - $it")
            }
        }

    }

    // replay is like a buffer , it will store the number of elements in the buffer for the
    // consumers who join late to consume
    
    fun producer() : Flow<Int> {
        var mutableSharedFlow = MutableSharedFlow<Int>(2)
        GlobalScope.launch {
            val list = listOf(1,2,3,4,5,6,7,8,9,10)
            list.forEach{
                mutableSharedFlow.emit(it)
                delay((1000))
            }
        }
        return mutableSharedFlow
    }
}