package app.bootcamp.com.recruitapp.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.*
import android.app.ProgressDialog.show
import app.bootcamp.com.recruitapp.models.CompanyUser
import app.bootcamp.com.recruitapp.models.Configs
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_company_signup.*

class CompanySignupFragment : TitledFragment() { init{ title = "Company Signup"}
    internal lateinit var auth: FirebaseAuth
    internal var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var v=inflater.inflate(R.layout.fragment_company_signup, container, false) as View

        auth = FirebaseAuth.getInstance()
        v.findViewById<Button>(R.id.com_reg_butn).setOnClickListener(View.OnClickListener {
            register()

        })

        v.findViewById<EditText>(R.id.reg_email_com).clear()
        v.findViewById<EditText>(R.id.reg_pass_com).clear()
        v.findViewById<EditText>(R.id.reg_name_com).clear()

        return v
    }

    fun register() {
        val progressDialog = show(context, "Please wait..", "Processing", true)
        auth.createUserWithEmailAndPassword(reg_email_com.getText().toString().trim({ it <= ' ' }),
                reg_pass_com.getText().toString().trim({ it <= ' ' })).addOnCompleteListener(OnCompleteListener<AuthResult> { task ->

            if (task.isSuccessful) {
                Toast.makeText(context, "registered", Toast.LENGTH_SHORT).show()
                Configs.mCompany = CompanyUser(
                        auth.currentUser?.uid!!,
                        reg_name_com.str(),
                        reg_email_com.str(),reg_pass_com.str(),"","","",""
                )
                ref.child(auth.currentUser?.uid).setValue(Configs.mCompany)
                        .addOnCompleteListener{
                            if(it.isSuccessful){
                                computeUser(activity!!,auth).also {  progressDialog.dismiss() }
                            }
                        }
            } else {
                Log.e("Error", task.exception!!.toString())
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}



