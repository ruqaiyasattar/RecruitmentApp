package app.bootcamp.com.recruitapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import app.bootcamp.com.recruitapp.adapters.MyPagerAdapter
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.fragments.CompanyPostFragment
import app.bootcamp.com.recruitapp.fragments.CompanyProfileFragment
import app.bootcamp.com.recruitapp.fragments.StuFragment
import com.google.firebase.auth.FirebaseAuth
import github.chenupt.springindicator.SpringIndicator
import java.util.ArrayList







class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(app.bootcamp.com.recruitapp.R.layout.activity_admin_dashboard)

        val springIndicator = findViewById(app.bootcamp.com.recruitapp.R.id.adminindicatordb) as SpringIndicator
        val pager = findViewById(app.bootcamp.com.recruitapp.R.id.admindbviewPagerLayout) as ViewPager
        val fragments = ArrayList<TitledFragment>()

        var toolbar = findViewById(app.bootcamp.com.recruitapp.R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)

        fragments.add(CompanyProfileFragment())
        fragments.add(CompanyPostFragment())
        fragments.add(StuFragment())
        val adapter = MyPagerAdapter(supportFragmentManager, fragments)
        pager.setAdapter(adapter)
        springIndicator.setViewPager(pager)
        springIndicator.animate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(app.bootcamp.com.recruitapp.R.menu.admin_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        when (menuItem.itemId) {
            app.bootcamp.com.recruitapp.R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut().also {
                    startActivity( Intent(application, MainActivity::class.java))
                    finish()
                }

            }

        }

        return true
    }



}


