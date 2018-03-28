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

public class Patron extends Fragment {
    CardView patronA;
    ImageButton patronIBA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View patron = inflater.inflate(R.layout.patron, container, false);
        patronA = (CardView) patron.findViewById(R.id.patron);
        patronA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 6;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });
        patronIBA = (ImageButton) patron.findViewById(R.id.patronIB);
        patronIBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Web_view.num = 6;
                Intent i1 = new Intent(getActivity(), Web_view.class);
                startActivity(i1);
            }
        });

        return patron;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Patron");
    }
}
