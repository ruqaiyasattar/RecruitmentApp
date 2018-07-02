package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.models.StudentUser
import java.util.ArrayList

class ViewStudentAdapter (private val mStudents: ArrayList<StudentUser>,val onItemDelClick:(Int)->Unit) : RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.showstudent, parent, false)
        return StudentViewHolder(myItemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val studentuser = mStudents[position]
        holder.stuName.setText(studentuser.name)
        holder.stuQualif.setText(studentuser.qualification)
        holder.stuEmail.setText(studentuser.email)
        holder.stuLocation.setText(studentuser.city)
        holder.stuAge.setText("${studentuser.age}")
        holder.Delete!!.setOnClickListener {
            onItemDelClick(position);
        }

    }

    override fun getItemCount(): Int {
        return mStudents.size
    }
}



