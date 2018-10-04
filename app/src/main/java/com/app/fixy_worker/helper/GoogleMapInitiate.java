package com.app.fixy_worker.helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.AddAddressActivity;
import com.app.fixy_worker.activities.LandingActivity;
import com.app.fixy_worker.activities.MapAddressActivity;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.utils.Utils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GoogleMapInitiate extends AppCompatActivity implements OnMapReadyCallback, InterfacesCall.LocationInterface {
    Activity context;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 3;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation, lastlocation;
    private GoogleMap mMap;
    private int isFirstTime = 0;
    MarshMallowPermission marshMallowPermission;
    SupportMapFragment supportMapFragment;
    public static InterfacesCall.MapInterface mapInterface;
    int width, height;
    Utils utils;

    public static void setInterface(InterfacesCall.MapInterface location) {
        mapInterface = location;
    }

    public GoogleMapInitiate(Activity context, SupportMapFragment mapFragment) {
        this.context = context;
        supportMapFragment = mapFragment;
        marshMallowPermission = new MarshMallowPermission(context);
        onCreate();
        utils = new Utils(context);
        if (context instanceof AddAddressActivity){
            AddAddressActivity.setInterface(this);
        }
        else if(context instanceof  MapAddressActivity){
            MapAddressActivity.setInterface(this);
        }

        width = utils.getInt("width", 0);
        height = utils.getInt("height", 0);
    }

    private void onCreate() {
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);//
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.e("GOOGLE", "location getLongitude " + locationResult.getLocations().get(0).getLongitude());
                Log.e("GOOGLE", "location getLatitude " + locationResult.getLocations().get(0).getLatitude());

                mCurrentLocation = locationResult.getLastLocation();
                mapInterface.onLocationUpdate(locationResult);

            }
        };
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

        if (supportMapFragment == null) {
            createLocationCallback();//buildLocationSettingsRequest
        }
    }

    private void stopLocationUpdates() {

        if (mFusedLocationClient != null && mLocationCallback != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                    .addOnCompleteListener(context, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
//                        setButtonsEnabledState();
                        }
                    });
        }
    }

    public void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(context, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());

//                        updateUI();
                    }
                })
                .addOnFailureListener(context, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(context, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
                        }

//                        updateUI();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            boolean shouldProvideRationale =
                    ActivityCompat.shouldShowRequestPermissionRationale(context,
                            Manifest.permission.ACCESS_FINE_LOCATION);

            // Provide an additional rationale to the user. This would happen if the user denied the
            // request previously, but didn't check the "Don't ask again" checkbox.
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();

            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (isFirstTime == 0) {
                        isFirstTime = 1;
                        if (marshMallowPermission != null)
                            marshMallowPermission.requestPermissionForLocation();

                        isFirstTime = 2;
                    } else if (isFirstTime == 1) {
                        snackBarLocation();
                    }


                }
            }
        }
    }

    public void snackBarLocation() {

        Snackbar.make(findViewById(android.R.id.content), R.string.enable_location_setting,
                Snackbar.LENGTH_INDEFINITE).setAction(R.string.enable,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(intent);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(context, R.color.red)).show();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (supportMapFragment != null) {
            mMap = googleMap;
//        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style));
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(false);
            View locationButton = ((View) supportMapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, (int) (height * 0.3), (int) (height * 0.2));
            //Disable Map Toolbar:
            mMap.getUiSettings().setMapToolbarEnabled(false);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                mMap.setMyLocationEnabled(true);
            }
            mapInterface.onMapReady(mMap);
            createLocationCallback();
        }
    }


    @Override
    public void onresume() {
        if (marshMallowPermission.checkPermissionForLocation()) {
            startLocationUpdates();
        } else {
            marshMallowPermission.requestPermissionForLocation();
        }
//        Log.e("location", "recenver intnet onresume" );
    }

    @Override
    public void onpause() {
        stopLocationUpdates();

//        Log.e("location", "recenver intnet onpause" );

    }

}
