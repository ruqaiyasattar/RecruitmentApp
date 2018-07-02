package app.bootcamp.com.recruitapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.adapters.MyPagerAdapter
import app.bootcamp.com.recruitapp.base.TitledFragment
import app.bootcamp.com.recruitapp.base.setTitleAndReturn
import app.bootcamp.com.recruitapp.fragments.BaseJobFragment
import app.bootcamp.com.recruitapp.models.CompanyUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_student_dashboard.*
import java.util.ArrayList

class StudentDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        var toolbar = findViewById(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)


        val pager = findViewById(R.id.stubviewPagerLayout) as ViewPager
        val fragments = ArrayList<TitledFragment>()
        val adapter = MyPagerAdapter(supportFragmentManager, fragments)

        pager.setAdapter(adapter)
        tabslayout.setupWithViewPager(pager)

        FirebaseDatabase.getInstance().getReference("users").orderByChild("type").equalTo(1.toDouble())
                .addChildEventListener(object : ChildEventListener {
                    override fun onCancelled(p0: DatabaseError?) {}

                    override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}

                    override fun onChildChanged(p0: DataSnapshot?, p1: String?) {}

                    override fun onChildAdded(item: DataSnapshot?, p1: String?) {
                        if(item != null){
                            fragments.add(BaseJobFragment().setCompany(item.getValue(CompanyUser::class.java)!!.id).setTitleAndReturn(
                                    item.getValue(CompanyUser::class.java)!!.name
                            )).also {
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }

                    override fun onChildRemoved(p0: DataSnapshot?) {}

                })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.admin_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (menuItem.itemId) {
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
