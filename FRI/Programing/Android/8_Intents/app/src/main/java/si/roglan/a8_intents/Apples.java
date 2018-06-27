package si.roglan.a8_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Apples extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apples);
    } //NERABMO DODAT LISTENERJA NA ONCLICK KER SMO DODAL GUMBU ATRIBUT V XML FILU "ONCLICK"!!!!!!!

    public void onClick(View view){
        Intent i = new Intent(this, Bacon.class); //this is the activity u wanna launch

        final EditText applesInput = (EditText) findViewById(R.id.applesInput); //PASSING TEXT TO OTHER ACTIVITY
        String userMessage = applesInput.getText().toString();
        i.putExtra("appleMessage", userMessage); //switch to bacon activity and when u do.. pass along the message

        startActivity(i);
    }


}
