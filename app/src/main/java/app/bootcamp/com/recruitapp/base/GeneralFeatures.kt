package app.bootcamp.com.recruitapp.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.activities.AdminDashboardActivity
import app.bootcamp.com.recruitapp.activities.CompanyDashboardActivity
import app.bootcamp.com.recruitapp.activities.StudentDashboardActivity
import app.bootcamp.com.recruitapp.models.AppUser
import app.bootcamp.com.recruitapp.models.CompanyUser
import app.bootcamp.com.recruitapp.models.Configs
import app.bootcamp.com.recruitapp.models.StudentUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

fun TextView.str():String = this.text.toString()

fun EditText.clear() = this.setText("")


fun EditText.validateInputEmpty():Boolean{
    this.error = null
    if(this.str().isEmpty()){
        this.error = "Please enter ${this.hint}"
    }
    return !this.str().isEmpty()
}

fun ImageView.loadImageUrl(url:String,placeholder:Int = R.drawable.logo){
    Glide.with(this.context).applyDefaultRequestOptions(RequestOptions().placeholder(placeholder)).load(url).into(this)
}
fun TitledFragment.setTitleAndReturn(title:String):TitledFragment{
    this.title = title
    return  this
}

open class TitledFragment : Fragment(){var title:String = ""}

fun Context.progressDialog():ProgressDialog{
    val pd = ProgressDialog(this)
    pd.setMessage("Please Wait...")
    pd.setCancelable(false)
    return pd
}

fun delUserFromAuth(user:AppUser,onDone:()->Unit = {},onFailed:(String)->Unit = {}){
    val auth =  FirebaseAuth.getInstance()
    auth.log(user.email+"--"+user.password)
    auth.signInWithEmailAndPassword(user.email.toLowerCase(),user.password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    auth.currentUser!!.delete()
                            .addOnCompleteListener{iit->
                                if(iit.isSuccessful){
                                    auth.signInWithEmailAndPassword(Configs.mAdmin!!.email,Configs.mAdmin!!.password)
                                            .addOnCompleteListener{iitt ->
                                                if(iitt.isSuccessful){
                                                    onDone()
                                                }else{
                                                    onFailed("3 ${it.exception?.localizedMessage}")
                                                }
                                            }
                                }else{
                                    onFailed("2 ${it.exception?.localizedMessage}")
                                }
                            }.addOnFailureListener{
                                onFailed("1 ${it.localizedMessage}")
                            }
                }else{
                    onFailed("0  ${it.exception?.localizedMessage}")
                }

            }.addOnFailureListener{
                onFailed("-1 ${it.localizedMessage}")
            }
}

fun Context.toast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}

fun Any.log(msg:String){
    Log.e("${this.javaClass::getSimpleName}-Log",msg)
}

fun computeUser(activity: Activity,auth:FirebaseAuth) {
    FirebaseDatabase.getInstance().getReference("users").child(auth.currentUser?.uid ?: return)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(AppUser::class.javaObjectType)
                    when (user?.type) {

                        1 -> {
                            Configs.mCompany = dataSnapshot.getValue(CompanyUser::class.javaObjectType)
                            activity.startActivity(Intent(activity, CompanyDashboardActivity::class.java))
                            activity.finish()
                        }
                        2 -> {
                            Configs.mStudent = dataSnapshot.getValue(StudentUser::class.javaObjectType)
                            activity.startActivity(Intent(activity, StudentDashboardActivity::class.java))
                            activity.finish()
                        }
                        else -> {
                            Configs.mAdmin = dataSnapshot.getValue(AppUser::class.javaObjectType)
                            activity.startActivity(Intent(activity, AdminDashboardActivity::class.java))
                            activity.finish()
                        }
                    }
                }

            })
}