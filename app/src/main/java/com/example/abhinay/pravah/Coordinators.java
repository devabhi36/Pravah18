package com.example.abhinay.pravah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Abhinay on 06-02-2018.
 */

public class Coordinators extends Fragment {
    CardView cordiA, deputyA;
    ImageButton cordiIBA, deputyIBA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cordi_deputy = inflater.inflate(R.layout.coordinators, container, false);
        cordiA = (CardView) cordi_deputy.findViewById(R.id.cordi);
        cordiA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 7;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        cordiIBA = (ImageButton) cordi_deputy.findViewById(R.id.cordiIB);
        cordiIBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 7;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        deputyA = (CardView) cordi_deputy.findViewById(R.id.deputy);
        deputyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 8;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        deputyIBA = (ImageButton) cordi_deputy.findViewById(R.id.deputyIB);
        deputyIBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 8;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        return cordi_deputy;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Coordinators");
    }
}
