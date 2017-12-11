package si.roglan.EMP_Seminarska

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.our_travels.*

class TravelsFragment : Fragment() {
    internal lateinit var add_trip_float_button: FloatingActionButton


    private val DATABASE_NAME = "SlivkoDatabase"
    private val DATABASE_VERSION = 1


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.our_travels, container, false)


        add_trip_float_button = view.findViewById(R.id.add_trip_float_button) as FloatingActionButton

        add_trip_float_button.setOnClickListener {
            // CALL FRAGMENT
            Log.d("Fragment", "Call fragment here")
        }


        val databaseHelper = DatabaseHelper(context, DATABASE_NAME, context, DATABASE_VERSION)

        databaseHelper.insertContact("LastNameExample", "FirstNameExample", "AddressExample", "CityExample")

        databaseHelper.close()

        Log.d("DATABASE", "PERSON WRITTEN IN DATABASE ON PHONE!!!!!!!!!!!!!!!!!!!")
        val cursor = databaseHelper.getAllContacts()
        while (cursor.moveToNext()) {
            Log.d("DATABASE", cursor.getString(1)) // v log izpisujem samo lastname vsake N-Terice :)
        }

        // Inflate the layout for this fragment
        return view


    }


}// Required empty public constructor
