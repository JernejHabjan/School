package si.roglan.a4_grid_layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//IMPORTS FOR GESTURES
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener { //implemented !!!

    private TextView myTextSwipe;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREATE EVENTLISTENER AS WE CREATE

        Button myButton = (Button) findViewById(R.id.myButton); //NAMED SAME AS THAT ONE IN ACTIVITY_MAIN

        myButton.setOnClickListener( //EVENT LISTENER
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView myText = (TextView) findViewById(R.id.myText);
                        myText.setText("onClick");
                    }
                }
        );

        myButton.setOnLongClickListener(//holding it down
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        TextView myText = (TextView) findViewById(R.id.myText);
                        myText.setText("onLongPress");
                        return true; // POL IZVEDE SAM TA EVENT -> "the event was handled"
                        //return false; //IZVEDE TA EVENT IN ŠE ON CLICK!!!!!!!
                    }
                }
        );

        myTextSwipe = (TextView) findViewById(R.id.myTextSwipe);
        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    //IMPLEMENTED METHODS WITH ALT+INS -> implement methods

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        myTextSwipe.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        myTextSwipe.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        myTextSwipe.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        myTextSwipe.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        myTextSwipe.setText("onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        myTextSwipe.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        myTextSwipe.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        myTextSwipe.setText("onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        myTextSwipe.setText("onFling");
        return true;
    }

    //HANDLE IF SPECIAL TYPE OF GESTURE:
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect if this touch was gesture first
        this.gestureDetector.onTouchEvent(event); //detect if it was special type of gesture, if not continue normally
        return super.onTouchEvent(event);
    }
}
