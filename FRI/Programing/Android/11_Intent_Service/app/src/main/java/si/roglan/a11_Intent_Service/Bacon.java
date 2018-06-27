package si.roglan.a8_intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Bacon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //TLE DODAMO DA SE SPREMENI TEKST IZ APPLE BOXA

        Bundle applesData = getIntent().getExtras(); //getting extra info after launching from somewhere
        if(applesData == null) {
            return;
        }
        String applemessage = applesData.getString("appleMessage"); //keyword for the key
        final TextView baconText = (TextView) findViewById(R.id.baconText);
        baconText.setText(applemessage);
    }

    public void onClick(View view){
        Intent i = new Intent(this, Apples.class); //this is the activity u wanna launch
        startActivity(i);
    }

}
