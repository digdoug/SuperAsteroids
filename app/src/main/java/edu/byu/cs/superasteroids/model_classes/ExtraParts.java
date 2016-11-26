package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 1/16/16.
 */
public class ExtraParts extends parseCoordinates {

    /**
     * “attachPoint”: Coordinate String. The point of the extra part image that attaches to the main body image.
     “image”: String. The path to extra part image.
     “imageWidth”: Integer. The pixel width of the extra partimage.
     “imageHeight”: Integer. The pixel height of the extra partimage.
     */

    private String attachPoint;
    private String image;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private int imageId = -1;

    public ExtraParts(String attachPoint, String image, int imageWidth, int imageHeight) {
        this.attachPoint = attachPoint;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public ExtraParts(){};

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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }
}

