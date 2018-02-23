package com.example.abhinay.pravah;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{
    ArrayAdapter genderA, youareA, zoneA;
    Spinner genderS, youareS, zoneS;

    String[] gender = {"Gender", "Male", "Female", "Other",};
    String[] youare = {"ARE YOU", "Participant", "Accompanying Faculty","Others",};
    String[] zone = {"Zone", "Agra", "Allahabad", "Bareilly", "Gautum Buddh Nagar", "Ghaziabad", "Gorakhpur", "Lucknow", "Meerut",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        genderS = (Spinner)findViewById(R.id.gender);
        youareS = (Spinner)findViewById(R.id.areyou);
        zoneS = (Spinner)findViewById(R.id.zone);

        genderA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, gender);
        genderA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderS.setAdapter(genderA);
        youareA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, youare);
        youareA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        youareS.setAdapter(youareA);
        zoneA = new ArrayAdapter(this,android.R.layout.simple_spinner_item,zone);
        zoneA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zoneS.setAdapter(zoneA);
    }

    public void dob(View view){
    DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((EditText) findViewById(R.id.dob)).setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int day, int month, int year) {
        Calendar cal = new GregorianCalendar(day, month, year);
        setDate(cal);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static class DatePickerFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), day, month, year);
        }
    }
}
