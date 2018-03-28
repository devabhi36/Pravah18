package com.iet.abhinay.pravah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Abhinay on 06-02-2018.
 */

public class Student_Coordinators extends Fragment {
    CardView st_cordiA1, st_cordiA2;
    ImageButton st_cordiIBA1, st_cordiIBA2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View st_cordi = inflater.inflate(R.layout.student_coordinators, container, false);
        st_cordiA1 = (CardView) st_cordi.findViewById(R.id.st_cordi1);
        st_cordiA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 9;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        st_cordiIBA1 = (ImageButton) st_cordi.findViewById(R.id.st_cordiIB1);
        st_cordiIBA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 9;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        st_cordiA2 = (CardView) st_cordi.findViewById(R.id.st_cordi2);
        st_cordiA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 10;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        st_cordiIBA2 = (ImageButton) st_cordi.findViewById(R.id.st_cordiIB2);
        st_cordiIBA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 10;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        return st_cordi;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Student Coordinators");
    }
}
