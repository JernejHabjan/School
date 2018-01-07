package si.roglan.EMP_Seminarska


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
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
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import android.widget.EditText
import android.widget.RadioButton
import android.widget.DatePicker






class Nakup : Fragment() {
    private lateinit var mGeoDataClient: GeoDataClient
    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var activityCommander: NakupListener

    private lateinit var datum_prihoda: EditText
    private lateinit var razred_prihoda: Spinner
    private lateinit var datum_prihoda_view: TextView
    private lateinit var razred_prihoda_view: TextView
    private lateinit var dvosmerna: CheckBox

    private var mOdhodAutocompleteString: String = ""
    private var mPrihodAutocompleteString: String = ""
    private var mOdhodAutocomplete = SupportPlaceAutocompleteFragment()
    private var mPrihodAutocomplete = SupportPlaceAutocompleteFragment()


    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as NakupListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString())
        }

        super.onAttach(context)
    }


    fun recieveNakupData(bundle: Bundle?) {
        if (bundle != null) {

            val nakupData = bundle.getStringArrayList("nakupData")
            if (nakupData != null) {

                /*nakupData.add(fromLocation)
                nakupData.add(toLocation)
                nakupData.add(date)
                nakupData.add(travelClass)
                nakupData.add(returnDate)
                nakupData.add(returnClass)*/

                mOdhodAutocompleteString = nakupData[0]
                mPrihodAutocompleteString = nakupData[1]

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


        //Set initial departure and arrival dates
        val c = Calendar.getInstance();
        val df = SimpleDateFormat("dd.MM.yyyy");

        var datum_odhoda = view.findViewById(R.id.datum_odhoda) as EditText
        datum_odhoda.setText(df.format(c.getTime()));

        c.add(Calendar.DATE, 1);
        datum_prihoda.setText(df.format(c.getTime()));


        dvosmerna.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                datum_prihoda.visibility = View.VISIBLE
                razred_prihoda.visibility = View.VISIBLE
                datum_prihoda_view.visibility = View.VISIBLE
                razred_prihoda_view.visibility = View.VISIBLE

                //Set valid return date
                var datumOdhoda = view.findViewById(R.id.datum_odhoda) as EditText;
                val dateArray = datumOdhoda!!.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val c = Calendar.getInstance()
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[0]))
                c.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
                c.set(Calendar.YEAR, Integer.parseInt(dateArray[2]))
                c.add(Calendar.DATE, 1)
                datum_prihoda!!.setText(SimpleDateFormat("dd.MM.yyyy").format(c.time))

            } else {
                datum_prihoda.visibility = View.GONE
                razred_prihoda.visibility = View.GONE
                datum_prihoda_view.visibility = View.GONE
                razred_prihoda_view.visibility = View.GONE
            }
        }


        datum_odhoda!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v : View?){
                val dateArray = datum_odhoda.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val day = Integer.parseInt(dateArray[0])
                val month = Integer.parseInt(dateArray[1])
                val year = Integer.parseInt(dateArray[2])

                val dpd = DatePickerDialog(context,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            datum_odhoda!!.setText(
                                    String.format("%02d", dayOfMonth) + "." +
                                            String.format("%02d", monthOfYear + 1) + "." +
                                            String.format("%02d", year)
                            )

                            if (dvosmerna.isChecked) {
                                val dateArray = datum_prihoda!!.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                                val returnDay = Integer.parseInt(dateArray[0])
                                val returnMonth = Integer.parseInt(dateArray[1])
                                val returnYear = Integer.parseInt(dateArray[2])

                                if (year > returnYear || monthOfYear + 1 > returnMonth || dayOfMonth >= returnDay) {
                                    val c = Calendar.getInstance()
                                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                                    c.set(Calendar.MONTH, monthOfYear)
                                    c.set(Calendar.YEAR, year)
                                    c.add(Calendar.DATE, 1)
                                    datum_prihoda!!.setText(SimpleDateFormat("dd.MM.yyyy").format(c.time))
                                }
                            }
                        }, year, month - 1, day)

                //Limit date to today
                val c = Calendar.getInstance()
                dpd.datePicker.minDate = c.time.time
                dpd.show()
            }
        })


        datum_prihoda!!.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val dateArray = datum_prihoda.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val day = Integer.parseInt(dateArray[0])
                val month = Integer.parseInt(dateArray[1])
                val year = Integer.parseInt(dateArray[2])

                val dpd = DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        val fday = String.format("%02d", dayOfMonth)
                        val fmonth = String.format("%02d", monthOfYear + 1)
                        val fyear = String.format("%02d", year)
                        datum_prihoda.setText("$fday.$fmonth.$fyear")
                    }, year, month - 1, day)


                val ddateArray = datum_prihoda.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val limitDay = Integer.parseInt(ddateArray[0]);
                val limitMonth = Integer.parseInt(ddateArray[1]);
                val limitYear = Integer.parseInt(ddateArray[2]);

                val c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_MONTH, limitDay);
                c.set(Calendar.MONTH, limitMonth - 1);
                c.set(Calendar.YEAR, limitYear);
                dpd.getDatePicker().setMinDate(c.getTime().getTime());
                dpd.show();
            }
        })

        setupAutocompleteFragments(view)

        //Must be called after setting autocomplete fragments (avoid null values)
        val doloci_potnike_button = view.findViewById(R.id.doloci_potnike_button) as Button
        doloci_potnike_button.setOnClickListener { v -> dolociPotnikeButtonClicked(v) }

        recieveNakupData(this.arguments)

        return view
    }



    override fun onStop() {
        super.onStop()

        mGoogleApiClient!!.stopAutoManage(activity)
        mGoogleApiClient!!.disconnect()
    }

    private fun setAutocompleteFragmentOnSelectedListener(fragment: SupportPlaceAutocompleteFragment){

    }

    private fun setupAutocompleteFragments(view: View) {


        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(activity, null)

        // Construct a PlaceDetectionClient.
        val mPlaceDetectionClient = Places.getPlaceDetectionClient(activity, null)

        if (mGoogleApiClient == null)
        mGoogleApiClient = GoogleApiClient.Builder(activity)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(activity, null)
                .build()
        else
            mGoogleApiClient!!.connect()


        mOdhodAutocomplete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i("Autocomplete", "Place: " + place.name)

                /*mOdhodAutocompleteString = (place.name.toString() + "\n"
                        + place.id + "\n"
                        + place.latLng.toString() + "\n"
                        + place.address + "\n"
                        + place.attributions)*/
                //paste_location_view.text = placeDetailsStr

                mOdhodAutocompleteString = place.name.toString()
                mOdhodAutocomplete.setText(mOdhodAutocompleteString)
            }

            override fun onError(status: Status) {
                Log.i("Autocomplete fragment", "An error occurred: " + status)
            }
        })

        mPrihodAutocomplete.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i("Autocomplete", "Place: " + place.name)

                mPrihodAutocompleteString = place.name.toString()
                mPrihodAutocomplete.setText(mPrihodAutocompleteString)
            }

            override fun onError(status: Status) {
                Log.i("Autocomplete fragment", "An error occurred: " + status)
            }
        })


        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_odhod, mOdhodAutocomplete)
        ft.replace(R.id.fragment_prihod, mPrihodAutocomplete)
        ft.commit()
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
        nakup_data.add(mOdhodAutocompleteString)
        nakup_data.add(mPrihodAutocompleteString)
        nakup_data.add(datum_odhoda.text.toString())
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


    internal interface NakupListener {
        fun sendNakupData(userData: ArrayList<String>)

        fun setPlaciloFragment()

        fun setPotnikiFragment()
    }
}