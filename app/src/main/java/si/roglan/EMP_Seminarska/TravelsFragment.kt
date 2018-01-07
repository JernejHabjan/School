package si.roglan.EMP_Seminarska

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.telephony.cdma.CdmaCellLocation
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.fragment_placilo.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class TravelsFragment : Fragment() {
    private lateinit var add_trip_float_button: FloatingActionButton
    var travels_content_local: ConstraintLayout? = null
    var mTravelsData: ArrayList<TravelData> = ArrayList<TravelData>();

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

        VolleyHelper().getTravels(this, m_GID);

        //updateTravelsList(view);

        return view
    }


    public fun updatePassengerData(passengerData: ArrayList<String>){

    }

    public fun updateTravelsList(view: View?){
        if(view == null)return

        val table = view!!.findViewById(R.id.travels_table) as TableLayout;

        //Remove all children except column names
        for (j in 1 until table!!.childCount)
            table!!.removeView(table!!.getChildAt(1))

        for (i in 0 until mTravelsData.size)
        {
            val travelData = mTravelsData.get(i)

            val row = TableRow(context)
            row.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT)

            val id = TextView(context)
            id.text = travelData.mOrderId.toString()


            val fromLocation = TextView(context)
            fromLocation.text = travelData.mFromLocation
            val toLocation = TextView(context)
            toLocation.text = travelData.mToLocation

            //Limit location string lengths
            val locationMaxLength = 10;
            if (fromLocation.text.length > locationMaxLength + 3) {
                fromLocation.text = fromLocation.text.substring(0, locationMaxLength) + "...";
            }
            if (toLocation.text.length > locationMaxLength + 3) {
                toLocation.text = toLocation.text.substring(0, locationMaxLength) + "...";
            }

            val locationsLayout = LinearLayout(context);
            locationsLayout.orientation = LinearLayout.VERTICAL;
            locationsLayout.addView(fromLocation)
            locationsLayout.addView(toLocation)


            val date = TextView(context)
            date.text = travelData.mDate
            val returnDate = TextView(context)
            returnDate.text = travelData.mReturnDate

            val datesLayout = LinearLayout(context);
            datesLayout.orientation = LinearLayout.VERTICAL;
            datesLayout.addView(date)
            datesLayout.addView(returnDate)


            val odpriButton = Button(context);
            odpriButton.text = "ODPRI"
            odpriButton.tag = id.text
            odpriButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val id = Integer.parseInt(v!!.tag.toString())
                    Log.i("ORDER_ID: ", id.toString());

                    for (j in 0 until mTravelsData.size){
                        val tData = mTravelsData.get(j)
                        if(tData.mOrderId == id){ //Find correct id
                            activityCommander.setNakupFragment(id,
                                tData.mFromLocation,
                                tData.mToLocation,
                                tData.mDate,
                                tData.mReturnDate,
                                tData.mClass,
                                tData.mReturnClass,
                                tData.mPrice
                            )

                            break
                        }
                    }
                }
            })


            val closeButton = Button(context)
            closeButton.text = "X"
            closeButton.tag = id.text
            closeButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val id = Integer.parseInt(v!!.tag.toString())
                    Log.i("ORDER_ID: ", id.toString());

                    for (j in 0 until mTravelsData.size){
                        if(mTravelsData.get(j).mOrderId == id){ //Find correct id
                            table!!.removeView(table!!.getChildAt(1 + j))
                            mTravelsData.removeAt(j) //Remove array entry
                            
                            break
                        }
                    }

                    VolleyHelper().removeOrder(view, activity, id)
                    //updateTravelsList(view)
                }
            })

            row.addView(id)
            row.addView(locationsLayout)
            row.addView(datesLayout)
            row.addView(odpriButton)
            row.addView(closeButton)
            table!!.addView(row, 1 + i)


            val scale = resources.displayMetrics.density
            val heightDp = (45 * scale + 0.5f).toInt()

            val openButtonParams = odpriButton.getLayoutParams()
            openButtonParams.height = heightDp
            openButtonParams.width = (heightDp * 1.55).toInt()
            odpriButton.setLayoutParams(openButtonParams)


            val closeButtonParams = closeButton.getLayoutParams()
            closeButtonParams.height = heightDp
            closeButtonParams.width = heightDp
            closeButton.setLayoutParams(closeButtonParams)

            odpriButton.requestLayout()
            closeButton.requestLayout()
            table!!.requestLayout()
        }

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

    internal interface TravelsListener {
        fun updatePassengerData(passengerData: ArrayList<String>)
        fun setNakupFragment()
        fun setNakupFragment(orderID: Int, fromLocation: String, toLocation: String, date: String, returnDate: String,
                             travelClass: String, returnClass: String, price: Float)
    }
}
