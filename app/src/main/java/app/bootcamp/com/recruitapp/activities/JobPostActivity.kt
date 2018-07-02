package app.bootcamp.com.recruitapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.clear
import app.bootcamp.com.recruitapp.base.str
import app.bootcamp.com.recruitapp.base.toast
import app.bootcamp.com.recruitapp.base.validateInputEmpty

import kotlinx.android.synthetic.main.activity_job_post.*
import app.bootcamp.com.recruitapp.models.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class JobPostActivity : AppCompatActivity() {


    internal lateinit var database: FirebaseDatabase
    internal lateinit var ref: DatabaseReference
    internal lateinit var auth: FirebaseAuth
    private val mStorageRef: StorageReference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_post)


        firebaseinint()
        post.setOnClickListener {

            if (job.validateInputEmpty() && typ.validateInputEmpty()&&ex.validateInputEmpty()&&sal_com.validateInputEmpty()
                    &&tpy_com.validateInputEmpty()&&indus.validateInputEmpty()&&jb_desc.validateInputEmpty()){

                update_data()
            }

            else{
                toast("fill the fields")
            }
        }
    }

    fun firebaseinint() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("jobs")
    }

    fun update_data() {
        val jpost = Post(
                job.str(),
                typ.str(),
                ex.str(),
                sal_com.str(),
                tpy_com.str(),
                indus.str(),
                jb_desc.str(),
                auth.currentUser?.uid ?: ""
        )

        jpost.key = ref.push().key
        ref.child(jpost.key).setValue(jpost)
        finish()

    }


    fun empty() {
        job.clear()
        typ.clear()
        ex.clear()
        sal_com.clear()
        tpy_com.clear()
        indus.clear()
        jb_desc.clear()
    }
}
