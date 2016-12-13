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
        waypoints=new ArrayList<>();
        waypoints.add(new GeoPoint(42.8727247103058,74.42773818969727));
        waypoints.add(new GeoPoint(42.87278761274164,74.43198680877686));
        waypoints.add(new GeoPoint(42.87247309992134,74.43614959716797));
        waypoints.add(new GeoPoint(42.87256745393573,74.44147109985352));
        waypoints.add(new GeoPoint(42.87322792799739,74.44752216339111));
        waypoints.add(new GeoPoint(42.873856944341846,74.45498943328857));
        waypoints.add(new GeoPoint(42.873856944341846,74.46138381958008));
        waypoints.add(new GeoPoint(42.87413999960522,74.46730613708496));
        waypoints.add(new GeoPoint(42.87413999960522,74.47288513183594));
        waypoints.add(new GeoPoint(42.87429725196834,74.47726249694824));
        waypoints.add(new GeoPoint(42.874706106237184,74.48309898376465));
        waypoints.add(new GeoPoint(42.874894807293835,74.48803424835205));
        waypoints.add(new GeoPoint(42.87546090700176,74.49421405792236));
        waypoints.add(new GeoPoint(42.87511495779733,74.5008659362793));
        waypoints.add(new GeoPoint(42.874485954275166,74.5054578781128));

        waypoints.add(new GeoPoint(42.8741714501099,74.51138019561768));
        waypoints.add(new GeoPoint(42.87401419742623,74.51550006866455));
        waypoints.add(new GeoPoint(42.874202900598554,74.52198028564453));
        waypoints.add(new GeoPoint(42.87451740460354,74.52794551849365));
        waypoints.add(new GeoPoint(42.87445450393076,74.53189373016357));
        waypoints.add(new GeoPoint(42.875272207676126,74.53927516937256));
        waypoints.add(new GeoPoint(42.875775404595586,74.5448112487793));
        waypoints.add(new GeoPoint(42.87637294560815,74.55099105834961));
        waypoints.add(new GeoPoint(42.87719062393322,74.55618381500244));
        waypoints.add(new GeoPoint(42.87808691272639,74.56165552139282));
        waypoints.add(new GeoPoint(42.87766235649983,74.56560373306274));
        waypoints.add(new GeoPoint(42.877520837108506,74.57062482833862));
        waypoints.add(new GeoPoint(42.87615279959249,74.57174062728882));
        waypoints.add(new GeoPoint(42.8759955519576,74.57461595535278));
        waypoints.add(new GeoPoint(42.8758540287436,74.5773196220398));


        waypoints.add(new GeoPoint(42.87560243111733,74.58017349243164));
        waypoints.add(new GeoPoint(42.875555256448195,74.58465814590454));
        waypoints.add(new GeoPoint(42.8753036576038,74.5885419845581));
        waypoints.add(new GeoPoint(42.875130682803245,74.59214687347412));
        waypoints.add(new GeoPoint(42.87508350777349,74.59467887878418));
        waypoints.add(new GeoPoint(42.874926257413854,74.59841251373291));
        waypoints.add(new GeoPoint(42.874769006653516,74.60107326507568));
        waypoints.add(new GeoPoint(42.87465893088287,74.60633039474487));
        waypoints.add(new GeoPoint(42.87531938256163,74.60931301116943));
        waypoints.add(new GeoPoint(42.8756967803474,74.6116304397583));
        waypoints.add(new GeoPoint(42.87566533062006,74.61446285247803));
        waypoints.add(new GeoPoint(42.875633880876705,74.61849689483643));
        waypoints.add(new GeoPoint(42.8753036576038,74.62280988693237));
        waypoints.add(new GeoPoint(42.87531938256163,74.62660789489746));
        waypoints.add(new GeoPoint(42.87514640780515,74.62961196899414));

        waypoints.add(new GeoPoint(42.87525648270628,74.63364601135254));
        waypoints.add(new GeoPoint(42.875429457154226,74.63705778121948));
        waypoints.add(new GeoPoint(42.87769380520938,74.6376371383667));
        waypoints.add(new GeoPoint(42.87989517504822,74.63733673095703));
        waypoints.add(new GeoPoint(42.88190778731522,74.63692903518677));
        waypoints.add(new GeoPoint(42.884124729523926,74.63649988174438));
        waypoints.add(new GeoPoint(42.88682897669629,74.63585615158081));
        waypoints.add(new GeoPoint(42.88830682912404,74.63780879974365));
        waypoints.add(new GeoPoint(42.888558374947195,74.64031934738159));
        waypoints.add(new GeoPoint(42.88871559056573,74.64405298233032));
        waypoints.add(new GeoPoint(42.888526931775395,74.64692831039429));
        waypoints.add(new GeoPoint(42.88879419822473,74.65111255645752));
        waypoints.add(new GeoPoint(42.888526931775395,74.65503931045532));
        waypoints.add(new GeoPoint(42.888369715676,74.6575927734375));
        waypoints.add(new GeoPoint(42.88805528227508,74.66038227081299));

        waypoints.add(new GeoPoint(42.88783517794072,74.66390132904053));
        waypoints.add(new GeoPoint(42.88764651645762,74.66692686080933));
        waypoints.add(new GeoPoint(42.88715913828883,74.669930934906));
        waypoints.add(new GeoPoint(42.886876142746274,74.6729564666748));
        waypoints.add(new GeoPoint(42.88624725911459,74.67566013336182));
        waypoints.add(new GeoPoint(42.88583848131591,74.6778917312622));
        waypoints.add(new GeoPoint(42.885413978427124,74.68089580535889));
        waypoints.add(new GeoPoint(42.884690744555385,74.68402862548828));
        waypoints.add(new GeoPoint(42.883998947700576,74.68754768371582));
        waypoints.add(new GeoPoint(42.88354298644115,74.68982219696045));
        waypoints.add(new GeoPoint(42.88277256148325,74.69192504882813));
        waypoints.add(new GeoPoint(42.88113734193738,74.69196796417236));
        waypoints.add(new GeoPoint(42.87987945125642,74.69136714935303));
        waypoints.add(new GeoPoint(42.87860581081416,74.69093799591064));
        waypoints.add(new GeoPoint(42.87706482797305,74.6905517578125));


        waypoints.add(new GeoPoint(42.8758540287436,74.6905517578125));
        waypoints.add(new GeoPoint(42.8741714501099,74.69070196151733));
        waypoints.add(new GeoPoint(42.872551728276676,74.69074487686157));
        waypoints.add(new GeoPoint(42.87097914213699,74.69053030014038));
        waypoints.add(new GeoPoint(42.869752497131884,74.69040155410767));
        waypoints.add(new GeoPoint(42.869878307997936,74.69304084777832));
        waypoints.add(new GeoPoint(42.869752497131884,74.6964955329895));
        waypoints.add(new GeoPoint(42.868667367767806,74.69756841659546));
        waypoints.add(new GeoPoint(42.86880890746237,74.70044374465942));



         nearRoutes1=new NearRoutes();
        nearRoutes1.setNumber("258");
        nearRoutes1.setWaypoints(waypoints);

        nearRoutes.add(nearRoutes1);





        return nearRoutes;
    }
}
