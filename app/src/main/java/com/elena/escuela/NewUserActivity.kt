package com.elena.escuela

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.elena.escuela.databinding.CreateUserBinding
import kotlinx.coroutines.launch

class NewUserActivity: AppCompatActivity() {
    lateinit var binding: CreateUserBinding
    private lateinit var model: NewUserActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = CreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(NewUserActivityViewModel::class.java)


        binding.buttonGuardar.setOnClickListener {
            model.insertarUsuario(binding.editTextTextEmailAddress.text.toString(),
            binding.name.text.toString(),
            binding.surename.text.toString(),
            0)


        }
    }
}