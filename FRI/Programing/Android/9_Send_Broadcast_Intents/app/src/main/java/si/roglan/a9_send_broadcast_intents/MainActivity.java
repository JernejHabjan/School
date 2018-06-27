package si.roglan.a9_send_broadcast_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void sendOutBroadcast(View view){ // TA METODA JE POIMENOVANA V ACTIVITY_MAIN.XML POD ONCLICK NA BUTTNU
        Intent i = new Intent(); //ne pokliče drug screen ampak broadcasta
        i.setAction("si.roglan.a9_send_broadcast_intents"); //PACKAGE NAME is keyword for this broadcast
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES); //that its compatabile with all versions of android
        sendBroadcast(i);
    }

}
