package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 1/16/16.
 */
public class MainBody extends parseCoordinates{

    /**
     * “cannonAttach”: Coordinate String. The point on the main body image where the cannon should be attached.
     “engineAttach”: Coordinate String. The point on the main body image where the engine should be attached.
     “extraAttach”: Coordinate String. The point on the main body image where the extra part should be attached.
     “image”: String. The path to main body image.
     “imageWidth”: Integer. The pixel width of the main body image.
     “imageHeight”: Integer. The pixel height of the main body image.
     */

    private String cannonAttach;
    private String engineAttach;
    private String extraAttach;
    private String image;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private int imageId = -1;

    public MainBody(String cannonAttach, String engineAttach, String extraAttach, String image, int imageWidth, int imageHeight) {
        this.cannonAttach = cannonAttach;
        this.engineAttach = engineAttach;
        this.extraAttach = extraAttach;
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public MainBody(){};

    public String getCannonAttach() {
        return cannonAttach;
    }

    public int getCannonAttachX(){
        return getX(cannonAttach);
    }

    public int getCannonAttachY(){
        return getY(cannonAttach);
    }

    public String getEngineAttach() {
        return engineAttach;
    }

    public int getEngineAttachX(){
        return getX(engineAttach);
    }

    public int getEngineAttachY(){
        return getY(engineAttach);
    }

    public String getExtraAttach() {
        return extraAttach;
    }

    public int getExtraAttachX(){
        return getX(extraAttach);
    }

    public int getExtraAttachY(){
        return getY(extraAttach);
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

    public String printMainBody(){
        return cannonAttach+engineAttach+extraAttach+image+imageWidth+imageHeight+imageId;
    }


}
