package com.example.a15017096.p08_map;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView tvDetail;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner);
        tvDetail = (TextView)findViewById(R.id.tvDetail);
        tvDetail.setText("ABC Pte Ltd \n We now have 3 branches. Look below for the address and info");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
                                    @Override
                                    public void onMapReady(GoogleMap googleMap) {
                                        map = googleMap;
                                        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                                                android.Manifest.permission.ACCESS_FINE_LOCATION);

                                        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                                            map.setMyLocationEnabled(true);
                                        } else {
                                            Log.e("GMap - Permission", "GPS access has not been granted");
                                        }
                                        UiSettings ui = map.getUiSettings();
                                        ui.setZoomControlsEnabled(true);

                                        LatLng sgp = new LatLng(1.290270, 103.851959);
                                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sgp,
                                                10));

                                        LatLng north_hq = new LatLng(1.441073, 103.772070);
                                        Marker nh = map.addMarker(new
                                                MarkerOptions()
                                                .position(north_hq)
                                                .title("HQ - North")
                                                .snippet("Block 333, Admiralty Ave 3, 765654")
                                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.star)));

                                        LatLng central = new LatLng(1.297802, 103.847441);
                                        Marker c = map.addMarker(new
                                                MarkerOptions()
                                                .position(central)
                                                .title("Central")
                                                .snippet("Block 3A, Orchard Ave 3, 134542")
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                                        LatLng east = new LatLng(1.367149, 103.928021);
                                        Marker e = map.addMarker(new
                                                MarkerOptions()
                                                .position(east)
                                                .title("East")
                                                .snippet("Block 555, Tampines Ave 3, 287788 ")
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

                                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                            @Override
                                            public boolean onMarkerClick(Marker marker) {
                                                Toast.makeText(MainActivity.this, marker.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                                return false;
                                            }
                                        });

                                    }
                                });
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapter, View v,
                int pos, long id) {
                                    if(pos ==1 ){
                    LatLng north = new LatLng(1.441073, 103.772070);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(north,
                            15));
                } else if (pos ==2){
                    LatLng central = new LatLng(1.297802, 103.847441);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(central,
                            15));
                } else if(pos==3) {
                    LatLng east = new LatLng(1.367149, 103.928021);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(east,
                            15));
                } else {
                    LatLng sgp = new LatLng(1.290270, 103.851959);
                     map.moveCamera(CameraUpdateFactory.newLatLngZoom(sgp,
                     10));
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });

    }
}
