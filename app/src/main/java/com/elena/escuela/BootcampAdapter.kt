package com.elena.escuela

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BootcampAdapter : RecyclerView.Adapter<BootcampAdapter.BootcampViewHolder>() {

    private var bootcamps = listOf<Bootcamp>()

    class BootcampViewHolder(var root: View, var tvName: TextView, var tvAbout: TextView) : RecyclerView.ViewHolder(root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BootcampViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_bootcamp, parent, false)
        val tvAbout = view.findViewById<TextView>(R.id.tvAbout)
        val tvName = view.findViewById<TextView>(R.id.tvName)

        return BootcampViewHolder(view, tvName, tvAbout)
    }

    override fun getItemCount(): Int {
        return bootcamps.size
    }

    override fun onBindViewHolder(holder: BootcampViewHolder, position: Int) {
        holder.tvName.text = bootcamps[position].bootcampName
        holder.tvAbout.text = bootcamps[position].about
        holder.root.setOnClickListener {

        }
    }

}
