package com.example.abhinay.pravah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Abhinay on 05-02-2018.
 */

public class ChangePass extends Fragment {
    EditText oldpass, newpass;
    String get_oldpass, get_newpass;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.change_pass, container, false);
        oldpass = (EditText) v.findViewById(R.id.oldpass);
        newpass = (EditText) v.findViewById(R.id.newpass);
        get_oldpass = String.valueOf(oldpass.getText());
        get_newpass = String.valueOf(newpass.getText());
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("CHANGE PASSWORD");
    }
// adding a comment
    public void pass(View change_pass) {
        if (get_oldpass == get_newpass) {
            Toast.makeText(getContext(), "Password Successfully Changed", Toast.LENGTH_SHORT).show();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.setCustomAnimations(R.anim.slide_outb, R.anim.fadeout);
//            ft.replace(R.id.content_frame, new Dashboard(), "fragment");
//            ft.commit();
        } else {
            Toast.makeText(getContext(), "Passwords not matched", Toast.LENGTH_SHORT).show();
            oldpass.setText(null);
            newpass.setText(null);
        }
    }
}
