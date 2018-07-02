package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.models.CompanyUser


import java.util.ArrayList

class ProfilInfoAdapter


(private val students: ArrayList<CompanyUser>) : RecyclerView.Adapter<ProfilShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilShowViewHolder {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.sample, parent, false)
        return ProfilShowViewHolder(myItemView)
    }

    override fun onBindViewHolder(holder: ProfilShowViewHolder, position: Int) {
        val createProfile = students[position]
        holder.namInf.setText(createProfile.name)
        holder.aboutInfo.setText(createProfile.about)
        holder.imageView.setImageResource(Integer.parseInt(createProfile.image))

    }

    override fun getItemCount(): Int {
        return students.size
    }
}


