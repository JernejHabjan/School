package si.roglan.a9_recieve_broadcast_intents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


//TO JE BIV ČIST EMPTY PROJECT -> NO ACTIVITY!!


// KO ZALAUFAŠ: RUN MENU -> EDIT CONFIGURATIONS -> LAUNCH NOTHING


public class RecieveBroadcast extends BroadcastReceiver { //RIGHT CLICK -> OTHER ->BROADCAST_RECIEVER!!!!!!!!
    public RecieveBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Broadcast has been recieved LEL", Toast.LENGTH_LONG).show();
    }
}
