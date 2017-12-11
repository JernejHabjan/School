package si.roglan.EMP_Seminarska


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {
    private lateinit var rGroup_teza: RadioGroup
    private lateinit var rGroup_visina: RadioGroup


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_settings, container, false)
        rGroup_teza = view.findViewById(R.id.rGroup_teza) as RadioGroup
        rGroup_visina = view.findViewById(R.id.rGroup_teza) as RadioGroup

        rGroup_teza.setOnCheckedChangeListener { _, _ -> Snackbar.make(relative_settings!!, "Izbira teže še ni podprta", Snackbar.LENGTH_LONG).show() }

        rGroup_visina.setOnCheckedChangeListener { _, _ -> Snackbar.make(relative_settings!!, "Izbira velikosti še ni podprta", Snackbar.LENGTH_LONG).show() }


        // Inflate the layout for this fragment
        return view
    }


}// Required empty public constructor
