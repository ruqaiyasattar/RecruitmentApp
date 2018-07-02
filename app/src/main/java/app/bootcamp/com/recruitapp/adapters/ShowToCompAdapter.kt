package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.models.StudentUser
import java.util.ArrayList

class ShowToCompAdapter (private val mPosts: ArrayList<StudentUser>) : RecyclerView.Adapter<ShowToCmpanyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowToCmpanyViewHolder {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.showtocompany, parent, false)
        return ShowToCmpanyViewHolder(myItemView)
    }

    override fun onBindViewHolder(holder: ShowToCmpanyViewHolder, position: Int) {
        val post = mPosts[position]
        holder.name!!.setText(post.name)
        holder.email!!.setText(post.email)
        holder.age!!.setText(post.age.toString())
        holder.qualif!!.setText(post.qualification)
        holder.city!!.setText(post.city)


    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}




