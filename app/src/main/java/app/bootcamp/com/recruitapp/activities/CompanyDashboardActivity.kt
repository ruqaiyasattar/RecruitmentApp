package app.bootcamp.com.recruitapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.MyPagerAdapter
import app.bootcamp.com.recruitapp.fragments.CompanyPostFragment
import app.bootcamp.com.recruitapp.fragments.CompanyProfileFragment
import app.bootcamp.com.recruitapp.fragments.ProfileFragment
import app.bootcamp.com.recruitapp.fragments.ShowPostFragment
import com.google.firebase.auth.FirebaseAuth
import github.chenupt.springindicator.SpringIndicator
import java.util.ArrayList
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.support.design.widget.CollapsingToolbarLayout
import android.view.View
import app.bootcamp.com.recruitapp.base.TitledFragment


class CompanyDashboardActivity : AppCompatActivity() {
    internal var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_dashboard)
        var toolbar = findViewById(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)

        val springIndicator = findViewById(R.id.indicatordb) as SpringIndicator
        val pager = findViewById(R.id.companydbviewPagerLayout) as ViewPager
        val fragments = ArrayList<TitledFragment>()

        fragments.add(ProfileFragment())
        fragments.add(ShowPostFragment())
        val adapter = MyPagerAdapter(supportFragmentManager, fragments)
        pager.setAdapter(adapter)
        springIndicator.setViewPager(pager)
        springIndicator.animate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (menuItem.itemId) {
            R.id.action_profile ->
                startActivity(Intent(this, CreateCompanyProfile::class.java))
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut().also {
                    startActivity( Intent(application, MainActivity::class.java))
                    finish()
                }
            }
        }
        return true
    }

}
