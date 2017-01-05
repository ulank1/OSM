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
        ArrayList<Integer> countPoints=new ArrayList<>() ;

        int min=0,max=0;
        Log.e("TAGGAG",nearRoutes.size()+"");
        for (int i = 0; i < nearRoutes.size(); i++) {
            boolean bool = false, bool2 = false;
            NearRoutes nearRoutes1 = nearRoutes.get(i);
            ArrayList<GeoPoint> geoPoints = nearRoutes1.getWaypoints();


            for (int j = 0; j < geoPoints.size(); j++) {
                double d = Math.abs(geoPoint.getLatitude() - geoPoints.get(j).getLatitude());
                double c = Math.abs(geoPoint.getLongitude() - geoPoints.get(j).getLongitude());
                if (c < 0.01 && d < 0.01) {
                    min=j;
                    bool = true;
                    break;
                }
            }

            for (int j = 0; j < geoPoints.size(); j++) {
                double d = Math.abs(geoPoint2.getLatitude() - geoPoints.get(j).getLatitude());
                double c = Math.abs(geoPoint2.getLongitude() - geoPoints.get(j).getLongitude());
                if (c < 0.01 && d < 0.01) {
                    bool2 = true;
                    max=j;
                    break;
                }
            }

            if (bool && bool2){ searchNea.add(nearRoutes1.getNumber());
            countPoints.add(Math.abs(min-max));}
            Log.e("TAGGGA", bool + "");
        }
        String searchNeaStr[]=new String[searchNea.size()];
       for (int i=0;i<searchNea.size();i++){
           searchNeaStr[i]=searchNea.get(i);
       }

        String s;
        int b;
   int a;
        a=countPoints.size();
        for (int i=0;i<a;i++){
            for (int j=i;j<a;j++){
             if (countPoints.get(i)>countPoints.get(j)){
                 b=countPoints.get(i);
                 countPoints.add(i,countPoints.get(j));
                 countPoints.add(j,b);
                 s=searchNeaStr[i];
                 searchNeaStr[i]=searchNeaStr[j];
                 searchNea.add(j,s);
                 searchNeaStr[j]=s;
                 Log.e("IJ",i+"  "+j);

             }
            }

        }
        searchNea.clear();
        for (int i=0;i<searchNeaStr.length;i++)
            searchNea.add(searchNeaStr[i]);
        return searchNea;
    }
}
