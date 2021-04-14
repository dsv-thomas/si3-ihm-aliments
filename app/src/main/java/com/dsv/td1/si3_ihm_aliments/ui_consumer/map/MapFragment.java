package com.dsv.td1.si3_ihm_aliments.ui_consumer.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.dsv.td1.si3_ihm_aliments.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;


public class MapFragment extends Fragment {
    private static final int PERMISSION_FINE_LOCATION = 99;
    //Location request
    LocationRequest locationRequest;
    //Google api for location
    FusedLocationProviderClient fusedLocationProviderClient;
    IMapController mapController;
    private MapView map;
    private MapViewModel mapViewModel;
    private GeoPoint currentLocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);


        View root = inflater.inflate(R.layout.fragment_map_consumer, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        locationRequest = LocationRequest.create();

        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(3000);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        updateGPS();

        ItemizedOverlayWithFocus<OverlayItem> mMyLocationOverlay;

        assert getActivity() != null;
        map = root.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);    //render
        map.setBuiltInZoomControls(true);               // zoomable
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(17.0);
        GeoPoint startPoint = currentLocation;
        mapController.setCenter(startPoint);

        /////// FIN AFFICHAGE MAP

        return root;
    }

    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity().getApplicationContext());
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Log.d("LOCATION", String.valueOf(location.getLatitude()));
                        Log.d("LOCATION", String.valueOf(location.getLongitude()));
                        currentLocation = new GeoPoint(location);
                        mapController.setCenter(currentLocation);
                        addOverlay(currentLocation);
                    }
                }
            });
        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

    private void addOverlay(GeoPoint geoPoint) {
        //create a new item to draw on the map
        //your items
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        OverlayItem home = new OverlayItem("F. Rallo", "nos bureaux", new GeoPoint(43.65020, 7.00517));
        Drawable m = home.getMarker(0);

        items.add(home); // Lat/Lon decimal degrees
        items.add(new OverlayItem("Votre position", "", geoPoint)); // Lat/Lon decimal degrees

        //the Place icons on the map with a click listener
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity().getApplicationContext(), items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        //do something
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getActivity() != null;
        Configuration.getInstance().load(getActivity().getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }
}