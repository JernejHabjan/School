package si.roglan.EMP_Seminarska


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.Places
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import kotlinx.android.synthetic.main.fragment_nakup.*
import java.text.SimpleDateFormat
import java.util.*


class Nakup : Fragment() {
    private lateinit var mGeoDataClient: GeoDataClient
    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var activityCommander: HomeListener

    private lateinit var datum_prihoda: EditText
    private lateinit var razred_prihoda: Spinner
    private lateinit var datum_prihoda_view: TextView
    private lateinit var razred_prihoda_view: TextView
    private lateinit var dvosmerna: CheckBox





    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as HomeListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString())
        }

        super.onAttach(context)
    }


    fun recieveUserData(bundle: Bundle?) {
        if (bundle != null) {

            val userData = bundle.getStringArrayList("userData")
            if (userData != null) {
                /*destinacije.setText(userData[0]);
                home_surname_input_var.setText(userData[1]);
                if(userData[2].equals("Moški")) radioButton_male_var.setChecked(true);
                else radioButton_female_var.setChecked(true);
                visina_input_var.setText(userData[3]);
                */
                Snackbar.make(home_container, userData[0] + "<- ---USER DATA 0", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_nakup, container, false)
        datum_prihoda = view.findViewById(R.id.datum_prihoda) as EditText
        razred_prihoda = view.findViewById(R.id.razred_prihoda) as Spinner
        datum_prihoda_view = view.findViewById(R.id.datum_prihoda_view) as TextView
        razred_prihoda_view = view.findViewById(R.id.razred_prihoda_view) as TextView
        dvosmerna = view.findViewById(R.id.dvosmerna) as CheckBox


        datum_prihoda.visibility = View.GONE
        razred_prihoda.visibility = View.GONE
        datum_prihoda_view.visibility = View.GONE
        razred_prihoda_view.visibility = View.GONE


        recieveUserData(this.arguments)


        val doloci_potnike_button = view.findViewById(R.id.doloci_potnike_button) as Button
        doloci_potnike_button.setOnClickListener { v -> dolociPotnikeButtonClicked(v) }

        dvosmerna.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                datum_prihoda.visibility = View.VISIBLE
                razred_prihoda.visibility = View.VISIBLE
                datum_prihoda_view.visibility = View.VISIBLE
                razred_prihoda_view.visibility = View.VISIBLE
            } else {

                datum_prihoda.visibility = View.GONE
                razred_prihoda.visibility = View.GONE
                datum_prihoda_view.visibility = View.GONE
                razred_prihoda_view.visibility = View.GONE
            }
        }
        setupAutocompleteFramment(view)

        return view
    }

    private fun setupAutocompleteFramment(view: View) {


        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(activity, null)

        // Construct a PlaceDetectionClient.
        val mPlaceDetectionClient = Places.getPlaceDetectionClient(activity, null)

        mGoogleApiClient = GoogleApiClient.Builder(activity)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(activity, null)
                .build()


        // Autocomplete fragment - fragment found on Nakup fragment and it's used for quickly selecting places
        val autocompleteFragment = activity.fragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            val TAG = "bla"

            override fun onPlaceSelected(place: Place) {
                // TODO: Get info about the selected place. - THIS IS WHERE U INSERT TEXT IN TEXTVIEW
                Log.i(TAG, "Place: " + place.name)

                val placeDetailsStr = (place.name.toString() + "\n"
                        + place.id + "\n"
                        + place.latLng.toString() + "\n"
                        + place.address + "\n"
                        + place.attributions)
                paste_location_view.text = placeDetailsStr
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status)
            }
        })
    }

    private fun checkFields(): Boolean {

        if (datum_odhoda.text.toString() == "") {
            Snackbar.make(home_container, "Vnesite datum odhoda", Snackbar.LENGTH_LONG).show()
            return false
        }
        try {
            val format = SimpleDateFormat("d.m.yyyy", Locale.ENGLISH)
            format.parse(datum_odhoda.text.toString())

        } catch (e: Exception) {
            Snackbar.make(home_container, "Vnesite pravilen format datum odhoda -> dan.mesec.leto", Snackbar.LENGTH_LONG).show()
            return false
        }


        if (dvosmerna.isChecked) {
            if (datum_prihoda.text.toString() == "") {
                Snackbar.make(home_container, "Vnesite datum prihoda", Snackbar.LENGTH_LONG).show()
                return false
            }
            try {
                val format = SimpleDateFormat("d.m.yyyy", Locale.ENGLISH)
                format.parse(datum_prihoda.text.toString())
            } catch (e: Exception) {
                Snackbar.make(home_container, "Vnesite pravilen format datum prihoda -> dan.mesec.leto", Snackbar.LENGTH_LONG).show()
                return false
            }

        }


        return true
    }


    private fun dolociPotnikeButtonClicked(view: View) { //gets called when button is clicked


        //if (!checkFields()) // TODO COMMENTED CHECKING ON FRAGMENT NAKUP - TO DEBUG EASIER
        //    return;


        val button_label = (view as Button).text.toString()

        val nakup_data = ArrayList<String>()
        nakup_data.add(destinacije.selectedItem.toString())
        nakup_data.add(datum_odhoda.text.toString())
        nakup_data.add(st_oseb_spinner.selectedItem.toString())
        nakup_data.add(razred_odhoda.selectedItem.toString())

        if (dvosmerna.isChecked) {
            nakup_data.add(datum_prihoda.text.toString())
            nakup_data.add(razred_prihoda.selectedItem.toString())
        }
        activityCommander.sendNakupData(nakup_data)


        if (button_label == "DOLOČI POTNIKE") {
            activityCommander.setPotnikiFragment()
        } else {
            activityCommander.setPlaciloFragment() //passamo input
        }


    }


    internal interface HomeListener {
        fun sendNakupData(userData: ArrayList<String>)

        fun setPlaciloFragment()

        fun setPotnikiFragment()
    }
}