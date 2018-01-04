package si.roglan.EMP_Seminarska

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import si.roglan.EMP_Seminarska.R.id.imageView
import android.widget.LinearLayout


class Potniki : Fragment() {
    private var button: Button? = null
    private var textView: TextView? = null

    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    private var userData: ArrayList<String>? = ArrayList()
    private lateinit var activityCommander: PotnikiListener
    private var tabela: TableLayout? = null
    private var tabelaChildSize: Int = 0;
    private var potniki_container: ConstraintLayout? = null
    private var outer_linear_layout: LinearLayout? = null


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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_potniki, container, false)


        potniki_container = view.findViewById(R.id.potniki_container) as ConstraintLayout
       // outer_linear_layout = view.findViewById(R.id.outer_linear_layout) as LinearLayout
        tabela = view.findViewById(R.id.table_1) as TableLayout
        tabelaChildSize = tabela!!.childCount

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


        //GETTING LAYOUT DATA


       /* val m_GID = recieveGID(this.arguments)
        if (m_GID != "") {
            Log.d("GoogleID", "GOT GOOGLE ID:")
            Log.d("GoogleID", m_GID)
            getPrevEnteredPassengers(view,m_GID);
        } else {
            Log.d("GoogleID", "No google ID")

            val noIDText = TextView(context)
            noIDText.text = "No google ID"
            outer_linear_layout!!.addView(noIDText)
        }*/

        return view
    }

    private fun recieveGID(bundle: Bundle?): String {
        if (bundle != null) {
            val str = bundle.getString("GID")
            if (str != null)
                return str.toString()

        }
        return ""

    }


    private fun dodaj_buttonClicked() {
        activityCommander.setVnosPotnikovFragment()
    }

    private fun kupi_buttonClicked() { //gets called when button is clicked
        if (userData!!.size > 0) {
            activityCommander.launch_placilo()
        } else {
            Snackbar.make(potniki_container!!, "Vnesite vsaj enega potnika", Snackbar.LENGTH_LONG).show()
        }
    }


    fun addSingleLayout(view: View, jsonObj: JSONObject) {


        val entryLinearLayout = LinearLayout(context)
        entryLinearLayout.orientation = LinearLayout.HORIZONTAL
        entryLinearLayout.setOnClickListener {addPersonToGrid(jsonObj)}

        outer_linear_layout!!.addView(entryLinearLayout)


        val userDataVerticalLayout = LinearLayout(context)
        userDataVerticalLayout.orientation = LinearLayout.VERTICAL
        entryLinearLayout.addView(userDataVerticalLayout)

        val nameData = TextView(context)
        nameData.text = jsonObj.get("passengerName").toString()
        userDataVerticalLayout.addView(nameData)
        val surnameData = TextView(context)
        surnameData.text = jsonObj.get("passengerSurname").toString()
        userDataVerticalLayout.addView(surnameData)

    }

    private fun addPersonToGrid(jsonObj: JSONObject) {
        TODO("not implemented")
        // ADS PERSON FROM TOP VIEW OF PREVIOUSLY ENTERED PASSENGERS ON LOWER GRID OF CURRENTLY ENTERED PASSENGERS
        Log.i("PersonTransfer", "Transfered person from top to bottom")
        Log.i("PesrsonTransfer", "NAME:")

        Log.i("PesrsonTransfer", jsonObj.get("passengerName").toString())

    }

    private fun getPrevEnteredPassengers(view: View, GID: String): JsonArrayRequest {

        val service = "/ServicePersonData.svc"
        val operationContract = "/EnteredPassengers"
        val PersonID = "/" + GID

        val arrReq = JsonArrayRequest(Request.Method.GET, SERVER_URL + service + operationContract + PersonID,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
                    if (response.length() > 0) {
                        // The user does have repos, so let's loop through them all.
                        for (i in 0 until response.length()) {
                            try {
                                // For each repo, add a new line to our repo list.
                                val jsonObj = response.getJSONObject(i)


                                addSingleLayout(view, jsonObj)

                            } catch (e: JSONException) {
                                // If there is an error then output this to the logs.
                                Log.e("Volley", "Invalid JSON Object.")
                            }
                        }
                    } else {

                        Log.e("Volley", "No responses found")

                        val noResponsesText = TextView(context)
                        noResponsesText.text = "No previously entered passengers."
                        outer_linear_layout!!.addView(noResponsesText)
                    }
                },
                Response.ErrorListener { error ->
                    // If there a HTTP error then add a note to our repo list.
                    Log.e("Volley", "Error while calling REST API.")
                    Log.e("Volley", error.toString())
                }
        )
        return arrReq
    }



    fun recieveUserData(bundle: Bundle?) {
        if (bundle != null)
        {
            userData = bundle.getStringArrayList("userData")
            if (userData != null) {
                initPassengerTable(userData);
            }
        }
    }

    fun initPassengerTable(data: ArrayList<String>?){
        for (j in 1 until tabela!!.childCount)
            tabela!!.removeView(tabela!!.getChildAt(1))

        var i = 0
        while (i < userData!!.size)
        {
            val row = TableRow(context)

            val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            row.layoutParams = lp

            val ime = TextView(context)
            val priimek = TextView(context)
            val datum_rojstva = TextView(context)
            ime.text = userData!![i]
            priimek.text = userData!![i + 1]
            datum_rojstva.text = userData!![i + 3]

            val closeButton = Button(context);
            closeButton.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
            closeButton.text = "X"
            //closeButton.minimumWidth = 0
            //closeButton.width = 20
            closeButton.tag = i / 4;

            val index = i;
            closeButton.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v : View?){
                    val id = Integer.parseInt(v!!.tag.toString())
                    Log.i("BUTTON", id.toString());

                    for(j in 0..3) userData!!.removeAt(id);
                    Log.i("BUTTON_ARRAYSIZE", userData!!.size.toString());

                    initPassengerTable(userData)
                }
            })

            row.addView(ime)
            row.addView(priimek)
            row.addView(datum_rojstva)
            row.addView(closeButton)
            tabela!!.addView(row, tabelaChildSize + i / 4)
            i += 4
        }
    }

    internal interface PotnikiListener {

        fun setVnosPotnikovFragment()

        fun launch_placilo()
    }
}// Required empty public constructor
