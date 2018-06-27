package si.roglan.uv_dn05;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class VnosPotnikov extends Fragment {
    VnosPotnikovListener activityCommander;
    private EditText ime_field;
    private EditText priimek_field;
    private RadioButton rad_m;
    private RadioButton rad_f;
    private EditText date_field;
    private RelativeLayout vnos_container;

    public VnosPotnikov() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vnos_potnikov, container, false);


        vnos_container = (RelativeLayout) view.findViewById(R.id.vnos_container);

        ime_field = (EditText) view.findViewById(R.id.vnos_ime);
        priimek_field = (EditText) view.findViewById(R.id.vnos_priimek);
        rad_m = (RadioButton) view.findViewById(R.id.rad_male);
        rad_f = (RadioButton) view.findViewById(R.id.rad_female);
        date_field = (EditText) view.findViewById(R.id.vnos_datum);

        final Button button = (Button) view.findViewById(R.id.button_dodaj_potnika);

        button.setOnClickListener( //listener
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonClicked();
                    }
                }
        );
        return view;


    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCommander = (VnosPotnikovListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
        super.onAttach(context);
    }

    private boolean checkFields() {

        if (ime_field.getText().toString().equals("")) {
            Snackbar.make(vnos_container, "Vnesite ime", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (priimek_field.getText().toString().equals("")) {
            Snackbar.make(vnos_container, "Vnesite priimek", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (date_field.getText().toString().equals("")) {
            Snackbar.make(vnos_container, "Vnesite rojstni datum", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!(rad_m.isChecked() || rad_f.isChecked())) {
            Snackbar.make(vnos_container, "Vnesite spol", Snackbar.LENGTH_LONG).show();
            return false;
        }

        try {
            DateFormat format = new SimpleDateFormat("d.m.yyyy", Locale.ENGLISH);
            format.parse(date_field.getText().toString());
        } catch (Exception e) {
            Snackbar.make(vnos_container, "Vnesite pravilen format datum rojstva -> dan.mesec.leto", Snackbar.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    private void buttonClicked() {

        if (!checkFields())
            return;


        ArrayList<String> userData = new ArrayList<>();
        userData.add(ime_field.getText().toString());
        userData.add(priimek_field.getText().toString());
        userData.add(rad_m.isChecked() ? "Moški" : "Ženska");
        userData.add(date_field.getText().toString());


        Snackbar.make(vnos_container, "Uporabnik vnešen", Snackbar.LENGTH_LONG).show();
        activityCommander.sendUser(userData); //passamo input

        System.out.println("Sent user data");
    }


    interface VnosPotnikovListener {
        void sendUser(ArrayList<String> userData);
    }

}
