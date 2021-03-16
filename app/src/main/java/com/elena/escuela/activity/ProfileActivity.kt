package com.elena.escuela.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
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
}




