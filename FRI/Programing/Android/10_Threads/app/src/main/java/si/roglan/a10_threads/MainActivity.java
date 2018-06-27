package si.roglan.a10_threads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){ //sits on main thread and updates interface
        @Override
        public void handleMessage(Message msg) { //alt insert
            TextView buckysText = (TextView) findViewById(R.id.buckysText);
            buckysText.setText("lol");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickBuckysButton(View view){ //NA BUTTNU V XML ONCLICK

        Runnable r = new Runnable() {
            @Override
            public void run() { //KODO K SE IZVAJA "V OZADJU" DAMO SM NOT
                long futureTime = System.currentTimeMillis() + 10000; //simulatiing backkground process
                while(System.currentTimeMillis() < futureTime) {
                    synchronized (this) { //prevents multiple threads from bumping into each other
                        try{
                            wait(futureTime-System.currentTimeMillis());
                        } catch(Exception ignored) {}

                    }
                }
                handler.sendEmptyMessage(0); //it calls the handler
            }
        };
        //U NEVER WANNA CHANGE ANY INTERFACE ELEMENT WITHIN A THREAD
        //TO UPDATE AN INTERFACE U MUST HANDLE THEM IN A HANDLER!!!!!

        Thread buckysThread = new Thread(r);
        buckysThread.start();

        //app bo še vedno trajal 10 sec da spremeni tekst ampak gumb je šekr responsive in ne freeza cev app
    }


}
