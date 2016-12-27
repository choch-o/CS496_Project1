package com.example.q.project1;

import android.media.Image;

import java.io.File;

/**
 * Created by q on 2016-12-27.
 */

public class Tab3Item {
    private String name;
    private String image_route;

    public Tab3Item() {}

    public Tab3Item(String name, String image_route) {
        this.name = name;
        this.image_route = image_route;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getImageRoute() { return image_route; }

    public void setImageRoute(String image_route) { this.image_route = image_route; }
}
