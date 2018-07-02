package app.bootcamp.com.recruitapp.activities

import android.app.Activity
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.R.id.*
import app.bootcamp.com.recruitapp.base.clear
import app.bootcamp.com.recruitapp.base.str
import app.bootcamp.com.recruitapp.models.Configs
import com.bumptech.glide.Glide
import com.fxn.pix.Pix
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_create_company_profile.*
import java.io.File


class CreateCompanyProfile : AppCompatActivity() {

    internal lateinit var database: FirebaseDatabase
    internal  var auth: FirebaseAuth= FirebaseAuth.getInstance()
    internal var imageToSave: Uri? = null
    internal val IMAGE_PICK_REQ = 1000
    internal var ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    lateinit var progressDialog:ProgressDialog

    private var mStorageRef: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company_profile)

        done.setOnClickListener(View.OnClickListener {

            update_data()
        })

        get_img.setOnClickListener(View.OnClickListener { Pix.start(this@CreateCompanyProfile, IMAGE_PICK_REQ, 1) })

        mStorageRef = FirebaseStorage.getInstance().reference
        firebaseinint()
    }

    fun firebaseinint() {

        database = FirebaseDatabase.getInstance()
        ref = database.getReference("users").child(FirebaseAuth.getInstance().currentUser!!.uid)

    }

    fun update_data() {
        Configs.mCompany?.name = name.str()
        Configs.mCompany?.location = location.str()
        Configs.mCompany?.about =  about.str()
        Configs.mCompany?.contact =  contact.str()
        progressDialog = show(this, "Please wait..", "Processing", true)
        if (imageToSave != null) {
            val riversRef = mStorageRef!!.child("images/" + FirebaseAuth.getInstance().currentUser!!.uid + ".jpg")

            riversRef.putFile(imageToSave!!)
                    .addOnSuccessListener { taskSnapshot ->
                        taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->

                            Configs.mCompany?.image =  uri.toString()
                            ref.setValue(Configs.mCompany)
                            empty()
                            Toast.makeText(this@CreateCompanyProfile, "Profile Created!", Toast.LENGTH_SHORT).show()

                        }
                    }
                    .addOnFailureListener {
                        // Handle unsuccessful uploads
                        // ...
                    }
        } else {
            ref.setValue(Configs.mCompany)
            empty()
        }
    }

    fun empty() {
        progressDialog.dismiss()
        name.clear()
        location.clear()
        contact.clear()
        email.clear()
        about.clear()
        name.clear()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_REQ) {
            val returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS)
            if (returnValue.size > 0) {
                val imgFile = File(returnValue[0])
                if (imgFile.exists()) {
                    imageToSave = Uri.fromFile(imgFile)
                    Glide.with(this).load(imageToSave).into(com_img)
                }
            }
        }
    }
}
