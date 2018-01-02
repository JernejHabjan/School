package si.roglan.EMP_Seminarska

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_placilo.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Placilo : Fragment() {
    private var nakupData: ArrayList<String>? = ArrayList()
    private var userData: ArrayList<String>? = ArrayList()

    private lateinit var kartica_input: EditText
    private lateinit var priimek_placnika: EditText
    private lateinit var ime_placnika: EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_placilo, container, false)
        kartica_input = view.findViewById(R.id.kartica_input) as EditText
        priimek_placnika = view.findViewById(R.id.priimek_placnika) as EditText
        ime_placnika = view.findViewById(R.id.ime_placnika) as EditText

        kartica_input.visibility = View.GONE
        priimek_placnika.visibility = View.GONE
        ime_placnika.visibility = View.GONE


        val credit_card_switch = view.findViewById(R.id.kreditna_kartica_switch) as Switch;
        credit_card_switch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                kartica_input.visibility = View.VISIBLE
                priimek_placnika.visibility = View.VISIBLE
                ime_placnika.visibility = View.VISIBLE
            } else {
                kartica_input.visibility = View.GONE
                priimek_placnika.visibility = View.GONE
                ime_placnika.visibility = View.GONE
            }
        }

        val finish_payment = view.findViewById(R.id.zakljuci_placilo) as Button;
        finish_payment.setOnClickListener { v -> buttonClickedInfo(v) }

        recieveUser_NakupData(view, this.arguments)

        return view
    }

    private fun calculatePrice() {
        val format = SimpleDateFormat("d.m.yyyy", Locale.ENGLISH)
        var karta_price = 0;
        var i = 0
        while (i < userData!!.size) {
            val name = userData!![i]
            val surname = userData!![i + 1]
            val spol = userData!![i + 2]
            val age = userData!![i + 3]
            var age_date = Date()
            try {
                age_date = format.parse(age)
            } catch (e: ParseException) {
                Snackbar.make(placilo_relative!!, "Could not parse rojstni dan za osebo: " + name, Snackbar.LENGTH_LONG).show()
            }

            val today = Date()
            today.hours = 0

            val days = Math.abs((today.time - age_date.time) / (1000 * 60 * 60 * 24))

            var factor: Float
            factor = when {
                days < 2190 -> //6 let
                    0.0f
                days < 4380 -> //12 let
                    //polovicna cena
                    0.5f
                else -> //normal price
                    1.0f
            }


            val destinacija = nakupData!![0]
            val datum_odhoda = nakupData!![1]
            val st_oseb = nakupData!![2]
            val razred_odhoda = nakupData!![3]
            var datum_odhoda_date = Date()
            try {
                datum_odhoda_date = format.parse(datum_odhoda)
            } catch (e: ParseException) {
                Snackbar.make(placilo_relative!!, "Could not parse datum prihoda date", Snackbar.LENGTH_LONG).show()
            }

            when (razred_odhoda) {
                "Prvi" -> factor *= 2.0f
                "Poslovni" -> factor *= 1.5f
                "Ekonomski" -> factor *= 1.2f
            }


           //Generate random discount
            val discountInt = Random().nextInt()
            val discount = ((discountInt % 2) * 10.0f + (discountInt % 3) * 15.0f) / 100.0f;

            //Generate random price
            val maxPrice = 400;
            var randomPrice = (Random().nextInt() % maxPrice).toFloat();
            randomPrice -= randomPrice * discount;

            karta_price += (randomPrice * factor).toInt()


            //Has return flight
            if (nakupData!!.size > 4) {
                val datum_prihoda = nakupData!![4]
                val razred_prihoda = nakupData!![5]
                var datum_prihoda_date = Date()
                try {
                    datum_prihoda_date = format.parse(datum_prihoda)

                } catch (e: ParseException) {
                    Snackbar.make(placilo_relative!!, "Could not parse datum prihoda date", Snackbar.LENGTH_LONG).show()
                }


                when (razred_prihoda) {
                    "Prvi" -> factor *= 2.0f
                    "Poslovni" -> factor *= 1.5f
                    "Ekonomski" -> factor *= 1.2f
                }

                karta_price += (randomPrice * factor).toInt()
            }
            i += 4
        }
        cena_display!!.text = Integer.toString(karta_price)
    }

    private fun recieveUser_NakupData(view: View, bundle: Bundle?) {
        if (bundle != null) {
            nakupData = bundle.getStringArrayList("nakupData")
            userData = bundle.getStringArrayList("userData")
            println("RECIEVE USER NAKUP DATA")
            println(nakupData!!.size)
            println(userData!!.size)

            if (nakupData != null && nakupData!!.size > 0 && userData != null && userData!!.size > 0) {

                //String nakupData.get(0);

                run {
                    var i = 0
                    while (i < userData!!.size) {
                        println("GOT USER")
                        i += 4

                    }
                }
                for (i in nakupData!!.indices) {
                    println(nakupData!![i])

                }

                if (nakupData!!.size > 4) {
                    dvosnerna_display!!.text = "Da"

                }

                val numUsers = userData!!.size / 4
                println("NUM USERS:" + numUsers)

                val numPassengers = view.findViewById(R.id.st_potnikov_label) as TextView;
                numPassengers!!.text = Integer.toString(numUsers)

                calculatePrice()
            }
        }
    }

    private fun buttonClickedInfo(view: View) { //gets called when button is clicked
        if (kreditna_kartica_switch!!.isChecked && kartica_input!!.text.toString() == "") {
            Snackbar.make(placilo_relative!!, "Vnesite kreditno kartico", Snackbar.LENGTH_LONG).show()
        } else {

            AlertDialog.Builder(context)
                    .setTitle("Plačilo")
                    .setMessage("Ali res želite plačati?")
                    .setPositiveButton(android.R.string.yes) { dialog, which -> Snackbar.make(placilo_relative!!, "Nakup ste uspešno opravili", Snackbar.LENGTH_LONG).show() }
                    .setNegativeButton(android.R.string.no) { dialog, which -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()

        }
    }
}
