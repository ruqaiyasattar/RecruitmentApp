package app.bootcamp.com.recruitapp.activities


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.ShowToCompAdapter
import app.bootcamp.com.recruitapp.adapters.SimpleDividerItemDecoration
import app.bootcamp.com.recruitapp.models.StudentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_view_student.*
import java.util.ArrayList

class ViewStudentActivity : AppCompatActivity() {

    val baseRef = FirebaseDatabase.getInstance().getReference("users")
    val myRef = baseRef.orderByChild("type").equalTo(2.toDouble())
    internal lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        auth = FirebaseAuth.getInstance()

        val student = ArrayList<StudentUser>()

        val recyclerView = findViewById<RecyclerView>(R.id.showtoc)
        val layoutManager = LinearLayoutManager(this)
        val customRecyclerAdapter = ShowToCompAdapter(student)
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customRecyclerAdapter

        if (student.size <= 0) {
            nt_avalble.setVisibility(View.VISIBLE);
        }

        FirebaseDatabase.getInstance().getReference("jobs").child(intent.getStringExtra("jobId"))
                .child("applications")
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
    }
}