package com.elena.escuela.student

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elena.escuela.databinding.ListStudentBinding
import com.squareup.picasso.Picasso

interface StudentAdapterInterface {
    fun onItemClick(student : Student)
}

class StudentAdapter(val listener : StudentAdapterInterface): RecyclerView.Adapter<StudentAdapter.PersonajesViewHolder>()  {

    private var studentList = listOf<Student>()

    class PersonajesViewHolder(val itemBinding: ListStudentBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val itemBinding = ListStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val student = studentList[position]

        student.photoId?.let { holder.itemBinding.iwFoto.setImageResource(it) }
        student.image?.let { holder.itemBinding.iwFoto.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size)) }
        student.imageOnline.let { Picasso.get().load(it).into(holder.itemBinding.iwFoto) }


        holder.itemBinding.tvNombre.text = student.nombre
        holder.itemBinding.tvApellido.text = student.apellido
        holder.itemBinding.tvEmail.text = student.email

        holder.itemBinding.root.setOnClickListener {
            listener.onItemClick(student)
        }
    }
    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateData(studentList : List<Student>){
        this.studentList = studentList
        notifyDataSetChanged()
    }


}
