package com.elena.escuela.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elena.escuela.activity.FragmentProfile
import com.elena.escuela.activity.MenuActivity
import com.elena.escuela.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var model: MainActivityViewModel

    companion object {
        const val USER_NAME = "USER_NAME"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(textContent: Editable?) {
                textContent?.let {
                    binding.checkBox.isEnabled = textContent.contains("@") && textContent.contains(".")
                }
            }
        })

        model.loadEmailPreferences()?.let {
            binding.checkBox.isChecked = it.isNotEmpty()
            binding.editText.setText(it)
        }


        binding.checkBox.setOnClickListener {
            if (binding.checkBox.isEnabled && binding.checkBox.isChecked) {
                model.saveEmailPreferences(binding.editText.text.toString())
            } else {
                model.saveEmailPreferences("")
            }
            lifecycleScope.launch {
                if (model.verifyUser(binding.editText.text.toString())) {
                    // Lanzamos la activity 2.
                     MenuActivity()
                } else {
                    Toast.makeText(this@MainActivity, "El usuario no está en la Base de Datos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

        private fun startProfileActivity() {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra(MenuActivity.VALUE_1, binding.editText.text.toString())
            startActivity(intent)
        }


}


