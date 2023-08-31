package com.example.flows

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.concepts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class ChannelActivity : AppCompatActivity() {

    val channel = Channel<Int>()        //Channel<Data type>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)
        producer()
        consumer()
    }

    fun producer() {

        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }

    }

    fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("data1",channel.receive().toString())
            Log.d("data2",channel.receive().toString())
        }
    } 
}