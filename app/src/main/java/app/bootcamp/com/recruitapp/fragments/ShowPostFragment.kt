package app.bootcamp.com.recruitapp.fragments


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.activities.JobPostActivity
import app.bootcamp.com.recruitapp.activities.ViewStudentActivity
import app.bootcamp.com.recruitapp.adapters.ShowJobAdapter
import app.bootcamp.com.recruitapp.adapters.SimpleDividerItemDecoration
import app.bootcamp.com.recruitapp.base.TitledFragment

import app.bootcamp.com.recruitapp.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class ShowPostFragment : TitledFragment() {
    internal lateinit var auth: FirebaseAuth

    init{
        title = "Company Job Post"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v=inflater.inflate(R.layout.fragment_show_post, container, false) as View

        val fab = v.findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            // Click action
            val intent = Intent(context, JobPostActivity::class.java)
            startActivity(intent)
        }

        val postsjob = ArrayList<Post>()

        val recyclerView = v.findViewById<RecyclerView>(R.id.show)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val customRecyclerAdapter = ShowJobAdapter(postsjob){
            val i = Intent(context, ViewStudentActivity::class.java)
            i.putExtra("jobId",it.key)
            startActivity(i)
        }
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this.context!!))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        auth = FirebaseAuth.getInstance()

        FirebaseDatabase.getInstance().getReference("jobs").orderByChild("compId").equalTo(auth.currentUser!!.uid)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                        val posted = dataSnapshot.getValue(Post::class.javaObjectType)
                        postsjob.add(posted!!)
                        customRecyclerAdapter.notifyItemInserted(postsjob.size - 1)
                    }

                    override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

                    }

                    override fun onChildRemoved(dataSnapshot: DataSnapshot) {

                    }

                    override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

                    }

                    override fun onCancelled(databaseError: DatabaseError) {

                    }
                })
        return v
    }
}