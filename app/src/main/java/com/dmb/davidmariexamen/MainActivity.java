package com.dmb.davidmariexamen;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.dmb.davidmariexamen.Dialogs.AboutAppDialog;
import com.dmb.davidmariexamen.Dialogs.PickerDialog;
import com.dmb.davidmariexamen.Fragments.ListCyclesFragment;
import com.dmb.davidmariexamen.Models.Cycle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ListCyclesFragment.OnFragmentInteractionListener {

    private ArrayList<Cycle> cycles = new ArrayList<>();

    FragmentManager fm;
    FragmentTransaction ft;

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

        fillListFragment();
        callListFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navPickData) {
            PickerDialog newFragment = PickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY);
                    String date = (sdf.format(new Date(year - 1900, month, day)));
                    Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();
                }
            });
            newFragment.show(getSupportFragmentManager(),"");
        } else if (id == R.id.navAboutApp) {
            aboutAppDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void aboutAppDialog(){
        AboutAppDialog fragment = new AboutAppDialog();
        fragment.show(getSupportFragmentManager(),"aboutApp");
    }

    public void callListFragment(){
        fm = getSupportFragmentManager();
        fm.popBackStack();
        ft = fm.beginTransaction();
        ft.add(R.id.mainFragment, ListCyclesFragment.newInstance("",""));
        ft.addToBackStack(null);
        ft.commit();
    }

    public void fillListFragment(){
        Cycle cycle = new Cycle("2n","DAM","Curs: 17-18","27 alumnes");
        cycles.add(cycle);
        Cycle cycle2 = new Cycle("1r","DAM","Curs: 17-18","30 alumnes");
        cycles.add(cycle2);
        Cycle cycle3 = new Cycle("2n","DAW","Curs: 17-18","20 alumnes");
        cycles.add(cycle3);
        Cycle cycle4 = new Cycle("1r","DAW","Curs: 17-18","30 alumnes");
        cycles.add(cycle4);
        Cycle cycle5 = new Cycle("2n","DAM","Curs: 16-17","25 alumnes");
        cycles.add(cycle5);
        Cycle cycle6 = new Cycle("1r","DAM","Curs: 16-17","32 alumnes");
        cycles.add(cycle6);
        Cycle cycle7 = new Cycle("2n","DAW","Curs: 16-17","23 alumnes");
        cycles.add(cycle7);
        Cycle cycle8 = new Cycle("1r","DAW","Curs: 16-17","29 alumnes");
        cycles.add(cycle8);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ArrayList<Cycle> getCycles() {
        return this.cycles;
    }
}
