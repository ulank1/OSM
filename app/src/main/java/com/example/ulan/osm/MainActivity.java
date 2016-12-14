package com.example.ulan.osm;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.osmdroid.views.overlay.mylocation.SimpleLocationOverlay;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.data;
import static com.example.ulan.osm.R.drawable.person;
import static org.osmdroid.bonuspack.utils.BonusPackHelper.LOG_TAG;

public class MainActivity extends AppCompatActivity implements MapEventsReceiver {
    LocationManager locationManager;
    MapView map;
    int tTaskcounter=0;
    Routes routes;
    int asd=0;
    Polyline roadOverlay;
    ArrayList<ArrayList<GeoPoint>> busMarker;
    int posRoute = 0;
    Marker secondMarker;
    Handler h;
    int markerSum = 0;
    Spinner spinner;
    int posMArker = 0;
    ArrayList<String> s;
    Projection projection;
    double d = 0.0001;
    TimerTask tTask=null;
    double d0 = 42.82933821970032;
    Marker markerM;
    Search search;
    RoadManager roadManager;
    Marker firstMArker;
    int positionBus=0;
    ArrayList<NearRoutes> nearRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar;
        routes = new Routes();
        search = new Search();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        busMarker=new ArrayList<>();
        busMarker=kkk();
        s = new ArrayList<>();
        s.add("#");
        s.add("100");

        s.add("258");
        SearchPoints searchpoints = new SearchPoints();
        nearRoutes = searchpoints.getNearRoutes();


