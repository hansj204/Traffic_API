package com.traffic_api;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AutoText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;


public class search_by_bus extends Fragment {

    private String openAPIKey = "w1LpendNML9NSRvEVJVRbZTbwm0ZK8bwkZiIoXsOwU0QZzhoQZmSDrRgr%2FEeqvNRWV%2F4NGpifpwT8LM1Hvu0dg%3D%3D";
    private ImageView mainBg;
    private ListView listView;
    HashMap<String, String> result = new HashMap<>();
    private BusListViewAdapter adapter;
    ArrayList<BusLIstViewItem> data = new ArrayList<>();
    ArrayList<String> dataForBus = new ArrayList<>();
    ArrayList<Integer> dataForBusNUM = new ArrayList<>();
    private String tagName = "";
    String buses[] = {"N13", "N15", "N16", "N26", "N30", "N37", "N61", "N62", "N65", "N6001", "N6002"};

    int busnumbers[] = {100100589, 115000009, 100100586, 100100610, 115000008, 100100592,
        100100585, 100100593, 124000016,100100591, 100100588, 124000015};

    private String busRouteId;

    private String busRouteNm;

    private String corpNm;
    private String edStationNm;
    private String firstBusTm;
    private String firstLowTm;
    private String lastBusTm;
    private String lastBusYn;
    private String lastLowTm;
    private String length;
    private String routeType;
    private String stStationNm;
    private String term;

    public search_by_bus() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        for(int i = 0; i < buses.length; i++){
            dataForBus.add(buses[i]);

        }


        View view = inflater.inflate(R.layout.fragment_search_by_bus, container, false);

        listView = view.findViewById(R.id.search_bus_list_view);

        Button search_bus =  view.findViewById(R.id.search_bus);
        search_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        getXmlDataSearch();
                    }
                }).start();
           }
        });
        listView.setAdapter(null);

        adapter = new BusListViewAdapter();

        listView.setAdapter(adapter);
        return view;
    }

    void getXmlDataSearch() {
        String start = null, end = null;
        URL apiUrl = null;
        InputStream in = null;
        XmlPullParserFactory factory = null;
        XmlPullParser xpp = null;
        int eventType = -1;
        try {
            apiUrl = new URL("http://ws.bus.go.kr/api/rest/busRouteInfo/" +
                    "getRouteInfo?ServiceKey=w1LpendNML9NSRvEVJVRbZTbwm0ZK8bwkZiIoXsOwU0QZzhoQZmSDrRgr%2FEeqvNRWV%2F4NGpifpwT8LM1Hvu0dg%3D%3D&busRouteId=100100112");
            boolean containChk = false;

            in = apiUrl.openStream();
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            xpp.setInput(in, "UTF-8");
            eventType = xpp.getEventType();
            boolean isItemTag = false;

            while (eventType != 1)
            {
                if (eventType == 2)
                {
                    tagName = xpp.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = true;
                    }
                }
                else if (eventType == 4) {
                    if ((isItemTag) &&
                            (!tagName.equals("")) && (!xpp.getText().equals(""))) {
                        if (tagName.equals("busRouteId")) {
                            busRouteId = xpp.getText();
                        } else if (tagName.equals("busRouteNm")) {
                            busRouteNm = xpp.getText();
                            containChk = getBusName(xpp.getText());
                        } else if (tagName.equals("corpNm")) {
                            corpNm = xpp.getText();
                        } else if (tagName.equals("edStationNm")) {
                            edStationNm = xpp.getText();
                        } else if (tagName.equals("firstBusTm")) {
                            firstBusTm = xpp.getText();
                        } else if (tagName.equals("firstLowTm")) {
                            firstLowTm = xpp.getText();
                        } else if (tagName.equals("lastBusTm")) {
                            lastBusTm = xpp.getText();
                        } else if (tagName.equals("lastBusYn")) {
                            lastBusYn = xpp.getText();
                        } else if (tagName.equals("lastLowTm")) {
                            lastLowTm = xpp.getText();
                        } else if (tagName.equals("length")) {
                            length = xpp.getText();
                        } else if (tagName.equals("routeType")) {
                            routeType = xpp.getText();
                        } else if (tagName.equals("stStationNm")) {
                            stStationNm = xpp.getText();
                        } else if (tagName.equals("term")) {
                            term = xpp.getText();
                        }
                    }
                }
                else if (eventType == 3)
                {
                    tagName = xpp.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = false;
                        result.put("start", start);
                        result.put("end", end);
                    adapter.addItem(result);

                    } else {
                        tagName = "";
                    }
                }
                eventType = xpp.next();
            }
            return;
        } catch (MalformedURLException e) { e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    boolean getBusName(String arsId) {
        Log.e("버스함수", arsId);
        String queryUrl="http://ws.bus.go.kr/api/rest/stationinfo/getRouteByStation?"//요청 URL
                +"arsId=" + arsId
                +"&serviceKey=" + openAPIKey;

        try {

            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            Log.e("eventType", eventType+" ");

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("busRouteNm")){
                            xpp.next();
                            if(dataForBus.contains(xpp.getText())){
                                result.put("busNum", xpp.getText());
                                return true;
                            }
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:

                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    } // getBusName

}