// Adding another comment.
package com.example.abhinay.pravah;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int flag = 1;
    public static int new_flag = 0;

    public static String host = "http://192.168.43.239/";

    public static String _name, _dob, _gender, _email, _mobile, _fathername, _mothername, _aadhaar, _zone, _college, _rollno, _events1, _events2, _events3, _youare;
    public static String[ ] team = { "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1" };

    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        home(null);
        //Toast.makeText(getApplicationContext(), Integer.toString(new_flag), Toast.LENGTH_SHORT).show();

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


        /*
        Menu menuNav=navigationView.getMenu();

        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(true);

        MenuItem dashboard = menuNav.findItem(R.id.dashboard);
        dashboard.setVisible(false);

        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(true);

        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(false);
        */

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
            exit();
//            finish();
//            System.exit(0);
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
        } else if(id == R.id.map1){
            map(null);
        } else if (id == R.id.register) {
            register(null);
        } else if(id == R.id.dashboard){
            dashboard(null);
        } else if(id == R.id.changepass){
            changepass(null);
        } else if(id == R.id.profile){
            dashboard(null);
        } else if(id == R.id.yourteam){
            yourteam(null);
        } else if(id == R.id.accomadation){
            accomadation(null);
        } else if(id == R.id.sign_in) {
            sign_in(null);
        } else if(id == R.id.sign_out) {
            sign_out(null);
        } else if (id == R.id.feedback) {
            feedback(null);
        } else if (id == R.id.about1) {
            about(null);
        } else if(id == R.id.sponsor){
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

        if(new_flag == 1){
            hide_show2();
        } else {
            hide_show3();
        }
    }
    public void theme(View v2){
        Intent theme=new Intent(MainActivity.this, Theme.class);
        startActivity(theme);
    }
    public void events(View v3){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new Event(), "fragment");
        ft.commit();
        flag=0;

        if(new_flag == 1){
            hide_show5();
        } else {
            hide_show4();
        }
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
    public void map(View v5){
        Intent map=new Intent(MainActivity.this, Maps.class);
        startActivity(map);
    }
    public void about(View v6){
        Intent about= new Intent(MainActivity.this, About.class);
        startActivity(about);
    }
    public void contact(View v7){
        Intent conatct=new Intent(MainActivity.this, Contacts.class);
        startActivity(conatct);
    }
    public void feedback(View feedback){
       Intent email = new Intent(MainActivity.this, Email.class);
        startActivity(email);
    }
    public void register(View register){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
        flag=1;
    }
    public void sign_in(View sign_in){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        flag=1;
    }
    public void sign_out(View sign_out){
        // Changing flag value here will cause the app to close if user signs out on events page.
        // flag = 1;
        MainActivity._name = "";
        MainActivity._dob = "";
        MainActivity._gender = "";
        MainActivity._email = "";
        MainActivity._mobile = "";
        MainActivity._fathername = "";
        MainActivity._mothername = "";
        MainActivity._aadhaar = "";
        MainActivity._zone = "";
        MainActivity._college = "";
        MainActivity._rollno = "";
        MainActivity._events1 = "";
        MainActivity._events2 = "";
        MainActivity._events3 = "";
        MainActivity._youare = "";

        new_flag = 0;
        onBackPressed();

        hide_show3();
        Toast.makeText(this, "You have been signed out.", Toast.LENGTH_SHORT).show();
    }

    public void yourteam (View view) {

        Intent intent = new Intent(MainActivity.this, YourTeam.class);
        startActivity(intent);

        flag = 1;
    }

    public void sponcer(View sponcer){
        Toast.makeText(getApplicationContext(),"Will be announced shortly :)", Toast.LENGTH_SHORT).show();
    }
    public void dashboard(View dashboard){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new Dashboard(), "fragment");
        ft.commit();
        flag = 0;
        new_flag = 1;

        hide_show1();
    }
    public void changepass(View changepass){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new ChangePass(), "fragment");
        ft.commit();

        hide_show1();
    }
    public void team_members(View team_members){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
        ft.replace(R.id.content_frame, new TeamMembers(), "fragment");
        ft.commit();

        hide_show1();
    }
    public void accomadation(View accomadation){
        String web = "https://aktu.ac.in/pravah/";
        WebView website = new WebView(getApplicationContext());
        website.loadUrl(web);
        hide_show1();
    }
    FragmentManager fragmentManager = getSupportFragmentManager();
    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
    // Login -> Dashboard
    public void hide_show1(){
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();

        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(true);

        MenuItem profile = menuNav.findItem(R.id.profile);
        profile.setVisible(true);
        profile.setChecked(true);

        MenuItem changepass = menuNav.findItem(R.id.changepass);
        changepass.setVisible(true);

        MenuItem yourteam = menuNav.findItem(R.id.yourteam);
        yourteam.setVisible(true);

        MenuItem accomadation = menuNav.findItem(R.id.accomadation);
        accomadation.setVisible(true);

        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(false);

        MenuItem theme = menuNav.findItem(R.id.theme1);
        theme.setVisible(false);

        MenuItem schedule = menuNav.findItem(R.id.schedule1);
        schedule.setVisible(false);

        MenuItem map = menuNav.findItem(R.id.map1);
        map.setVisible(false);

        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(false);

        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(false);

        MenuItem dashboard = menuNav.findItem(R.id.dashboard);
        dashboard.setVisible(false);

        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(false);

        MenuItem feedback = menuNav.findItem(R.id.feedback);
        feedback.setVisible(false);

        MenuItem about = menuNav.findItem(R.id.about1);
        about.setVisible(false);

        MenuItem sponsors = menuNav.findItem(R.id.sponsor);
        sponsors.setVisible(false);

        MenuItem contact = menuNav.findItem(R.id.contact1);
        contact.setVisible(false);

        MenuItem join = menuNav.findItem(R.id.join);
        join.setVisible(false);

        MenuItem by_you = menuNav.findItem(R.id.by_you);
        by_you.setVisible(false);

        MenuItem about_us = menuNav.findItem(R.id.about_us);
        about_us.setVisible(false);
    }
    // Login/Dashboard -> Home
    public void hide_show2(){
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();

        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(false);

        MenuItem profile = menuNav.findItem(R.id.profile);
        profile.setVisible(false);
        profile.setChecked(true);

        MenuItem changepass = menuNav.findItem(R.id.changepass);
        changepass.setVisible(false);

        MenuItem yourteam = menuNav.findItem(R.id.yourteam);
        yourteam.setVisible(false);

        MenuItem accomadation = menuNav.findItem(R.id.accomadation);
        accomadation.setVisible(false);

        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(true);

        MenuItem theme = menuNav.findItem(R.id.theme1);
        theme.setVisible(true);

        MenuItem schedule = menuNav.findItem(R.id.schedule1);
        schedule.setVisible(true);

        MenuItem map = menuNav.findItem(R.id.map1);
        map.setVisible(true);

        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(false);

        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(false);

        MenuItem dashboard = menuNav.findItem(R.id.dashboard);
        dashboard.setVisible(true);

        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(true);

        MenuItem feedback = menuNav.findItem(R.id.feedback);
        feedback.setVisible(true);

        MenuItem about = menuNav.findItem(R.id.about1);
        about.setVisible(true);

        MenuItem sponsors = menuNav.findItem(R.id.sponsor);
        sponsors.setVisible(true);

        MenuItem contact = menuNav.findItem(R.id.contact1);
        contact.setVisible(true);

        MenuItem join = menuNav.findItem(R.id.join);
        join.setVisible(true);

        MenuItem by_you = menuNav.findItem(R.id.by_you);
        by_you.setVisible(true);

        MenuItem about_us = menuNav.findItem(R.id.about_us);
        about_us.setVisible(true);
    }
    // Not login -> Home
    public void hide_show3(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem dash = menuNav.findItem(R.id.dashboard);
        dash.setVisible(false);
        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(false);
        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(true);
        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(true);
        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(false);
        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(true);
    }
    // not login -> events
    public void hide_show4(){
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();
        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(true);
        MenuItem dash = menuNav.findItem(R.id.dashboard);
        dash.setVisible(false);
        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(false);
        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(true);
        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(true);
        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(false);
    }
    // Login ke baad -> events
    public void hide_show5(){
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();

        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(true);

        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(false);

        MenuItem profile = menuNav.findItem(R.id.profile);
        profile.setVisible(false);
        profile.setChecked(true);

        MenuItem changepass = menuNav.findItem(R.id.changepass);
        changepass.setVisible(false);

        MenuItem yourteam = menuNav.findItem(R.id.yourteam);
        yourteam.setVisible(false);

        MenuItem accomadation = menuNav.findItem(R.id.accomadation);
        accomadation.setVisible(false);

        MenuItem theme = menuNav.findItem(R.id.theme1);
        theme.setVisible(true);

        MenuItem schedule = menuNav.findItem(R.id.schedule1);
        schedule.setVisible(true);

        MenuItem map = menuNav.findItem(R.id.map1);
        map.setVisible(true);

        MenuItem register = menuNav.findItem(R.id.register);
        register.setVisible(false);

        MenuItem sign_in = menuNav.findItem(R.id.sign_in);
        sign_in.setVisible(false);

        MenuItem dashboard = menuNav.findItem(R.id.dashboard);
        dashboard.setVisible(true);

        MenuItem sign_out = menuNav.findItem(R.id.sign_out);
        sign_out.setVisible(true);

        MenuItem feedback = menuNav.findItem(R.id.feedback);
        feedback.setVisible(true);

        MenuItem about = menuNav.findItem(R.id.about1);
        about.setVisible(true);

        MenuItem sponsors = menuNav.findItem(R.id.sponsor);
        sponsors.setVisible(true);

        MenuItem contact = menuNav.findItem(R.id.contact1);
        contact.setVisible(true);

        MenuItem join = menuNav.findItem(R.id.join);
        join.setVisible(true);

        MenuItem by_you = menuNav.findItem(R.id.by_you);
        by_you.setVisible(true);

        MenuItem about_us = menuNav.findItem(R.id.about_us);
        about_us.setVisible(true);
    }

    public void exit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(" ")
                .setCancelable(false)
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        if(new_flag == 1){
                            alert();
                        }
                        else {
                            finish();
                            System.exit(0);
                        }
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Want to exit..?");
        alert.show();
    }
    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(" ")
                .setCancelable(false)
                .setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        System.exit(0);
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("You will be signed out.");
        alert.show();
    }
    }

