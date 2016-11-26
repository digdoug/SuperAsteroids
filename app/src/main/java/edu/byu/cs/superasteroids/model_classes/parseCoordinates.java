package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 3/1/16.
 */
public class parseCoordinates {

    public int getX(String s){
        String[] ar = s.split(",");
        return Integer.parseInt(ar[0]);
    }

    public int getY(String s){
        String[] ar = s.split(",");
        return Integer.parseInt(ar[1]);
    }
}
