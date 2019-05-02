package com.traffic_api;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class search_by_station extends Fragment {


    public search_by_station() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_search_by_station, container, false);
        ImageButton searchbus = rootview.findViewById(R.id.search_station);

        final TextView text = rootview.findViewById(R.id.text);
        final TextView search_key = rootview.findViewById(R.id.search_key);


        searchbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* activity.onFragmentChange(0);*/
        /*        Intent intent = new Intent(getActivity(), search_by_bus_content.class);
                startActivity(intent);*/
            }
        });
        return rootview;
    }

}
