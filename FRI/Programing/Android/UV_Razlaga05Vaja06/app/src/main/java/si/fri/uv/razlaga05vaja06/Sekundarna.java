package si.fri.uv.razlaga05vaja06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class Sekundarna extends AppCompatActivity {
    public static final String EXTRA_R = "si.fri.uv.razlaga05vaja06.znesek";
    int indeks;
    double tecaj = 1.289;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sekundarna);
        Intent intent = getIntent();
        indeks = intent.getIntExtra(GlavnaAktivnost.EXTRA, 0);
        if (indeks == 0) ((TextView)findViewById(R.id.textView5)).setText("EUR=>USD");
        else ((TextView)findViewById(R.id.textView5)).setText("USD=>EUR");
        List<Integer> spV = new ArrayList<>();
        for (int i=0;i<100;i++) spV.add(i);
        ArrayAdapter<Integer> adp = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spV);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spn = (Spinner)findViewById(R.id.spinner2);
        spn.setAdapter(adp);
    }
    public void preklici(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void pretvori(View view){
        double vrednost = ((Spinner)findViewById(R.id.spinner2)).getSelectedItemPosition();
        if (indeks == 0) vrednost*=tecaj;
        else vrednost /=tecaj;
        if (((CheckBox)findViewById(R.id.checkBox)).isChecked()) vrednost*=0.98;

        Intent intent = new Intent();
        intent.putExtra(EXTRA_R, vrednost);
        setResult(RESULT_OK, intent);
        finish();
    }
}
