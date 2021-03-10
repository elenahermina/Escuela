package com.elena.escuela.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elena.escuela.databinding.ActivitySplashBinding
import com.elena.escuela.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private lateinit var model : SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(SplashActivityViewModel::class.java)
        observeModelDbEntity()

    }
     private fun observeModelDbEntity(){
         model.status.observe(this@SplashActivity){
            if(it.isNotEmpty()&& it [0].created){
                 lifecycleScope.launch {
                     delay(2000)
                     val intent = Intent(this@SplashActivity, MainActivity::class.java)
                     startActivity(intent)
                     finish()
                 }

          }
         }






    }
}