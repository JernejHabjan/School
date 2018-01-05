package si.roglan.EMP_Seminarska

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import java.util.*


class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        Nakup.NakupListener,
        VnosPotnikov.VnosPotnikovListener,
        Potniki.PotnikiListener,
        SignInFragment.LoginListener,
        TravelsFragment.TravelsListener,
        Placilo.PlaciloListener
{
    private lateinit var fragmentTransaction: FragmentTransaction
    private var userData = ArrayList<String>()
    private var nakupData = ArrayList<String>()
    private var m_verifiedAccount = false
    private var m_GID = ""


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

        setContainerFragment(SignInFragment(), "Prijava");
    }

    override fun onLogin(account: GoogleSignInAccount) {
        if (!m_verifiedAccount) {
            m_GID = account.id.toString()
            val bundle = Bundle() // send GID to Potovanja fragment on login
            bundle.putString("GID", m_GID)
            setContainerFragment(TravelsFragment(), "Potovanja", bundle)

            Snackbar.make(findViewById(R.id.main_container),
                    "Prijavljeni ste kot '" + account.displayName.toString() + "'", Snackbar.LENGTH_LONG).show()


            m_verifiedAccount = true

            val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

            val toolbar = findViewById(R.id.toolbar) as Toolbar
        }
        Log.i("LOGIN", "LOGIN")
    }

    override fun onLogout() {
        val drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (m_verifiedAccount) {
            val toolbar = findViewById(R.id.toolbar) as Toolbar
            //toolbar.setNavigationIcon(null) //TODO maybe if we find R.drawable.ic_drawer

            setContainerFragment(SignInFragment(), "Prijava")

            m_verifiedAccount = false;
        }
        Log.i("LOGOUT", "LOGOUT")
    }


    fun setContainerFragment(fragment: Fragment, name: String,
                             bundle: Bundle? = null, backStackName: String? = null) {
        fragmentTransaction = supportFragmentManager.beginTransaction()

        if (bundle != null) fragment.arguments = bundle
        fragmentTransaction.replace(R.id.main_container, fragment)
        if (backStackName != null) fragmentTransaction.addToBackStack("1")

        fragmentTransaction.commit()

        if (supportActionBar != null)
            supportActionBar!!.title = name
    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (supportActionBar != null)
            supportActionBar!!.title = "Potovanja"
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
        if (item.itemId == R.id.action_settings) {
            setContainerFragment(SignInFragment(), "Uporabnik", null, "1")
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> { // send login data by bundle to travelsfragment
                val bundle = Bundle()
                bundle.putString("GID", m_GID)
                setContainerFragment(TravelsFragment(), "Potovanja", bundle, "1")
            }
            R.id.nav_nakup -> setContainerFragment(Nakup(), "Nakup", null, "1")
            R.id.nav_avtor -> setContainerFragment(AvtorFragment(), "Avtor", null, "1")
            R.id.nav_itm -> {
                if (nakupData.size > 0) {
                    setPotnikiFragment()
                } else {
                    Snackbar.make(findViewById(android.R.id.content),
                            "Vnesite podatke o potovanju v zavihku Nakup", Snackbar.LENGTH_LONG).show()

                }
            }
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)

        return true
    }


    override fun setVnosPotnikovFragment() {
        setContainerFragment(VnosPotnikov(), "Vnos potnika", null, "1")
    }

    override fun launch_placilo() {
        setPlaciloFragment()
    }

    override fun setPlaciloFragment() {
        val bundle = Bundle()
        bundle.putStringArrayList("nakupData", nakupData)
        bundle.putStringArrayList("userData", userData)

        setContainerFragment(Placilo(), "Plačilo", bundle, "1")
    }

    override fun setNakupFragment() {
        setContainerFragment(Nakup(), "Nakup", null, "1")
    }

    override fun sendNakupData(nakupData: ArrayList<String>) {
        this.nakupData = nakupData //da posljemo homefragmentu
    }

    override fun setPotnikiFragment() {
        val bundle = Bundle()
        if (userData.size > 0) {
            bundle.putStringArrayList("userData", userData)

        }
        bundle.putString("GID", m_GID)

        setContainerFragment(Potniki(), "Potniki", bundle, "1")
    }


    override fun sendUser(userData: ArrayList<String>) {
        this.userData.addAll(userData)
        setPotnikiFragment()
    }

    override fun finalizePurchase(discount: Float) {
        val bundle = Bundle()
        bundle.putString("GID", m_GID)
        setContainerFragment(TravelsFragment(), "Potovanja", bundle)

        //TODO send to db
        //VolleyHelper().addTravel(this, nakupData, userData);

        nakupData.clear();
        userData.clear();
    }

}
