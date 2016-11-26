package edu.byu.cs.superasteroids.model_classes;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;

import edu.byu.cs.superasteroids.base.Conversion;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.viewStuff.viewPort;

/**
 * Created by lp1 on 2/17/16.
 */
public class theShip {

    // instantiate,
    // make getters and setters for all of the parts

    private Cannon cannon;// = new Cannon();
    private Engine engine;// = new Engine();
    private ExtraParts extraParts;// = new ExtraParts();
    private MainBody mainBody;// = new MainBody();
    private PowerCore powerCore;// = new PowerCore();
    private PointF bodyLocation;
    private float scaleFactor = .25f;
    private asteroidsGame model;// = asteroidsGame.getInstance();
    private ContentManager cm = ContentManager.getInstance();
    private float currentRotation = 0;
    private Conversion conversion;
    private PointF emitLaser;
    private viewPort aView;


   // private DrawingHelper drawingHelper;

    public theShip(){
        conversion = new Conversion();
        //model = asteroidsGame.getInstance();
    };


    public Cannon getCannon() {
        return cannon;
    }

    public Engine getEngine() {
        return engine;
    }

    public ExtraParts getExtraParts() {
        return extraParts;
    }

    public MainBody getMainBody() {
        return mainBody;
    }

    public PowerCore getPowerCore() {
        return powerCore;
    }

    public float getCurrentRotation() {
        return currentRotation;
    }

    public PointF getEmitLaser() {
        return emitLaser;
    }

    public void setCannon(Cannon cannon, int imageId) {
        this.cannon = cannon;
        this.cannon.setImageId(imageId);
    }

    public void setEngine(Engine engine, int imageId) {
        this.engine = engine;
        this.engine.setImageId(imageId);
    }

    public void setExtraParts(ExtraParts extraParts, int imageId) {
        this.extraParts = extraParts;
        this.extraParts.setImageId(imageId);
    }

    public void setMainBody(MainBody mainBody, int imageId) {
        this.mainBody = mainBody;
        this.mainBody.setImageId(imageId);
    }

    public void setPowerCore(PowerCore powerCore, int imageId) {
        this.powerCore = powerCore;
        this.powerCore.setImageId(imageId);
    }

    public void setBodyLocation(PointF point) {
        if (point == null) {
            conversion.setUpConversion();
            model = asteroidsGame.getInstance();
            aView = viewPort.getInstance();
            bodyLocation = new PointF(model.getCurrentLevel().getWidth()/2,
                    model.getCurrentLevel().getHeight()/2);
//            aView.recalcViewPort(bodyLocation);

        } else {
            bodyLocation = point;
        }
    }


