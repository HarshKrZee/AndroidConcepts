package com.example.concepts.ProgrammingParadigms.Flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// state flows are hot flows
// they maintain state , means they always store the last emitted element in buffer

class StateFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        GlobalScope.launch {
            var result = producer()
            delay(6000)   // after 6 seconds , the emitter already has stopped emitting but
                                   // as it is a state flow , the last value is store in buffer
            result.collect{  // so this collect is taking the value in the buffer that is 30
                Log.d("data","$it")
            }
        }
    }

    private fun producer() : Flow<Int>
    {
        var mutableStateFlow = MutableStateFlow<Int>(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }

        return mutableStateFlow
    }
}