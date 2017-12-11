package si.roglan.EMP_Seminarska

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Nakup.HomeListener, VnosPotnikov.VnosPotnikovListener, Potniki.PotnikiListener {

    private lateinit var fragmentTransaction: FragmentTransaction
    private var userData = ArrayList<String>()
    private var nakupData = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)


        //display home fragment
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, TravelsFragment())
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Travels"


    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (supportActionBar != null)
            supportActionBar!!.title = "Travels"
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {

            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, SettingsFragment())
            fragmentTransaction.addToBackStack("1")
            fragmentTransaction.commit()
            if (supportActionBar != null)
                supportActionBar!!.title = "Nastavitve"

            return true
        }

        return super.onOptionsItemSelected(item)
    }


    //                      HANDLING NAVIGATION                                //

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            val travelsFragment = TravelsFragment()

            fragmentTransaction.replace(R.id.main_container, travelsFragment)
            fragmentTransaction.commit()
            if (supportActionBar != null)
                supportActionBar!!.title = "Travels"

        } else if (id == R.id.nav_nakup) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, Nakup())
            fragmentTransaction.addToBackStack("1")
            fragmentTransaction.commit()
            if (supportActionBar != null)
                supportActionBar!!.title = "Nakup"


        } else if (id == R.id.nav_itm) {
            if (nakupData.size > 0) {
                fragmentTransaction = supportFragmentManager.beginTransaction()
                val potniki = Potniki()


                if (userData.size > 0) {
                    val bundle = Bundle()
                    bundle.putStringArrayList("userData", userData)
                    potniki.arguments = bundle
                }


                fragmentTransaction.replace(R.id.main_container, potniki)
                fragmentTransaction.addToBackStack("1")
                fragmentTransaction.commit()
                if (supportActionBar != null)
                    supportActionBar!!.title = "Potniki"
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Vnesite podatke o potovanju v zavihku Nakup", Snackbar.LENGTH_LONG).show()

            }


        } else if (id == R.id.nav_avtor) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, AvtorFragment())
            fragmentTransaction.addToBackStack("1")
            fragmentTransaction.commit()
            if (supportActionBar != null)
                supportActionBar!!.title = "Avtor"
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    override fun launch_dodaj_potnika() {

        fragmentTransaction = supportFragmentManager.beginTransaction()
        val vnosPotnikov = VnosPotnikov()


        fragmentTransaction.replace(R.id.main_container, vnosPotnikov)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Vnos potnika"
    }

    override fun launch_placilo() {


        fragmentTransaction = supportFragmentManager.beginTransaction()
        val placilo = Placilo()

        val bundle = Bundle()
        bundle.putStringArrayList("nakupData", nakupData)
        bundle.putStringArrayList("userData", userData)
        placilo.arguments = bundle


        fragmentTransaction.replace(R.id.main_container, placilo)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Plačilo"
    }

    override fun setPlaciloFragment() {
        val placilo = Placilo()
        fragmentTransaction = supportFragmentManager.beginTransaction()

        val bundle = Bundle()
        bundle.putStringArrayList("nakupData", nakupData)
        bundle.putStringArrayList("userData", userData)
        placilo.arguments = bundle

        fragmentTransaction.replace(R.id.main_container, placilo)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Plačilo"

    }


    override fun sendNakupData(nakupData: ArrayList<String>) {
        this.nakupData = nakupData //da posljemo homefragmentu
    }

    override fun setPotnikiFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        val potniki = Potniki()

        if (userData.size > 0) {
            val bundle = Bundle()
            bundle.putStringArrayList("userData", userData)
            potniki.arguments = bundle
        }

        fragmentTransaction.replace(R.id.main_container, potniki)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Potniki"
    }


    override fun sendUser(userData: ArrayList<String>) {
        this.userData.addAll(userData)

        fragmentTransaction = supportFragmentManager.beginTransaction()
        val potniki = Potniki()
        println("Created potniki")

        if (this.userData.size > 0) {
            val bundle = Bundle()
            bundle.putStringArrayList("userData", this.userData)
            potniki.arguments = bundle
        }

        fragmentTransaction.replace(R.id.main_container, potniki)
        fragmentTransaction.addToBackStack("1")
        fragmentTransaction.commit()
        if (supportActionBar != null)
            supportActionBar!!.title = "Potniki"

    }


}
