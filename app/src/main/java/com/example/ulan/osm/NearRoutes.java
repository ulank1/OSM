package com.example.ulan.osm;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 12.12.2016.
 */

public class NearRoutes {
    ArrayList<GeoPoint> waypoints;
    String number;

    public ArrayList<GeoPoint> getWaypoints() {
        return waypoints;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setWaypoints(ArrayList<GeoPoint> waypoints) {
        this.waypoints = waypoints;
    }
}
