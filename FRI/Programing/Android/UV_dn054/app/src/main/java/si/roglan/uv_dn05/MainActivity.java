package si.roglan.uv_dn05;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Nakup.HomeListener, VnosPotnikov.VnosPotnikovListener, Potniki.PotnikiListener {

    FragmentTransaction fragmentTransaction;


    Nakup nakup = new Nakup();

    ArrayList<String> userData = new ArrayList<>();
    ArrayList<String> nakupData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //display home fragment
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, nakup);
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Nakup");

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Nakup");
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new SettingsFragment());
            fragmentTransaction.addToBackStack("1");
            fragmentTransaction.commit();
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Nastavitve");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Nakup nakup = new Nakup();

            fragmentTransaction.replace(R.id.main_container, nakup);
            fragmentTransaction.commit();
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Nakup");

        } else if (id == R.id.nav_itm) {
            if (nakupData.size() > 0) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Potniki potniki = new Potniki();


                if (userData.size() > 0) {
                    final Bundle bundle = new Bundle();
                    bundle.putStringArrayList("userData", userData);
                    potniki.setArguments(bundle);
                }


                fragmentTransaction.replace(R.id.main_container, potniki);
                fragmentTransaction.addToBackStack("1");
                fragmentTransaction.commit();
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle("Potniki");
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Vnesite podatke o potovanju v zavihku Nakup", Snackbar.LENGTH_LONG).show();

            }


        } else if (id == R.id.nav_avtor) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new AvtorFragment());
            fragmentTransaction.addToBackStack("1");
            fragmentTransaction.commit();
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("Avtor");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void launch_dodaj_potnika() {

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        VnosPotnikov vnosPotnikov = new VnosPotnikov();


        fragmentTransaction.replace(R.id.main_container, vnosPotnikov);
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Vnos potnika");
    }

    @Override
    public void launch_placilo() {


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Placilo placilo = new Placilo();

        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("nakupData", nakupData);
        bundle.putStringArrayList("userData", userData);
        placilo.setArguments(bundle);


        fragmentTransaction.replace(R.id.main_container, placilo);
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Plačilo");
    }

    @Override
    public void setPlaciloFragment() {
        Placilo placilo = new Placilo();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("nakupData", nakupData);
        bundle.putStringArrayList("userData", userData);
        placilo.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_container, placilo);
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Plačilo");

    }


    @Override
    public void sendNakupData(ArrayList<String> nakupData) {
        this.nakupData = nakupData; //da posljemo homefragmentu
    }

    @Override
    public void setPotnikiFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Potniki potniki = new Potniki();

        if (userData.size() > 0) {
            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("userData", userData);
            potniki.setArguments(bundle);
        }

        fragmentTransaction.replace(R.id.main_container, potniki);
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Potniki");
    }


    @Override
    public void sendUser(ArrayList<String> userData) {
        this.userData.addAll(userData);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Potniki potniki = new Potniki();
        System.out.println("Created potniki");

        if (this.userData.size() > 0) {
            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("userData", this.userData);
            potniki.setArguments(bundle);
        }

        fragmentTransaction.replace(R.id.main_container, potniki);
        fragmentTransaction.addToBackStack("1");
        fragmentTransaction.commit();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Potniki");

    }


}
/*
final Snackbar snackBar = Snackbar.make(findViewById(R.id.main_container),  "moj_poljuben_text", Snackbar.LENGTH_INDEFINITE);
snackBar.setAction("Dismiss", new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        snackBar.dismiss();
    }
});
snackBar.show();
*/
//Toast.makeText(getApplicationContext(), "ni uspelo", Toast.LENGTH_LONG).show();
//Snackbar.make(findViewById(android.R.id.content), "Podatki o lokaciji so izklujčeni, ali pa se je prekinila povezava", Snackbar.LENGTH_LONG).show();

