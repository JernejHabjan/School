package si.roglan.EMP_Seminarska;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
public class TravelsFragment extends Fragment {
    FloatingActionButton add_trip_float_button;

    public TravelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.our_travels, container, false);


        add_trip_float_button = (FloatingActionButton) view.findViewById(R.id.add_trip_float_button);

        add_trip_float_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // CALL FRAGMENT
            }
        });


        // Inflate the layout for this fragment
        return view;




    }


}
