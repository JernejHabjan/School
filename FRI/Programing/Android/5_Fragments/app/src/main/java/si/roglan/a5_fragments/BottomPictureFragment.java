package si.roglan.a5_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class BottomPictureFragment extends Fragment{ //zmeri ga extendaš

    private TextView top_TextView;
    private TextView bottom_TextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) { //overridat morš onCreate view
        View view = inflater.inflate(R.layout.bottom_picture_fragment, container, false); //un xml cev
        top_TextView = (TextView) view.findViewById(R.id.top_TextView);
       bottom_TextView = (TextView) view.findViewById(R.id.bottom_TextView);


        return view;
    }

    public void setMemeText(String top, String bottom){
        top_TextView.setText(top);
        bottom_TextView.setText(bottom);
    }
}
