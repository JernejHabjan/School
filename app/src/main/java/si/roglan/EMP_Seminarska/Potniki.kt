package si.roglan.EMP_Seminarska

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*


class Potniki : Fragment() {
    private var button: Button? = null
    private var textView: TextView? = null
    private var server_url = "http://192.168.0.107/greetings.php"

    internal var userData: ArrayList<String>? = ArrayList()
    internal lateinit var activityCommander: PotnikiListener
    private var tabela: TableLayout? = null
    private var potniki_container: RelativeLayout? = null

    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as PotnikiListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString())
        }

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_potniki, container, false)


        potniki_container = view.findViewById(R.id.potniki_container) as RelativeLayout
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

        button!!.setOnClickListener {
            val requestQueue = Volley.newRequestQueue(activity)
            val stringRequest = StringRequest(Request.Method.POST, server_url,
                    Response.Listener { response ->
                        textView!!.text = response
                        Snackbar.make(potniki_container!!, "BLA", Snackbar.LENGTH_LONG).show()
                        requestQueue.stop()
                    }, Response.ErrorListener { error ->
                //textView.setText("something went wrong with volley");
                error.printStackTrace()

                requestQueue.stop()
            })
            requestQueue.add(stringRequest)
        }
        // TODO - https://www.youtube.com/watch?v=9GeW3UoEnDw&list=PLshdtb5UWjSraOqG1iZW-8mDkJXe3LSL0&index=6

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
