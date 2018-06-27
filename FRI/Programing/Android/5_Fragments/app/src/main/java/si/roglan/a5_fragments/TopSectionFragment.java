package si.roglan.a5_fragments;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class TopSectionFragment extends Fragment{ //zmeri ga extendaš

    private EditText topTextInput;
    private EditText bottomTextInput;

    TopSectionListener activityCommander;

    public interface TopSectionListener{
        public void createMeme(String top, String bottom);
    }

    @Override
    public void onAttach(Context context) {
        try {
            activityCommander = (TopSectionListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //overridat morš onCreate view
        View view = inflater.inflate(R.layout.top_selection_fragment, container, false); //un xml cev

        topTextInput = (EditText) view.findViewById(R.id.topTextInput);
        bottomTextInput = (EditText) view.findViewById(R.id.bottomTextInput);
        final Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener( //listener
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );
        return view;
    }

    public void buttonClicked(View v){ //gets called when button is clicked
        activityCommander.createMeme(topTextInput.getText().toString(), bottomTextInput.getText().toString()); //passamo inputa
    }

}
