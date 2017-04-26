package id.sch.smktelkom_mlg.letsadventure.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.sch.smktelkom_mlg.letsadventure.ListActivity;
import id.sch.smktelkom_mlg.letsadventure.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private Button btList;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btList = (Button) getView().findViewById(R.id.buttonList);
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
