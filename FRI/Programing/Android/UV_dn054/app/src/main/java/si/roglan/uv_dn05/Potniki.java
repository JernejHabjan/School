package si.roglan.uv_dn05;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class Potniki extends Fragment {

    ArrayList<String> userData = new ArrayList<>();
    PotnikiListener activityCommander;
    private TableLayout tabela;
    private RelativeLayout potniki_container;


    public Potniki() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCommander = (PotnikiListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_potniki, container, false);


        potniki_container = (RelativeLayout) view.findViewById(R.id.potniki_container);
        tabela = (TableLayout) view.findViewById(R.id.table_1);

        recieveUserData(this.getArguments());

        final Button button_kupi = (Button) view.findViewById(R.id.kupi_button);
        final Button button_dodaj = (Button) view.findViewById(R.id.button_dodaj_potnika);
        button_kupi.setOnClickListener( //listener
                new View.OnClickListener() {
                    public void onClick(View v) {
                        kupi_buttonClicked();
                    }
                }
        );
        button_dodaj.setOnClickListener( //listener
                new View.OnClickListener() {
                    public void onClick(View v) {
                        dodaj_buttonClicked();
                    }
                }
        );


        return view;
    }

    private void dodaj_buttonClicked() {
        activityCommander.launch_dodaj_potnika();
    }

    private void kupi_buttonClicked() { //gets called when button is clicked
        if (userData.size() > 0) {
            activityCommander.launch_placilo();
        } else {
            Snackbar.make(potniki_container, "Vnesite vsaj enega potnika", Snackbar.LENGTH_LONG).show();
        }
    }

    public void recieveUserData(Bundle bundle) {

        if (bundle != null) {

            userData = bundle.getStringArrayList("userData");
            if (userData != null) {

                int add_index = tabela.getChildCount();
                for (int i = 0; i < userData.size(); i += 4) {

                    TableRow row = new TableRow(getContext());

                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);

                    TextView ime = new TextView(getContext());
                    TextView priimek = new TextView(getContext());
                    TextView spol = new TextView(getContext());
                    TextView datum_rojstva = new TextView(getContext());
                    ime.setText(userData.get(i));
                    priimek.setText(userData.get(i + 1));
                    spol.setText(userData.get(i + 2));
                    datum_rojstva.setText(userData.get(i + 3));

                    row.addView(ime);
                    row.addView(priimek);
                    row.addView(spol);
                    row.addView(datum_rojstva);
                    tabela.addView(row, add_index + ((i) / 4));
                }
            }
        }
    }

    interface PotnikiListener {

        void launch_dodaj_potnika();

        void launch_placilo();
    }
}
