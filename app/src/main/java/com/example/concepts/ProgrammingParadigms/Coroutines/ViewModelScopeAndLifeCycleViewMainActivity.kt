package com.example.concepts.ProgrammingParadigms.Coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.concepts.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelScopeAndLifeCycleViewMainActivity : AppCompatActivity() {

   private lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_scope_and_life_cycle_view_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        lifecycleScope.launch{
            delay(2000)
            var intent = Intent(this@ViewModelScopeAndLifeCycleViewMainActivity,CoroutineActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}