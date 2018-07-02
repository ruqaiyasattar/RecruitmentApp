package app.bootcamp.com.recruitapp.fragments



import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.CreateJobAdapter
import app.bootcamp.com.recruitapp.adapters.SimpleDividerItemDecoration
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.models.Post
import com.google.firebase.database.*
import java.util.ArrayList


class CompanyPostFragment : TitledFragment() {
    val myRef = FirebaseDatabase.getInstance().getReference("jobs")
    init{
        title = "Company Job Post"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v=inflater.inflate(R.layout.fragment_company_post, container, false) as View

        val postsjob = ArrayList<Post>()

        val recyclerView = v.findViewById<RecyclerView>(R.id.show_post)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val customRecyclerAdapter = CreateJobAdapter(postsjob,onItemDelClick = {position ->
            myRef.child(postsjob[position].key).removeValue()

            postsjob.removeAt(position).also {
                recyclerView.adapter.notifyItemRemoved(postsjob.size - 1)
            }

            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()

        })

        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this.context!!))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        FirebaseDatabase.getInstance().getReference("jobs")
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                        val posted = dataSnapshot.getValue(Post::class.java)
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
