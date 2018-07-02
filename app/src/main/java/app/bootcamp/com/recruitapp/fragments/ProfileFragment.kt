package app.bootcamp.com.recruitapp.fragments


import android.os.Bundle
import android.support.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.base.TitledFragment

import app.bootcamp.com.recruitapp.models.Configs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : TitledFragment() {
    var visible: Boolean=false

    init {
        title = "Company Profile"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_profile, container, false) as View


        if(Configs.mCompany != null){
            Glide.with(this).load(Configs.mCompany!!.image)
                    .thumbnail(0.5f)
                    .into(v.findViewById(R.id.imagbutton));
        }

        v.findViewById<ImageView>(R.id.imagbutton).setOnClickListener(View.OnClickListener {

            TransitionManager.beginDelayedTransition(transitionsContainer)
            visible = !visible

            v.findViewById<TextView>(R.id.name) .setText(Configs.mCompany!!.name)
            v.findViewById<TextView>(R.id.name).setVisibility(if (visible) View.VISIBLE else View.GONE)

            v.findViewById<TextView>(R.id.email).setText(Configs.mCompany!!.email)
            v.findViewById<TextView>(R.id.email).setVisibility(if (visible) View.VISIBLE else View.GONE)

            v.findViewById<TextView>(R.id.about).setText(Configs.mCompany!!.about)
            v.findViewById<TextView>(R.id.about).setVisibility(if (visible) View.VISIBLE else View.GONE)

            v.findViewById<TextView>(R.id.contact).setText(Configs.mCompany!!.contact)
            v.findViewById<TextView>(R.id.contact).setVisibility(if (visible) View.VISIBLE else View.GONE)

            v.findViewById<TextView>(R.id.location).setText(Configs.mCompany!!.location)
            v.findViewById<TextView>(R.id.location).setVisibility(if (visible) View.VISIBLE else View.GONE)

        })

        return v
    }


}
