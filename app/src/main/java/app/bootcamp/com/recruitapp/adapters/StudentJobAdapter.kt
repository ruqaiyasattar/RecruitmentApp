package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.models.Post
import java.util.ArrayList

class StudentJobAdapter (private val mPosts: ArrayList<Post>,val onItemDelClick:(Int)->Unit) : RecyclerView.Adapter<ViewHolderStudent>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStudent {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.title, parent, false)
        return ViewHolderStudent(myItemView)
    }

    override fun onBindViewHolder(holder: ViewHolderStudent, position: Int) {
        val post = mPosts[position]
        holder.postShow!!.setText(post.jobCom)
        holder.expShow!!.setText(post.xperienc)
        holder.shoSal!!.setText(post.comSalary)
        holder.showIndstry!!.setText(post.comIndustry)
        holder.showDesc!!.setText(post.jobDesc)
        holder.showType!!.setText(post.postTyp)
        holder.comShow!!.setText(post.comType)


        holder.apply!!.setOnClickListener {
            onItemDelClick(position);
        }
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}



