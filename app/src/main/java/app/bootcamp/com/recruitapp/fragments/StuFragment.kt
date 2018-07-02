package app.bootcamp.com.recruitapp.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.SimpleDividerItemDecoration
import app.bootcamp.com.recruitapp.adapters.ViewStudentAdapter
import app.bootcamp.com.recruitapp.base.*

import app.bootcamp.com.recruitapp.models.StudentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList

class StuFragment : TitledFragment() {

    val baseRef = FirebaseDatabase.getInstance().getReference("users")
    val myRef = baseRef.orderByChild("type").equalTo(2.toDouble())
    internal lateinit var auth: FirebaseAuth

    init{
        title = "Students"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v=inflater.inflate(R.layout.fragment_stu, container, false) as View

        auth = FirebaseAuth.getInstance()

        val student = ArrayList<StudentUser>()

        val recyclerView = v.findViewById<RecyclerView>(R.id.students)
        val layoutManager = LinearLayoutManager(context)
        val customRecyclerAdapter = ViewStudentAdapter(student,onItemDelClick = {position ->
            val pd = context!!.progressDialog()
            pd.show()
            val c = student[position];
            baseRef.child(c.id).removeValue()
            delUserFromAuth(c,{pd.dismiss()},{pd.dismiss()})
            student.removeAt(position).also {
                recyclerView.adapter.notifyItemRemoved(student.size - 1)
            }
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()

        })
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this.context!!))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        FirebaseDatabase.getInstance().getReference("users").orderByChild("type").equalTo(2.toDouble())
                .addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                    }

                    override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                        val viewProfile = p0?.getValue(StudentUser::class.javaObjectType)
                        student.add(viewProfile!!)
                        customRecyclerAdapter.notifyItemInserted(student.size - 1)
                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {
                    }
                })

        return v
    }
}