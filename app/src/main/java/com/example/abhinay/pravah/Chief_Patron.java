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

public class Chief_Patron extends Fragment {
    CardView chief_patronA;
    ImageButton chief_patronIBA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View chief_patron = inflater.inflate(R.layout.chief_patron, container, false);
        chief_patronA = (CardView) chief_patron.findViewById(R.id.chief_patron);
        chief_patronA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 5;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        chief_patronIBA = (ImageButton) chief_patron.findViewById(R.id.chief_patronIB);
        chief_patronIBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 5;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        return chief_patron;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Chief Patron");
    }
}
