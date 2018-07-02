package app.bootcamp.com.recruitapp.fragments


import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.*
import app.bootcamp.com.recruitapp.models.Configs
import app.bootcamp.com.recruitapp.models.StudentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_student_signup.*
import kotlinx.android.synthetic.main.fragment_student_signup.view.*

class StudentSignupFragment : TitledFragment() { init{title = "Student Signup"}
    internal val auth: FirebaseAuth = FirebaseAuth.getInstance() // :d
    internal var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_student_signup, container, false) as View


        v.findViewById<Button>(R.id.stu_reg_butn).setOnClickListener {
            if(reg_email_stu.validateInputEmpty() && reg_name_stu.validateInputEmpty())
            {
                register()
            }
        }
        v.reg_email_stu.clear()
        v.reg_pass_stu.clear()
        v.reg_name_stu.clear()
        v.reg_age_stu.clear()
        v.reg_qual_stu.clear()
        v.reg_loc_stu.clear()

        return  v
    }
    lateinit var progressDialog: ProgressDialog
    fun register() {
        progressDialog = show(context, "Please wait..", "Processing", true)
        auth.createUserWithEmailAndPassword(reg_email_stu.str().trim { it <= ' ' }, reg_pass_stu.str().trim({ it <= ' ' })).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(context, "registered", Toast.LENGTH_SHORT).show()
                update_data()

            } else {
                Log.e("Error", task.exception!!.toString())
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun update_data() {
        Configs.mStudent = StudentUser(
                auth.currentUser?.uid!!,
                reg_name_stu.str(),
                reg_email_stu.str(),
                reg_pass_stu.str(),
                Integer.valueOf(reg_age_stu.str()),
                reg_qual_stu.str(),
                reg_loc_stu.str())
        ref.child(auth.currentUser?.uid).setValue(Configs.mStudent)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        computeUser(activity!!,auth).also {
                            progressDialog.dismiss() }
                    }
                }
    }
}
