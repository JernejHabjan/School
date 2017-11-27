package si.roglan.EMP_Seminarska;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    RadioGroup radioGroup_teza;
    RadioGroup radioGroup_velikost;
    private RelativeLayout relative_settings;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        relative_settings = (RelativeLayout) view.findViewById(R.id.relative_settings);


        radioGroup_teza = (RadioGroup) view.findViewById(R.id.rGroup_teza);
        radioGroup_velikost = (RadioGroup) view.findViewById(R.id.rGroup_visina);

        radioGroup_teza.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Snackbar.make(relative_settings, "Izbira teže še ni podprta", Snackbar.LENGTH_LONG).show();
            }
        });

        radioGroup_velikost.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Snackbar.make(relative_settings, "Izbira velikosti še ni podprta", Snackbar.LENGTH_LONG).show();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


}
