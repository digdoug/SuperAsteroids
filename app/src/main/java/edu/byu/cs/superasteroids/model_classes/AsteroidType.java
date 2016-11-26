package edu.byu.cs.superasteroids.model_classes;

import android.graphics.PointF;

/**
 * Created by lp1 on 1/16/16.
 */
public class AsteroidType {

    /**
     * “name”: String. The name of the asteroid type.
     “image”: String. The path for the image file for the asteroid.
     “imageWidth”: Integer. The pixel width of the asteroid’s image.
     “imageHeight”: Integer. The pixel height of the asteroid’s image.
     “type”: String. The type of the asteroid. This is used to determine the behavior and characteristics of the asteroid.
     */

    private String name;
    private String image;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private String type;
    private PointF randomLocation;


    public AsteroidType(String str, String im, int iWidth, int iHeight, String ty){
        name = str;
        image = im;
        imageWidth = iWidth;
        imageHeight = iHeight;
        type = ty;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    public int getImageHeight(){
        return imageHeight;
    }

    public String getType(){
        return type;
    }

    public String printAsteroid(){
        return name + " " + image + " " + imageWidth +
        " " + imageHeight + " " + type;
    }

    public void setRandomLocation(PointF randomLocation) {
        this.randomLocation = randomLocation;
    }

    public PointF getRandomLocation() {
        return randomLocation;
    }
}

