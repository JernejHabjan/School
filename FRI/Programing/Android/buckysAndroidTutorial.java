###################### 1. LOG ######################

##IZPIS LOG V LOGCAT
	//v Main Activity java:
	import android.util.Log; 
	//nekje v MainActivity class:
		private static final String TAG = "borings_Message.";
	//nekje v onCreate
		Log.i(TAG, "onCreate");
		
##GENERIRANJE SETTER|GETTER|CONSTRUCTOR|TOSTRING|OVERRIDE_METHODS...
	//alt + insert
	//--za generirat override onStart...
	
##FILTRI ZA LOG
	//desno dol v logcat -> edit filter config
	//v log_Tag dodaš string ki si ga dodav v TAG -> moj je "borings_Message"

################# 2. STRING REFERENCE ###################

##MAKE REFERENCE FOR STRING
	//klikneš red warning sign -> extract as reference

##FIND REFERENCE FOR STRING
	//control + click ko smo z miško nad reference
	
################ 3. INTERFACE WITH JAVA #############
	
	//v MainActivity.java zakomentiramo setContentView od xml datoteke ker je nebomo rabli.
	
##CODE
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
	
################ GRID LAYOUT ##################

	##PREPARING GRID
		//dodaš GridLayout na Relative grid
		//klikneš 2 gumbka k sta levo gor ->Set layoutwidth to match_parent in isto za height da je velikost 0 0
	##DODAJANJE
		//dodaš gumb in se pojavi na koordinati row, column -> 0,0
		//potegneš gumb na Component tree -> modr stuff
	##LAYOUT COLUMN SPAN, LAYOUT ROW SPAN, LAYOUT GRAVITY
		//morš extendat propertyje

############## EVENT HANDLING ################ 3. APP

	##ADDING BUTTON AND TEXT
		//dodaš gumb in text in jima spremeniš ID
	##CODE
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

			private TextView myTextSwipe; //narejen še ta textSwipe v xml ... to se prav -> 1 button in 2 textViewa
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

############### FRAGMENTS ################# 5. APP

	//PAZI TEŽAK TUTORIAL
	
	//fragment is an activity that is reusable, can have multiple fragments on one activity
	
	##ADDING PICTURE
		//spremenimo na PROJECT view
		//gremo pod -> app -> src-> main -> res  in v drawable damo sliko
	##ADDING LAYOUT
		// right click on layout mapo -> new layout resource file -> napišeš ime in določiš RelativeLayout
	##CODE V TEMU TOP_SELECTION_FRAGMENT
		//v filu
	##CODING JAVA BRAINS FOR FRAGMENT
		//v filu
	##ADDING PICTURE CONTAINER
		//dodaš v layout isto en xml file z relativeLayoutom
		//klik na relative layout in klikneš pikce za background -> posearchaš sliko - drawable-> cmarjan.jpg
		
		//dodaš še besedila zgor pa spodni na sliko
	##ADDING BRAINS FOR PICTURE CONTAINER
		//create java class BottomPictureFragment
	##CODE FOR JAVA FILE FOR PICTURE CONTAINER/FRAGMENT
		//V FILU
	##ADDING FRAGMENT ON MAIN ACTIVITY
		//greš pod Layouts tm k je paleta gumbov in vsega
		//drag and dropaš <fragment> in jih dodaš na activity_main
	########LISTENING FRAGMENT FOR BUTTON CLICKS
		//rules:
			//fragments do not talk to one another
	##LISTENERS
		//PAZI POGLEJ KODO V PROJECT: 5_Fragments
		

		
################MASTER DETAIL FLOW ############### -----SPUSTU KER NI DELAL
		


################ OVERFLOW MENU ################### 6 APP
	##DOLOČIŠ SAMO ENO STVAR
		 <group android:checkableBehavior="single"> !!!!PAZI SINGLE




############### ANIMATIONS AND TRANSITIONS ##########  7 APP




