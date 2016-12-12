package com.example.ulan.osm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import static org.osmdroid.bonuspack.utils.BonusPackHelper.LOG_TAG;

public class MainActivity extends AppCompatActivity implements MapEventsReceiver{
    LocationManager locationManager;
    MapView map;
    Routes routes;
    Polyline roadOverlay;
    int posRoute=0;
    Marker secondMarker;
    Handler h;

    Spinner spinner;
    int posMArker=0;
   ArrayList<String> s;
    Projection projection;
    double d=0.0001;
    double d0=42.82933821970032;
    Marker markerM;
    Search search;
    RoadManager roadManager;
    ArrayList<NearRoutes> nearRoutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar;
        routes=new Routes();
        search=new Search();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        s=new ArrayList<>();
        s.add("#");
        s.add("100");
        s.add("101");
        s.add("102");
        s.add("258");
      SearchPoints searchpoints=new SearchPoints();
        nearRoutes=searchpoints.getNearRoutes();

        
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
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                final ArrayList<GeoPoint> waypoints =routes.getReout(s.get(position));
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {


                        Road road = roadManager.getRoad(waypoints);

                        if (posRoute==1)
                            map.getOverlays().remove(roadOverlay);
                        roadOverlay = RoadManager.buildRoadOverlay(road);

                        map.getOverlays().add(roadOverlay);

                    }
                });
                thread.start();
                map.invalidate();
                posRoute=1;
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
        IMapController mapController = map.getController();
        mapController.setZoom(15);
        GeoPoint startPoint = new GeoPoint(42.856, 74.6018);
        mapController.setCenter(startPoint);


        h = new Handler() {
            @Override


            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                map.invalidate();
            };
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
        TimerTask tTask;
        Timer timer = new Timer();
        tTask = new TimerTask() {
            public void run() {
                d0+=d;
                map.getOverlays().remove(markerM);
               markerM = new Marker(map);
                markerM.setPosition(new GeoPoint(d0,74.58536624908447));
                markerM.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                map.getOverlays().add(markerM);
                h.sendEmptyMessage(1);
            }
        };
        timer.schedule(tTask, 1000, 200);
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

            IMapController mapController = map.getController();
            mapController.setZoom(13);
            GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
            mapController.setCenter(new GeoPoint(42.82517,74.54311));

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {

                    Marker startMarker = new Marker(map);
                    startMarker.setPosition(new GeoPoint(location.getLatitude(),location.getLongitude()));
                    startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

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




        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                if (posMArker==1)
                    map.getOverlays().remove(secondMarker);
                secondMarker = new Marker(map);
                secondMarker.setPosition(new GeoPoint(geoPoint.getLatitude(),geoPoint.getLongitude()));
                secondMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                secondMarker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDrag(Marker marker) {
                        Log.e("SDF","SAD");
                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }
                });
                map.getOverlays().add(secondMarker);
                h.sendEmptyMessage(1);
            }
        });


        thread.start();
        s= search.searchNear(geoPoint,nearRoutes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);






        posMArker=1;
        searchGoodRout(geoPoint);
        return true;
    }

    private void searchGoodRout(GeoPoint geoPoint) {

    }


    @Override
    public boolean longPressHelper(GeoPoint geoPoint) {
        return false;
    }
}
