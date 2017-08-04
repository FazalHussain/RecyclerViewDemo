package com.cds.recyclerviewdemo;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by fazal on 8/3/2017.
 */

public class Data {
    String product_name;
    String product_description;
    Bitmap image;

    public Data(String product_name, String product_description, Bitmap image) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.image = image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Bitmap getImage() {
        return image;
    }
}
