package com.example.ulan.osm;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * Created by Админ on 12.12.2016.
 */

public class SearchPoints {
    ArrayList<NearRoutes> nearRoutes=new ArrayList<>();
    public ArrayList<NearRoutes> getNearRoutes(){
        ArrayList<GeoPoint> waypoints=new ArrayList<>();



        waypoints.add(new GeoPoint(42.82556130410877,74.54463958740234));
        waypoints.add(new GeoPoint(42.827072098038144,74.54850196838379));
        waypoints.add(new GeoPoint(42.82814222139804,74.55172061920166));
        waypoints.add(new GeoPoint(42.829149379401606,74.55562591552734));
        waypoints.add(new GeoPoint(42.82959000586782,74.5603895187378));
        waypoints.add(new GeoPoint(42.82959000586782,74.5645523071289));
        waypoints.add(new GeoPoint(42.829243799623086,74.56991672515869));
        waypoints.add(new GeoPoint(42.83273724642086,74.56897258758545));
        waypoints.add(new GeoPoint(42.8374892759524,74.56867218017578));
        waypoints.add(new GeoPoint(42.841831870777796,74.5688009262085));
        waypoints.add(new GeoPoint(42.84381425824815,74.57167625427246));
        waypoints.add(new GeoPoint(42.84375132629021,74.57661151885986));
        waypoints.add(new GeoPoint(42.843625462182004,74.58283424377441));
        waypoints.add(new GeoPoint(42.84346813168613,74.58798408508301));
        waypoints.add(new GeoPoint(42.84334226700096,74.5922327041626));

        waypoints.add(new GeoPoint(42.8432478683188,74.59798336029053));
        waypoints.add(new GeoPoint(42.842838805696026,74.60180282592773));
        waypoints.add(new GeoPoint(42.842933205003234,74.60587978363037));
        waypoints.add(new GeoPoint(42.8427444062446,74.60892677307129));
        waypoints.add(new GeoPoint(42.846237089884106,74.60914134979248));
        waypoints.add(new GeoPoint(42.84969811310071,74.60969924926758));
        waypoints.add(new GeoPoint(42.85466906167286,74.61047172546387));
        waypoints.add(new GeoPoint(42.85973398377773,74.61077213287354));
        waypoints.add(new GeoPoint(42.863760457706775,74.61145877838135));
        waypoints.add(new GeoPoint(42.869548053927616,74.61154460906982));
        waypoints.add(new GeoPoint(42.86794393759348,74.6116304397583));
        waypoints.add(new GeoPoint(42.873542436971015,74.61205959320068));
        waypoints.add(new GeoPoint(42.87687613355197,74.61240291595459));
        waypoints.add(new GeoPoint(42.87923477234301,74.61201667785645));
        waypoints.add(new GeoPoint(42.88275683842475,74.61188793182373));
        waypoints.add(new GeoPoint(42.88524103196829,74.61441993713379));

        waypoints.add(new GeoPoint(42.88524103196829,74.619140625));
        waypoints.add(new GeoPoint(42.886152926016855,74.62502002716064));
        waypoints.add(new GeoPoint(42.886624590062965,74.62995529174805));
        waypoints.add(new GeoPoint(42.88794523020608,74.63523387908936));
        waypoints.add(new GeoPoint(42.88505236255113,74.63686466217041));
        waypoints.add(new GeoPoint(42.88127885303417,74.63712215423584));
        waypoints.add(new GeoPoint(42.87803973995659,74.63776588439941));
        waypoints.add(new GeoPoint(42.873039221843875,74.63789463043213));
        waypoints.add(new GeoPoint(42.868635914458274,74.6367359161377));
        waypoints.add(new GeoPoint(42.86555341237382,74.63557720184326));
        waypoints.add(new GeoPoint(42.862974057652735,74.63682174682617));
        waypoints.add(new GeoPoint(42.86026876496821,74.63690757751465));
        waypoints.add(new GeoPoint(42.85737459970219,74.63703632354736));


        waypoints.add(new GeoPoint(42.85476344290181,74.63484764099121));
        waypoints.add(new GeoPoint(42.85249825359579,74.6340537071228));
        waypoints.add(new GeoPoint(42.849981279145176,74.63407516479492));
        waypoints.add(new GeoPoint(42.84718102453893,74.63431119918823));
        waypoints.add(new GeoPoint(42.84552912942721,74.63499784469604));
        waypoints.add(new GeoPoint(42.843782792277196,74.63564157485962));
        waypoints.add(new GeoPoint(42.84219373990316,74.63630676269531));
        waypoints.add(new GeoPoint(42.84164306865359,74.63559865951538));
        waypoints.add(new GeoPoint(42.84180040379715,74.63253021240234));
        waypoints.add(new GeoPoint(42.8418476042621,74.63036298751831));
        waypoints.add(new GeoPoint(42.84186333774239,74.62841033935547));
        waypoints.add(new GeoPoint(42.84216227310683,74.62549209594727));
        waypoints.add(new GeoPoint(42.84213080629447,74.62268114089966));
        waypoints.add(new GeoPoint(42.842429740364786,74.61952686309814));
        waypoints.add(new GeoPoint(42.842461207024876,74.6171236038208));
        waypoints.add(new GeoPoint(42.84258707350502,74.61416244506836));
        waypoints.add(new GeoPoint(42.84290173858353,74.61102962493896));

       NearRoutes nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("100");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);



        return nearRoutes;
    }
}
