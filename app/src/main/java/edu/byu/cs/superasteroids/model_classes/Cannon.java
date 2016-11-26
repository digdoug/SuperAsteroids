package edu.byu.cs.superasteroids.model_classes;

/**
 * Created by lp1 on 1/16/16.
 */
public class Cannon extends parseCoordinates {

    /**
     * “attachPoint”: Coordinate String. The point of the cannon image that attaches to the
     main body image.
     “emitPoint”: Coordinate String. The point of the cannon image the projectile is emitted from. “image”: String. The path to cannon image.
     “imageWidth”: Integer. The pixel width of the cannon image.
     “imageHeight”: Integer. The pixel height of the cannon image.
     “attackImage”: String. The path to the cannon’s projectile image. “attackImageWidth”: Integer. The pixel width of the cannon’s projectile image. “attackImageHeight”: Integer. The pixel height of the cannon’s projectile image. “attackSound”: String. The path to the cannon’s projectile sound file.
     “damage”: Integer. The base damage for each projectile.
     */

    private String attachPoint;
    private String emitPoint;
    private String image;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private String attackImage;
    private int attackImageWidth = 0;
    private int attackImageHeight = 0;
    private String attackSound;
    private int damage = 0;
    private int imageId = -1;
    private int attackImageId = -1;
    private int attackSoundId = -1;

    public Cannon (String aP, String eP, String i, int iW, int iH, String aI,
                   int aIW, int aIH, String aS, int d){
        attachPoint = aP;
        emitPoint = eP;
        image = i;
        imageWidth = iW;
        imageHeight = iH;
        attackImage = aI;
        attackImageWidth = aIW;
        attackImageHeight = aIH;
        attackSound = aS;
        damage = d;
    }

    public Cannon(){};

    public String getAttachPoint(){
        return attachPoint;
    }

    public int getAttachPointX(){

        return getX(attachPoint);
    }

    public int getAttachPointY(){

        return getY(attachPoint);
    }

    public String getEmitPoint(){
        return emitPoint;
    }

    public int getEmitX(){
        return getX(emitPoint);
    }

    public int getEmitY(){
        return getY(emitPoint);
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

    public String getAttackImage(){
        return attackImage;
    }

    public int getAttackImageWidth(){
        return attackImageWidth;
    }

    public int getAttackImageHeight(){
        return attackImageHeight;
    }

    public String getAttackSound(){
        return attackSound;
    }

    public int getDamage(){
        return damage;
    }


    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getAttackImageId() {
        return attackImageId;
    }

    public String printCannon(){
        return attachPoint+emitPoint+image+imageWidth+imageHeight+attackImage+attackImageWidth+
                attackImageHeight+attackSound+damage+imageId+attackImageId+attackSoundId;
    }

    public int getAttackSoundId() {
        return attackSoundId;
    }

    public void setAttackImageId(int attackImageId) {
        this.attackImageId = attackImageId;
    }

    public void setAttackSoundId(int attackSoundId) {
        this.attackSoundId = attackSoundId;
    }
}
