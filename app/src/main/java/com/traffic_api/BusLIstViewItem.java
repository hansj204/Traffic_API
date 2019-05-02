
package com.traffic_api;

public class BusLIstViewItem {
        private String bus_route_nm;
        private String st_station_nm;
        private String ed_station_nm;


        public String getBus_route_nm() { return bus_route_nm; }
        public String getSt_station_nm() { return st_station_nm; }
        public String getEd_station_nm() { return ed_station_nm; }


        public BusLIstViewItem(){
        }

        public void setBus_route_nm(String nm){
            this.bus_route_nm = nm;
        }

        public void setSt_station_nm(String st){
            this.st_station_nm = st;
        }

        public void setEd_station_nm(String ed){
            this.ed_station_nm = ed;
        }
    }