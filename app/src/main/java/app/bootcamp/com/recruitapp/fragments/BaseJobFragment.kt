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
import app.bootcamp.com.recruitapp.adapters.StudentJobAdapter
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.base.log
import app.bootcamp.com.recruitapp.models.Configs
import app.bootcamp.com.recruitapp.models.Post
import app.bootcamp.com.recruitapp.models.StudentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class BaseJobFragment : TitledFragment() {

    var visible: Boolean=false
    internal lateinit var database: FirebaseDatabase
    internal lateinit var ref: DatabaseReference
    internal lateinit var auth: FirebaseAuth

    lateinit var compId:String

    fun setCompany(id:String):BaseJobFragment{
        compId = id
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_base_job, container, false)
        val postsjob = java.util.ArrayList<Post>()
        firebaseinint()

        val recyclerView = v.findViewById<RecyclerView>(R.id.stushow)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val customRecyclerAdapter = StudentJobAdapter(postsjob,onItemDelClick = { position ->
            update_data(postsjob[position].key)
            Toast.makeText(context,"applied",Toast.LENGTH_SHORT).show()
        })

        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this.context!!))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        auth = FirebaseAuth.getInstance()

        ref.orderByChild("compId").equalTo(compId)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                        log("data found")
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



    fun firebaseinint() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("jobs")
    }


    fun update_data(ley:String) {
        val sdata = StudentUser(
                auth.currentUser?.uid ?: "",
                Configs.mStudent!!.name ,
                Configs.mStudent!!.email,
                Configs.mStudent!!.password,
                Configs.mStudent!!.age,
                Configs.mStudent!!.qualification,
                Configs.mStudent!!.city
        )

        val jobApplications =  ref.child(ley).child("applications")
        jobApplications.child( sdata.id).setValue(sdata)

    }

}
