package com.elena.escuela.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elena.escuela.databinding.ActivityPerfilBinding
import com.elena.escuela.student.Student
import com.elena.escuela.student.StudentAdapter
import com.elena.escuela.student.StudentAdapterInterface
import com.elena.escuela.viewmodel.ProfileActivityViewModel

class ProfileActivity : AppCompatActivity(), StudentAdapterInterface {

    private lateinit var binding: ActivityPerfilBinding
    private  var adapter = StudentAdapter(this)
    private lateinit var model : ProfileActivityViewModel

    companion object {
        const val VALUE_1 = "VALUE_1"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(ProfileActivityViewModel::class.java)
        intent.getStringExtra(VALUE_1)

        createRecyclerView()


        model.userList.observe(this){
            updateStudent(it)
            binding.progressBar.visibility = View.GONE
        }

         downloadStudent()

        binding.actionButton.setOnClickListener {

            val i = Intent(this , NewUserActivity:: class.java)
            startActivity(i)
        }

        binding.progressBar.visibility = View.VISIBLE


        binding.bShowDialog.setOnClickListener {
            showDialog()
        }

    }

    override fun onResume() {
        super.onResume()
        model.getAllUser()
    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        }

    private fun updateStudent(students : List<Student>){
        adapter.updateData(students)
    }

    private fun downloadStudent() {
        binding.descargar.setOnClickListener {
            model.downloadUser()
        }
    }

    override fun onItemClick(student: Student) {
        model.deleteStudent(student)
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Aceptas ${binding.etText.text}?")
            .setPositiveButton("Sí") { dialog, id ->
                Toast.makeText(this, "${binding.etText.text} ha sido aceptado", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No") { dialog, id ->
                Toast.makeText(this, "${binding.etText.text} ha sido cancelado", Toast.LENGTH_LONG).show()
            }
        builder.create()
        builder.show()
    }
}




