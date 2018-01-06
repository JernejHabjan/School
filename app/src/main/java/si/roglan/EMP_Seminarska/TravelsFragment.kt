package si.roglan.EMP_Seminarska

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException
import org.json.JSONObject

class TravelsFragment : Fragment() {
    private lateinit var add_trip_float_button: FloatingActionButton
    var travels_content_local: ConstraintLayout? = null

    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"
    private lateinit var activityCommander: TravelsListener

    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as TravelsListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString())
        }

        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.our_travels, container, false)
        add_trip_float_button = view.findViewById(R.id.add_trip_float_button) as FloatingActionButton
        travels_content_local = view.findViewById(R.id.travels_content) as ConstraintLayout
        add_trip_float_button.setOnClickListener {
            activityCommander.setNakupFragment()
        }


        //GETTING LAYOUT DATA

        val m_GID = recieveGID(this.arguments)
        /*if (m_GID != "") {
            Log.d("GoogleID", "GOT GOOGLE ID:")
            Log.d("GoogleID", m_GID)
            getPreviousTravels(view, m_GID)
        } else {
            Log.d("GoogleID", "No google ID")


            val grid_linear_layout_add = view.findViewById(R.id.grid_linear_layout_add) as LinearLayout
            val noIDText = TextView(context)
            noIDText.text = "No google ID"
            grid_linear_layout_add.addView(noIDText)
        }*/

        updateTravelsList(view, m_GID);

        return view
    }


    private fun updateTravelsList(view: View?, googleID: String){
        val travels = VolleyHelper().getTravels(activity, googleID);

        val table = view!!.findViewById(R.id.travels_table) as TableLayout;
        val travel_layout = view!!.findViewById(R.id.travel_layout) as TableRow;


        //val inflator = activity.getLayoutInflater()
        //val rowView = TableRow(travel_layout.context)
        //inflator.inflate(R.layout.travel_entry, rowView)
        //var tableRow = TableRow(context);

        //table.addView(rowView)

        //TODO update table
    }

    fun recieveGID(bundle: Bundle?): String {
        if (bundle != null) {
            return bundle.getString("GID").toString()

        }
        return ""
    }

    fun addProperty(layout: LinearLayout, propertyName: String, propertyValue: String) {
        val wrapperLinear = LinearLayout(context)
        wrapperLinear.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        wrapperLinear.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
        wrapperLinear.orientation = LinearLayout.HORIZONTAL
        layout.addView(wrapperLinear)

        val propertyNameView = TextView(context)
        propertyNameView.text = propertyName
        wrapperLinear.addView(propertyNameView)

        val noResponsesText = TextView(context)
        noResponsesText.text = propertyValue
        wrapperLinear.addView(propertyNameView)

    }

    fun addSingleLayout(view: View, jsonObj: JSONObject) {


        /*val grid_linear_layout_add = view.findViewById(R.id.grid_linear_layout_add) as LinearLayout

        val outer_linear_layout = LinearLayout(context)
        outer_linear_layout.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        outer_linear_layout.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        outer_linear_layout.orientation = LinearLayout.HORIZONTAL
        grid_linear_layout_add.addView(outer_linear_layout)


        val flightName = TextView(context)
        grid_linear_layout_add.addView(flightName)

        val inner_linear_layout = LinearLayout(context)
        inner_linear_layout.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        inner_linear_layout.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        inner_linear_layout.orientation = LinearLayout.VERTICAL
        grid_linear_layout_add.addView(inner_linear_layout)

        //adding properties to single entry
        addProperty(inner_linear_layout, "Plane name", jsonObj.get("planeName").toString())
        addProperty(inner_linear_layout, "Destination", jsonObj.get("destination").toString())*/

    }


    private fun getPreviousTravels(view: View, GID: String): JsonArrayRequest {

        val service = "/ServiceTravelData.svc"
        val operationContract = "/PreviousTravelsAll"
        val personID = "/" + GID

        val arrReq = JsonArrayRequest(Request.Method.GET, SERVER_URL + service + operationContract + personID,
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

                       /* Log.e("Volley", "No responses found")
                        val grid_linear_layout_add = view.findViewById(R.id.grid_linear_layout_add) as LinearLayout
                        val noResponsesText = TextView(context)
                        noResponsesText.text = "No previous travels found."
                        grid_linear_layout_add.addView(noResponsesText)*/
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

    fun internalDatabaseExampleCode() {
        // We may need this ???
        val DATABASE_NAME = "SlivkoDatabase"
        val DATABASE_VERSION = 1

        val databaseHelper = DatabaseHelper(context, DATABASE_NAME, context, DATABASE_VERSION)
        databaseHelper.insertContact("LastNameExample", "FirstNameExample", "AddressExample", "CityExample")
        databaseHelper.close()
        Log.d("DATABASE", "PERSON WRITTEN IN DATABASE ON PHONE!!!!!!!!!!!!!!!!!!!")
        val cursor = databaseHelper.getAllContacts()
        while (cursor.moveToNext()) {
            Log.d("DATABASE", cursor.getString(1)) // v log izpisujem samo lastname vsake N-Terice :)
        }
    }

    internal interface TravelsListener {

        fun setNakupFragment()
    }
}
