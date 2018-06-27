package si.roglan.a3_interface_with_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;

import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //LAYOUT
        RelativeLayout rl = new RelativeLayout(this);
        rl.setBackgroundColor(Color.GREEN);

        //BUTTON
        Button b1 = new Button(this);
        b1.setText("hey boss");
        b1.setTextColor(Color.GREEN);
        b1.setBackgroundColor(Color.YELLOW);

        //INPUT
        EditText username = new EditText(this);


        //ID'S
        b1.setId(1);
        username.setId(2);


        //BUTTON LAYOUT
        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams( //FOR PLACING BUTTON IN MIDDLE
                RelativeLayout.LayoutParams.WRAP_CONTENT, //WRAP CONTENT DOBI WIDTH IN HEIGHT
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //USERNAME LAYOUT
        RelativeLayout.LayoutParams usernameDetails = new RelativeLayout.LayoutParams( //FOR PLACING BUTTON IN MIDDLE
                RelativeLayout.LayoutParams.WRAP_CONTENT, //WRAP CONTENT DOBI WIDTH IN HEIGHT
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        //RULES FOR BUTTON POSITION
        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        //RULES FOR USERNAME POSITION
        usernameDetails.addRule(RelativeLayout.ABOVE, 1); //puts it above button who has ID 1!!!
        usernameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        usernameDetails.setMargins(0, 0, 0, 50);


        //CONVERT DIP TO PIXELS
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, //kaj hočmo convertat,
                200,                                                          //kok jih hočmo convertat,
                r.getDisplayMetrics()                                         //info o screenu
        );

        //SET USERNAME WIDTH
        username.setWidth(px);

        //VIEW - ADDING ON LAYOUT
        rl.addView(b1, buttonDetails); //dodan button details ki pove kako naj doda button na layout
        rl.addView(username,usernameDetails);

        setContentView(rl);


    }
}
