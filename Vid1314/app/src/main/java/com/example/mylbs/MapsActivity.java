package com.example.mylbs;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    public static final String nama_pulau[]={"Pilih pulau","Sumatera","Jawa","Kalimantan","Sulawesi","Bali","NTB","NTT","Maluku","Papua"};
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        spinner = findViewById(R.id.pilihpulau);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nama_pulau);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        double lati,longi;
        switch (position) {
            case 0:

            case 1:
                mMap.clear();
                // Add a marker in Sydney and move the camera
                LatLng aceh = new LatLng(5.5611863, 95.293683);
                mMap.addMarker(new MarkerOptions().position(aceh).title("Aceh"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aceh));

                LatLng mdn = new LatLng(3.6422756, 98.5294067);
                mMap.addMarker(new MarkerOptions().position(mdn).title("Medan"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mdn));

                LatLng pdg = new LatLng(-0.9342375, 100.2511844);
                mMap.addMarker(new MarkerOptions().position(pdg).title("Padang"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pdg));
                break;
            case 2:
            case 3:
            case 4:
            default:
                Toast.makeText(this, "Pilihan tidak ada lurr..!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}