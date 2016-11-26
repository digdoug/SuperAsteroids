package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 1/16/16.
 */
public class PowerCore {

    private int cannonBoost = 0;
    private int engineBoost = 0;
    private String image;
    private int imageId = -1;

    /**
     * “cannonBoost”: Integer. The value of extra damage that should be added to the cannon’s base damage.
     “engineBoost”: Integer. Adds to the base speed of the engine.
     “image”: String. The path to the extra part image.
     */

    public PowerCore(int cannonBoost, int engineBoost, String image) {
        this.cannonBoost = cannonBoost;
        this.engineBoost = engineBoost;
        this.image = image;
    }

    public PowerCore(){};

    public int getCannonBoost() {
        return cannonBoost;
    }

    public int getEngineBoost() {
        return engineBoost;
    }

    public String getImage() {
        return image;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public String printPowerCore(){
        return cannonBoost+engineBoost+image+imageId;
    }


}
