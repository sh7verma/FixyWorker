package com.app.fixy_worker.interfaces;


import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.GoogleMap;

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
    public  interface NewRequest{
        void clickIndex( int pos);
        void Accept( int pos);
        void Decline( int pos);
    }
    public  interface ScheduleRequest{
        void clickIndex( int pos);
        void Confirm( int pos);
    }
    public  interface Callback{
        void selected( int pos);
    }
    public  interface ItemCategoryClick{
        void clickItem( int pos);
        void clickCategory( int pos);
    }
    public interface ServiceOffer{
        void serviceClick( int pos);
        void adsClick( int pos);
        void recomendClick( int pos);
    }
}
