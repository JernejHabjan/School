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
import java.util.ArrayList
import java.util.Locale


/**
 * A simple [Fragment] subclass.
 */
class VnosPotnikov : Fragment() {
    internal lateinit var activityCommander: VnosPotnikovListener
    private var ime_field: EditText? = null
    private var priimek_field: EditText? = null
    private var rad_m: RadioButton? = null
    private var rad_f: RadioButton? = null
    private var date_field: EditText? = null
    private var vnos_container: RelativeLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_vnos_potnikov, container, false)


        vnos_container = view.findViewById(R.id.vnos_container) as RelativeLayout

        ime_field = view.findViewById(R.id.vnos_ime) as EditText
        priimek_field = view.findViewById(R.id.vnos_priimek) as EditText
        rad_m = view.findViewById(R.id.rad_male) as RadioButton
        rad_f = view.findViewById(R.id.rad_female) as RadioButton
        date_field = view.findViewById(R.id.vnos_datum) as EditText

        val button = view.findViewById(R.id.button_dodaj_potnika) as Button

        button.setOnClickListener { buttonClicked() }
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
            return false
        }
        if (priimek_field!!.text.toString() == "") {
            Snackbar.make(vnos_container!!, "Vnesite priimek", Snackbar.LENGTH_LONG).show()
            return false
        }
        if (date_field!!.text.toString() == "") {
            Snackbar.make(vnos_container!!, "Vnesite rojstni datum", Snackbar.LENGTH_LONG).show()
            return false
        }
        if (!(rad_m!!.isChecked || rad_f!!.isChecked)) {
            Snackbar.make(vnos_container!!, "Vnesite spol", Snackbar.LENGTH_LONG).show()
            return false
        }

        try {
            val format = SimpleDateFormat("d.m.yyyy", Locale.ENGLISH)
            format.parse(date_field!!.text.toString())
        } catch (e: Exception) {
            Snackbar.make(vnos_container!!, "Vnesite pravilen format datum rojstva -> dan.mesec.leto", Snackbar.LENGTH_LONG).show()
            return false
        }


        return true
    }

    private fun buttonClicked() {

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
    }


    internal interface VnosPotnikovListener {
        fun sendUser(userData: ArrayList<String>)
    }

}// Required empty public constructor
