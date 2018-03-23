package com.encore.abhinay.pravah;

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

public class Developers extends Fragment {
    CardView developerA1, developerA2;
    ImageButton developerIBA1, developerIBA2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View developer = inflater.inflate(R.layout.developers, container, false);
        developerA1 = (CardView) developer.findViewById(R.id.developer1);
        developerA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 11;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        developerIBA1 = (ImageButton) developer.findViewById(R.id.developerIB1);
        developerIBA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 11;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        developerA2 = (CardView) developer.findViewById(R.id.developer2);
        developerA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 12;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        developerIBA2 = (ImageButton) developer.findViewById(R.id.developerIB2);
        developerIBA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 12;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        return developer;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Developers");
        Toast.makeText(getContext(), "Click to know more", Toast.LENGTH_SHORT).show();
    }
}
