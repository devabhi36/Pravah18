package com.example.abhinay.pravah;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{
    ArrayAdapter genderA, youareA, zoneA, event1A, event2A, event3A, tshirtA;
    Spinner genderS, youareS, zoneS, event1S, event2S, event3S, tshirtS;

    public static int waiting = 0;
    ImageView loader;

    EditText firstname, lastname, dob, email, password, cpassword, mobile, fathername, mothername, aadhaar, rollno, college;
    int gen, you, zon, tsh;
    String e1, e2, e3;

    String[ ] event = { "Select", "Collage Making", "Face Painting", "Mehandi Design", "Poster Making", "3D Rangoli/Painting", "T-shirt Painting", "Bandwars", "Fashion Jalwa", "Solo Dance", "Duet Dance", "Group Dance", "Mimicry/Standup Comedy", "Solo Singing", "Duet Singing", "Group Singing", "Skit/Play", };

    String[] gender = { " MALE", " FEMALE", " OTHER",};
    String[] youare = { " PARTICIPANT", " ACCOMPANYING FACULTY"," OTHERS",};
    String[] zone = { " AGRA", " ALLAHABAD", " BAREILLY", " GAUTAM BUDH NAGAR", " GHAZIABAD", " GORAKHPUR", " LUCKNOW", " MEERUT",};
    String[] tshirt = { "SMALL", "MEDIUM", "LARGE", "XL", "XXL" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Already Registered?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(" ");
        alert.show();

        setContentView(R.layout.activity_register);

        genderS = (Spinner)findViewById(R.id.gender);
        youareS = (Spinner)findViewById(R.id.areyou);
        zoneS = (Spinner)findViewById(R.id.zone);
        tshirtS = (Spinner)findViewById(R.id.tshirt);

        event1S = (Spinner)findViewById(R.id.event1);
        event2S = (Spinner)findViewById(R.id.event2);
        event3S = (Spinner)findViewById(R.id.event3);

        genderA = new ArrayAdapter(this, R.layout.spinner_item_text, gender);
        genderA.setDropDownViewResource(R.layout.spinner_dropdown_text);
        genderS.setAdapter(genderA);
        genderS.setOnItemSelectedListener(new genderSpinnerClass());

        youareA = new ArrayAdapter(this, R.layout.spinner_item_text, youare);
        youareA.setDropDownViewResource(R.layout.spinner_dropdown_text);
        youareS.setAdapter(youareA);
        youareS.setOnItemSelectedListener(new youAreSpinnerClass());

        zoneA = new ArrayAdapter(this, R.layout.spinner_item_text, zone);
        zoneA.setDropDownViewResource(R.layout.spinner_dropdown_text);
        zoneS.setAdapter(zoneA);
        zoneS.setOnItemSelectedListener(new zoneSpinnerClass());

        tshirtA = new ArrayAdapter(this, R.layout.spinner_item_text, tshirt);
        tshirtA.setDropDownViewResource(R.layout.spinner_dropdown_text);
        tshirtS.setAdapter(tshirtA);
        tshirtS.setOnItemSelectedListener(new tshirtSpinnerClass());

        event1A = new ArrayAdapter(this, R.layout.spinner_item_text, event);
        event1A.setDropDownViewResource(R.layout.spinner_dropdown_text);
        event1S.setAdapter(event1A);
        event1S.setOnItemSelectedListener(new event1SpinnerClass());

        event2A = new ArrayAdapter(this, R.layout.spinner_item_text, event);
        event2A.setDropDownViewResource(R.layout.spinner_dropdown_text);
        event2S.setAdapter(event2A);
        event2S.setOnItemSelectedListener(new event2SpinnerClass());

        event3A = new ArrayAdapter(this, R.layout.spinner_item_text, event);
        event3A.setDropDownViewResource(R.layout.spinner_dropdown_text);
        event3S.setAdapter(event3A);
        event3S.setOnItemSelectedListener(new event3SpinnerClass());

        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        dob = (EditText)findViewById(R.id.dob);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        cpassword = (EditText)findViewById(R.id.cpassword);
        mobile = (EditText)findViewById(R.id.mobile);
        fathername = (EditText)findViewById(R.id.fathername);
        mothername = (EditText)findViewById(R.id.mothername);
        aadhaar = (EditText)findViewById(R.id.aadhaar);
        rollno = (EditText)findViewById(R.id.rollno);
        college = (EditText)findViewById(R.id.college);
    }

    private class dataBundle {

        String f, l, d, e, p, fa, mo, c, m, a, ro, ev;
        int g, z, y, t;

        private dataBundle (String f, String l, String d, String e, int g, String p, String fa, String mo, String c, String m, String a, String ro, int z, String ev, int y, int t){
            this.f = f;
            this.l = l;
            this.d = d;
            this.e = e;
            this.g = g;
            this.p = p;
            this.fa = fa;
            this.mo = mo;
            this.c = c;
            this.m = m;
            this.a = a;
            this.ro = ro;
            this.z = z;
            this.ev = ev;
            this.y = y;
            this.t = t;
        }
    }

    public void submit(View view) {

        if ( waiting == 0 ) {

            String valueFirstname = firstname.getText().toString();
            String valueLastname = lastname.getText().toString();
            String valueDob = dob.getText().toString();
            String valueEmail = email.getText().toString();
            String valuePassword = password.getText().toString();
            String valueCPassword = cpassword.getText().toString();
            String valueFather = fathername.getText().toString();
            String valueMother = mothername.getText().toString();
            String valueCollege = college.getText().toString();
            String valueMobile = mobile.getText().toString();
            String valueAadhar = aadhaar.getText().toString();
            String valueRollno = rollno.getText().toString();

            if ((valueFirstname.length() > 0) && (valueLastname.length() > 0) && (valueDob.length() > 0) && (valueEmail.length() > 0) && (valuePassword.length() > 0) && (valueFather.length() > 0) && (valueMother.length() > 0) && (valueCollege.length() > 0) && (valueAadhar.length() > 0) && (valueRollno.length() > 0)) {

                if (valueDob.length() > 10) {
                    // Format the date for the database.
                    int comma = valueDob.indexOf(",");

                    String year = valueDob.substring(comma + 2, comma + 6);
                    String mon = returnD(valueDob.substring(0, 3));
                    String date = valueDob.substring(4, comma);

                    if (Integer.parseInt(date) < 10)
                        date = "0" + date;

                    valueDob = year + "-" + mon + "-" + date;
                } else {
                    Toast.makeText(this, "Invalid date format!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valuePassword.length() < 8) {
                    Toast.makeText(this, "Password must be atleast 8 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((!valueEmail.contains("@")) && (valueEmail.length() < 3)) {
                    Toast.makeText(this, "Invalid email format! Enter: example@email.com", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!valuePassword.equals(valueCPassword)) {
                    Toast.makeText(this, "Your entered passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valueMobile.length() != 10) {
                    Toast.makeText(this, "Phone number must consist of 10 digits.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (valueAadhar.length() != 14) {
                    Toast.makeText(this, "Invalid format for Aadhaar. Enter: XXXX XXXX XXXX", Toast.LENGTH_SHORT).show();
                    return;
                }

                int valueZone = zon;
                int valueGender = gen;
                int valueYouAre = you;
                int valueTshirt = tsh;

                String valueEvents = e1 + ";" + e2 + ";" + e3 + ";";

                dataBundle bundle = new dataBundle(valueFirstname, valueLastname, valueDob, valueEmail, valueGender, valuePassword, valueFather, valueMother, valueCollege, valueMobile, valueAadhar, valueRollno, valueZone, valueEvents, valueYouAre, valueTshirt);
                new AsyncCaller().execute(bundle);
            } else {
                Toast.makeText(this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public static String returnD ( String month )
    {
        String [ ] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

        int i;
        for ( i = 0; i < 12; i++ )
        {
            if ( months[ i ].equals( month ) )
            {
                break;
            }
        }

        if ( i > 9 )
            return String.valueOf( ( i + 1 ) );
        else
            return "0" + String.valueOf( ( i + 1 ) );
    }

    private class AsyncCaller extends AsyncTask<dataBundle, Void, String> {

        @Override
        protected void onPreExecute() {

            View view = Register.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            loader = (ImageView)findViewById(R.id.loader);
            loader.setImageResource(R.mipmap.loader);

            waiting = 1;
        }

        @Override
        protected String doInBackground(dataBundle... data) {
            String fname = data[0].f;
            String lname = data[0].l;
            String dob = data[0].d;
            String em = data[0].e;
            int ge = (data[0].g + 1);
            String pass = data[0].p;
            String papa = data[0].fa;
            String mama = data[0].mo;
            String col = data[0].c;
            String mob = data[0].m;
            String aad = data[0].a;
            String roll = data[0].ro;
            int zo = (data[0].z + 1);
            String eve = (data[0].ev);
            int yo = (data[0].y + 1);
            int ts = (data[0].t);

            try{

                String link = MainActivity.host + "register.php";
                String data_  = URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" + URLEncoder.encode("dob", "UTF-8") + "=" + URLEncoder.encode(dob, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(em, "UTF-8") + "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ge), "UTF-8") +"&"+ URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&" + URLEncoder.encode("fathername", "UTF-8") + "=" + URLEncoder.encode(papa, "UTF-8") + "&" + URLEncoder.encode("mothername", "UTF-8") + "=" + URLEncoder.encode(mama, "UTF-8") + "&" + URLEncoder.encode("college", "UTF-8") + "=" + URLEncoder.encode(col, "UTF-8")+ "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(mob, "UTF-8") + "&" + URLEncoder.encode("aadhaar", "UTF-8") + "=" + URLEncoder.encode(aad, "UTF-8")+ "&" + URLEncoder.encode("rollno", "UTF-8") + "=" + URLEncoder.encode(roll, "UTF-8")+ "&" + URLEncoder.encode("zone", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(zo), "UTF-8") + "&" + URLEncoder.encode("youare", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(yo), "UTF-8") + "&" + URLEncoder.encode("events", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(eve), "UTF-8") + "&" + URLEncoder.encode("tshirt", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ts), "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data_ );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            }
            catch(Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if ( result.equals("Registration successful.") )
            {
                Toast.makeText(getApplicationContext(), result + " You may now sign in using your details.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);

                loader.setVisibility(View.INVISIBLE);
                waiting = 0;
            }
            else if ( result.equals("An account with the given email is already registered!") )
            {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Databse error.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void dob(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
        Toast.makeText(getApplicationContext(), "Click to change year.", Toast.LENGTH_SHORT).show();
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

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class genderSpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            gen = position;
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class youAreSpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            you = position;
            if ( position > 0 ){

                fathername.setEnabled(false);
                mothername.setEnabled(false);
                rollno.setEnabled(false);

                fathername.setText("-");
                mothername.setText("-");
                rollno.setText("-");

                event1S.setEnabled(false);
                event2S.setEnabled(false);
                event3S.setEnabled(false);

                event1S.setSelection(0);
                event2S.setSelection(0);
                event3S.setSelection(0);

                e1 = e2 = e3 = "-";
            }
            else {
                fathername.setEnabled(true);
                mothername.setEnabled(true);
                rollno.setEnabled(true);

                fathername.setText("");
                mothername.setText("");
                rollno.setText("");

                event1S.setEnabled(true);
                event2S.setEnabled(true);
                event3S.setEnabled(true);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class zoneSpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            zon = position;
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class tshirtSpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            tsh = position+1;
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class event1SpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if ( position == 0 )
            {
                e1 = "-";
            }
            else if ( position > 0 && position < 7 )
            {
                e1 = "0"+String.valueOf(position);
            }
            else
            {
                position = position + 3;
                e1 = String.valueOf(position);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class event2SpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {
                e2 = "-";
            } else if (position > 0 && position < 7) {
                e2 = "0" + String.valueOf(position);
            } else {
                position = position + 3;
                e2 = String.valueOf(position);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class event3SpinnerClass implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if ( position == 0 )
            {
                e3 = "-";
            }
            else if ( position > 0 && position < 7 )
            {
                e3 = "0"+String.valueOf(position);
            }
            else
            {
                position = position + 3;
                e3 = String.valueOf(position);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public static class DatePickerFragment extends DialogFragment{
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.DATE);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), day, month, year);
        }
    }
}
