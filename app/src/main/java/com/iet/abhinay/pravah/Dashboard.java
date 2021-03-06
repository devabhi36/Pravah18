package com.iet.abhinay.pravah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Abhinay on 05-02-2018.
 */

public class Dashboard extends Fragment {

    TextView name, dob, gender, email, mobile, fathername, mothername, aadhaar, zone, college, rollno, events1, events2, events3, youare, tshirt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.dashboard,container,false);

        name = (TextView)v.findViewById(R.id.dname);
        dob = (TextView)v.findViewById(R.id.ddob);
        gender = (TextView)v.findViewById(R.id.dgender);
        email = (TextView)v.findViewById(R.id.demail);
        mobile = (TextView)v.findViewById(R.id.dphone);
        fathername = (TextView)v.findViewById(R.id.dfather);
        mothername = (TextView)v.findViewById(R.id.dmother);
        aadhaar = (TextView)v.findViewById(R.id.daadhaar);
        zone = (TextView)v.findViewById(R.id.dzone);
        college = (TextView)v.findViewById(R.id.dcollege);
        rollno = (TextView)v.findViewById(R.id.drollno);
        events1 = (TextView)v.findViewById(R.id.devents1);
        events2 = (TextView)v.findViewById(R.id.devents2);
        events3 = (TextView)v.findViewById(R.id.devents3);
        youare = (TextView)v.findViewById(R.id.dyouare);
        tshirt = (TextView)v.findViewById(R.id.dtshirt);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("प्रवाह");
        MainActivity.forback = 0;

        name.setText(MainActivity._name);
        dob.setText(MainActivity._dob);
        gender.setText(MainActivity._gender);
        email.setText(MainActivity._email);
        mobile.setText(MainActivity._mobile);

        aadhaar.setText(MainActivity._aadhaar);
        zone.setText(MainActivity._zone);
        college.setText(MainActivity._college);
        events1.setText(MainActivity._events1);
        events2.setText(MainActivity._events2);
        events3.setText(MainActivity._events3);
        youare.setText(MainActivity._youare);
        tshirt.setText(MainActivity._tshirt);

        if ( MainActivity._fathername.equals("-") ) {

            fathername.setText("");
            mothername.setText("");
            rollno.setText("");
        }
        else {
            fathername.setText(MainActivity._fathername);
            mothername.setText(MainActivity._mothername);
            rollno.setText(MainActivity._rollno);
        }
    }

}
