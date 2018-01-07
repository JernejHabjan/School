package si.roglan.EMP_Seminarska


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RelativeLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

import android.widget.DatePicker
import android.app.DatePickerDialog




/**
 * A simple [Fragment] subclass.
 */
class VnosPotnikov : Fragment() {
    private lateinit var activityCommander: VnosPotnikovListener
    private var ime_field: EditText? = null
    private var priimek_field: EditText? = null
    private var rad_m: RadioButton? = null
    private var rad_f: RadioButton? = null
    private var date_field: EditText? = null
    private var vnos_container: RelativeLayout? = null
    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_vnos_potnikov, container, false)


        vnos_container = view.findViewById(R.id.vnos_container) as RelativeLayout

        ime_field = view.findViewById(R.id.vnos_ime) as EditText
        priimek_field = view.findViewById(R.id.vnos_priimek) as EditText
        rad_m = view.findViewById(R.id.rad_male) as RadioButton
        rad_f = view.findViewById(R.id.rad_female) as RadioButton
        date_field = view.findViewById(R.id.vnos_datum) as EditText

        //set default date
        date_field!!.setText("1.1.1970");
        date_field!!.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v : View?){
                    val dateArray = date_field!!.text.toString().split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val day = Integer.parseInt(dateArray[0])
                    val month = Integer.parseInt(dateArray[1])
                    val year = Integer.parseInt(dateArray[2])

                    val dpd = DatePickerDialog(context,
                            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                                date_field!!.setText(
                                        String.format("%02d", dayOfMonth) + "." +
                                                String.format("%02d", monthOfYear + 1) + "." +
                                                String.format("%02d", year)
                                )
                            }, year, month - 1, day)

                    dpd.getDatePicker().setMaxDate(Calendar.getInstance().getTime().getTime());
                    dpd.show()
                }
            }
        )


        val button = view.findViewById(R.id.button_dodaj_potnika) as Button
        button.setOnClickListener { addPassenger() }

        return view
    }

    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as VnosPotnikovListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString())
        }

        super.onAttach(context)
    }

    private fun checkFields(): Boolean {

        if (ime_field!!.text.toString() == "") {
            Snackbar.make(vnos_container!!, "Vnesite ime", Snackbar.LENGTH_LONG).show()
            ime_field!!.requestFocus();
            return false
        }
        if (priimek_field!!.text.toString() == "") {
            Snackbar.make(vnos_container!!, "Vnesite priimek", Snackbar.LENGTH_LONG).show()
            priimek_field!!.requestFocus();
            return false
        }
        if (date_field!!.text.toString() == "") {
            Snackbar.make(vnos_container!!, "Vnesite rojstni datum", Snackbar.LENGTH_LONG).show()
            date_field!!.requestFocus();
            return false
        }
        if (!(rad_m!!.isChecked || rad_f!!.isChecked)) {
            Snackbar.make(vnos_container!!, "Vnesite spol", Snackbar.LENGTH_LONG).show()
            return false
        }

        try {
            val format = SimpleDateFormat("d.M.yyyy", Locale.ENGLISH)
            format.parse(date_field!!.text.toString())
        } catch (e: Exception) {
            Snackbar.make(vnos_container!!, "Vnesite pravilen format datum rojstva -> dan.mesec.leto", Snackbar.LENGTH_LONG).show()
            return false
        }

        return true
    }


    private fun addPassenger() {

        if (!checkFields())
            return


        val userData = ArrayList<String>()
        userData.add(ime_field!!.text.toString())
        userData.add(priimek_field!!.text.toString())
        userData.add(if (rad_m!!.isChecked) "Moški" else "Ženska")
        userData.add(date_field!!.text.toString())


        Snackbar.make(vnos_container!!, "Uporabnik vnešen", Snackbar.LENGTH_LONG).show()
        activityCommander.sendUser(userData) //passamo input

        println("Sent user data")


        // write to database

        /*
        TODO ---------- THIS CANNOT BE WRITTEN THIS WAY BUT PASSENGERS CAN BE WRITTEN IN DATABASE WITH

        val service = "/ServicePersonData.svc"
        val operationContract = "/WritePassenger"


        val params = JSONObject()
        params.put("name", ime_field!!.text.toString())
        params.put("surname", priimek_field!!.text.toString())
        params.put("gender", if (rad_m!!.isChecked) "Moški" else "Ženska")
        params.put("age", date_field!!.text.toString())

        VolleyHelper().writeRequest(params, service, operationContract)
        */
    }


    internal interface VnosPotnikovListener {
        fun sendUser(userData: ArrayList<String>)
    }

}// Required empty public constructor
