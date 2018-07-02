package app.bootcamp.com.recruitapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.MyPagerAdapter
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.fragments.*
import github.chenupt.springindicator.SpringIndicator
import java.util.ArrayList

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val springIndicator = findViewById(R.id.indicator) as SpringIndicator
        val pager = findViewById(R.id.viewPagerLayout) as ViewPager
        val fragments = ArrayList<TitledFragment>()
        fragments.add(LoginFragment())
        fragments.add(StudentSignupFragment())
        fragments.add(CompanySignupFragment())
        val adapter = MyPagerAdapter(supportFragmentManager, fragments)
        pager.setAdapter(adapter)
        pager.setOffscreenPageLimit(3)
        springIndicator.setViewPager(pager)
        springIndicator.animate()
    }

}

