package com.traffic_api;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class search_by_bus extends Fragment {


    public search_by_bus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageButton search_bus = (ImageButton) getView().findViewById(R.id.search_bus);

        search_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return inflater.inflate(R.layout.fragment_search_by_bus, container, false);

    }

}
