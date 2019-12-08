package `in`.terxlabs.rtbms.dashboard

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.dashboard.does.fragment.DoDoesFragment
import `in`.terxlabs.rtbms.dashboard.events.EventsFragment
import `in`.terxlabs.rtbms.dashboard.home.HomeFragment
import `in`.terxlabs.rtbms.dashboard.hospital_pharmacy.HospitalFragment
import `in`.terxlabs.rtbms.dashboard.hospital_pharmacy.PharmacyFragment
import `in`.terxlabs.rtbms.dashboard.newsfeed.NewsFeedFragment
import `in`.terxlabs.rtbms.dashboard.ourteam.OurTeamFragment
import `in`.terxlabs.rtbms.dashboard.profile.ProfileFragment
import `in`.terxlabs.rtbms.dashboard.science.ScienceFragment
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity.*
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import kotlinx.android.synthetic.main.layout_bottom.*

class Dashboard : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    private val inactivatedDrawable = arrayListOf(R.drawable.ic_profile, R.drawable.ic_hospital, R.drawable.ic_pharmacy, R.drawable.ic_event)
    private val activatedDrawable = arrayListOf(R.drawable.ic_profile_red, R.drawable.ic_hospital_red, R.drawable.ic_pharmacy_red , R.drawable.ic_events_red)
    private var bottomItems = ArrayList<ImageView>()
    lateinit var drawer_Layout: DrawerLayout
    private var selectedItem = -1

    @SuppressLint("RtlHardcoded", "WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val home = findViewById<FloatingActionButton>(R.id.fab)
        val hospital = findViewById<LinearLayout>(R.id.hospital)
        drawer_Layout=findViewById(R.id.drawerlayout)
        val profile = findViewById<LinearLayout>(R.id.profile)
        val events = findViewById<LinearLayout>(R.id.events)
        val pharmacy = findViewById<LinearLayout>(R.id.pharmacy)
        val l_profile = findViewById<ImageView>(R.id.l_profile)
        val l_hospital = findViewById<ImageView>(R.id.l_hospital)
        val l_pharmacy = findViewById<ImageView>(R.id.l_pharmacy)
        navigation_view.setNavigationItemSelectedListener(this)
        val l_events = findViewById<ImageView>(R.id.l_events)
        bottomItems.add(l_profile)
        bottomItems.add(l_hospital)
        bottomItems.add(l_pharmacy)
        bottomItems.add(l_events)
        drawer.setOnClickListener {
            drawer_Layout.openDrawer(START)
            if (drawer_Layout.isDrawerOpen(navigation_view)){
                drawer_Layout.closeDrawer(RIGHT)
            }
            else{
                drawer_Layout.openDrawer(LEFT)
            }
        }
        loadFragment(HomeFragment.getInstance())
        home.setOnClickListener { setActivatedTab(null, HomeFragment.getInstance()) }
        hospital.setOnClickListener { setActivatedTab(l_hospital, HospitalFragment.getInstance()) }
        profile.setOnClickListener { setActivatedTab(l_profile, ProfileFragment.getInstance()) }
        events.setOnClickListener { setActivatedTab(l_events, EventsFragment.getInstance()) }
        pharmacy.setOnClickListener { setActivatedTab(l_pharmacy, PharmacyFragment.getInstance()) }

    }

    private fun setActivatedTab(imageView: ImageView?, fragment: Fragment) {
        var newSelected = -1
        for (i in 0 until bottomItems.size) {
            if (imageView == null) {
                newSelected = -1
            }
            if (bottomItems[i] == imageView) {
                newSelected = i
                bottomItems[i].setImageResource(activatedDrawable[i])
            } else {
                bottomItems[i].setImageResource(inactivatedDrawable[i])
            }
        }
        if (selectedItem != newSelected) {
            loadFragment(fragment)
            selectedItem = newSelected
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navigation, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    @SuppressLint("WrongConstant")
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.science -> {
                setBottomNavigationUnSelected()
                loadFragment(ScienceFragment())
                true
            }
            R.id.newsfeed -> {
               setBottomNavigationUnSelected()
                loadFragment(NewsFeedFragment.getInstance())
                true
            }
            R.id.dodont -> {
                loadFragment(DoDoesFragment.getInstance())
                setBottomNavigationUnSelected()

                true
            }
            R.id.ourteam -> {
                setBottomNavigationUnSelected()
                loadFragment( OurTeamFragment.getInstance())
                true
            }

            else->{
                false
            }
        }
        drawer_Layout.closeDrawer(START)

    return true
    }

    private fun setBottomNavigationUnSelected(){
        l_profile.setImageDrawable(resources.getDrawable(R.drawable.ic_profile))
        l_hospital.setImageDrawable(resources.getDrawable(R.drawable.ic_hospital))
        l_events.setImageDrawable(resources.getDrawable(R.drawable.ic_event))
        l_pharmacy.setImageDrawable(resources.getDrawable(R.drawable.ic_pharmacy))
    }

}
