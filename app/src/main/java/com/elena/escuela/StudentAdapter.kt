package com.elena.escuela

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.PersonajesViewHolder>()  {
    var listaStudents = mutableListOf<Student>()

    class PersonajesViewHolder(val root: View, val  imageView: ImageView, val textViewNombre: TextView, val textViewApellido: TextView, val textViewEmail: TextView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_student, parent, false)
        val ivFoto = view.findViewById<ImageView>(R.id.iwFoto)
        val tvApellido = view.findViewById<TextView>(R.id.tvApellido)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)

        return PersonajesViewHolder(view, ivFoto, tvNombre, tvApellido, tvEmail)
    }

    override fun getItemCount(): Int {
        return listaStudents.size
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        holder.imageView.setImageResource(listaStudents[position].foto)
        holder.textViewNombre.text = listaStudents[position].nombre
        holder.textViewApellido.text = listaStudents[position].apellido
        holder.textViewEmail.text = listaStudents[position].email



    }
    fun updateData(studentList : List<Student>){
        this.listaStudents = studentList.toMutableList()
        notifyDataSetChanged()
    }
    fun updateData(student: Student){
        listaStudents.add(student)
        notifyDataSetChanged()
    }


}
