package si.roglan.a7_tranny;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ViewGroup activity_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (ViewGroup) findViewById(R.id.activity_main);

        activity_main.setOnTouchListener(
                 new RelativeLayout.OnTouchListener(){
                     @Override
                     public boolean onTouch(View v, MotionEvent event) {
                         moveButton();
                         return true;
                     }
                 }
        );
    }

    public void moveButton(){
       View button = findViewById(R.id.button);

        //change the position of the button
        RelativeLayout.LayoutParams positionRules =
                new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE); //dodaj button dol
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); //dodaj button desno
        button.setLayoutParams(positionRules); //use these rules

        //change the size of the button
        ViewGroup.LayoutParams sizeRules = button.getLayoutParams(); //returning info o buttnu
        sizeRules.width = 450;
        sizeRules.height = 300;
        button.setLayoutParams(sizeRules);


    }
}
