package com.elena.escuela.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.elena.escuela.FragmentBootcamp
import com.elena.escuela.R
import com.elena.escuela.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding


    companion object {

        const val VALUE_1 = "VALUE_1"
        fun getIntent(context: Context) : Intent {
            return Intent(context, MenuActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // showFragment(FragmentProfile())
        binding.navigationButton.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.navigation_students -> {
                 //   showFragment(FragmentProfile.getFragment())
                    true
                }
                R.id.navigation_bootcamp -> {
                    showFragment(FragmentBootcamp.getFragment())
                    true
                }
                else -> false
            }
        }
    }


    fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}
