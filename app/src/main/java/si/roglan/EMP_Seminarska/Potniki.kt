package si.roglan.EMP_Seminarska

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.util.*


class Potniki : Fragment() {
    private var button: Button? = null
    private var textView: TextView? = null

    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    private var userData: ArrayList<String>? = ArrayList()
    private lateinit var activityCommander: PotnikiListener
    private var tabela: TableLayout? = null
    private var potniki_container: ConstraintLayout? = null

    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as PotnikiListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString())
        }

        super.onAttach(context)
    }


    private fun getPerson(): StringRequest {
        // VOLLEY GET PERSON
        val service = "/Service1.svc"
        val operationContract = "/Oseba"
        val PersonID = "/1"

        val strReq = StringRequest(Request.Method.GET, SERVER_URL + service + operationContract + PersonID,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
                    if (response != null) {
                        try {
                            textView!!.text = textView!!.text.toString() + response.toString() + "\n"
                            Snackbar.make(potniki_container!!, "RETURNED OSEBE", Snackbar.LENGTH_LONG).show()


                        } catch (e: JSONException) {
                            // If there is an error then output this to the logs.
                            Log.e("Volley", "Invalid JSON Object.")
                        }
                    } else {
                        Snackbar.make(potniki_container!!, "No responses found", Snackbar.LENGTH_LONG).show()
                    }
                },

                Response.ErrorListener { error ->
                    // If there a HTTP error then add a note to our repo list.
                    Snackbar.make(potniki_container!!, "Error while calling REST API", Snackbar.LENGTH_LONG).show()
                    Log.e("Volley", error.toString())
                }
        )
        return strReq
    }

    fun getAllPersons(): JsonArrayRequest {
        //VOLLEY GET ALL PERSONS
        val service = "/Service1.svc"
        val operationContract = "/Osebe"
        // GETTING JSON ARRAY - RETURNS ALL OSEBE
        val arrReq = JsonArrayRequest(Request.Method.GET, SERVER_URL + service + operationContract,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
                    if (response.length() > 0) {
                        // The user does have repos, so let's loop through them all.
                        for (i in 0 until response.length()) {
                            try {
                                // For each repo, add a new line to our repo list.
                                val jsonObj = response.getJSONObject(i)
                                val lastName = jsonObj.get("Address").toString()
                                textView!!.text = textView!!.text.toString() + lastName + "\n";
                                Snackbar.make(potniki_container!!, "RETURNED OSEBE", Snackbar.LENGTH_LONG).show()
                            } catch (e: JSONException) {
                                // If there is an error then output this to the logs.
                                Log.e("Volley", "Invalid JSON Object.")
                            }
                        }
                    } else {
                        Snackbar.make(potniki_container!!, "No responses found", Snackbar.LENGTH_LONG).show()
                    }
                },
                Response.ErrorListener { error ->
                    // If there a HTTP error then add a note to our repo list.
                    Snackbar.make(potniki_container!!, "Error while calling REST API", Snackbar.LENGTH_LONG).show()
                    Log.e("Volley", error.toString())
                }
        )
        return arrReq
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_potniki, container, false)


        potniki_container = view.findViewById(R.id.potniki_container) as ConstraintLayout
        tabela = view.findViewById(R.id.table_1) as TableLayout

        recieveUserData(this.arguments)

        // VOLLEY
        button = view.findViewById(R.id.button) as Button
        textView = view.findViewById(R.id.textView) as TextView
        // END VOLLEY

        val button_kupi = view.findViewById(R.id.kupi_button) as Button
        val button_dodaj = view.findViewById(R.id.button_dodaj_potnika) as Button
        button_kupi.setOnClickListener { kupi_buttonClicked() }
        button_dodaj.setOnClickListener { dodaj_buttonClicked() }


        // TODO --- VOLLEY EXAMPLE - we bind functions on button click
        button!!.setOnClickListener {
            val requestQueue = Volley.newRequestQueue(activity)

            requestQueue.add(getPerson())
            //requestQueue.add(getAllPersons())
        }
        return view
    }


    private fun dodaj_buttonClicked() {
        activityCommander.launch_dodaj_potnika()
    }

    private fun kupi_buttonClicked() { //gets called when button is clicked
        if (userData!!.size > 0) {
            activityCommander.launch_placilo()
        } else {
            Snackbar.make(potniki_container!!, "Vnesite vsaj enega potnika", Snackbar.LENGTH_LONG).show()
        }
    }

    fun recieveUserData(bundle: Bundle?) {

        if (bundle != null) {

            userData = bundle.getStringArrayList("userData")
            if (userData != null) {

                val add_index = tabela!!.childCount
                var i = 0
                while (i < userData!!.size) {

                    val row = TableRow(context)

                    val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                    row.layoutParams = lp

                    val ime = TextView(context)
                    val priimek = TextView(context)
                    val spol = TextView(context)
                    val datum_rojstva = TextView(context)
                    ime.text = userData!![i]
                    priimek.text = userData!![i + 1]
                    spol.text = userData!![i + 2]
                    datum_rojstva.text = userData!![i + 3]

                    row.addView(ime)
                    row.addView(priimek)
                    row.addView(spol)
                    row.addView(datum_rojstva)
                    tabela!!.addView(row, add_index + i / 4)
                    i += 4
                }
            }
        }
    }

    internal interface PotnikiListener {

        fun launch_dodaj_potnika()

        fun launch_placilo()
    }
}// Required empty public constructor
