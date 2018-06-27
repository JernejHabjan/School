package si.emp.primes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements Primes.OnProgressListener {
    private static final String filename = "primes.txt";
    private static final Integer N = 200000;

    private ProgressBar progressBar;
    private Button btnGenerate;
    private Button btnStop;
    private Primes primes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        btnGenerate = (Button)findViewById(R.id.buttonGenerate);
        btnStop = (Button)findViewById(R.id.buttonStop);

        progressBar.setMax(100);

        btnGenerate.setEnabled(true);
        btnStop.setEnabled(false);

        primes = null;

        btnGenerate.setOnClickListener(new Button.OnClickListener() {
               @Override
               public void onClick(View view) {
                    primes = new Primes();
                    primes.setOnProgressListener(MainActivity.this);
                    primes.execute(getApplicationContext(), filename, N);
               }
           }
        );

        btnStop.setOnClickListener(new Button.OnClickListener() {
               @Override
               public void onClick(View view) {
                    if (primes != null)
                        primes.stop();
               }
           }
        );
    }

    @Override
    public void onStarted() {
        btnGenerate.setEnabled(false);
        btnStop.setEnabled(true);
        progressBar.setProgress(0);
    }

    @Override
    public void onProgress(double progress) {
        progressBar.setProgress((int)progress);
    }

    @Override
    public void onStopped(boolean finished) {
        btnGenerate.setEnabled(true);
        btnStop.setEnabled(false);

        Toast.makeText(getApplicationContext(),
                (finished ? "Process finished" : "Process stopped"),
                Toast.LENGTH_SHORT).show();

        primes = null;
    }
}
