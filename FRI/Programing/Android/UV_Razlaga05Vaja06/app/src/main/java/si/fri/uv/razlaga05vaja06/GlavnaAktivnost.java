package si.fri.uv.razlaga05vaja06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class GlavnaAktivnost extends AppCompatActivity {
    public static final String EXTRA = "si.fri.uv.razlaga05vaja06.pretvorba";
    private final int REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavna_aktivnost);
    }

    public void pretvori(View view){
        Intent intent = new Intent(this, Sekundarna.class);

        int indeks = ((Spinner)findViewById(R.id.spinner)).getSelectedItemPosition();
        intent.putExtra(EXTRA, indeks);
        startActivityForResult(intent, REQUEST);
    }
    @Override
    protected void onActivityResult(int reqC, int resC, Intent intent){
        if (reqC == REQUEST){
            if (resC == RESULT_OK){
                double indeks = intent.getDoubleExtra(Sekundarna.EXTRA_R, 0.0);
                ((TextView)findViewById(R.id.textView3)).setText("Znesek: "+indeks);
            }
            else
                ((TextView)findViewById(R.id.textView3)).setText("Znesek: ");
        }
    }
}
