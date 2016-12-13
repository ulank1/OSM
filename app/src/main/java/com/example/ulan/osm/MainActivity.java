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
    Polyline roadOverlay;
    ArrayList<GeoPoint> busMarker;
    int posRoute = 0;
    Marker secondMarker;
    Handler h;
    int markerSum = 0;
    Spinner spinner;
    int posMArker = 0;
    ArrayList<String> s;
    Projection projection;
    double d = 0.0001;
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
        s.add("101");
        s.add("102");
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
                TimerTask tTask;

                Timer timer = new Timer();

                    tTask = new TimerTask() {
                        public void run() {
                            tTaskcounter=1;
                            if (s.get(position).equals("100")) {
                                map.getOverlays().remove(markerM);
                                h.sendEmptyMessage(1);
                                markerM = new Marker(map);
                                markerM.setPosition(busMarker.get(positionBus));
                                markerM.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
                                markerM.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_buss));
                                map.getOverlays().add(markerM);
                                h.sendEmptyMessage(1);
                                positionBus++;
                                if (positionBus == busMarker.size())
                                    positionBus = 0;
                            }
                        }
                    };
                    timer.schedule(tTask, 1000, 500);
                if (!s.get(position).equals("100")) {
                    tTask.cancel();
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

        mapController.setCenter(new GeoPoint(42.82517, 74.54311));


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




    public ArrayList<GeoPoint> kkk(){
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








        return waypoints;
    }
}
