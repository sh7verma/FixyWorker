package com.app.fixy.interfaces;


import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

public class InterfacesCall {

    public interface LocationInterface {
        void onresume();

        void onpause();
    }

    public interface MapInterface {
        void onLocationUpdate(LocationResult location);
        void onMapReady(GoogleMap map);
    }

    public  interface IndexClick{
        void clickIndex( int pos);
    }
}
