package app.bootcamp.com.recruitapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import app.bootcamp.com.recruitapp.base.TitledFragment


import java.util.ArrayList


class MyPagerAdapter(fm: FragmentManager, internal var fragments: ArrayList<TitledFragment>) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getCount(): Int  = fragments.size
    override fun getPageTitle(position: Int): CharSequence? = fragments[position].title
}
