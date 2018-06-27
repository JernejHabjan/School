package si.roglan.uv_dn05;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Placilo extends Fragment {
    ArrayList<String> nakupData = new ArrayList<>();
    ArrayList<String> userData = new ArrayList<>();
    private RelativeLayout placilo_relative;
    private Switch kreditna_kartica_switch;
    private EditText kartica_input;
    private TextView cena_display;
    private TextView st_potnikov_label;
    private TextView dvosnerna_display;
    private EditText ime_placnika;
    private EditText priimek_placnika;

    public Placilo() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_placilo, container, false);

        placilo_relative = (RelativeLayout) view.findViewById(R.id.placilo_relative);


        cena_display = (TextView) view.findViewById(R.id.cena_display);
        kreditna_kartica_switch = (Switch) view.findViewById(R.id.kreditna_kartica_switch);
        kartica_input = (EditText) view.findViewById(R.id.kartica_input);
        Button zakljuci_placilo = (Button) view.findViewById(R.id.zakljuci_placilo);

        st_potnikov_label = (TextView) view.findViewById(R.id.st_potnikov_label);
        dvosnerna_display = (TextView) view.findViewById(R.id.dvosnerna_display);

        ime_placnika = (EditText) view.findViewById(R.id.ime_placnika);
        priimek_placnika = (EditText) view.findViewById(R.id.priimek_placnika);

        kartica_input.setVisibility(View.GONE);
        priimek_placnika.setVisibility(View.GONE);
        ime_placnika.setVisibility(View.GONE);

        //recieveUserData(this.getArguments());

        kreditna_kartica_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    kartica_input.setVisibility(View.VISIBLE);
                    priimek_placnika.setVisibility(View.VISIBLE);
                    ime_placnika.setVisibility(View.VISIBLE);
                } else {
                    kartica_input.setVisibility(View.GONE);
                    priimek_placnika.setVisibility(View.GONE);
                    ime_placnika.setVisibility(View.GONE);
                }
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });

        zakljuci_placilo.setOnClickListener( //listener
                new View.OnClickListener() {
                    public void onClick(View v) {
                        buttonClickedInfo(v);
                    }
                }
        );


        recieveUser_NakupData(this.getArguments());

        return view;
    }

    void calculatePrice() {


        DateFormat format = new SimpleDateFormat("d.m.yyyy", Locale.ENGLISH);
        int karta_price = 0;
        for (int i = 0; i < userData.size(); i += 4) {
            String name = userData.get(i);
            String surname = userData.get(i + 1);
            String spol = userData.get(i + 2);
            String age = userData.get(i + 3);
            Date age_date = new Date();
            try {
                age_date = format.parse(age);
            } catch (ParseException e) {
                Snackbar.make(placilo_relative, "Could not parse rojstni dan za osebo: " + name, Snackbar.LENGTH_LONG).show();
            }

            Date today = new Date();
            today.setHours(0);

            long days = Math.abs((today.getTime() - age_date.getTime()) / (1000 * 60 * 60 * 24));

            float factor;
            if (days < 2190) { //6 let
                factor = 0.0f;
            } else if (days < 4380) {//12 let
                //polovicna cena
                factor = 0.5f;
            } else {
                //normal price
                factor = 1.0f;
            }


            String destinacija = nakupData.get(0);
            String datum_odhoda = nakupData.get(1);
            String st_oseb = nakupData.get(2);
            String razred_odhoda = nakupData.get(3);
            Date datum_odhoda_date = new Date();
            try {
                datum_odhoda_date = format.parse(datum_odhoda);
            } catch (ParseException e) {
                Snackbar.make(placilo_relative, "Could not parse datum prihoda date", Snackbar.LENGTH_LONG).show();
            }

            switch (razred_odhoda) {
                case "Prvi":
                    factor = 2.0f * factor;
                    break;
                case "Poslovni":
                    factor = 1.5f * factor;
                    break;
                case "Ekonomski":
                    factor = 1.2f * factor;
                    break;
            }


            switch (destinacija) {
                case "Gabrška gora":
                    karta_price += 50 * factor;
                    break;
                case "Žiri":
                    karta_price += 55 * factor;
                    break;
                case "Gorenja vas":
                    karta_price += 101 * factor;
                    break;
                case "Log nad Škofjo Loko":
                    karta_price += 14 * factor;
                    break;
                case "Žetina":
                    karta_price += 22 * factor;
                    break;
                case "Brode":
                    karta_price += 66 * factor;
                    break;
                case "Zminec":
                    karta_price += 52 * factor;
                    break;
                case "Rovte":
                    karta_price += 80 * factor;
                    break;
                case "Dolenčice":
                    karta_price += 255 * factor;
                    break;
                case "Javorje":
                    karta_price += 12 * factor;
                    break;
                default:
                    Snackbar.make(placilo_relative, "Destinacija ni podprta", Snackbar.LENGTH_LONG).show();
                    karta_price = 0;
                    cena_display.setText(Integer.toString(karta_price));
                    return;
            }


            if (nakupData.size() > 4) {
                String datum_prihoda = nakupData.get(4);
                String razred_prihoda = nakupData.get(5);
                Date datum_prihoda_date = new Date();
                try {
                    datum_prihoda_date = format.parse(datum_prihoda);

                } catch (ParseException e) {
                    Snackbar.make(placilo_relative, "Could not parse datum prihoda date", Snackbar.LENGTH_LONG).show();
                }


                switch (razred_prihoda) {
                    case "Prvi":
                        factor = 2.0f * factor;
                        break;
                    case "Poslovni":
                        factor = 1.5f * factor;
                        break;
                    case "Ekonomski":
                        factor = 1.2f * factor;
                        break;
                }


                switch (destinacija) {
                    case "Gabrška gora":
                        karta_price += 50 * factor;
                        break;
                    case "Žiri":
                        karta_price += 55 * factor;
                        break;
                    case "Gorenja vas":
                        karta_price += 101 * factor;
                        break;
                    case "Log nad Škofjo Loko":
                        karta_price += 14 * factor;
                        break;
                    case "Žetina":
                        karta_price += 22 * factor;
                        break;
                    case "Brode":
                        karta_price += 66 * factor;
                        break;
                    case "Zminec":
                        karta_price += 52 * factor;
                        break;
                    case "Rovte":
                        karta_price += 80 * factor;
                        break;
                    case "Dolenčice":
                        karta_price += 255 * factor;
                        break;
                    case "Javorje":
                        karta_price += 12 * factor;
                        break;
                    default:
                        Snackbar.make(placilo_relative, "Destinacija ni podprta", Snackbar.LENGTH_LONG).show();
                        karta_price = 0;
                        cena_display.setText(Integer.toString(karta_price));
                        return;
                }


            }


        }
        cena_display.setText(Integer.toString(karta_price));


    }


    private void recieveUser_NakupData(Bundle bundle) {
        if (bundle != null) {
            nakupData = bundle.getStringArrayList("nakupData");
            userData = bundle.getStringArrayList("userData");
            System.out.println("RECIEVE USER NAKUP DATA");
            System.out.println(nakupData.size());
            System.out.println(userData.size());

            if ((nakupData != null && nakupData.size() > 0) && (userData != null && userData.size() > 0)) {

                //String nakupData.get(0);

                for (int i = 0; i < userData.size(); i += 4) {
                    System.out.println("GOT USER");

                }
                for (int i = 0; i < nakupData.size(); i++) {
                    System.out.println(nakupData.get(i));

                }

                if (nakupData.size() > 4) {
                    dvosnerna_display.setText("Da");

                }

                int numUsers = userData.size() / 4;
                st_potnikov_label.setText(Integer.toString(numUsers));
                calculatePrice();
            }


        }
    }


    private void buttonClickedInfo(View view) { //gets called when button is clicked
        if (kreditna_kartica_switch.isChecked() && kartica_input.getText().toString().equals("")) {
            Snackbar.make(placilo_relative, "Vnesite kreditno kartico", Snackbar.LENGTH_LONG).show();
        } else {

            new AlertDialog.Builder(getContext())
                    .setTitle("Plačilo")
                    .setMessage("Ali res želite plačati?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Snackbar.make(placilo_relative, "Nakup ste uspešno opravili", Snackbar.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }


    }


}
