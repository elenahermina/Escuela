package com.elena.escuela

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elena.escuela.databinding.ActivityPerfilBinding
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityPerfilBinding
    private  var adapter = StudentAdapter()
    private lateinit var model :ProfileActivityViewModel

    companion object {
        const val VALUE_1 = "VALUE_1"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(ProfileActivityViewModel::class.java)

        val email = intent.getStringExtra(VALUE_1)
        email?.let {
            model.setMyselfEmail(email)
        }

        createRecyclerView()


        binding.actionButton.setOnClickListener {

            val i = Intent(this , NewUserActivity:: class.java)
            startActivity(i)
        }

    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch{
            binding.progressBar.visibility = View.VISIBLE
            model.getListaAlumnos().forEach {
                val alumnoAMostrar = model.getAlumno(it)
                adapter.updateData(alumnoAMostrar)
            }

            binding.progressBar.visibility = View.GONE
        }
    }




}