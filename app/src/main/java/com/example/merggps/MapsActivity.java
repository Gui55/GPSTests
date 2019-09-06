package com.example.merggps;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText enterEndereco;
    double latitude = -34, longitude = 151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        enterEndereco = (EditText)findViewById(R.id.enterLugar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void aBusca(View view) {

        String endrecoString = enterEndereco.getText().toString();

        Geocoder geocoder = new Geocoder(this);

        try{

            Address local = geocoder.getFromLocationName(endrecoString, 50).get(0);
            latitude = local.getLatitude();
            longitude = local.getLongitude();

            porMarker(latitude, longitude, local);


        }catch (Exception e){



        }

    }

    public void porMarker(double la, double lo, Address ad){
        LatLng localProcurado = new LatLng(la, lo);
        mMap.addMarker(new MarkerOptions().position(localProcurado).title(ad.getAddressLine(0)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localProcurado)); //Move a câmera para as latitudes e longitudes especificados
        mMap.moveCamera(CameraUpdateFactory.zoomTo( 25.0f )); //Aplica um zoom de acordo com o valor passado
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE); //Aqui se define o tipo de mapa que você quer
    }
}
