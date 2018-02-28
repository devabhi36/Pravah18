package com.example.abhinay.pravah;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class Dashboard extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("DASHBOARD");
        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.dashboard, container, false);
        hide_show(v);
        return v;
    }

    public void hide_show(View view){
        NavigationView navigationView= (NavigationView)view.findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();

        MenuItem home = menuNav.findItem(R.id.home);
        home.setVisible(true);

        MenuItem profile = menuNav.findItem(R.id.profile);
        profile.setVisible(true);
        profile.setChecked(true);

        MenuItem changepass = menuNav.findItem(R.id.changepass);
        changepass.setVisible(true);

        MenuItem team = menuNav.findItem(R.id.team);
        team.setVisible(true);

        MenuItem accomadation = menuNav.findItem(R.id.accomadation);
        accomadation.setVisible(true);

        MenuItem events = menuNav.findItem(R.id.events1);
        events.setVisible(false);

        MenuItem theme = menuNav.findItem(R.id.theme1);
        theme.setVisible(false);

        MenuItem schedule = menuNav.findItem(R.id.schedule1);
        schedule.setVisible(false);

        MenuItem gallery = menuNav.findItem(R.id.gallery1);
        gallery.setVisible(false);

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
}
