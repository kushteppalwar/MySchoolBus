package com.example.ardent.myschoolbus;


/**
 * Created by apple on 9/14/17.
 */

public class Locaten {

    Double lat;
    Double lont;

    public Locaten()
    {

    }

    public Locaten(Double lat, Double lont) {
        this.lat = lat;
        this.lont = lont;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLont() {
        return lont;
    }

    public void setLont(Double lont) {
        this.lont = lont;
    }


}