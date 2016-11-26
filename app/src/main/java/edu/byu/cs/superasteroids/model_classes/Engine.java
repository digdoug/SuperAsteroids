package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 1/16/16.
 */
public class Engine extends parseCoordinates {




    /**
     * “baseSpeed”: Integer. The base maximum velocity of the ship in pixels per second.
     * “baseTurnRate”: Integer. The base turn rate of the ship in degrees per second.
     * “attachPoint”: Coordinate String. The point of the extra part image that attaches to the main body image.
     “image”: String. The path to extra part image.
     “imageWidth”: Integer. The pixel width of the extra part image.
     “imageHeight”: Integer. The pixel height of the extra part image.
     */


    private int baseSpeed = 0;
    private int baseTurnRate = 0;
    private String attachPoint;
    private String image;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private int imageId = -1;

    public Engine(int baseSpeed, int baseTurnRate, String attachPoint, String image, int imageWidth, int imageHeight) {
        this.baseSpeed = baseSpeed;
        this.baseTurnRate = baseTurnRate;
        this.attachPoint = attachPoint;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public Engine(){};


    public int getBaseTurnRate() {
        return baseTurnRate;
    }

    public String getAttachPoint() {
        return attachPoint;
    }

    public int getAttachPointX(){
        return getX(attachPoint);
    }

    public int getAttachPointY(){
        return getY(attachPoint);
    }

    public String getImage() {
        return image;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String printEngine(){
        return baseSpeed+baseTurnRate+attachPoint+image+imageWidth+imageHeight;
    }

}
