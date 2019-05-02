package com.traffic_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListviewAdapter extends BaseAdapter {

    private ArrayList<BusLIstViewItem> data = new ArrayList<>();

    public ListviewAdapter(){}
    String firstName = "firstValue";
    String firstId = "firstValue";

    @Override
    public int getCount() {
        return data.size() ;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.activity_bus_list_view_item, parent, false);
        }

        TextView stationName = convertView.findViewById(R.id. bus_route_nm);
        TextView startstation = convertView.findViewById(R.id.st_station_nm);
        TextView endstation = convertView.findViewById(R.id.ed_station_nm);
        //TextView info = convertView.findViewById(R.id.info);

        BusLIstViewItem listViewitem = data.get(position);

        stationName.setText(listViewitem.getName());

//        info.setText(listViewitem.getInfo());


        return convertView;
    }
    public void addItem(HashMap getData) {
        BusLIstViewItem item = new BusLIstViewItem();


//        ListViewitem item = new ListViewitem();
//        item.setInfo(info);
        if(getData.get("station") != null && getData.get("id") != null) {
            if(!getData.get("station").equals(firstName) || !getData.get("id").equals(firstId)) {

                firstName = (String) getData.get("station");
                firstId = (String) getData.get("id");

                item.setStationName((String) getData.get("station"));
                item.setStationId((String) getData.get("id"));
                data.add(item);
            }

        }
    }
}