        //Spinner------------------------------------------------------------------------------------

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spiner);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(0);
        roadManager = new OSRMRoadManager(this);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       final int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                final ArrayList<GeoPoint> waypoints = routes.getReout(s.get(position));
                if (waypoints.size() > 2) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {


                            Road road = roadManager.getRoad(waypoints);

                            if (posRoute == 1)
                                map.getOverlays().remove(roadOverlay);
                            roadOverlay = RoadManager.buildRoadOverlay(road);

                            map.getOverlays().add(roadOverlay);

                        }
                    });
                    thread.start();
                }

                int ad=0;

                Timer timer = new Timer();
                        switch (s.get(position)){
                            case "100":
                                positionBus=100;
                                ad=0;
                                break;
                            case "258":
                                ad=1;
                                positionBus=300;
                                break;
                            default: ad=2;

                        }
                final int finalAd = ad;

                    Log.e("TAHHH",ad+"");
                if (asd==1) {
                    tTask.cancel();
                }
                if (ad!=2) {
                    tTask = new TimerTask() {
                        public void run() {
                            tTaskcounter = 1;

                            map.getOverlays().remove(markerM);

                            markerM = new Marker(map);
                            markerM.setPosition(busMarker.get(finalAd).get(positionBus));
                            markerM.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
                            markerM.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_buss));
                            map.getOverlays().add(markerM);
                            h.sendEmptyMessage(1);
                            positionBus++;
                            if (positionBus == busMarker.get(finalAd).size())
                                positionBus = 0;
                        }

                    };

                    timer.schedule(tTask, 1000, 1500);
                    asd=1;
                }
                else{
                    map.getOverlays().remove(markerM);

                    asd=0;
                }

                map.invalidate();
                posRoute = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.getOverlays().add(0, mapEventsOverlay);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setMaxZoomLevel(16);
        final IMapController mapController = map.getController();
        mapController.setZoom(13);

        mapController.setCenter(new GeoPoint(42.876,74.602));


        h = new Handler() {
            @Override


            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                if (msg.what == 1)
                    map.invalidate();
                else if (msg.what == 2)
                    searNearRourtes(secondMarker.getPosition(), firstMArker.getPosition());
            }

            ;
        };


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 10, 10, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
                locationListener);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    //42.82467/74.53717
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(final Location location) {


            Thread thread = new Thread(new Runnable() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {

                    Marker startMarker = new Marker(map);

                    startMarker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
                    startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    startMarker.setIcon(getResources().getDrawable(R.drawable.person));
                    map.getOverlays().add(startMarker);
                    h.sendEmptyMessage(1);
                }
            });
            thread.start();


        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };


    @Override
    public boolean singleTapConfirmedHelper(final GeoPoint geoPoint) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (markerSum == 1) {

                    secondMarker = new Marker(map);
                    secondMarker.setDraggable(true);
                    secondMarker.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_location));

                    secondMarker.setPosition(new GeoPoint(geoPoint.getLatitude(), geoPoint.getLongitude()));
                    secondMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    secondMarker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker arg0) {
                        }

                        @SuppressWarnings("unchecked")
                        @Override
                        public void onMarkerDragEnd(Marker arg0) {
                            Log.e("System out", "onMarkerDragEnd...");
                            searNearRourtes(arg0.getPosition(), firstMArker.getPosition());

                        }

                        @Override
                        public void onMarkerDrag(Marker arg0) {
                        }
                    });
                    map.getOverlays().add(secondMarker);
                    h.sendEmptyMessage(2);

                    markerSum = 2;
                } else if (markerSum == 0) {

                    firstMArker = new Marker(map);
                    firstMArker.setDraggable(true);
                    firstMArker.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_location_green));
                    firstMArker.setPosition(new GeoPoint(geoPoint.getLatitude(), geoPoint.getLongitude()));
                    firstMArker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    firstMArker.setTitle("Start point");
                    firstMArker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker arg0) {
                        }

                        @SuppressWarnings("unchecked")
                        @Override
                        public void onMarkerDragEnd(Marker arg0) {
                            Log.e("System out", "onMarkerDragEnd...");
                            if (markerSum == 2)
                                searNearRourtes(arg0.getPosition(), secondMarker.getPosition());

                        }

                        @Override
                        public void onMarkerDrag(Marker arg0) {
                        }
                    });
                    map.getOverlays().add(firstMArker);
                    markerSum = 1;

                }
                h.sendEmptyMessage(1);
            }
        });


        thread.start();


        posMArker = 1;
        searchGoodRout(geoPoint);
        return true;
    }

    private void searchGoodRout(GeoPoint geoPoint) {

    }

    public void searNearRourtes(GeoPoint geoPoint1, GeoPoint geoPoint2) {
        s = search.searchNear(geoPoint1, geoPoint2, nearRoutes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (s.size()==0){
            map.getOverlays().remove(roadOverlay);
        }
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean longPressHelper(GeoPoint geoPoint) {
        return false;
    }




    public ArrayList<ArrayList<GeoPoint>> kkk(){

        ArrayList<ArrayList<GeoPoint>> waypointsList=new ArrayList<>();
        ArrayList<GeoPoint> waypoints=new ArrayList<>();


        waypoints.add(new GeoPoint(42.826064906187845,74.54485416412354));
        waypoints.add(new GeoPoint(42.826222280996205,74.54601287841797));
        waypoints.add(new GeoPoint(42.8263167056889,74.54637229442596));
        waypoints.add(new GeoPoint(42.826395392822654,74.54669415950775));
        waypoints.add(new GeoPoint(42.82661178192397,74.54716622829437));
        waypoints.add(new GeoPoint(42.826706206021726,74.54757928848267));
        waypoints.add(new GeoPoint(42.82690685675052,74.54808354377747));
        waypoints.add(new GeoPoint(42.82713504698391,74.54867362976074));
        waypoints.add(new GeoPoint(42.8273160248459,74.5492422580719));
        waypoints.add(new GeoPoint(42.827516673595255,74.54968750476837));
        waypoints.add(new GeoPoint(42.82761896486388,74.55023467540741));
        waypoints.add(new GeoPoint(42.82783534968205,74.55066382884979));
        waypoints.add(new GeoPoint(42.827925837654114,74.55101251602173));
        waypoints.add(new GeoPoint(42.828157958368045,74.55164015293121));
        waypoints.add(new GeoPoint(42.82837040707105,74.55239653587341));
        waypoints.add(new GeoPoint(42.828622197180934,74.55302953720093));
        waypoints.add(new GeoPoint(42.82883857848716,74.5536196231842));
        waypoints.add(new GeoPoint(42.82902348554861,74.55416679382324));
        waypoints.add(new GeoPoint(42.829165116115206,74.55469250679016));
        waypoints.add(new GeoPoint(42.82928314133946,74.55525577068329));
        waypoints.add(new GeoPoint(42.82938542968485,74.5557975769043));
        waypoints.add(new GeoPoint(42.82944050795456,74.55625355243683));
        waypoints.add(new GeoPoint(42.82952312526711,74.55681145191193));
        waypoints.add(new GeoPoint(42.82951525695639,74.55749809741974));
        waypoints.add(new GeoPoint(42.829534927731316,74.55808281898499));
        waypoints.add(new GeoPoint(42.829550664346755,74.55862998962402));
        waypoints.add(new GeoPoint(42.82954279603953,74.5592200756073));
        waypoints.add(new GeoPoint(42.82952312526711,74.55982089042664));
        waypoints.add(new GeoPoint(42.82952312526711,74.56031441688538));
        waypoints.add(new GeoPoint(42.82951919111187,74.56094205379486));
        waypoints.add(new GeoPoint(42.829527059422105,74.56159114837646));

        waypoints.add(new GeoPoint(42.82954279603953,74.56228315830231));
        waypoints.add(new GeoPoint(42.829479849545734,74.5628410577774));
        waypoints.add(new GeoPoint(42.82951525695639,74.56343650817871));
        waypoints.add(new GeoPoint(42.829530993576824,74.56403195858002));
        waypoints.add(new GeoPoint(42.82948378370348,74.56468641757965));
        waypoints.add(new GeoPoint(42.82948771786097,74.56603288650513));
        waypoints.add(new GeoPoint(42.82951525695639,74.56657469272614));
        waypoints.add(new GeoPoint(42.829491652018206,74.56727206707001));
        waypoints.add(new GeoPoint(42.82951525695639,74.56778705120087));
        waypoints.add(new GeoPoint(42.82949952033193,74.56821084022522));
        waypoints.add(new GeoPoint(42.8294955861752,74.56885993480682));
        waypoints.add(new GeoPoint(42.829530993576824,74.5693588256836));
        waypoints.add(new GeoPoint(42.829865395814096,74.5693051815033));
        waypoints.add(new GeoPoint(42.83021946679926,74.56924080848694));
        waypoints.add(new GeoPoint(42.830538128951716,74.56924080848694));

        waypoints.add(new GeoPoint(42.83092366861886,74.56926226615906));
        waypoints.add(new GeoPoint(42.83131313992239,74.5691978931427));
        waypoints.add(new GeoPoint(42.831741948923025,74.56918716430664));
        waypoints.add(new GeoPoint(42.83241072766449,74.56913352012634));
        waypoints.add(new GeoPoint(42.83280412354366,74.56909596920013));
        waypoints.add(new GeoPoint(42.83325259179144,74.5689994096756));
        waypoints.add(new GeoPoint(42.83371285845132,74.5690369606018));
        waypoints.add(new GeoPoint(42.83419672483362,74.56903159618378));
        waypoints.add(new GeoPoint(42.8347395948017,74.56903159618378));
        waypoints.add(new GeoPoint(42.8352903275775,74.56890821456909));
        waypoints.add(new GeoPoint(42.8357899166357,74.56884920597076));
        waypoints.add(new GeoPoint(42.83622656203244,74.5688384771347));
        waypoints.add(new GeoPoint(42.836686806543064,74.56883311271667));
        waypoints.add(new GeoPoint(42.83728865957705,74.56882774829865));
        waypoints.add(new GeoPoint(42.8378669050097,74.56879019737244));

        waypoints.add(new GeoPoint(42.83835467245975,74.56876337528229));
        waypoints.add(new GeoPoint(42.838877838106356,74.56876873970032));
        waypoints.add(new GeoPoint(42.83955047314327,74.56867218017578));
        waypoints.add(new GeoPoint(42.84026636905668,74.56863462924957));
        waypoints.add(new GeoPoint(42.8408603203594,74.56859171390533));
        waypoints.add(new GeoPoint(42.84139526499001,74.5685487985611));
        waypoints.add(new GeoPoint(42.842284206853336,74.56853806972504));
        waypoints.add(new GeoPoint(42.842044272477864,74.56845760345459));
        waypoints.add(new GeoPoint(42.84299220449698,74.56851124763489));
        waypoints.add(new GeoPoint(42.84336193337492,74.56846833229065));
        waypoints.add(new GeoPoint(42.843747393040715,74.56846833229065));
        waypoints.add(new GeoPoint(42.84389685634567,74.56881701946259));
        waypoints.add(new GeoPoint(42.84386539041679,74.56956803798676));
        waypoints.add(new GeoPoint(42.84386539041679,74.56999719142914));
        waypoints.add(new GeoPoint(42.84384179095961,74.57047998905182));

        waypoints.add(new GeoPoint(42.843873256900515,74.57093596458435));
        waypoints.add(new GeoPoint(42.84388898986496,74.57156360149384));
        waypoints.add(new GeoPoint(42.84379459201818,74.57212686538696));
        waypoints.add(new GeoPoint(42.84379065877144,74.57279741764069));
        waypoints.add(new GeoPoint(42.84377099253395,74.57345724105835));
        waypoints.add(new GeoPoint(42.843743459790964,74.57406342029572));
        waypoints.add(new GeoPoint(42.84370412727968,74.57475006580353));
        waypoints.add(new GeoPoint(42.843696260774415,74.57522213459015));
        waypoints.add(new GeoPoint(42.843747393040715,74.57578539848328));
        waypoints.add(new GeoPoint(42.84375132629021,74.57635402679443));
        waypoints.add(new GeoPoint(42.84371592703569,74.57686364650726));
        waypoints.add(new GeoPoint(42.84372379353846,74.5773035287857));
        waypoints.add(new GeoPoint(42.843696260774415,74.57779705524445));
        waypoints.add(new GeoPoint(42.84369232752141,74.57831740379333));
        waypoints.add(new GeoPoint(42.84371592703569,74.57886457443237));

        waypoints.add(new GeoPoint(42.843696260774415,74.57939565181732));
        waypoints.add(new GeoPoint(42.843680527760895,74.57999110221863));
        waypoints.add(new GeoPoint(42.843688394268156,74.58058655261993));
        waypoints.add(new GeoPoint(42.84372772678945,74.5812839269638));
        waypoints.add(new GeoPoint(42.84364906172182,74.58189010620117));
        waypoints.add(new GeoPoint(42.84370806053194,74.5824533700943));
        waypoints.add(new GeoPoint(42.843625462182004,74.58312392234802));
        waypoints.add(new GeoPoint(42.84354286372163,74.58386421203613));
        waypoints.add(new GeoPoint(42.84359792937414,74.58442211151123));
        waypoints.add(new GeoPoint(42.8435782630753,74.58559155464172));
        waypoints.add(new GeoPoint(42.843574329814786,74.58606362342834));
        waypoints.add(new GeoPoint(42.84349566455183,74.58644986152649));
        waypoints.add(new GeoPoint(42.84355466350844,74.58692193031311));
        waypoints.add(new GeoPoint(42.84350353108264,74.58745837211609));
        waypoints.add(new GeoPoint(42.84349566455183,74.58801090717316));

        waypoints.add(new GeoPoint(42.84349173128606,74.58848834037781));
        waypoints.add(new GeoPoint(42.84350746434766,74.58908915519714));
        waypoints.add(new GeoPoint(42.84350746434766,74.58969533443451));
        waypoints.add(new GeoPoint(42.843448465345986,74.59031224250793));
        waypoints.add(new GeoPoint(42.84344453207719,74.59094524383545));
        waypoints.add(new GeoPoint(42.84345239861452,74.59143877029419));
        waypoints.add(new GeoPoint(42.843420932459246,74.5920181274414));
        waypoints.add(new GeoPoint(42.843405199375596,74.59250628948212));
        waypoints.add(new GeoPoint(42.843397332832275,74.59302127361298));
        waypoints.add(new GeoPoint(42.843377666469586,74.59355771541595));
        waypoints.add(new GeoPoint(42.84338159974262,74.59418535232544));
        waypoints.add(new GeoPoint(42.84337373319629,74.59482908248901));
        waypoints.add(new GeoPoint(42.843377666469586,74.59545135498047));
        waypoints.add(new GeoPoint(42.843358000100636,74.59597170352936));
        waypoints.add(new GeoPoint(42.84331080078961,74.5966637134552));
        waypoints.add(new GeoPoint(42.84327540128267,74.59716260433197));
        waypoints.add(new GeoPoint(42.84325573488116,74.59778487682343));
        waypoints.add(new GeoPoint(42.84326360144252,74.59838569164276));
        waypoints.add(new GeoPoint(42.8432360684734,74.59891676902771));
        waypoints.add(new GeoPoint(42.84318886906921,74.59958732128143));
        waypoints.add(new GeoPoint(42.84318886906921,74.60022568702698));
        waypoints.add(new GeoPoint(42.84318100249834,74.60092842578888));
        waypoints.add(new GeoPoint(42.84321246877581,74.60153460502625));
        waypoints.add(new GeoPoint(42.843153469492414,74.60211396217346));
        waypoints.add(new GeoPoint(42.84316920264016,74.60278987884521));
        waypoints.add(new GeoPoint(42.84314953620485,74.60349798202515));
        waypoints.add(new GeoPoint(42.84311020331545,74.60399687290192));
        waypoints.add(new GeoPoint(42.84308267027796,74.60449039936066));
        waypoints.add(new GeoPoint(42.843078736985895,74.60495173931122));
        waypoints.add(new GeoPoint(42.84311020331545,74.60545063018799));

        waypoints.add(new GeoPoint(42.84308660356978,74.60594415664673));
        waypoints.add(new GeoPoint(42.84304727064031,74.60631430149078));
        waypoints.add(new GeoPoint(42.8430551372282,74.60683465003967));
        waypoints.add(new GeoPoint(42.84300400438895,74.60738718509674));
        waypoints.add(new GeoPoint(42.843000071091886,74.60783779621124));
        waypoints.add(new GeoPoint(42.842976471304155,74.6082079410553));
        waypoints.add(new GeoPoint(42.84295680480749,74.60857808589935));
        waypoints.add(new GeoPoint(42.84290173858353,74.60879266262054));
        waypoints.add(new GeoPoint(42.84331866734395,74.60880875587463));
        waypoints.add(new GeoPoint(42.84379459201818,74.60890531539917));
        waypoints.add(new GeoPoint(42.844262646592945,74.60898578166962));
        waypoints.add(new GeoPoint(42.84475429673918,74.60895359516144));
        waypoints.add(new GeoPoint(42.84516334667891,74.60898578166962));
        waypoints.add(new GeoPoint(42.84563532401299,74.60901260375977));
        waypoints.add(new GeoPoint(42.84611516393932,74.60912525653839));

        waypoints.add(new GeoPoint(42.84670512595334,74.60919499397278));
        waypoints.add(new GeoPoint(42.847161359380735,74.6092540025711));
        waypoints.add(new GeoPoint(42.847696249452746,74.60933983325958));
        waypoints.add(new GeoPoint(42.84821147006914,74.6093076467514));
        waypoints.add(new GeoPoint(42.848789613277596,74.60942566394806));
        waypoints.add(new GeoPoint(42.849344153719585,74.60949540138245));
        waypoints.add(new GeoPoint(42.84983183056126,74.6095061302185));
        waypoints.add(new GeoPoint(42.85047288377796,74.60959196090698));
        waypoints.add(new GeoPoint(42.850964484497936,74.60960268974304));
        waypoints.add(new GeoPoint(42.851558332949416,74.60967242717743));
        waypoints.add(new GeoPoint(42.85195160692682,74.60979044437408));
        waypoints.add(new GeoPoint(42.85235667650551,74.60984945297241));
        waypoints.add(new GeoPoint(42.85283253154891,74.60984945297241));
        waypoints.add(new GeoPoint(42.85319826888372,74.6098655462265));
        waypoints.add(new GeoPoint(42.85373310667806,74.60994601249695));
        waypoints.add(new GeoPoint(42.85410277127003,74.60994601249695));
        waypoints.add(new GeoPoint(42.85466119656394,74.61008548736572));
        waypoints.add(new GeoPoint(42.85513703385091,74.61013376712799));
        waypoints.add(new GeoPoint(42.85572690969532,74.61023569107056));
        waypoints.add(new GeoPoint(42.856218468594854,74.61023032665253));
        waypoints.add(new GeoPoint(42.8567178884295,74.610316157341));
        waypoints.add(new GeoPoint(42.857276290075916,74.61030542850494));
        waypoints.add(new GeoPoint(42.85794086010227,74.61044490337372));
        waypoints.add(new GeoPoint(42.85848745375744,74.61043417453766));
        waypoints.add(new GeoPoint(42.85899078752033,74.61052000522614));
        waypoints.add(new GeoPoint(42.85943906569853,74.61054146289825));
        waypoints.add(new GeoPoint(42.85989913728677,74.61063265800476));
        waypoints.add(new GeoPoint(42.860367069830126,74.61063265800476));
        waypoints.add(new GeoPoint(42.86079960936314,74.61065948009491));
        waypoints.add(new GeoPoint(42.86156637654277,74.61075067520142));
        waypoints.add(new GeoPoint(42.861354041969484,74.6107667684555));
        waypoints.add(new GeoPoint(42.862183715506056,74.61089551448822));
        waypoints.add(new GeoPoint(42.86288755303526,74.61086332798004));
        waypoints.add(new GeoPoint(42.86354026670137,74.61098670959473));
        waypoints.add(new GeoPoint(42.86443282180925,74.61095452308655));
        waypoints.add(new GeoPoint(42.86414579007649,74.61104571819305));
        waypoints.add(new GeoPoint(42.86497935799413,74.61112082004547));
        waypoints.add(new GeoPoint(42.865482638834685,74.6110188961029));
        waypoints.add(new GeoPoint(42.86598984740556,74.61104035377502));
        waypoints.add(new GeoPoint(42.86642234754617,74.61119055747986));
        waypoints.add(new GeoPoint(42.86706322854623,74.6112710237503));
        waypoints.add(new GeoPoint(42.86745247204075,74.61126029491425));
        waypoints.add(new GeoPoint(42.86800291319685,74.61128175258636));
        waypoints.add(new GeoPoint(42.8684983060406,74.61138904094696));
        waypoints.add(new GeoPoint(42.86903301131502,74.61138367652893));
        waypoints.add(new GeoPoint(42.8694497636845,74.61138367652893));
        waypoints.add(new GeoPoint(42.8700906132529,74.61146414279938));
        waypoints.add(new GeoPoint(42.87058598934122,74.61146950721741));
        waypoints.add(new GeoPoint(42.871049909374264,74.61151242256165));
        waypoints.add(new GeoPoint(42.87157279811275,74.6115392446518));
        waypoints.add(new GeoPoint(42.87200525913589,74.61159825325012));
        waypoints.add(new GeoPoint(42.87252420836371,74.61164116859436));
        waypoints.add(new GeoPoint(42.87314536912622,74.6116304397583));
        waypoints.add(new GeoPoint(42.87297631966448,74.61165726184845));
        waypoints.add(new GeoPoint(42.87358175048002,74.61164116859436));
        waypoints.add(new GeoPoint(42.87402992271262,74.61166799068451));
        waypoints.add(new GeoPoint(42.87435229020077,74.61166799068451));
        waypoints.add(new GeoPoint(42.874816181923684,74.61168944835663));
        waypoints.add(new GeoPoint(42.875284001400885,74.61168944835663));
        waypoints.add(new GeoPoint(42.87564960575039,74.61170554161072));
        waypoints.add(new GeoPoint(42.876129212472804,74.6116840839386));
        waypoints.add(new GeoPoint(42.87654984808735,74.61173236370087));
        waypoints.add(new GeoPoint(42.876954756297536,74.61173236370087));
        waypoints.add(new GeoPoint(42.87739897292812,74.61172699928284));
        waypoints.add(new GeoPoint(42.87811836121957,74.61176991462708));
        waypoints.add(new GeoPoint(42.877933601092664,74.61172163486481));
        waypoints.add(new GeoPoint(42.87853898327493,74.61176991462708));
        waypoints.add(new GeoPoint(42.87903429155177,74.61179673671722));
        waypoints.add(new GeoPoint(42.87940773563947,74.61171627044678));
        waypoints.add(new GeoPoint(42.87983227985701,74.61172699928284));
        waypoints.add(new GeoPoint(42.88031578499136,74.61172163486481));
        waypoints.add(new GeoPoint(42.88082287167136,74.6117377281189));
        waypoints.add(new GeoPoint(42.88133388503971,74.61175918579102));
        waypoints.add(new GeoPoint(42.882080743118536,74.61176455020905));
        waypoints.add(new GeoPoint(42.88254064596402,74.61176455020905));

        waypoints.add(new GeoPoint(42.883083091067576,74.61173236370087));
        waypoints.add(new GeoPoint(42.88357050144028,74.61178600788116));
        waypoints.add(new GeoPoint(42.88406183864431,74.6117913722992));
        waypoints.add(new GeoPoint(42.8844981428012,74.61180746555328));
        waypoints.add(new GeoPoint(42.884730050961956,74.6120434999466));
        waypoints.add(new GeoPoint(42.88480473306543,74.61241364479065));
        waypoints.add(new GeoPoint(42.88480080243066,74.61279451847076));
        waypoints.add(new GeoPoint(42.88487155381823,74.61306273937225));
        waypoints.add(new GeoPoint(42.88487155381823,74.61348116397858));
        waypoints.add(new GeoPoint(42.88493051324588,74.61394786834717));
        waypoints.add(new GeoPoint(42.88493051324588,74.61434483528137));
        waypoints.add(new GeoPoint(42.88496981949967,74.61467206478119));
        waypoints.add(new GeoPoint(42.88501698697117,74.61509585380554));
        waypoints.add(new GeoPoint(42.885013056349926,74.61549818515778));
        waypoints.add(new GeoPoint(42.885060223788365,74.6159166097641));

        waypoints.add(new GeoPoint(42.8850445013129,74.61632966995239));
        waypoints.add(new GeoPoint(42.885060223788365,74.61688220500946));
        waypoints.add(new GeoPoint(42.88511525242097,74.61730599403381));
        waypoints.add(new GeoPoint(42.885186003447885,74.61813747882843));
        waypoints.add(new GeoPoint(42.88522924014662,74.61884021759033));
        waypoints.add(new GeoPoint(42.88526854621009,74.6194839477539));
        waypoints.add(new GeoPoint(42.88544149259179,74.62015986442566));
        waypoints.add(new GeoPoint(42.88549259029359,74.6208143234253));
        waypoints.add(new GeoPoint(42.88559085498579,74.6215009689331));
        waypoints.add(new GeoPoint(42.88562623023667,74.622021317482));
        waypoints.add(new GeoPoint(42.885744147593066,74.62253093719482));
        waypoints.add(new GeoPoint(42.88583062017785,74.62307274341583));
        waypoints.add(new GeoPoint(42.88584241188456,74.62360382080078));
        waypoints.add(new GeoPoint(42.885960328827736,74.62405979633331));
        waypoints.add(new GeoPoint(42.885960328827736,74.62441921234131));

        waypoints.add(new GeoPoint(42.8860625233295,74.62497174739838));
        waypoints.add(new GeoPoint(42.886117551068025,74.62550282478333));
        waypoints.add(new GeoPoint(42.88617650930482,74.62600708007813));
        waypoints.add(new GeoPoint(42.88625118965721,74.62652742862701));
        waypoints.add(new GeoPoint(42.88630621722742,74.6270477771759));
        waypoints.add(new GeoPoint(42.886376966888434,74.62748765945435));
        waypoints.add(new GeoPoint(42.88645557752772,74.62813675403595));
        waypoints.add(new GeoPoint(42.886506674389544,74.62862491607666));
        waypoints.add(new GeoPoint(42.886608867986205,74.62920427322388));
        waypoints.add(new GeoPoint(42.88676608857355,74.62989091873169));
        waypoints.add(new GeoPoint(42.887001918703255,74.6303790807724));
        waypoints.add(new GeoPoint(42.88704515412923,74.63071167469025));
        waypoints.add(new GeoPoint(42.88711590294275,74.63121592998505));
        waypoints.add(new GeoPoint(42.88724167841085,74.63159143924713));
        waypoints.add(new GeoPoint(42.88739889738535,74.63200986385345));

        waypoints.add(new GeoPoint(42.88751681135326,74.63249802589417));
        waypoints.add(new GeoPoint(42.887579698710645,74.63285744190216));
        waypoints.add(new GeoPoint(42.88766616872237,74.63316321372986));
        waypoints.add(new GeoPoint(42.88772905592748,74.6334958076477));
        waypoints.add(new GeoPoint(42.88787055190458,74.63403761386871));
        waypoints.add(new GeoPoint(42.887913786721725,74.63455259799957));
        waypoints.add(new GeoPoint(42.88803956056295,74.63508367538452));
        waypoints.add(new GeoPoint(42.88803169970539,74.63539481163025));
        waypoints.add(new GeoPoint(42.88784696926426,74.63553428649902));
        waypoints.add(new GeoPoint(42.88762293373165,74.63553428649902));
        waypoints.add(new GeoPoint(42.88744213253308,74.63560402393341));
        waypoints.add(new GeoPoint(42.88728884414528,74.63564693927765));
        waypoints.add(new GeoPoint(42.887104111479466,74.6357274055481));
        waypoints.add(new GeoPoint(42.88691544776038,74.63573813438416));
        waypoints.add(new GeoPoint(42.88669140884452,74.63577568531036));

        waypoints.add(new GeoPoint(42.88662852058153,74.63577568531036));
        waypoints.add(new GeoPoint(42.88646343858614,74.63581323623657));
        waypoints.add(new GeoPoint(42.88625905074166,74.63585078716278));
        waypoints.add(new GeoPoint(42.88613327327001,74.63587760925293));
        waypoints.add(new GeoPoint(42.88597998162968,74.63595271110535));
        waypoints.add(new GeoPoint(42.88587385642479,74.63593661785126));
        waypoints.add(new GeoPoint(42.88568911952147,74.63601171970367));
        waypoints.add(new GeoPoint(42.88552010442318,74.63602244853973));
        waypoints.add(new GeoPoint(42.88529606043961,74.63611364364624));
        waypoints.add(new GeoPoint(42.88509559934353,74.63615119457245));
        waypoints.add(new GeoPoint(42.88485190066316,74.63616728782654));
        waypoints.add(new GeoPoint(42.88460427037278,74.63619410991669));
        waypoints.add(new GeoPoint(42.884105076130936,74.6365213394165));
        waypoints.add(new GeoPoint(42.88401860112739,74.63641405105591));
        waypoints.add(new GeoPoint(42.88382599727474,74.63644623756409));

        waypoints.add(new GeoPoint(42.88356657072688,74.63655352592468));
        waypoints.add(new GeoPoint(42.883307143088096,74.63655352592468));
        waypoints.add(new GeoPoint(42.88304771435838,74.63662326335907));
        waypoints.add(new GeoPoint(42.882898345807,74.63663399219513));
        waypoints.add(new GeoPoint(42.88266643076061,74.6367359161377));
        waypoints.add(new GeoPoint(42.88243451484243,74.63676810264587));
        waypoints.add(new GeoPoint(42.882175082443254,74.63680028915405));
        waypoints.add(new GeoPoint(42.88200605771749,74.63681638240814));
        waypoints.add(new GeoPoint(42.88182524005635,74.63692367076874));
        waypoints.add(new GeoPoint(42.881589390136725,74.636971950531));
        waypoints.add(new GeoPoint(42.88140857125444,74.6369880437851));
        waypoints.add(new GeoPoint(42.88123168270464,74.63700950145721));
        waypoints.add(new GeoPoint(42.88096831436862,74.63713824748993));
        waypoints.add(new GeoPoint(42.8807206684898,74.6371328830719));
        waypoints.add(new GeoPoint(42.880347232348534,74.63719725608826));

        waypoints.add(new GeoPoint(42.88011137677899,74.63722944259644));
        waypoints.add(new GeoPoint(42.87982441795361,74.63736355304718));
        waypoints.add(new GeoPoint(42.87957283651563,74.6374386548996));
        waypoints.add(new GeoPoint(42.87945097638783,74.63742256164551));
        waypoints.add(new GeoPoint(42.87923084135336,74.63755667209625));
        waypoints.add(new GeoPoint(42.878994981516335,74.63756203651428));
        waypoints.add(new GeoPoint(42.87879449994598,74.63756203651428));
        waypoints.add(new GeoPoint(42.87853112120673,74.63764786720276));
        waypoints.add(new GeoPoint(42.87828739659577,74.637690782547));
        waypoints.add(new GeoPoint(42.878043671022105,74.63772296905518));
        waypoints.add(new GeoPoint(42.87778422016005,74.63774979114532));
        waypoints.add(new GeoPoint(42.87759159684474,74.637690782547));
        waypoints.add(new GeoPoint(42.87731641963693,74.63758885860443));
        waypoints.add(new GeoPoint(42.877060896845165,74.63752448558807));
        waypoints.add(new GeoPoint(42.876844684425684,74.63737964630127));

        waypoints.add(new GeoPoint(42.87667171394467,74.63724553585052));
        waypoints.add(new GeoPoint(42.87640832614457,74.63719725608826));
        waypoints.add(new GeoPoint(42.876180317887396,74.63706314563751));
        waypoints.add(new GeoPoint(42.87595230878775,74.63708996772766));
        waypoints.add(new GeoPoint(42.87577933580536,74.63700413703918));
        waypoints.add(new GeoPoint(42.875594568674984,74.63696658611298));
        waypoints.add(new GeoPoint(42.875425525922154,74.63707387447357));
        waypoints.add(new GeoPoint(42.875268276434035,74.63712751865387));
        waypoints.add(new GeoPoint(42.87509923278741,74.63717579841614));
        waypoints.add(new GeoPoint(42.87442698486626,74.63720262050629));
        waypoints.add(new GeoPoint(42.874886944761336,74.63712215423584));
        waypoints.add(new GeoPoint(42.87433263369482,74.63713824748993));
        waypoints.add(new GeoPoint(42.8740535106347,74.63706851005554));
        waypoints.add(new GeoPoint(42.8738766009993,74.63709533214569));
        waypoints.add(new GeoPoint(42.873589613178815,74.63710069656372));

        waypoints.add(new GeoPoint(42.87326724170674,74.63702023029327));
        waypoints.add(new GeoPoint(42.87310998671906,74.63699340820313));
        waypoints.add(new GeoPoint(42.872803338340596,74.63703632354736));
        waypoints.add(new GeoPoint(42.872614630888826,74.636971950531));
        waypoints.add(new GeoPoint(42.87242592286006,74.63690757751465));
        waypoints.add(new GeoPoint(42.87217037981806,74.63687539100647));
        waypoints.add(new GeoPoint(42.87197380752728,74.63684320449829));
        waypoints.add(new GeoPoint(42.87176150875004,74.63684320449829));
        waypoints.add(new GeoPoint(42.87155314072152,74.63686466217041));
        waypoints.add(new GeoPoint(42.87135263497333,74.63687002658844));
        waypoints.add(new GeoPoint(42.871053840885075,74.636789560318));
        waypoints.add(new GeoPoint(42.870739319229465,74.63674664497375));
        waypoints.add(new GeoPoint(42.87048770075098,74.63674664497375));
        waypoints.add(new GeoPoint(42.87027932842203,74.63666081428528));
        waypoints.add(new GeoPoint(42.87001198176213,74.63665008544922));

        waypoints.add(new GeoPoint(42.869811471007274,74.63661253452301));
        waypoints.add(new GeoPoint(42.86961095960105,74.63668763637543));
        waypoints.add(new GeoPoint(42.869217798090226,74.63661789894104));
        waypoints.add(new GeoPoint(42.86902121639569,74.63659107685089));
        waypoints.add(new GeoPoint(42.86876172760025,74.6365374326706));
        waypoints.add(new GeoPoint(42.86847864767017,74.63654279708862));
        waypoints.add(new GeoPoint(42.86825454180481,74.63647305965424));
        waypoints.add(new GeoPoint(42.868042230234465,74.6364837884903));
        waypoints.add(new GeoPoint(42.86777880560427,74.6365213394165));
        waypoints.add(new GeoPoint(42.86750358464056,74.63652670383453));
        waypoints.add(new GeoPoint(42.867291270487264,74.63651061058044));
        waypoints.add(new GeoPoint(42.866972797888074,74.63651061058044));
        waypoints.add(new GeoPoint(42.866776209043714,74.63650524616241));
        waypoints.add(new GeoPoint(42.86651671081052,74.63649451732635));
        waypoints.add(new GeoPoint(42.86623362058473,74.6364676952362));

        waypoints.add(new GeoPoint(42.865966256401634,74.6365213394165));
        waypoints.add(new GeoPoint(42.865797187276456,74.63645160198212));
        waypoints.add(new GeoPoint(42.86555734423473,74.63649988174438));
        waypoints.add(new GeoPoint(42.86532143213709,74.63649988174438));
        waypoints.add(new GeoPoint(42.86505013211026,74.63649988174438));
        waypoints.add(new GeoPoint(42.864826013797504,74.63649988174438));
        waypoints.add(new GeoPoint(42.864597962749336,74.63649988174438));
        waypoints.add(new GeoPoint(42.86435811504837,74.63646233081818));
        waypoints.add(new GeoPoint(42.86405928710081,74.63646233081818));
        waypoints.add(new GeoPoint(42.86376832165673,74.63651597499847));
        waypoints.add(new GeoPoint(42.86358745055436,74.63651597499847));
        waypoints.add(new GeoPoint(42.863233570777965,74.63652670383453));
        waypoints.add(new GeoPoint(42.863029105982584,74.63647305965424));
        waypoints.add(new GeoPoint(42.86278138811176,74.63650524616241));
        waypoints.add(new GeoPoint(42.86257692181882,74.63652670383453));

        waypoints.add(new GeoPoint(42.86233706626593,74.63651061058044));
        waypoints.add(new GeoPoint(42.86206575312389,74.63654279708862));
        waypoints.add(new GeoPoint(42.861877012843635,74.6365374326706));
        waypoints.add(new GeoPoint(42.86175118566959,74.6364676952362));
        waypoints.add(new GeoPoint(42.86134224558289,74.6364676952362));
        waypoints.add(new GeoPoint(42.861204620906086,74.6364676952362));
        waypoints.add(new GeoPoint(42.86103553874033,74.63649451732635));
        waypoints.add(new GeoPoint(42.86074062687798,74.63653206825256));
        waypoints.add(new GeoPoint(42.86048110327386,74.63649451732635));
        waypoints.add(new GeoPoint(42.86029629034533,74.63649988174438));
        waypoints.add(new GeoPoint(42.860064290354075,74.63648915290833));
        waypoints.add(new GeoPoint(42.85977723831089,74.63651597499847));
        waypoints.add(new GeoPoint(42.85948625268587,74.6364837884903));
        waypoints.add(new GeoPoint(42.85923065607312,74.63652670383453));
        waypoints.add(new GeoPoint(42.85886495446426,74.63650524616241));

        waypoints.add(new GeoPoint(42.858664407501124,74.63643550872803));
        waypoints.add(new GeoPoint(42.85836948431324,74.63650524616241));
        waypoints.add(new GeoPoint(42.85813747708219,74.63651061058044));
        waypoints.add(new GeoPoint(42.857862213135014,74.6365213394165));
        waypoints.add(new GeoPoint(42.85755155663503,74.63646233081818));
        waypoints.add(new GeoPoint(42.85731561394522,74.63636040687561));
        waypoints.add(new GeoPoint(42.857087535154776,74.6362316608429));
        waypoints.add(new GeoPoint(42.856934171356485,74.63619410991669));
        waypoints.add(new GeoPoint(42.8566667668997,74.63605999946594));
        waypoints.add(new GeoPoint(42.85647407767624,74.63588297367096));
        waypoints.add(new GeoPoint(42.8562617255907,74.63554501533508));
        waypoints.add(new GeoPoint(42.85593533114653,74.63545382022858));
        waypoints.add(new GeoPoint(42.85567971983513,74.63517487049103));
        waypoints.add(new GeoPoint(42.855451635002446,74.63505148887634));
        waypoints.add(new GeoPoint(42.85518422412581,74.63474571704865));

        waypoints.add(new GeoPoint(42.854948272390686,74.63460087776184));
        waypoints.add(new GeoPoint(42.854747712707,74.63449895381927));
        waypoints.add(new GeoPoint(42.85449209647979,74.6343058347702));
        waypoints.add(new GeoPoint(42.854244344357554,74.63417708873749));
        waypoints.add(new GeoPoint(42.85404771498167,74.6340537071228));
        waypoints.add(new GeoPoint(42.85377243280366,74.6340161561966));
        waypoints.add(new GeoPoint(42.85353647567443,74.63388741016388));
        waypoints.add(new GeoPoint(42.853324113487325,74.63382840156555));
        waypoints.add(new GeoPoint(42.853155009742004,74.63382303714752));
        waypoints.add(new GeoPoint(42.852970174886075,74.6338015794754));
        waypoints.add(new GeoPoint(42.85269882058478,74.6338015794754));
        waypoints.add(new GeoPoint(42.85238420540959,74.6338015794754));
        waypoints.add(new GeoPoint(42.85225442618292,74.6338015794754));
        waypoints.add(new GeoPoint(42.85200666508381,74.63384985923767));
        waypoints.add(new GeoPoint(42.85165665167853,74.63393032550812));


        waypoints.add(new GeoPoint(42.851389224369214,74.63389277458191));
        waypoints.add(new GeoPoint(42.85116898924501,74.6339464187622));
        waypoints.add(new GeoPoint(42.85076784467949,74.63398933410645));
        waypoints.add(new GeoPoint(42.85052007761686,74.63402152061462));
        waypoints.add(new GeoPoint(42.85023691404255,74.63404834270477));
        waypoints.add(new GeoPoint(42.849969480585905,74.63407516479492));
        waypoints.add(new GeoPoint(42.84954079807063,74.63415026664734));
        waypoints.add(new GeoPoint(42.84921043520326,74.63411808013916));
        waypoints.add(new GeoPoint(42.84891939978473,74.63413417339325));
        waypoints.add(new GeoPoint(42.8486794911789,74.63416635990143));
        waypoints.add(new GeoPoint(42.84837665439515,74.63422536849976));
        waypoints.add(new GeoPoint(42.84809741396717,74.63417172431946));
        waypoints.add(new GeoPoint(42.8477355794223,74.63423609733582));
        waypoints.add(new GeoPoint(42.84749173320742,74.63427364826202));
        waypoints.add(new GeoPoint(42.84727935023596,74.63434338569641));

        waypoints.add(new GeoPoint(42.84710629690451,74.63438630104065));
        waypoints.add(new GeoPoint(42.84688604650875,74.63446140289307));
        waypoints.add(new GeoPoint(42.84662646467701,74.63455259799957));
        waypoints.add(new GeoPoint(42.84631575165625,74.63468670845032));
        waypoints.add(new GeoPoint(42.84605223432526,74.63480472564697));
        waypoints.add(new GeoPoint(42.84583591328802,74.6349173784256));
        waypoints.add(new GeoPoint(42.84548979805283,74.63498175144196));
        waypoints.add(new GeoPoint(42.845194811946705,74.63514268398285));
        waypoints.add(new GeoPoint(42.84500208692993,74.63516414165497));
        waypoints.add(new GeoPoint(42.84471103168334,74.63536262512207));
        waypoints.add(new GeoPoint(42.84441997506545,74.63537871837616));
        waypoints.add(new GeoPoint(42.84414071675118,74.6354752779007));
        waypoints.add(new GeoPoint(42.843955854919145,74.63555037975311));
        waypoints.add(new GeoPoint(42.84367659450688,74.63566303253174));
        waypoints.add(new GeoPoint(42.84346419841861,74.63568985462189));

        waypoints.add(new GeoPoint(42.84322033534267,74.63586688041687));
        waypoints.add(new GeoPoint(42.842889938672,74.63592052459717));
        waypoints.add(new GeoPoint(42.842701139781155,74.6359795331955));
        waypoints.add(new GeoPoint(42.84238647368097,74.63608682155609));
        waypoints.add(new GeoPoint(42.84206787262169,74.63617265224457));
        waypoints.add(new GeoPoint(42.8419380717191,74.63617265224457));
        waypoints.add(new GeoPoint(42.84177680355116,74.63617265224457));
        waypoints.add(new GeoPoint(42.84174533654249,74.63591516017914));
        waypoints.add(new GeoPoint(42.84174533654249,74.63575959205627));
        waypoints.add(new GeoPoint(42.84170993613856,74.63553428649902));
        waypoints.add(new GeoPoint(42.84166273556845,74.635169506073));
        waypoints.add(new GeoPoint(42.84174926991944,74.63476717472076));
        waypoints.add(new GeoPoint(42.84174533654249,74.63445603847504));
        waypoints.add(new GeoPoint(42.84172566965392,74.63413953781128));
        waypoints.add(new GeoPoint(42.841741403165265,74.63355481624603));

        waypoints.add(new GeoPoint(42.841741403165265,74.63328659534454));
        waypoints.add(new GeoPoint(42.84180040379715,74.63269114494324));
        waypoints.add(new GeoPoint(42.84175713667261,74.63232100009918));
        waypoints.add(new GeoPoint(42.841772870175966,74.63196694850922));
        waypoints.add(new GeoPoint(42.8418476042621,74.63165581226349));
        waypoints.add(new GeoPoint(42.841875137849996,74.63097989559174));
        waypoints.add(new GeoPoint(42.84186727111184,74.63054537773132));
        waypoints.add(new GeoPoint(42.841902671425615,74.63008403778076));
        waypoints.add(new GeoPoint(42.84188300458714,74.62965488433838));
        waypoints.add(new GeoPoint(42.84192627162352,74.62940812110901));
        waypoints.add(new GeoPoint(42.84194593844823,74.62869465351105));
        waypoints.add(new GeoPoint(42.84200493888479,74.62830305099487));
        waypoints.add(new GeoPoint(42.84196560526668,74.62773978710175));
        waypoints.add(new GeoPoint(42.842024605684465,74.62740182876587));
        waypoints.add(new GeoPoint(42.84202853904364,74.62690830230713));

        waypoints.add(new GeoPoint(42.84207573933429,74.62658107280731));
        waypoints.add(new GeoPoint(42.8420560725509,74.62613582611084));
        waypoints.add(new GeoPoint(42.842099339466095,74.62555646896362));
        waypoints.add(new GeoPoint(42.84209147275649,74.62514340877533));
        waypoints.add(new GeoPoint(42.842150473054076,74.62446212768555));
        waypoints.add(new GeoPoint(42.84214260635099,74.62404370307922));
        waypoints.add(new GeoPoint(42.842150473054076,74.62339997291565));
        waypoints.add(new GeoPoint(42.84220553994765,74.62303519248962));
        waypoints.add(new GeoPoint(42.84220947329531,74.62245583534241));
        waypoints.add(new GeoPoint(42.84220160659974,74.62221443653107));
        waypoints.add(new GeoPoint(42.84223700672194,74.62177455425262));
        waypoints.add(new GeoPoint(42.8422645401363,74.6213561296463));
        waypoints.add(new GeoPoint(42.842252740103085,74.62117373943329));
        waypoints.add(new GeoPoint(42.84227240682386,74.62084114551544));
        waypoints.add(new GeoPoint(42.842299940222446,74.62032616138458));

        waypoints.add(new GeoPoint(42.8423078069055,74.61996138095856));
        waypoints.add(new GeoPoint(42.842351073644394,74.61958050727844));
        waypoints.add(new GeoPoint(42.84234320696686,74.61905479431152));
        waypoints.add(new GeoPoint(42.84238647368097,74.61871683597565));
        waypoints.add(new GeoPoint(42.842398273688644,74.61828231811523));
        waypoints.add(new GeoPoint(42.84238647368097,74.61795508861542));
        waypoints.add(new GeoPoint(42.842370740333884,74.61767613887787));
        waypoints.add(new GeoPoint(42.84242187369725,74.61722552776337));
        waypoints.add(new GeoPoint(42.842445473696834,74.61690366268158));
        waypoints.add(new GeoPoint(42.84246514035625,74.61659789085388));
        waypoints.add(new GeoPoint(42.842449407029214,74.61613118648529));
        waypoints.add(new GeoPoint(42.84249267366896,74.61582005023956));
        waypoints.add(new GeoPoint(42.84248874033932,74.61543381214142));
        waypoints.add(new GeoPoint(42.84249267366896,74.6152138710022));
        waypoints.add(new GeoPoint(42.84253594027839,74.6148544549942));

        waypoints.add(new GeoPoint(42.842516273641486,74.61435556411743));
        waypoints.add(new GeoPoint(42.84257134020903,74.61394786834717));
        waypoints.add(new GeoPoint(42.8425752735334,74.61360991001129));
        waypoints.add(new GeoPoint(42.84263034004836,74.61326122283936));
        waypoints.add(new GeoPoint(42.84267360656137,74.61286962032318));
        waypoints.add(new GeoPoint(42.842657873287415,74.61254239082336));
        waypoints.add(new GeoPoint(42.84272473967407,74.61225807666779));
        waypoints.add(new GeoPoint(42.84272867298868,74.6117913722992));
        waypoints.add(new GeoPoint(42.842716873044104,74.61156070232391));
        waypoints.add(new GeoPoint(42.8428152058467,74.61104571819305));
        waypoints.add(new GeoPoint(42.8427837393669,74.61077749729156));
        waypoints.add(new GeoPoint(42.84273653961714,74.61045026779175));
        waypoints.add(new GeoPoint(42.84287813875822,74.61007475852966));
        waypoints.add(new GeoPoint(42.84285847223026,74.60965633392334));
        waypoints.add(new GeoPoint(42.84285847223026,74.60940957069397));

        waypoints.add(new GeoPoint(42.842897805279925,74.60913062095642));
        waypointsList.add(waypoints);

        waypoints=new ArrayList<>();

        waypoints.add(new GeoPoint(42.872496688438474,74.42221283912659));

        waypoints.add(new GeoPoint(42.87239447146584,74.42299604415894));
        waypoints.add(new GeoPoint(42.87239447146584,74.4235110282898));
        waypoints.add(new GeoPoint(42.87239447146584,74.42402601242065));
        waypoints.add(new GeoPoint(42.87239447146584,74.42477703094482));
        waypoints.add(new GeoPoint(42.87239447146584,74.42559242248535));
        waypoints.add(new GeoPoint(42.87237088290966,74.42654728889465));
        waypoints.add(new GeoPoint(42.872355157200516,74.42792057991028));
        waypoints.add(new GeoPoint(42.87236302005558,74.42896127700806));
        waypoints.add(new GeoPoint(42.872433785706114,74.42991614341736));
        waypoints.add(new GeoPoint(42.87230798004908,74.43107485771179));
        waypoints.add(new GeoPoint(42.87242592286006,74.43184733390808));
        waypoints.add(new GeoPoint(42.87234729434445,74.43263053894043));
        waypoints.add(new GeoPoint(42.872323705770235,74.43340301513672));
        waypoints.add(new GeoPoint(42.87237874576272,74.43445444107056));
        waypoints.add(new GeoPoint(42.87237874576272,74.43517327308655));

        waypoints.add(new GeoPoint(42.87239447146584,74.43620324134827));
        waypoints.add(new GeoPoint(42.872410197164946,74.43697571754456));
        waypoints.add(new GeoPoint(42.872418060013,74.43771600723267));
        waypoints.add(new GeoPoint(42.87255959110671,74.43858504295349));
        waypoints.add(new GeoPoint(42.872551728276676,74.43934679031372));
        waypoints.add(new GeoPoint(42.87255959110671,74.44025874137878));
        waypoints.add(new GeoPoint(42.872653944988855,74.44124579429626));
        waypoints.add(new GeoPoint(42.872732573113794,74.4421899318695));
        waypoints.add(new GeoPoint(42.872732573113794,74.44328427314758));
        waypoints.add(new GeoPoint(42.87281120113856,74.44424986839294));
        waypoints.add(new GeoPoint(42.87283478952644,74.44541931152344));
        waypoints.add(new GeoPoint(42.87286624069628,74.4463849067688));
        waypoints.add(new GeoPoint(42.87294486855075,74.4471949338913));
        waypoints.add(new GeoPoint(42.87298418244042,74.44764018058777));
        waypoints.add(new GeoPoint(42.8730195649197,74.44841265678406));

        waypoints.add(new GeoPoint(42.87239447146584,74.43620324134827));
        waypoints.add(new GeoPoint(42.872410197164946,74.43697571754456));
        waypoints.add(new GeoPoint(42.872418060013,74.43771600723267));
        waypoints.add(new GeoPoint(42.87255959110671,74.43858504295349));
        waypoints.add(new GeoPoint(42.872551728276676,74.43934679031372));
        waypoints.add(new GeoPoint(42.87255959110671,74.44025874137878));
        waypoints.add(new GeoPoint(42.872653944988855,74.44124579429626));
        waypoints.add(new GeoPoint(42.872732573113794,74.4421899318695));
        waypoints.add(new GeoPoint(42.872732573113794,74.44328427314758));
        waypoints.add(new GeoPoint(42.87281120113856,74.44424986839294));
        waypoints.add(new GeoPoint(42.87283478952644,74.44541931152344));
        waypoints.add(new GeoPoint(42.87286624069628,74.4463849067688));
        waypoints.add(new GeoPoint(42.87294486855075,74.4471949338913));
        waypoints.add(new GeoPoint(42.87298418244042,74.44764018058777));
        waypoints.add(new GeoPoint(42.8730195649197,74.44841265678406));

        waypoints.add(new GeoPoint(42.87351491749981,74.45815980434418));
        waypoints.add(new GeoPoint(42.87353064291343,74.45869624614716));
        waypoints.add(new GeoPoint(42.87355423102634,74.45926487445831));
        waypoints.add(new GeoPoint(42.87357388778022,74.45982277393341));
        waypoints.add(new GeoPoint(42.87361320126919,74.46035385131836));
        waypoints.add(new GeoPoint(42.87361713261671,74.46092784404755));
        waypoints.add(new GeoPoint(42.873668240111684,74.46157693862915));
        waypoints.add(new GeoPoint(42.87371148488205,74.46233332157135));
        waypoints.add(new GeoPoint(42.873754729622135,74.46299314498901));
        waypoints.add(new GeoPoint(42.87379011165966,74.4636583328247));
        waypoints.add(new GeoPoint(42.87382549367688,74.46451127529144));
        waypoints.add(new GeoPoint(42.873837287678136,74.46516573429108));
        waypoints.add(new GeoPoint(42.87388446366054,74.4659274816513));
        waypoints.add(new GeoPoint(42.87390412030924,74.46656584739685));
        waypoints.add(new GeoPoint(42.873919845623675,74.46713447570801));

        waypoints.add(new GeoPoint(42.87396702154298,74.46775674819946));
        waypoints.add(new GeoPoint(42.873998472135824,74.46848630905151));
        waypoints.add(new GeoPoint(42.87406530459237,74.46927487850189));
        waypoints.add(new GeoPoint(42.874088892500936,74.47007954120636));
        waypoints.add(new GeoPoint(42.874147862232896,74.47078227996826));
        waypoints.add(new GeoPoint(42.87418324404503,74.47148501873016));
        waypoints.add(new GeoPoint(42.87421862583686,74.47221457958221));
        waypoints.add(new GeoPoint(42.87429725196834,74.47299778461456));
        waypoints.add(new GeoPoint(42.87426973283372,74.47353422641754));
        waypoints.add(new GeoPoint(42.87431297718262,74.47426378726959));
        waypoints.add(new GeoPoint(42.87433656499651,74.47478413581848));
        waypoints.add(new GeoPoint(42.874360152801394,74.47530448436737));
        waypoints.add(new GeoPoint(42.87439160319388,74.47607696056366));
        waypoints.add(new GeoPoint(42.87442305357034,74.47681725025177));
        waypoints.add(new GeoPoint(42.874556717491465,74.47743952274323));

        waypoints.add(new GeoPoint(42.87445450393076,74.47811007499695));
        waypoints.add(new GeoPoint(42.87456458006604,74.47876989841461));
        waypoints.add(new GeoPoint(42.87457637392604,74.47935998439789));
        waypoints.add(new GeoPoint(42.87456851135296,74.47987496852875));
        waypoints.add(new GeoPoint(42.874603892923915,74.48052942752838));
        waypoints.add(new GeoPoint(42.874631411909526,74.48128044605255));
        waypoints.add(new GeoPoint(42.874690381123095,74.48196172714233));
        waypoints.add(new GeoPoint(42.87470217495904,74.48270201683044));
        waypoints.add(new GeoPoint(42.87476507537938,74.48328673839569));
        waypoints.add(new GeoPoint(42.874769006653516,74.48416650295258));
        waypoints.add(new GeoPoint(42.87481225065256,74.48493897914886));
        waypoints.add(new GeoPoint(42.87483976954526,74.48558807373047));
        waypoints.add(new GeoPoint(42.8748830134947,74.48647320270538));
        waypoints.add(new GeoPoint(42.87490660109072,74.48723495006561));
        waypoints.add(new GeoPoint(42.87493411994135,74.48803424835205));

        waypoints.add(new GeoPoint(42.87498522634568,74.48890328407288));
        waypoints.add(new GeoPoint(42.87499702012528,74.48983669281006));
        waypoints.add(new GeoPoint(42.875028470193186,74.49061989784241));
        waypoints.add(new GeoPoint(42.87505992024508,74.49123680591583));
        waypoints.add(new GeoPoint(42.875095301534294,74.49199855327606));
        waypoints.add(new GeoPoint(42.87510709529287,74.49278712272644));
        waypoints.add(new GeoPoint(42.875150339055,74.49352741241455));
        waypoints.add(new GeoPoint(42.87521717026413,74.49403703212738));
        waypoints.add(new GeoPoint(42.87521323901853,74.49484705924988));
        waypoints.add(new GeoPoint(42.875252551463184,74.49567317962646));
        waypoints.add(new GeoPoint(42.875252551463184,74.49637591838837));
        waypoints.add(new GeoPoint(42.87531545132255,74.49715375900269));
        waypoints.add(new GeoPoint(42.87528007015955,74.49810862541199));
        waypoints.add(new GeoPoint(42.875205376526594,74.49872016906738));
        waypoints.add(new GeoPoint(42.875150339055,74.4992458820343));

        waypoints.add(new GeoPoint(42.8750166764196,74.50022757053375));
        waypoints.add(new GeoPoint(42.874914463620726,74.50088739395142));
        waypoints.add(new GeoPoint(42.87485156335265,74.50140237808228));
        waypoints.add(new GeoPoint(42.874769006653516,74.50201392173767));
        waypoints.add(new GeoPoint(42.87467858728489,74.50280249118805));
        waypoints.add(new GeoPoint(42.87450167944135,74.50370371341705));
        waypoints.add(new GeoPoint(42.87444664134216,74.5044493675232));
        waypoints.add(new GeoPoint(42.874360152801394,74.50526475906372));
        waypoints.add(new GeoPoint(42.874246144994274,74.50602650642395));
        waypoints.add(new GeoPoint(42.874246144994274,74.5070081949234));
        waypoints.add(new GeoPoint(42.87419110666719,74.50777530670166));
        waypoints.add(new GeoPoint(42.87417538142186,74.50848340988159));
        waypoints.add(new GeoPoint(42.87413999960522,74.50923442840576));
        waypoints.add(new GeoPoint(42.87415572485957,74.51000154018402));
        waypoints.add(new GeoPoint(42.87411248040048,74.51066136360168));

        waypoints.add(new GeoPoint(42.87411248040048,74.51141774654388));
        waypoints.add(new GeoPoint(42.874088892500936,74.51229751110077));
        waypoints.add(new GeoPoint(42.87401812874819,74.51306462287903));
        waypoints.add(new GeoPoint(42.87404171667479,74.51389610767365));
        waypoints.add(new GeoPoint(42.87400633478152,74.51497435569763));
        waypoints.add(new GeoPoint(42.87398667816537,74.51565563678741));
        waypoints.add(new GeoPoint(42.87397881551717,74.5164442062378));
        waypoints.add(new GeoPoint(42.873900188979995,74.51701819896698));
        waypoints.add(new GeoPoint(42.873900188979995,74.51780140399933));
        waypoints.add(new GeoPoint(42.87392377695166,74.51845586299896));
        waypoints.add(new GeoPoint(42.87396702154298,74.51933026313782));
        waypoints.add(new GeoPoint(42.87397488419269,74.51995253562927));
        waypoints.add(new GeoPoint(42.8740259913914,74.5206069946289));
        waypoints.add(new GeoPoint(42.87408102986574,74.5216691493988));
        waypoints.add(new GeoPoint(42.87413999960522,74.52239871025085));

        waypoints.add(new GeoPoint(42.87415572485957,74.52300488948822));
        waypoints.add(new GeoPoint(42.87422255714581,74.52385783195496));
        waypoints.add(new GeoPoint(42.87430904587943,74.52465176582336));
        waypoints.add(new GeoPoint(42.87430904587943,74.52533841133118));
        waypoints.add(new GeoPoint(42.87437587799964,74.52603042125702));
        waypoints.add(new GeoPoint(42.874442710047475,74.52658295631409));
        waypoints.add(new GeoPoint(42.87449774815018,74.52744662761688));
        waypoints.add(new GeoPoint(42.87458030521219,74.52828884124756));
        waypoints.add(new GeoPoint(42.874603892923915,74.52900767326355));
        waypoints.add(new GeoPoint(42.87463534319219,74.5296835899353));
        waypoints.add(new GeoPoint(42.87469431240199,74.53041315078735));
        waypoints.add(new GeoPoint(42.87473362517722,74.53101396560669));
        waypoints.add(new GeoPoint(42.87475328155545,74.53166842460632));
        waypoints.add(new GeoPoint(42.874816181923684,74.53237116336823));
        waypoints.add(new GeoPoint(42.87484763208378,74.53304708003998));

        waypoints.add(new GeoPoint(42.874902669825346,74.5337176322937));
        waypoints.add(new GeoPoint(42.874977363824684,74.53460276126862));
        waypoints.add(new GeoPoint(42.874989157605796,74.53521966934204));
        waypoints.add(new GeoPoint(42.87506385150045,74.53605115413666));
        waypoints.add(new GeoPoint(42.87510709529287,74.53689336776733));
        waypoints.add(new GeoPoint(42.875158201553944,74.53773558139801));
        waypoints.add(new GeoPoint(42.875272207676126,74.5387065410614));
        waypoints.add(new GeoPoint(42.87532724503903,74.53984379768372));
        waypoints.add(new GeoPoint(42.87540586975805,74.54067528247833));
        waypoints.add(new GeoPoint(42.8754569757717,74.54129219055176));
        waypoints.add(new GeoPoint(42.87551201296982,74.54206466674805));
        waypoints.add(new GeoPoint(42.875508081743014,74.54270303249359));
        waypoints.add(new GeoPoint(42.87554739399984,74.54346477985382));
        waypoints.add(new GeoPoint(42.87559849989627,74.54395294189453));
        waypoints.add(new GeoPoint(42.87568498670152,74.54465568065643));

        waypoints.add(new GeoPoint(42.87584616633332,74.54534232616425));
        waypoints.add(new GeoPoint(42.87588154717175,74.54602360725403));
        waypoints.add(new GeoPoint(42.87594444638999,74.54665660858154));
        waypoints.add(new GeoPoint(42.87607417582513,74.54732179641724));
        waypoints.add(new GeoPoint(42.876199973804816,74.5481264591217));
        waypoints.add(new GeoPoint(42.87630218447449,74.54877018928528));
        waypoints.add(new GeoPoint(42.87637294560815,74.54938173294067));
        waypoints.add(new GeoPoint(42.876486949486186,74.55020248889923));
        waypoints.add(new GeoPoint(42.87658915968052,74.55076038837433));
        waypoints.add(new GeoPoint(42.87663633355927,74.55130755901337));
        waypoints.add(new GeoPoint(42.87675819924578,74.55211222171783));
        waypoints.add(new GeoPoint(42.876832890999204,74.55284714698792));
        waypoints.add(new GeoPoint(42.87696261856658,74.55362498760223));
        waypoints.add(new GeoPoint(42.87706875910069,74.55420970916748));
        waypoints.add(new GeoPoint(42.877115932612796,74.55471932888031));

        waypoints.add(new GeoPoint(42.87723779735216,74.55534160137177));
        waypoints.add(new GeoPoint(42.87728497073504,74.55586194992065));
        waypoints.add(new GeoPoint(42.877383248500216,74.55652177333832));
        waypoints.add(new GeoPoint(42.87750511271166,74.55735325813293));
        waypoints.add(new GeoPoint(42.87762697668243,74.55800771713257));
        waypoints.add(new GeoPoint(42.87765449431995,74.55873727798462));
        waypoints.add(new GeoPoint(42.87771739173102,74.55944001674652));
        waypoints.add(new GeoPoint(42.877831393125206,74.56009447574615));
        waypoints.add(new GeoPoint(42.87790608357961,74.56095278263092));
        waypoints.add(new GeoPoint(42.87785891067156,74.5617413520813));
        waypoints.add(new GeoPoint(42.87785891067156,74.56230461597443));
        waypoints.add(new GeoPoint(42.87776063366396,74.56306636333466));
        waypoints.add(new GeoPoint(42.87778422016005,74.56381738185883));
        waypoints.add(new GeoPoint(42.87771739173102,74.56441819667816));
        waypoints.add(new GeoPoint(42.8776662875894,74.56486880779266));

        waypoints.add(new GeoPoint(42.87764270104822,74.56517457962036));
        waypoints.add(new GeoPoint(42.87765056322961,74.56571102142334));
        waypoints.add(new GeoPoint(42.87760732121954,74.56624209880829));
        waypoints.add(new GeoPoint(42.87754835479335,74.56678926944733));
        waypoints.add(new GeoPoint(42.877489388310806,74.56735253334045));
        waypoints.add(new GeoPoint(42.87751690600967,74.56778705120087));
        waypoints.add(new GeoPoint(42.87750511271166,74.56811964511871));
        waypoints.add(new GeoPoint(42.87744614618779,74.56855952739716));
        waypoints.add(new GeoPoint(42.87741862845736,74.56911742687225));
        waypoints.add(new GeoPoint(42.87745007729113,74.56992745399475));
        waypoints.add(new GeoPoint(42.87739111071467,74.57064628601074));
        waypoints.add(new GeoPoint(42.87732035074855,74.5711237192154));
        waypoints.add(new GeoPoint(42.87737145517666,74.57162261009216));
        waypoints.add(new GeoPoint(42.877123794861305,74.57181572914124));
        waypoints.add(new GeoPoint(42.876691369705554,74.57181572914124));

        waypoints.add(new GeoPoint(42.87647122482588,74.57164406776428));
        waypoints.add(new GeoPoint(42.876219629715976,74.57158505916595));
        waypoints.add(new GeoPoint(42.87608596939662,74.57188546657562));
        waypoints.add(new GeoPoint(42.87608990058662,74.57237362861633));
        waypoints.add(new GeoPoint(42.876042726290144,74.57284569740295));
        waypoints.add(new GeoPoint(42.87603879509714,74.57339286804199));
        waypoints.add(new GeoPoint(42.87596410238254,74.57394540309906));
        waypoints.add(new GeoPoint(42.87593265279146,74.57465887069702));
        waypoints.add(new GeoPoint(42.87588940957753,74.57530796527863));
        waypoints.add(new GeoPoint(42.87591299678888,74.5757907629013));
        waypoints.add(new GeoPoint(42.87588154717175,74.57639157772064));
        waypoints.add(new GeoPoint(42.87588154717175,74.57699239253998));
        waypoints.add(new GeoPoint(42.8757989918505,74.577357172966));
        waypoints.add(new GeoPoint(42.87580292305879,74.57794725894928));
        waypoints.add(new GeoPoint(42.87576361096475,74.57851052284241));

        waypoints.add(new GeoPoint(42.87580685426683,74.57894504070282));
        waypoints.add(new GeoPoint(42.87573216127147,74.57944929599762));
        waypoints.add(new GeoPoint(42.87572429884566,74.57996964454651));
        waypoints.add(new GeoPoint(42.875677124269686,74.58036661148071));
        waypoints.add(new GeoPoint(42.87566533062006,74.58072602748871));
        waypoints.add(new GeoPoint(42.87566533062006,74.58115518093109));
        waypoints.add(new GeoPoint(42.875629949657664,74.5815896987915));
        waypoints.add(new GeoPoint(42.87562208721882,74.58208322525024));
        waypoints.add(new GeoPoint(42.875594568674984,74.58264112472534));
        waypoints.add(new GeoPoint(42.87557491256472,74.5833545923233));
        waypoints.add(new GeoPoint(42.875527737874556,74.58378374576569));
        waypoints.add(new GeoPoint(42.875508081743014,74.58418607711792));
        waypoints.add(new GeoPoint(42.8755198754227,74.58467960357666));
        waypoints.add(new GeoPoint(42.87548056314833,74.58525896072388));
        waypoints.add(new GeoPoint(42.875413732224445,74.58588123321533));

        waypoints.add(new GeoPoint(42.875413732224445,74.58635866641998));
        waypoints.add(new GeoPoint(42.87540586975805,74.58694338798523));
        waypoints.add(new GeoPoint(42.87536262617499,74.5875334739685));
        waypoints.add(new GeoPoint(42.87536262617499,74.58804309368134));
        waypoints.add(new GeoPoint(42.87529972636372,74.5885580778122));
        waypoints.add(new GeoPoint(42.875268276434035,74.58905160427094));
        waypoints.add(new GeoPoint(42.875272207676126,74.58951830863953));
        waypoints.add(new GeoPoint(42.87528793264197,74.59009230136871));
        waypoints.add(new GeoPoint(42.875232895244,74.59044635295868));
        waypoints.add(new GeoPoint(42.875209307772685,74.59090769290924));
        waypoints.add(new GeoPoint(42.875209307772685,74.59137439727783));
        waypoints.add(new GeoPoint(42.87518965153971,74.59187865257263));
        waypoints.add(new GeoPoint(42.875150339055,74.59230244159698));
        waypoints.add(new GeoPoint(42.87518965153971,74.59290325641632));
        waypoints.add(new GeoPoint(42.87512675155214,74.5932787656784));

        waypoints.add(new GeoPoint(42.87510709529287,74.59395468235016));
        waypoints.add(new GeoPoint(42.87505992024508,74.59435701370239));
        waypoints.add(new GeoPoint(42.87506778275555,74.59498465061188));
        waypoints.add(new GeoPoint(42.87503633270767,74.59547817707062));
        waypoints.add(new GeoPoint(42.87500488264376,74.5961594581604));
        waypoints.add(new GeoPoint(42.874989157605796,74.59658324718475));
        waypoints.add(new GeoPoint(42.87493805120472,74.59690511226654));
        waypoints.add(new GeoPoint(42.87494591373072,74.59728062152863));
        waypoints.add(new GeoPoint(42.87493411994135,74.59805309772491));
        waypoints.add(new GeoPoint(42.87499702012528,74.59855735301971));
        waypoints.add(new GeoPoint(42.87494591373072,74.59897577762604));
        waypoints.add(new GeoPoint(42.87486728842568,74.59951758384705));
        waypoints.add(new GeoPoint(42.87486728842568,74.59996283054352));
        waypoints.add(new GeoPoint(42.87483976954526,74.60041344165802));
        waypoints.add(new GeoPoint(42.87487908222783,74.60091233253479));

        waypoints.add(new GeoPoint(42.87485156335265,74.60128784179688));
        waypoints.add(new GeoPoint(42.87479259429311,74.60163652896881));
        waypoints.add(new GeoPoint(42.87482404446521,74.6020656824112));
        waypoints.add(new GeoPoint(42.87478473174758,74.60244655609131));
        waypoints.add(new GeoPoint(42.87481225065256,74.60277378559113));
        waypoints.add(new GeoPoint(42.87477686920105,74.6032190322876));
        waypoints.add(new GeoPoint(42.87476507537938,74.60364818572998));
        waypoints.add(new GeoPoint(42.87474148772926,74.60404515266418));
        waypoints.add(new GeoPoint(42.87474148772926,74.60436165332794));
        waypoints.add(new GeoPoint(42.87475328155545,74.60478007793427));
        waypoints.add(new GeoPoint(42.87469431240199,74.60519313812256));
        waypoints.add(new GeoPoint(42.87468644984394,74.60563838481903));
        waypoints.add(new GeoPoint(42.874690381123095,74.60592806339264));
        waypoints.add(new GeoPoint(42.87467072472484,74.60630893707275));
        waypoints.add(new GeoPoint(42.87466286216378,74.60673809051514));

        waypoints.add(new GeoPoint(42.8746156867764,74.60708677768707));
        waypoints.add(new GeoPoint(42.87463534319219,74.60751593112946));
        waypoints.add(new GeoPoint(42.87461961806006,74.60793972015381));
        waypoints.add(new GeoPoint(42.87464713703866,74.60853517055511));
        waypoints.add(new GeoPoint(42.87453312976171,74.60886776447296));
        waypoints.add(new GeoPoint(42.87468251856453,74.60911452770233));
        waypoints.add(new GeoPoint(42.87503633270767,74.60912525653839));
        waypoints.add(new GeoPoint(42.875354763702084,74.60918426513672));
        waypoints.add(new GeoPoint(42.87564567453235,74.60920035839081));
        waypoints.add(new GeoPoint(42.87576361096475,74.60958123207092));
        waypoints.add(new GeoPoint(42.875740023696295,74.60998356342316));
        waypoints.add(new GeoPoint(42.87571250520505,74.61040198802948));
        waypoints.add(new GeoPoint(42.87570464277672,74.61077213287354));
        waypoints.add(new GeoPoint(42.87576361096475,74.61121737957001));
        waypoints.add(new GeoPoint(42.875681055485735,74.61152851581573));

        waypoints.add(new GeoPoint(42.87565353696819,74.6120434999466));
        waypoints.add(new GeoPoint(42.87566139940303,74.61241364479065));
        waypoints.add(new GeoPoint(42.8756731930534,74.61290717124939));
        waypoints.add(new GeoPoint(42.87566139940303,74.61335241794586));
        waypoints.add(new GeoPoint(42.87561815599903,74.61371183395386));
        waypoints.add(new GeoPoint(42.87568891791707,74.61417853832245));
        waypoints.add(new GeoPoint(42.87561815599903,74.61455941200256));
        waypoints.add(new GeoPoint(42.87562601843837,74.61492955684662));
        waypoints.add(new GeoPoint(42.87562601843837,74.61522459983826));
        waypoints.add(new GeoPoint(42.87562208721882,74.61566984653473));
        waypoints.add(new GeoPoint(42.87554739399984,74.61614727973938));
        waypoints.add(new GeoPoint(42.8755198754227,74.61650133132935));
        waypoints.add(new GeoPoint(42.87554346277528,74.61685538291931));
        waypoints.add(new GeoPoint(42.875496288061086,74.61720943450928));
        waypoints.add(new GeoPoint(42.87547663191952,74.61747229099274));

        waypoints.add(new GeoPoint(42.87548842560521,74.6177726984024));
        waypoints.add(new GeoPoint(42.87546483823158,74.6181857585907));
        waypoints.add(new GeoPoint(42.87545304454138,74.61856126785278));
        waypoints.add(new GeoPoint(42.87545304454138,74.61897432804108));
        waypoints.add(new GeoPoint(42.87541766345727,74.61922109127045));
        waypoints.add(new GeoPoint(42.87541766345727,74.61961805820465));
        waypoints.add(new GeoPoint(42.87539407605659,74.61982190608978));
        waypoints.add(new GeoPoint(42.87541766345727,74.62018132209778));
        waypoints.add(new GeoPoint(42.87539407605659,74.62048709392548));
        waypoints.add(new GeoPoint(42.87536655741107,74.62084114551544));
        waypoints.add(new GeoPoint(42.87533903875327,74.62121665477753));
        waypoints.add(new GeoPoint(42.87533903875327,74.6216082572937));
        waypoints.add(new GeoPoint(42.875342969990854,74.62201595306396));
        waypoints.add(new GeoPoint(42.87531938256163,74.62239682674408));
        waypoints.add(new GeoPoint(42.87531545132255,74.62277233600616));

        waypoints.add(new GeoPoint(42.87530758884363,74.6231210231781));
        waypoints.add(new GeoPoint(42.875272207676126,74.62355554103851));
        waypoints.add(new GeoPoint(42.875311520083216,74.6239310503006));
        waypoints.add(new GeoPoint(42.875225032754564,74.62441921234131));
        waypoints.add(new GeoPoint(42.87523682648832,74.62480008602142));
        waypoints.add(new GeoPoint(42.87522110150947,74.62513267993927));
        waypoints.add(new GeoPoint(42.875268276434035,74.6255511045456));
        waypoints.add(new GeoPoint(42.875177857796935,74.62587296962738));
        waypoints.add(new GeoPoint(42.87514247655504,74.62642014026642));
        waypoints.add(new GeoPoint(42.87500881390262,74.62672591209412));
        waypoints.add(new GeoPoint(42.87499308886566,74.62727308273315));
        waypoints.add(new GeoPoint(42.87504026396454,74.62782561779022));
        waypoints.add(new GeoPoint(42.875000951384635,74.62828695774078));
        waypoints.add(new GeoPoint(42.87498522634568,74.6287751197815));
        waypoints.add(new GeoPoint(42.87500881390262,74.62907016277313));

        waypoints.add(new GeoPoint(42.875000951384635,74.62945103645325));
        waypoints.add(new GeoPoint(42.875012745161236,74.62998747825623));
        waypoints.add(new GeoPoint(42.87499702012528,74.63054537773132));
        waypoints.add(new GeoPoint(42.874977363824684,74.63097989559174));
        waypoints.add(new GeoPoint(42.87499702012528,74.63137686252594));
        waypoints.add(new GeoPoint(42.87499308886566,74.63183283805847));
        waypoints.add(new GeoPoint(42.874989157605796,74.6322512626648));
        waypoints.add(new GeoPoint(42.874981295085306,74.63256776332855));
        waypoints.add(new GeoPoint(42.874989157605796,74.63295936584473));
        waypoints.add(new GeoPoint(42.874981295085306,74.63347971439362));
        waypoints.add(new GeoPoint(42.8749695013027,74.63391423225403));
        waypoints.add(new GeoPoint(42.8749695013027,74.63422536849976));
        waypoints.add(new GeoPoint(42.874965570041326,74.63460624217987));
        waypoints.add(new GeoPoint(42.8749695013027,74.63508903980255));
        waypoints.add(new GeoPoint(42.874941982467845,74.6357274055481));

        waypoints.add(new GeoPoint(42.874989157605796,74.63634431362152));
        waypoints.add(new GeoPoint(42.875508081743014,74.63716506958008));
        waypoints.add(new GeoPoint(42.8757911294332,74.6369880437851));
        waypoints.add(new GeoPoint(42.87616066196372,74.63708460330963));
        waypoints.add(new GeoPoint(42.876502674142486,74.63718116283417));
        waypoints.add(new GeoPoint(42.87685254670876,74.63737428188324));
        waypoints.add(new GeoPoint(42.877253521817124,74.63750302791595));
        waypoints.add(new GeoPoint(42.877544423696264,74.63768541812897));
        waypoints.add(new GeoPoint(42.877866772825406,74.63768541812897));
        waypoints.add(new GeoPoint(42.87820091343789,74.63767468929291));
        waypoints.add(new GeoPoint(42.878668707255535,74.63761568069458));
        waypoints.add(new GeoPoint(42.879069670562274,74.63751375675201));
        waypoints.add(new GeoPoint(42.87937235682283,74.63741719722748));
        waypoints.add(new GeoPoint(42.87977331555703,74.63737428188324));
        waypoints.add(new GeoPoint(42.88016247889557,74.6372938156128));

        waypoints.add(new GeoPoint(42.88053198524829,74.63726162910461));
        waypoints.add(new GeoPoint(42.88092900556534,74.63710606098175));
        waypoints.add(new GeoPoint(42.88134174675078,74.63701486587524));
        waypoints.add(new GeoPoint(42.88171517687272,74.63696658611298));
        waypoints.add(new GeoPoint(42.88203357340208,74.63690221309662));
        waypoints.add(new GeoPoint(42.882414860910984,74.63682174682617));
        waypoints.add(new GeoPoint(42.882890484294286,74.6366822719574));
        waypoints.add(new GeoPoint(42.88321673623037,74.63659644126892));
        waypoints.add(new GeoPoint(42.88362946211138,74.63651061058044));
        waypoints.add(new GeoPoint(42.88419548168686,74.63646233081818));
        waypoints.add(new GeoPoint(42.8844981428012,74.63633358478546));
        waypoints.add(new GeoPoint(42.884836178134584,74.63625848293304));
        waypoints.add(new GeoPoint(42.8853078522485,74.63618874549866));
        waypoints.add(new GeoPoint(42.88570484183264,74.63604390621185));
        waypoints.add(new GeoPoint(42.88612934271989,74.6359795331955));

        waypoints.add(new GeoPoint(42.88649881333662,74.63594734668732));
        waypoints.add(new GeoPoint(42.886935100258036,74.63580787181854));
        waypoints.add(new GeoPoint(42.88733994031688,74.635711312294));
        waypoints.add(new GeoPoint(42.88767796007821,74.63565766811371));
        waypoints.add(new GeoPoint(42.888067073556535,74.6355664730072));
        waypoints.add(new GeoPoint(42.888102447387396,74.63590979576111));
        waypoints.add(new GeoPoint(42.88809458653785,74.63608682155609));
        waypoints.add(new GeoPoint(42.88814568204201,74.63640868663788));
        waypoints.add(new GeoPoint(42.88819677750384,74.63681638240814));
        waypoints.add(new GeoPoint(42.88825573375336,74.63715434074402));
        waypoints.add(new GeoPoint(42.88828717706341,74.63756203651428));
        waypoints.add(new GeoPoint(42.88828717706341,74.63785171508789));
        waypoints.add(new GeoPoint(42.88830682912404,74.63815212249756));
        waypoints.add(new GeoPoint(42.88830289871241,74.63847398757935));
        waypoints.add(new GeoPoint(42.888365785268384,74.63883340358734));

        waypoints.add(new GeoPoint(42.88838150689735,74.63919818401337));
        waypoints.add(new GeoPoint(42.88840901973841,74.63947713375092));
        waypoints.add(new GeoPoint(42.88841295014328,74.63973999023438));
        waypoints.add(new GeoPoint(42.88842474135637,74.64009940624237));
        waypoints.add(new GeoPoint(42.88847190618619,74.64056074619293));
        waypoints.add(new GeoPoint(42.88847976698765,74.64103281497955));
        waypoints.add(new GeoPoint(42.88847976698765,74.64139223098755));
        waypoints.add(new GeoPoint(42.888483697388004,74.64173018932343));
        waypoints.add(new GeoPoint(42.88849941898693,74.64226126670837));
        waypoints.add(new GeoPoint(42.88850727978489,74.64264214038849));
        waypoints.add(new GeoPoint(42.88851907097994,74.64313566684723));
        waypoints.add(new GeoPoint(42.888515140581845,74.64355409145355));
        waypoints.add(new GeoPoint(42.88856230534253,74.64398860931396));
        waypoints.add(new GeoPoint(42.8885937484963,74.64442312717438));
        waypoints.add(new GeoPoint(42.8885976788894,74.64485228061676));

        waypoints.add(new GeoPoint(42.88855444455159,74.6451848745346));
        waypoints.add(new GeoPoint(42.88854658375965,74.64560866355896));
        waypoints.add(new GeoPoint(42.88858981810295,74.64596271514893));
        waypoints.add(new GeoPoint(42.88858981810295,74.64637041091919));
        waypoints.add(new GeoPoint(42.88853479256984,74.64682102203369));
        waypoints.add(new GeoPoint(42.888542653363295,74.64728236198425));
        waypoints.add(new GeoPoint(42.888515140581845,74.64777052402496));
        waypoints.add(new GeoPoint(42.88856230534253,74.64819431304932));
        waypoints.add(new GeoPoint(42.88855444455159,74.64847326278687));
        waypoints.add(new GeoPoint(42.88853872296669,74.64891850948334));
        waypoints.add(new GeoPoint(42.888558374947195,74.64938521385193));
        waypoints.add(new GeoPoint(42.888570166132475,74.64980900287628));
        waypoints.add(new GeoPoint(42.88853872296669,74.65016841888428));
        waypoints.add(new GeoPoint(42.88855051415574,74.65061902999878));
        waypoints.add(new GeoPoint(42.88855051415574,74.65108036994934));


        waypoints.add(new GeoPoint(42.888526931775395,74.65142905712128));
        waypoints.add(new GeoPoint(42.88853872296669,74.65185284614563));
        waypoints.add(new GeoPoint(42.888526931775395,74.65219616889954));
        waypoints.add(new GeoPoint(42.88853086217275,74.65273261070251));
        waypoints.add(new GeoPoint(42.88853872296669,74.65317249298096));
        waypoints.add(new GeoPoint(42.88849155818797,74.65355336666107));
        waypoints.add(new GeoPoint(42.88850727978489,74.65398788452148));
        waypoints.add(new GeoPoint(42.88849548858757,74.6543151140213));
        waypoints.add(new GeoPoint(42.88849155818797,74.65471744537354));
        waypoints.add(new GeoPoint(42.88853086217275,74.65506076812744));
        waypoints.add(new GeoPoint(42.888526931775395,74.65553820133209));
        waypoints.add(new GeoPoint(42.888558374947195,74.65587615966797));
        waypoints.add(new GeoPoint(42.888515140581845,74.65635895729065));
        waypoints.add(new GeoPoint(42.88845618458026,74.65678811073303));
        waypoints.add(new GeoPoint(42.8884050893333,74.65749621391296));

        waypoints.add(new GeoPoint(42.888361854860506,74.65802729129791));
        waypoints.add(new GeoPoint(42.888361854860506,74.65842962265015));
        waypoints.add(new GeoPoint(42.888291107476036,74.65879440307617));
        waypoints.add(new GeoPoint(42.888259664168004,74.65928792953491));
        waypoints.add(new GeoPoint(42.88825573375336,74.65967953205109));
        waypoints.add(new GeoPoint(42.88814961246365,74.66031789779663));
        waypoints.add(new GeoPoint(42.88815747330619,74.66080069541931));
        waypoints.add(new GeoPoint(42.888110308235945,74.6612298488617));
        waypoints.add(new GeoPoint(42.88805135184742,74.66168582439423));
        waypoints.add(new GeoPoint(42.88800811712669,74.66225445270538));
        waypoints.add(new GeoPoint(42.8879766736744,74.66271579265594));
        waypoints.add(new GeoPoint(42.88789806497354,74.66321468353271));
        waypoints.add(new GeoPoint(42.88783517794072,74.66371357440948));
        waypoints.add(new GeoPoint(42.88781552572983,74.66427683830261));
        waypoints.add(new GeoPoint(42.887752638612874,74.66473817825317));

        waypoints.add(new GeoPoint(42.8877408472713,74.66517269611359));
        waypoints.add(new GeoPoint(42.88766223826991,74.66573059558868));
        waypoints.add(new GeoPoint(42.88759149008301,74.66630458831787));
        waypoints.add(new GeoPoint(42.887556115959136,74.66679811477661));
        waypoints.add(new GeoPoint(42.887465715328176,74.66734528541565));
        waypoints.add(new GeoPoint(42.887410688792286,74.66794073581696));
        waypoints.add(new GeoPoint(42.887347801262585,74.66846108436584));
        waypoints.add(new GeoPoint(42.887308496523985,74.66896533966064));
        waypoints.add(new GeoPoint(42.887214165049116,74.66948568820953));
        waypoints.add(new GeoPoint(42.88711197245523,74.67009723186493));
        waypoints.add(new GeoPoint(42.88704515412923,74.67055320739746));
        waypoints.add(new GeoPoint(42.88695868324697,74.67101454734802));
        waypoints.add(new GeoPoint(42.88687221224349,74.6716046333313));
        waypoints.add(new GeoPoint(42.88681718517817,74.67203915119171));
        waypoints.add(new GeoPoint(42.886730713976334,74.67258632183075));

        waypoints.add(new GeoPoint(42.88667175626923,74.6729028224945));
        waypoints.add(new GeoPoint(42.88656170173186,74.67349290847778));
        waypoints.add(new GeoPoint(42.88645557752772,74.67408299446106));
        waypoints.add(new GeoPoint(42.88634945314101,74.67480182647705));
        waypoints.add(new GeoPoint(42.886207953674734,74.67536509037018));
        waypoints.add(new GeoPoint(42.88612934271989,74.67591762542725));
        waypoints.add(new GeoPoint(42.88600356498375,74.67645943164825));
        waypoints.add(new GeoPoint(42.885901370384325,74.67705488204956));
        waypoints.add(new GeoPoint(42.88584241188456,74.67762351036072));
        waypoints.add(new GeoPoint(42.8857166335634,74.67809021472931));
        waypoints.add(new GeoPoint(42.88568125836437,74.67849254608154));
        waypoints.add(new GeoPoint(42.88555941030131,74.67913091182709));
        waypoints.add(new GeoPoint(42.88549652088428,74.67960298061371));
        waypoints.add(new GeoPoint(42.885358950061,74.68015015125275));
        waypoints.add(new GeoPoint(42.88529606043961,74.68065440654755));

        waypoints.add(new GeoPoint(42.885174211615706,74.68104064464569));
        waypoints.add(new GeoPoint(42.88509559934353,74.68161463737488));
        waypoints.add(new GeoPoint(42.88496981949967,74.68207061290741));
        waypoints.add(new GeoPoint(42.88484010876711,74.68275725841522));
        waypoints.add(new GeoPoint(42.88462392360675,74.68377649784088));
        waypoints.add(new GeoPoint(42.88453351867868,74.6841949224472));
        waypoints.add(new GeoPoint(42.884443113618126,74.6848064661026));
        waypoints.add(new GeoPoint(42.884305540445695,74.68532145023346));
        waypoints.add(new GeoPoint(42.884191551013274,74.68589544296265));
        waypoints.add(new GeoPoint(42.88411686816749,74.68638360500336));
        waypoints.add(new GeoPoint(42.883959640828195,74.68694150447845));
        waypoints.add(new GeoPoint(42.88384958145226,74.68736529350281));
        waypoints.add(new GeoPoint(42.8837748981925,74.68801438808441));
        waypoints.add(new GeoPoint(42.88361766998167,74.6885883808136));
        waypoints.add(new GeoPoint(42.88344471848695,74.68925356864929));

        waypoints.add(new GeoPoint(42.883322866006345,74.68978464603424));
        waypoints.add(new GeoPoint(42.88322459770152,74.69013333320618));
        waypoints.add(new GeoPoint(42.88308702181179,74.69070196151733));
        waypoints.add(new GeoPoint(42.88293372260185,74.69110429286957));
        waypoints.add(new GeoPoint(42.88283545367719,74.69169437885284));
        waypoints.add(new GeoPoint(42.88272932306273,74.69198405742645));
        waypoints.add(new GeoPoint(42.882627123039235,74.69227910041809));
        waypoints.add(new GeoPoint(42.88217115164093,74.69224154949188));
        waypoints.add(new GeoPoint(42.88195888794394,74.69213962554932));
        waypoints.add(new GeoPoint(42.88178986262587,74.69209134578705));
        waypoints.add(new GeoPoint(42.88157366677669,74.69204306602478));
        waypoints.add(new GeoPoint(42.88130636904308,74.69204306602478));
        waypoints.add(new GeoPoint(42.881015484899514,74.69194650650024));
        waypoints.add(new GeoPoint(42.880787493666126,74.69184994697571));
        waypoints.add(new GeoPoint(42.88061846513894,74.69185531139374));

        waypoints.add(new GeoPoint(42.8768053729953,74.69043374061584));
        waypoints.add(new GeoPoint(42.87651053646913,74.69052493572235));
        waypoints.add(new GeoPoint(42.876215698534246,74.69048202037811));
        waypoints.add(new GeoPoint(42.87597196477781,74.69052493572235));
        waypoints.add(new GeoPoint(42.87570071156219,74.69051420688629));
        waypoints.add(new GeoPoint(42.875358694938676,74.69050884246826));
        waypoints.add(new GeoPoint(42.875095301534294,74.69048738479614));
        waypoints.add(new GeoPoint(42.874926257413854,74.69046592712402));
        waypoints.add(new GeoPoint(42.87465893088287,74.69048738479614));
        waypoints.add(new GeoPoint(42.87433656499651,74.69043910503387));
        waypoints.add(new GeoPoint(42.8741714501099,74.69049274921417));
        waypoints.add(new GeoPoint(42.873915914295445,74.69047665596008));
        waypoints.add(new GeoPoint(42.873833356344626,74.69047665596008));
        waypoints.add(new GeoPoint(42.873632858004285,74.69048738479614));
        waypoints.add(new GeoPoint(42.873452015805256,74.69047129154205));

        waypoints.add(new GeoPoint(42.873200408385934,74.69052493572235));
        waypoints.add(new GeoPoint(42.872972388276146,74.6904981136322));
        waypoints.add(new GeoPoint(42.872732573113794,74.69054102897644));
        waypoints.add(new GeoPoint(42.87252420836371,74.69050347805023));
        waypoints.add(new GeoPoint(42.87226866572866,74.69052493572235));
        waypoints.add(new GeoPoint(42.872001327685695,74.69053566455841));
        waypoints.add(new GeoPoint(42.871785097539124,74.69053030014038));
        waypoints.add(new GeoPoint(42.87155314072152,74.69054639339447));
        waypoints.add(new GeoPoint(42.87137229242842,74.69056785106659));
        waypoints.add(new GeoPoint(42.87118751210336,74.69052493572235));
        waypoints.add(new GeoPoint(42.87091230633839,74.69054102897644));
        waypoints.add(new GeoPoint(42.87062923627134,74.69057321548462));
        waypoints.add(new GeoPoint(42.87040513821423,74.69059467315674));
        waypoints.add(new GeoPoint(42.870181039343436,74.69058394432068));
        waypoints.add(new GeoPoint(42.869949076497406,74.69060003757477));

        waypoints.add(new GeoPoint(42.869905829090705,74.69101309776306));
        waypoints.add(new GeoPoint(42.869878307997936,74.69134569168091));
        waypoints.add(new GeoPoint(42.869854718480084,74.69178557395935));
        waypoints.add(new GeoPoint(42.869874376412255,74.692263007164));
        waypoints.add(new GeoPoint(42.86989010275348,74.69257950782776));
        waypoints.add(new GeoPoint(42.86989010275348,74.69298720359802));
        waypoints.add(new GeoPoint(42.869878307997936,74.69355046749115));
        waypoints.add(new GeoPoint(42.86985865006702,74.69406545162201));
        waypoints.add(new GeoPoint(42.86986651324014,74.69458043575287));
        waypoints.add(new GeoPoint(42.869831128953216,74.69500958919525));
        waypoints.add(new GeoPoint(42.869835060541654,74.6957391500473));
        waypoints.add(new GeoPoint(42.86980753941734,74.69622731208801));
        waypoints.add(new GeoPoint(42.869756428725324,74.69665110111237));
        waypoints.add(new GeoPoint(42.869496943020735,74.69671547412872));
        waypoints.add(new GeoPoint(42.8692728408525,74.6966940164566));

        waypoints.add(new GeoPoint(42.86898583162419,74.69661891460419));
        waypoints.add(new GeoPoint(42.86876172760025,74.69666719436646));
        waypoints.add(new GeoPoint(42.868730274338795,74.69707489013672));
        waypoints.add(new GeoPoint(42.86871847936162,74.69754159450531));
        waypoints.add(new GeoPoint(42.86871061604224,74.6979171037674));
        waypoints.add(new GeoPoint(42.868734205997356,74.69848573207855));
        waypoints.add(new GeoPoint(42.8687656592568,74.69884514808655));
        waypoints.add(new GeoPoint(42.868746000971534,74.6992689371109));
        waypoints.add(new GeoPoint(42.86876959091311,74.69968736171722));
        waypoints.add(new GeoPoint(42.86881677076923,74.70011115074158));
        waypoints.add(new GeoPoint(42.86879711250023,74.70073878765106));


        waypointsList.add(waypoints);












        return waypointsList;
    }
}
