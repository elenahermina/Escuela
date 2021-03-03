package com.elena.escuela

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.elena.escuela.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        const val EMAIL = "EMAIL"
    }
    val usuarioValido = RegisteredUser("elena@neoland.com")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(textContent: Editable?) {
                textContent?.let {
                    binding.checkBox.isEnabled = textContent.contains("@") &&  textContent.contains(".")
                }
            }
        } )

        cargarPreferencias()?.let {
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
            if (binding.checkBox.isEnabled && binding.checkBox.isChecked){
                guardarPreferencias(binding.editText.text.toString())
            } else {
                guardarPreferencias("")
            }
            if(binding.editText.text.toString().contentEquals(usuarioValido.email)) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(ProfileActivity.VALUE_1, binding.editText.text.toString())

                startActivity(intent)

            } else {
                Toast.makeText(this, "El usuario no esta registrado", Toast.LENGTH_LONG).show()
            }
        }

    }
    private  fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(EMAIL, "")
    }

    private  fun guardarPreferencias(string : String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(EMAIL, string)
            commit()
        }
    }


}