    public Conversion getConversion() {
        return conversion;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public void setCurrentRotation(float currentRotation) {
        this.currentRotation = currentRotation;
    }

    public PointF getBodyLocation() {
        return bodyLocation;
    }

    public float radiansToDegrees(float degrees){
        return (float) (degrees* Math.PI/180);
    }

    public float getAngle(PointF target) {
        float angle = (float) Math.toDegrees(Math.atan2(
                target.y - conversion.convToVP(bodyLocation).y,
                target.x - conversion.convToVP(bodyLocation).x));

        if(angle < 0){
            angle += 360;
        }
        return angle;
    }


    public void drawShipDuringGame(){


        PointF movePoint = InputManager.movePoint;

        if (movePoint != null) { // calculate rotation
            //movePoint = conversion.convToVP(movePoint);
            float angle = (270 - getAngle(movePoint)) * -1;

            setCurrentRotation(angle);
        }
            drawMainBody();
            drawCannon();
            drawEngine();
            drawExtraParts();


    }

    public void drawMainBody(){

        // rotationAngle = (float) Math.toDegrees(rotationAngle);


        DrawingHelper.drawImage(mainBody.getImageId(),
                conversion.convToVP(bodyLocation).x,
                conversion.convToVP(bodyLocation).y, currentRotation,
                scaleFactor, scaleFactor, 255);

    }

    public void drawMainBodyduringShipBuilder(){
        model = asteroidsGame.getInstance();
        conversion.setUpConversion();
        bodyLocation = new PointF(DrawingHelper.getGameViewWidth()/2,
                DrawingHelper.getGameViewHeight()/2);

        DrawingHelper.drawImage(mainBody.getImageId(),
                conversion.convToVP(bodyLocation).x,
                conversion.convToVP(bodyLocation).y, currentRotation,
                scaleFactor, scaleFactor, 255);
    }


    public void drawCannon(){

        // part offset = (bodyattach - bodycenter) + (partcenter - partAttach)
        // part location = bodyLocation + partOffset

        // for part attachment
        float X = ((mainBody.getCannonAttachX() -
                mainBody.getImageWidth()/2)
                + ((cannon.getImageWidth()/2) -
                cannon.getAttachPointX())) * scaleFactor;

        float Y = ((mainBody.getCannonAttachY() -
                mainBody.getImageHeight()/2)
                + (cannon.getImageHeight()/2) -
                cannon.getAttachPointY()) * scaleFactor;

        // for laser
        float A = ((mainBody.getCannonAttachX() -
                mainBody.getImageWidth()/2)
                + ((cannon.getEmitX()) -
                cannon.getAttachPointX())) * scaleFactor;


        float B = ((mainBody.getCannonAttachY() -
                mainBody.getImageHeight()/2)
                + (cannon.getEmitY()) -
                cannon.getAttachPointY()) * scaleFactor;

        PointF pointF = new PointF(X,Y);
        pointF = GraphicsUtils.rotate(pointF,Math.toRadians(currentRotation));

        PointF emitLaserPointF = new PointF(A,B);
        emitLaserPointF = GraphicsUtils.rotate(emitLaserPointF,Math.toRadians(currentRotation));

        emitLaser = new PointF(bodyLocation.x + emitLaserPointF.x,
                    bodyLocation.y + emitLaserPointF.y);

        DrawingHelper.drawImage(cannon.getImageId(),
                conversion.convToVP(bodyLocation).x + pointF.x,
                conversion.convToVP(bodyLocation).y + pointF.y,
                currentRotation,scaleFactor,scaleFactor,255);

    }

    public void drawEngine(){

        float X = ((mainBody.getEngineAttachX() -
                mainBody.getImageWidth()/2)
                + ((engine.getImageWidth()/2) -
                engine.getAttachPointX())) * scaleFactor;

        float Y = ((mainBody.getEngineAttachY() -
                mainBody.getImageHeight()/2)
                + (engine.getImageHeight()/2) -
                engine.getAttachPointY()) * scaleFactor;

        PointF pointF = new PointF(X,Y);
        pointF = GraphicsUtils.rotate(pointF,Math.toRadians(currentRotation));

        DrawingHelper.drawImage(engine.getImageId(),
                conversion.convToVP(bodyLocation).x + pointF.x,
                conversion.convToVP(bodyLocation).y + pointF.y,
                currentRotation, scaleFactor, scaleFactor, 255);
    }

    public void drawExtraParts(){
        float X = ((mainBody.getExtraAttachX() -
                mainBody.getImageWidth()/2)
                + ((extraParts.getImageWidth()/2) -
                extraParts.getAttachPointX())) * scaleFactor;

        float Y = ((mainBody.getExtraAttachY() -
                mainBody.getImageHeight()/2)
                + (extraParts.getImageHeight()/2) -
                extraParts.getAttachPointY()) * scaleFactor;

        PointF pointF = new PointF(X,Y);
        pointF = GraphicsUtils.rotate(pointF,Math.toRadians(currentRotation));

        DrawingHelper.drawImage(extraParts.getImageId(),
                conversion.convToVP(bodyLocation).x + pointF.x,
                conversion.convToVP(bodyLocation).y + pointF.y,
                currentRotation,scaleFactor,scaleFactor,255);
    }

    public void createRandomShip(){
        // get first of everything in model
        model = asteroidsGame.getInstance();

        // main body
        int mainBodyImageid = cm.loadImage(model.getMainBodies().get(0).getImage());
        setMainBody(model.getMainBodies().get(0),mainBodyImageid);

        // engine
        int engineImageid = cm.loadImage(model.getEngines().get(0).getImage());
        setEngine(model.getEngines().get(0),engineImageid);

        // extra parts
        int extraPartsImageid = cm.loadImage(model.getExtraParts().get(0).getImage());
        setExtraParts(model.getExtraParts().get(0),extraPartsImageid);

        // cannon
        int cannonImageid = cm.loadImage(model.getCannons().get(0).getImage());
        setCannon(model.getCannons().get(0),cannonImageid);

        // powerCore
        int powerCoreImageid = cm.loadImage(model.getPowerCores().get(0).getImage());
        setPowerCore(model.getPowerCores().get(0),powerCoreImageid);

        setBodyLocation(null);

    }

    public void drawEverything(){


    }

}
