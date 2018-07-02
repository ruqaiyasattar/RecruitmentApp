package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


import java.util.ArrayList

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.loadImageUrl
import app.bootcamp.com.recruitapp.models.CompanyUser



class CreateProfileAdapter

(private val students: ArrayList<CompanyUser>,val onItemDelClick:(Int)->Unit) : RecyclerView.Adapter<MyCustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.sample, parent, false)
        return MyCustomViewHolder(myItemView)
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        val student = students[position]
        holder.comName.text = student.name
        holder.comAbout.text = student.about
        holder.comContact.text = student.contact
        holder.comLocation.text = student.location
        holder.comEmail.text = student.email
        holder.imageView.loadImageUrl(student.image)
        holder.Del!!.setOnClickListener {
            onItemDelClick(position);
        }

    }

    override fun getItemCount(): Int {
        return students.size
    }
}

