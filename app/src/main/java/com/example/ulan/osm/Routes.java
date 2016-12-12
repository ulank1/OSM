package com.example.ulan.osm;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 09.12.2016.
 */

public class Routes {
    ArrayList<GeoPoint> waypoints;
    public ArrayList<GeoPoint> getReout(String number){
        waypoints=new ArrayList<>();
        if (number.equals("100")||number.equals("101")) {
            waypoints.add(new GeoPoint(42.82467, 74.53717));
            GeoPoint endPoint = new GeoPoint(42.82462, 74.53928);

            waypoints.add(new GeoPoint(42.82467, 74.53717));
            waypoints.add(endPoint);
            waypoints.add(new GeoPoint(42.82468, 74.54049));
            waypoints.add(new GeoPoint(42.82476, 74.54145));
            waypoints.add(new GeoPoint(42.82517, 74.54311));
            waypoints.add(new GeoPoint(42.82571, 74.54464));
            waypoints.add(new GeoPoint(42.82759, 74.55005));
            waypoints.add(new GeoPoint(42.82951, 74.56047));
            waypoints.add(new GeoPoint(42.82966, 74.5693));
            waypoints.add(new GeoPoint(42.84384, 74.56838));
            waypoints.add(new GeoPoint(42.84292, 74.60881));
            waypoints.add(new GeoPoint(42.88473, 74.61175));
            waypoints.add(new GeoPoint(42.88809, 74.63546));
            waypoints.add(new GeoPoint(42.84174, 74.63617));
            waypoints.add(new GeoPoint(42.84291, 74.60881));
        }
        else if (number.equals("102")){
            waypoints.add(new GeoPoint(42.85522748184612,74.68825578689575));
            waypoints.add(new GeoPoint(42.856108359741434,74.6662187576294));
            waypoints.add(new GeoPoint(42.85532186222165,74.66154098510742));
            waypoints.add(new GeoPoint(42.85622633350544,74.64162826538086));
            waypoints.add(new GeoPoint(42.85757908322346,74.58682537078857));
            waypoints.add(new GeoPoint(42.872826926731484,74.58789825439453));
            waypoints.add(new GeoPoint(42.873542436971015,74.5713758468628));
            waypoints.add(new GeoPoint(42.87121503261237,74.57107543945313));
            waypoints.add(new GeoPoint(42.871334370237664,74.58789825439453));





        }
        else if (number.equals("258")){
            waypoints.add(new GeoPoint(42.87253600261362,74.42203044891357));
            waypoints.add(new GeoPoint(42.876844684425684,74.55425262451172));
            waypoints.add(new GeoPoint(42.877253521817124,74.57133293151855));
            waypoints.add(new GeoPoint(42.87615279959249,74.57356452941895));
            waypoints.add(new GeoPoint(42.87454885491588,74.60864782333374));
            waypoints.add(new GeoPoint(42.87580685426683,74.61055755615234));
            waypoints.add(new GeoPoint(42.87487908222783,74.63677883148193));
            waypoints.add(new GeoPoint(42.88755218549967,74.6357274055481));
            waypoints.add(new GeoPoint(42.887913786721725,74.66222763061523));
            waypoints.add(new GeoPoint(42.88275683842475,74.69192504882813));
            waypoints.add(new GeoPoint(42.87014565523683,74.6906590461731));
            waypoints.add(new GeoPoint(42.86981540259698,74.69628095626831));
            waypoints.add(new GeoPoint(42.86869882106132,74.70089435577393));

        }
        else {
            waypoints.add(new GeoPoint(42.82467, 74.53717));
            waypoints.add(new GeoPoint(42.88473, 74.61175));
        }
        return  waypoints;
    }
}
