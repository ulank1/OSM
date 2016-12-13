package com.example.ulan.osm;

import android.util.Log;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 12.12.2016.
 */

public class Search {
    public ArrayList<String> searchNear(GeoPoint geoPoint, GeoPoint geoPoint2, ArrayList<NearRoutes> nearRoutes) {
        ArrayList<String> searchNea = new ArrayList<>();
        Log.e("TAGGAG",nearRoutes.size()+"");
        for (int i = 0; i < nearRoutes.size(); i++) {
            boolean bool = false, bool2 = false;
            NearRoutes nearRoutes1 = nearRoutes.get(i);
            ArrayList<GeoPoint> geoPoints = nearRoutes1.getWaypoints();


            for (int j = 0; j < geoPoints.size(); j++) {
                double d = Math.abs(geoPoint.getLatitude() - geoPoints.get(j).getLatitude());
                double c = Math.abs(geoPoint.getLongitude() - geoPoints.get(j).getLongitude());
                if (c < 0.01 && d < 0.01) {
                    bool = true;
                    break;
                }
            }

            for (int j = 0; j < geoPoints.size(); j++) {
                double d = Math.abs(geoPoint2.getLatitude() - geoPoints.get(j).getLatitude());
                double c = Math.abs(geoPoint2.getLongitude() - geoPoints.get(j).getLongitude());
                if (c < 0.01 && d < 0.01) {
                    bool2 = true;
                    break;
                }
            }

            if (bool && bool2) searchNea.add(nearRoutes1.getNumber());
            Log.e("TAGGGA", bool + "");
        }
        return searchNea;
    }
}
