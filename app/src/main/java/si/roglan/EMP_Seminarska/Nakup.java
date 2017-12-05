package si.roglan.EMP_Seminarska;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;






//import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment ;


import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Nakup extends Fragment {
    protected GeoDataClient mGeoDataClient;
    private GoogleApiClient mGoogleApiClient;

    HomeListener activityCommander;
    private RelativeLayout home_container;
    private Spinner destinacije;
    private EditText datum_odhoda;
    private Spinner st_oseb_spinner;
    private Spinner razred_odhoda;
    private CheckBox dvosmerna;
    private EditText datum_prihoda;
    private Spinner razred_prihoda;
    private TextView datum_prihoda_view;
    private TextView razred_prihoda_view;
    private TextView paste_location_view;

    public Nakup() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCommander = (HomeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
        super.onAttach(context);
    }


    public void recieveUserData(Bundle bundle) {
        if (bundle != null) {

            ArrayList<String> userData = bundle.getStringArrayList("userData");
            if (userData != null) {
                /*destinacije.setText(userData[0]);
                home_surname_input_var.setText(userData[1]);
                if(userData[2].equals("Moški")) radioButton_male_var.setChecked(true);
                else radioButton_female_var.setChecked(true);
                visina_input_var.setText(userData[3]);
                */
                Snackbar.make(home_container, userData.get(0) + "<- ---USER DATA 0", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nakup, container, false);


        home_container = (RelativeLayout) view.findViewById(R.id.home_container);

        destinacije = (Spinner) view.findViewById(R.id.destinacije_spinner);
        datum_odhoda = (EditText) view.findViewById(R.id.datum_odhoda_field);
        st_oseb_spinner = (Spinner) view.findViewById(R.id.st_oseb_spinner);


        razred_odhoda = (Spinner) view.findViewById(R.id.razred_odhoda_spinner);
        dvosmerna = (CheckBox) view.findViewById(R.id.checkBox);
        datum_prihoda = (EditText) view.findViewById(R.id.datum_prihoda);
        razred_prihoda = (Spinner) view.findViewById(R.id.razredPrihoda);


        datum_prihoda_view = (TextView) view.findViewById(R.id.datum_prihoda_view);
        razred_prihoda_view = (TextView) view.findViewById(R.id.razred_prihoda_view);


        datum_prihoda.setVisibility(View.GONE);
        razred_prihoda.setVisibility(View.GONE);
        datum_prihoda_view.setVisibility(View.GONE);
        razred_prihoda_view.setVisibility(View.GONE);


        recieveUserData(this.getArguments());


        final Button doloci_potnike_button = (Button) view.findViewById(R.id.doloci_potnike_button);
        doloci_potnike_button.setOnClickListener( //listener
                new View.OnClickListener() {
                    public void onClick(View v) {
                        dolociPotnikeButtonClicked(v);
                    }
                }
        );

        dvosmerna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    datum_prihoda.setVisibility(View.VISIBLE);
                    razred_prihoda.setVisibility(View.VISIBLE);
                    datum_prihoda_view.setVisibility(View.VISIBLE);
                    razred_prihoda_view.setVisibility(View.VISIBLE);
                } else {

                    datum_prihoda.setVisibility(View.GONE);
                    razred_prihoda.setVisibility(View.GONE);
                    datum_prihoda_view.setVisibility(View.GONE);
                    razred_prihoda_view.setVisibility(View.GONE);
                }
                // do something, the isChecked will be
                // true if the switch is in the On position
            }
        });






        /*SupportPlaceAutocompleteFragment  autocompleteFragment = (SupportPlaceAutocompleteFragment )
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

                */

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);

        // Construct a PlaceDetectionClient.
        PlaceDetectionClient mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), null)
                .build();


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);



        paste_location_view =(TextView) view.findViewById(R.id.paste_location_view);

        if(autocompleteFragment != null) {

            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                public static final String TAG = "bla";

                @Override
                public void onPlaceSelected(Place place) {
                    // TODO: Get info about the selected place.
                    Log.i(TAG, "Place: " + place.getName());

                    String placeDetailsStr = place.getName() + "\n"
                            + place.getId() + "\n"
                            + place.getLatLng().toString() + "\n"
                            + place.getAddress() + "\n"
                            + place.getAttributions();
                    paste_location_view.setText(placeDetailsStr);
                }

                @Override
                public void onError(Status status) {
                    // TODO: Handle the error.
                    Log.i(TAG, "An error occurred: " + status);
                }
            });
        }else{
            Log.i("blyat", " NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL " );
        }




        return view;
    }

    private boolean checkFields() {

        if (datum_odhoda.getText().toString().equals("")) {
            Snackbar.make(home_container, "Vnesite datum odhoda", Snackbar.LENGTH_LONG).show();
            return false;
        }
        try {
            DateFormat format = new SimpleDateFormat("d.m.yyyy", Locale.ENGLISH);
            format.parse(datum_odhoda.getText().toString());

        } catch (Exception e) {
            Snackbar.make(home_container, "Vnesite pravilen format datum odhoda -> dan.mesec.leto", Snackbar.LENGTH_LONG).show();
            return false;
        }


        if (dvosmerna.isChecked()) {
            if (datum_prihoda.getText().toString().equals("")) {
                Snackbar.make(home_container, "Vnesite datum prihoda", Snackbar.LENGTH_LONG).show();
                return false;
            }
            try {
                DateFormat format = new SimpleDateFormat("d.m.yyyy", Locale.ENGLISH);
                format.parse(datum_prihoda.getText().toString());
            } catch (Exception e) {
                Snackbar.make(home_container, "Vnesite pravilen format datum prihoda -> dan.mesec.leto", Snackbar.LENGTH_LONG).show();
                return false;
            }
        }


        return true;
    }
















    private void dolociPotnikeButtonClicked(View view) { //gets called when button is clicked


        if (!checkFields())
            return;

        Button b = (Button) view;
        String button_label = b.getText().toString();

        ArrayList<String> nakup_data = new ArrayList<>();
        nakup_data.add(destinacije.getSelectedItem().toString());
        nakup_data.add(datum_odhoda.getText().toString());
        nakup_data.add(st_oseb_spinner.getSelectedItem().toString());
        nakup_data.add(razred_odhoda.getSelectedItem().toString());

        if (dvosmerna.isChecked()) {
            nakup_data.add(datum_prihoda.getText().toString());
            nakup_data.add(razred_prihoda.getSelectedItem().toString());
        }
        activityCommander.sendNakupData(nakup_data);


        if (button_label.equals("DOLOČI POTNIKE")) {
            activityCommander.setPotnikiFragment();
        } else {
            activityCommander.setPlaciloFragment(); //passamo input
        }


    }


    interface HomeListener {
        void sendNakupData(ArrayList<String> userData);

        void setPlaciloFragment();

        void setPotnikiFragment();
    }


}
