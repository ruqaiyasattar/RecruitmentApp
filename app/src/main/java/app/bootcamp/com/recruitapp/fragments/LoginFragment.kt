package app.bootcamp.com.recruitapp.fragments


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.base.clear
import app.bootcamp.com.recruitapp.base.computeUser
import app.bootcamp.com.recruitapp.base.str
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : TitledFragment() { init {title = "Login"}
    internal lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_login, container, false) as View

        auth = FirebaseAuth.getInstance()


        v.findViewById<Button>(R.id.login).setOnClickListener {
            val progressDialog = ProgressDialog.show(context, "Please wait..", "Processing", true)
            auth.signInWithEmailAndPassword(email_user.str(), password.str())
                    .addOnCompleteListener { task ->
                        progressDialog.dismiss()
                        if (task.isSuccessful) {
                            Toast.makeText(context, "logedin", Toast.LENGTH_SHORT).show()
                            computeUser(activity!!,auth)
                            email_user.clear()
                            password.clear()
                        } else {
                            Log.e("Error", task.exception!!.toString())
                            Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(auth.currentUser !== null){
            computeUser(activity!!,auth)
        }
    }




}
