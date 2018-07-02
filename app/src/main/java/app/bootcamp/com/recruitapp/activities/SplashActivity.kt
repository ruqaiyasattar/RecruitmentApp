package app.bootcamp.com.recruitapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import app.bootcamp.com.recruitapp.R
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        var animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.antirotate);
        var animation_3 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        imageView.startAnimation(animation_2);
        animation_2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                imageView.startAnimation(animation_1);
            }

            override fun onAnimationStart(animation: Animation?) {
                imageView.startAnimation(animation_1);
            }
        })

        animation_1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

                imageView.startAnimation(animation_3);
                finish();
                startActivity(Intent(application, MainActivity::class.java))
            }

            override fun onAnimationStart(animation: Animation?) {
                // actually, I don't need this method but I have to implement this.
            }
        })


    }
}




