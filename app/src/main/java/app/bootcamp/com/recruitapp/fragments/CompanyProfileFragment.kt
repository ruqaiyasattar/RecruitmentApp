package app.bootcamp.com.recruitapp.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.CreateProfileAdapter
import app.bootcamp.com.recruitapp.adapters.SimpleDividerItemDecoration
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.base.delUserFromAuth
import app.bootcamp.com.recruitapp.base.progressDialog

import app.bootcamp.com.recruitapp.models.CompanyUser
import com.google.firebase.database.*
import java.util.ArrayList

class CompanyProfileFragment : TitledFragment() {
    val baseRef = FirebaseDatabase.getInstance().getReference("users")
    val myRef = baseRef.orderByChild("type").equalTo(1.toDouble())
    init {
        title = "Company Profile"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_company_profile, container, false) as View

        val company = ArrayList<CompanyUser>()

        val recyclerView = v.findViewById<RecyclerView>(R.id.showprofildata)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val customRecyclerAdapter = CreateProfileAdapter(company) { position ->
            val pd = context!!.progressDialog()
            pd.show()
            val c = company[position];
            baseRef.child(c.id).removeValue()
            delUserFromAuth(c,{pd.dismiss()},{pd.dismiss()})
            company.removeAt(position).also {
                recyclerView.adapter.notifyItemRemoved(company.size - 1)
            }
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()

        }
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this.context!!))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        myRef.addChildEventListener(object :ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {

                val createProfile = p0?.getValue(CompanyUser::class.java)
                company.add(createProfile!!)
                customRecyclerAdapter.notifyItemInserted(company.size - 1)
            }

            override fun onChildRemoved(p0: DataSnapshot?) {
            }
        })

        return v
    }
}