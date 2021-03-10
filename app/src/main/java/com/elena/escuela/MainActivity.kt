package com.elena.escuela

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elena.escuela.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainActivityViewModel::class.java)



        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(textContent: Editable?) {
                textContent?.let {
                    binding.checkBox.isEnabled = viewModel.isStudentValid(textContent.toString())
                }
            }
        })

        viewModel.cargarPreferencias()?.let {
            binding.checkBox.isChecked = it.isNotEmpty()
            binding.editText.setText(it)
        }


        binding.checkBox.setOnClickListener {
            if (binding.checkBox.isChecked) {
                Toast.makeText(this, "El check box esta seleccionado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El check box esta NO seleccionado", Toast.LENGTH_LONG).show()
            }
        }


        binding.button.setOnClickListener {
            if (binding.checkBox.isEnabled && binding.checkBox.isChecked) {
                viewModel.guardarPreferencias(binding.editText.text.toString())
            } else {
                viewModel.guardarPreferencias("")
            }
            lifecycleScope.launch {
                if (viewModel.isEmailRegistered((binding.editText.text.toString()))) {
                    //viewModel.insertNewUser(binding.editText.toString())
                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@MainActivity, "Nada", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}


