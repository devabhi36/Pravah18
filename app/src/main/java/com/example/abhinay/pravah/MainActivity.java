
package com.example.abhinay.pravah;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int flag=-1;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        setContentView(R.layout.activity_main);

        home(null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//       fab.setVisibility(View.INVISIBLE);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.main);


        Menu menuNav=navigationView.getMenu();

        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(true);

        MenuItem dashboard = menuNav.findItem(R.id.dashboard);
        dashboard.setVisible(false);

        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(true);

        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(false);

       // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       // navigation.setOnNavigationItemSelectedListener(bottomNavigationView);
    }

//    public class TimerSet extends TimerTask {
//
//        @Override
//        public void run() {
//
//            MainActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    switch (mViewPager.getCurrentItem()) {
//                        case 0:
//                            mViewPager.setCurrentItem(1);
//                            break;
//                        case 1:
//                            mViewPager.setCurrentItem(2);
//                            break;
//                        case 2:
//                            mViewPager.setCurrentItem(3);
//                            break;
//                        case 3:
//                            mViewPager.setCurrentItem(4);
//                            break;
//                        case 4:
//                            mViewPager.setCurrentItem(0);
//                            break;
//                        default: break;
//                    }
//
//                }
//            });
//
//        }
//    }

// Adding this comment as a test.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(flag==0){
            flag=1;
           home(null);
        } else if(flag==3) {
            flag = 0;
            events(null);
        } else if(flag==1) {
            finish();
            System.exit(0);
        } else {
            Toast.makeText(getApplicationContext(),Integer.toString(flag)+" Flag value not set for this.",Toast.LENGTH_SHORT).show();
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
         if(id== R.id.rate){
            Toast.makeText(this, "Url to be added", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.share){
            Toast.makeText(this,"After getting the URL", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        if(id==R.id.home){
            home(null);
        } else if (id == R.id.theme1) {
            theme(null);
        } else if (id == R.id.events1) {
            events(null);
        } else if (id == R.id.schedule1) {
            schedule(null);
        } else if (id == R.id.gallery1) {
            gallery(null);
        } else if(id == R.id.map1){
            map(null);
        } else if (id == R.id.register) {
            register(null);
        } else if(id == R.id.sign_in) {
            sign_in(null);
        } else if (id == R.id.post1) {
           post(null);
        } else if (id == R.id.feedback) {
            feedback(null);
        } else if (id == R.id.about1) {
            about(null);
        } else if(id == R.id.sponcer){
            sponcer(null);
        } else if (id == R.id.contact1) {
            contact(null);
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void home(View v1){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fadein, R.anim.slide_in);
        ft.replace(R.id.content_frame, new PRAVAH(), "fragment");
        ft.commit();
        flag=1;

        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();
        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(false);
    }
    public void theme(View v2){
        Intent theme=new Intent(MainActivity.this, Theme.class);
        startActivity(theme);
    }
    public void events(View v3){
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();
        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(true);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new Event(), "fragment");
        ft.commit();
        flag=0;
    }
        public void cultural(View v31){
            NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
            Menu menuNav=navigationView.getMenu();
            MenuItem home = menuNav.findItem(R.id.home);
            home.setVisible(true);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
            ft.replace(R.id.content_frame, new Cultural(), "fragment");
            ft.commit();
            flag=3;
        }
            public void band(View v311){
                Intent i = new Intent(this, Band.class);
                startActivity(i);
                ((Activity) this).overridePendingTransition(0,0);
            }
            public void play(View v312){
                Intent i = new Intent(this, Play.class);
                startActivity(i);
                ((Activity) this).overridePendingTransition(0,0);
    }
            public void fashion(View v313){
        Intent i = new Intent(this, Fashion.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void comedy(View v314){
        Intent i = new Intent(this, Comedy.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void singing(View v315){
        Intent i = new Intent(this, Singing.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void dance(View v316){
        Intent i = new Intent(this, Dance.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
        public void arts(View v32){
            NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
            Menu menuNav=navigationView.getMenu();
            MenuItem home = menuNav.findItem(R.id.home);
            home.setVisible(true);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new Arts(), "fragment");
        ft.commit();
        flag=3;
    }
            public void collage(View v321){
        Intent i = new Intent(this, Collage.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void tshirt(View v322){
        Intent i = new Intent(this, Tshirt.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void poster(View v323){
        Intent i = new Intent(this, Poster.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void face(View v324){
        Intent i = new Intent(this, Face.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void rangoli(View v325){
        Intent i = new Intent(this, Rangoli.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
            public void mehandi(View v326){
        Intent i = new Intent(this, Mehandi.class);
        startActivity(i);
        ((Activity) this).overridePendingTransition(0,0);
    }
    public void schedule(View v3){
        Intent schedule=new Intent(MainActivity.this, Schedule.class);
        startActivity(schedule);
    }
    public void gallery(View v4){
        Toast.makeText(getApplicationContext(),"Go to open gallery", Toast.LENGTH_SHORT).show();
    }
    public void map(View v5){
        Intent map=new Intent(MainActivity.this, Maps.class);
        startActivity(map);
    }
    public void about(View v6){
        Intent about= new Intent(MainActivity.this, About.class);
        startActivity(about);
    }
    public void contact(View v7){
        Toast.makeText(getApplicationContext(),"Contact Page", Toast.LENGTH_SHORT).show();
    }
    public void post(View v8){
        Toast.makeText(getApplicationContext(),"Post your pic or vids", Toast.LENGTH_SHORT).show();
    }
    public void feedback(View feedback){
        Toast.makeText(this,"Give us feedback or comment", Toast.LENGTH_SHORT).show();
    }
    public void register(View register){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        flag=1;
    }
        public void submit(View view1){
        Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        flag = 1;

            NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
            Menu menuNav=navigationView.getMenu();

            MenuItem register = menuNav.findItem(R.id.register);
            register.setVisible(false);

            MenuItem dashboard = menuNav.findItem(R.id.dashboard);
            dashboard.setVisible(true);

            MenuItem sign_in = menuNav.findItem(R.id.sign_in);
            sign_in.setVisible(false);

            MenuItem sign_out = menuNav.findItem(R.id.sign_out);
            sign_out.setVisible(true);

        }
    public void sign_in(View sign_in){
        Toast.makeText(this,"Go to Sign In Page", Toast.LENGTH_SHORT).show();
    }
    public void sponcer(View sponcer){
        Toast.makeText(getApplicationContext(),"Show Sponcers", Toast.LENGTH_SHORT).show();
    }


    FragmentManager fragmentManager = getSupportFragmentManager();
    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

}
