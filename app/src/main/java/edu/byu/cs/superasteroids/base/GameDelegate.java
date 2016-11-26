package edu.byu.cs.superasteroids.base;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.database.DAO.DAOhelper;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;
import edu.byu.cs.superasteroids.viewStuff.viewPort;

/**
 * Created by lp1 on 3/3/16.
 */
public class GameDelegate implements IGameDelegate {

    private asteroidsGame model;
    public GameDelegate(){
        model = asteroidsGame.getInstance();
    }
    private int spaceIMGID;
    private PointF world; //model.getCurrentLevel().getWidth();
    private ArrayList<Integer> backgroundIds = new ArrayList<>();
    private ArrayList<Integer> asteroidIds = new ArrayList<>();
    private float spaceScale;
    private viewPort aViewPort;
    private Conversion conv;
    private ArrayList<PointF> randomPosition = new ArrayList<>();
    private ArrayList<Float> randomAngles = new ArrayList<>();
    private ArrayList<Integer> randomVelocities = new ArrayList<>();
    private ArrayList<PointF> widthHeights = new ArrayList<>();
    private ArrayList<PointF> laserLocations = new ArrayList<>();
    private ArrayList<Float> laserAngles = new ArrayList<>();
    private ArrayList<RectF> lasersRects = new ArrayList<>();
    private ArrayList<RectF> asteroidRects = new ArrayList<>();
    private ArrayList<RectF> asteroidRectsCopy = new ArrayList<>();
    private ArrayList<Integer> timesHit = new ArrayList<>();
    private ArrayList<Float> asteroidScale = new ArrayList<>();
    private ArrayList<String> asteroidType = new ArrayList<>();
    private ArrayList<PointF> originalWidthHeight = new ArrayList<>();
    private boolean safeTime = false;
    private double timer = 0;
    private int timer2 = 0;
    private boolean didWin = true;

    @Override
    public void update(double elapsedTime) {

        if (asteroidIds.size() > 0) {


            // move objects
            if (InputManager.movePoint != null) {
                moveShip(elapsedTime);
            }
            moveAsteroid(elapsedTime);
            moveLaser(elapsedTime);
            intersectLasersWithAsteroids();
            intersectAsteroidWithAsteroid(elapsedTime);
        }
        checkIfWon();
        System.out.println("SIZE OF SCREEN   " + DrawingHelper.getGameViewWidth());
        System.out.println(DrawingHelper.getGameViewHeight());

    }

    @Override
    public void loadContent(ContentManager content) {

        //conv = new Conversion();
        //daOhelper.onlyDoQueries();
        model.constructViewPort();
        aViewPort = model.getVP();
        conv = new Conversion();
        conv.setUpConversion();
        model.getCurrentShip().setBodyLocation(null);
        aViewPort.initRect();
        world = new PointF(model.getCurrentLevel().getWidth(),model.getCurrentLevel().getHeight());
        spaceIMGID = content.loadImage("images/space.bmp");
        for (int i = 0; i < model.getBackGroundObjects().size();i++){
            backgroundIds.add(content.loadImage(
                    model.getBackGroundObjects().get(i).getImage()));
        }
        // level sound
        try {
            model.getCurrentLevel().setMusicId(
                    content.loadSound(model.getCurrentLevel().getMusic()));
            content.playLoop(content.loadLoopSound((model.getCurrentLevel().getMusic())));
            System.out.println("THIS IS THE MUSIC   " + model.getCurrentLevel().getMusic());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //content.playLoop(model.getCurrentLevel().getMusicId());

        // laser
        model.getCurrentShip().getCannon().setAttackImageId(
                content.loadImage(model.getCurrentShip().getCannon().getAttackImage()));
        try {
            model.getCurrentShip().getCannon().setAttackSoundId(
                    content.loadSound(model.getCurrentShip().getCannon().getAttackSound()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // calc viewPort at least once
        // ----- to enable from the beginning
        //aViewPort.recalcViewPort(model.getCurrentShip().getBodyLocation());


        // canvas setting
        // asteroids
        /*for (int i = 0; i < model.getAsteroids().size();i++){

            asteroidIds.add(content.loadImage(
                    model.getAsteroids().get(i).getImage()));


        }*/
        for (int i = 0; i< model.getCurrentLevel().getlAsteroids().size(); i++) {

            for (int j = 0; j < model.getCurrentLevel().getlAsteroids().get(i).getNumber(); j++) {

                // asteroid ids
                asteroidIds.add(content.loadImage(
                        model.getAsteroids().get(
                                model.getCurrentLevel().getlAsteroids().get(i).getAsteroidId()-1).getImage()
                ));

                timesHit.add(0); // all should be zero in the beginning
                asteroidScale.add((float) 1);
                asteroidType.add(model.getAsteroids().get(
                        model.getCurrentLevel().getlAsteroids().get(i).getAsteroidId()-1).getType());
                //
                float randomX = (float) (Math.random() * model.getCurrentLevel().getWidth() + 1);
                float randomY = (float) (Math.random() * model.getCurrentLevel().getHeight() + 1);
                PointF randomLocation = new PointF(randomX, randomY);
                randomPosition.add(randomLocation);

                // make the same amount of random angles
                float randomAngle = (float) (Math.random() * 360 + 1);
                randomAngles.add(randomAngle);

                // random speeds
                int randomSpeed = (int) (Math.random() * 700 + 1);
                randomVelocities.add(randomSpeed);

                // width and heights
                PointF widthHeight = new PointF(
                        model.getAsteroids().get(model.getCurrentLevel().getlAsteroids().get(i).getAsteroidId()-1).getImageWidth(),
                        model.getAsteroids().get(model.getCurrentLevel().getlAsteroids().get(i).getAsteroidId()-1).getImageHeight());

                widthHeights.add(widthHeight);
                originalWidthHeight.add(widthHeight);
            }
        }


    }

    @Override
    public void unloadContent(ContentManager content) {

    }

    @Override
    public void draw() {

        if (asteroidIds.size() > 0) {


            drawLevelImage();
            drawBackgroundObjects();
            drawAsteroids();
            model.getCurrentShip().drawShipDuringGame();
            fireProjectiles();
            if (safeTime) {
                drawSafeZone();
            }
        }
    }

    public void drawLevelImage(){

        spaceScale = (float) model.getCurrentLevel().getWidth()/2048;

        DrawingHelper.drawImage(spaceIMGID, model.getCurrentLevel().getWidth() / 2,
                model.getCurrentLevel().getHeight() / 2, 0, spaceScale, spaceScale, 255);

    }

    public void drawBackgroundObjects() {



            for (int i = 0; i < model.getCurrentLevel().getlObjects().size(); i++) {
                int backGroundIndex = model.getCurrentLevel().getlObjects().get(i).getObjectId() - 1;
                PointF backGroundLoc = new PointF(model.getCurrentLevel().getlObjects().get(i).getPosX(),
                        model.getCurrentLevel().getlObjects().get(i).getPosY());

                backGroundLoc = conv.convToVP(backGroundLoc);

                DrawingHelper.drawImage(backgroundIds.get(backGroundIndex), backGroundLoc.x,
                        backGroundLoc.y, 0,
                        model.getCurrentLevel().getlObjects().get(i).getScale(),
                        model.getCurrentLevel().getlObjects().get(i).getScale(), 255);

            }


    }

    public void drawAsteroids() {

        //for (int i = 0; i < model.getCurrentLevel().getlAsteroids().size();i++){
        //int asteroidIndex =  model.getCurrentLevel().getlAsteroids().get(i).getAsteroidId() -1;
            //for (int j = 0; j < model.getCurrentLevel().getlAsteroids().get(i).getNumber(); j++){



            for (int i = 0; i < asteroidIds.size(); i++) {
                if (asteroidIds.get(i) != null) { // only draw exisiting asteroids
                    //float scaleFactor =1;
                    //if (timesHit.get(i) == 1){
                    //    scaleFactor = scaleFactor/2;
                    //}
                    //if (!asteroidType.get(i).equals("growing")){
                    if (asteroidType.get(i).equals("growing") && asteroidScale.get(i) < 4
                            && timesHit.get(i) == 1) { // set cap
                        asteroidScale.set(i, (float) (asteroidScale.get(i) + .01));
                        PointF newWidthHeights = new PointF(originalWidthHeight.get(i).x * asteroidScale.get(i),
                                originalWidthHeight.get(i).y * asteroidScale.get(i));
                        widthHeights.set(i, newWidthHeights);
                    }

                    DrawingHelper.drawImage(asteroidIds.get(i),
                            conv.convToVP(randomPosition.get(i)).x,
                            conv.convToVP(randomPosition.get(i)).y,
                            0, asteroidScale.get(i), asteroidScale.get(i), 255);

                }
            }

    }



    public void moveShip(double elapsedTime){
        // find the ship
        GraphicsUtils.MoveObjectResult objectResult = new GraphicsUtils.MoveObjectResult();


        objectResult = GraphicsUtils.moveObject(model.getCurrentShip().getBodyLocation(),
                        aViewPort.getView(),
                        model.getCurrentShip().getEngine().getBaseSpeed(),
                        Math.toRadians(model.getCurrentShip().getCurrentRotation() - 90),
                        elapsedTime);


        model.getCurrentShip().setBodyLocation(objectResult.getNewObjPosition());
        // recalculate view Port here
        aViewPort.recalcViewPort(objectResult.getNewObjPosition());

    }

    public void moveAsteroid(double elapsedTime){
        for (int i = 0; i < randomPosition.size();i++){
            if (randomPosition.get(i) != null) { // only move exisiting asteroids



                // rect left, top, right, bottom
                RectF asteroidPosition = new RectF(randomPosition.get(i).x - widthHeights.get(i).x / 2,
                        randomPosition.get(i).y - widthHeights.get(i).y / 2,
                        randomPosition.get(i).x + widthHeights.get(i).x / 2,
                        randomPosition.get(i).y + widthHeights.get(i).y / 2);

                if (asteroidRects.size() < i + 1) { // then add it
                    asteroidRects.add(asteroidPosition);
                } else { // replace it
                    asteroidRects.set(i, asteroidPosition);
                }
                float newAngle = (float) GraphicsUtils.ricochetObject(randomPosition.get(i), asteroidRects.get(i),
                        Math.toRadians(randomAngles.get(i)), model.getCurrentLevel().getWidth(),
                        model.getCurrentLevel().getHeight()).getNewAngleRadians();



                /*if (asteroidScale.get(i) != 1
                        && asteroidType.get(i).equals("growing")){
                    PointF newWidthHeights = new PointF(widthHeights.get(i).x * asteroidScale.get(i),
                            widthHeights.get(i).y * asteroidScale.get(i));
                    widthHeights.set(i,newWidthHeights);
                }*/

                if (randomAngles.get(i) != (float) Math.toDegrees(newAngle)) {
                    randomAngles.set(i, (float) Math.toDegrees(newAngle));
                }


                randomPosition.set(i, GraphicsUtils.moveObject(
                        randomPosition.get(i),
                        asteroidRects.get(i),
                        randomVelocities.get(i),
                        Math.toRadians(randomAngles.get(i)),
                        elapsedTime).getNewObjPosition());
                //System.out.println("")
            }

        }
    }

    public void moveLaser(double elapsedTime){
        for (int i = 0; i < laserLocations.size();i++) {
            if (laserLocations.get(i) != null) {


                // rect: left, top, right, bottom
                RectF laserPosition = new RectF(laserLocations.get(i).x - model.getCurrentShip().getCannon().getAttackImageWidth() / 2,
                        laserLocations.get(i).y - model.getCurrentShip().getCannon().getAttackImageHeight() / 2,
                        laserLocations.get(i).x + model.getCurrentShip().getCannon().getAttackImageWidth() / 2,
                        laserLocations.get(i).y + model.getCurrentShip().getCannon().getAttackImageHeight() / 2);

                if (lasersRects.size() < i + 1) { // then add it
                    lasersRects.add(laserPosition);
                } else { // replace it
                    lasersRects.set(i, laserPosition);
                }

                laserLocations.set(i, GraphicsUtils.moveObject(
                        laserLocations.get(i),
                        laserPosition,
                        1500,
                        Math.toRadians(laserAngles.get(i) - 90),
                        elapsedTime).getNewObjPosition());
            }
        }
    }


    public void fireProjectiles(){
        if (InputManager.firePressed){
            laserLocations.add(model.getCurrentShip().getEmitLaser());
            laserAngles.add(model.getCurrentShip().getCurrentRotation());
            ContentManager.getInstance().playSound(model.getCurrentShip().getCannon().getAttackSoundId(), 1, 1);

        }

        for (int i = 0; i < laserLocations.size();i++){
            if (laserLocations.get(i) != null) {


                DrawingHelper.drawImage(model.getCurrentShip().getCannon().getAttackImageId(),
                        conv.convToVP(laserLocations.get(i)).x,
                        conv.convToVP(laserLocations.get(i)).y,
                        laserAngles.get(i),
                        model.getCurrentShip().getScaleFactor(),
                        model.getCurrentShip().getScaleFactor(), 255);


            }

        }
    }
    public void explodeRegularAsteroids(int i){
        // i is asteroidRects current index
        // j is laserRects current index

        for (int a = 0; a < 2;a++){
            asteroidIds.add(asteroidIds.get(i));
            randomPosition.add(randomPosition.get(i));

            // finish making the rest of the asteroid
            float randomAngle = (float) (Math.random() * 360 + 1);
            randomAngles.add(randomAngle);
            randomVelocities.add(randomVelocities.get(i));
            asteroidRectsCopy.add(asteroidRects.get(i));
            timesHit.add(1);
            asteroidType.add("regular");
            asteroidScale.add(asteroidScale.get(i)/2);
            PointF newWidthHeights = new PointF(widthHeights.get(i).x * asteroidScale.get(i)/2,
                    widthHeights.get(i).y * asteroidScale.get(i)/2);
            widthHeights.add(newWidthHeights);
            originalWidthHeight.add(originalWidthHeight.get(i));

        }
        //deleteAsteroidandLaser(i,j);

    }

    public void explodeOcteroidAsteroids(int i){
        // i is asteroidRects current index
        // j is laserRects current index
        for (int a = 0; a < 8;a++){
            asteroidIds.add(asteroidIds.get(i));
            randomPosition.add(randomPosition.get(i));

            // finish making the rest of the asteroid
            float randomAngle = (float) (Math.random() * 360 + 1);
            randomAngles.add(randomAngle);
            randomVelocities.add(randomVelocities.get(i));
            asteroidRectsCopy.add(asteroidRects.get(i));
            timesHit.add(1);
            asteroidType.add("octeroid");
            asteroidScale.add(asteroidScale.get(i)/2);
            PointF newWidthHeights = new PointF(widthHeights.get(i).x * asteroidScale.get(i)/2,
                    widthHeights.get(i).y * asteroidScale.get(i)/2);
            widthHeights.add(newWidthHeights);
            originalWidthHeight.add(originalWidthHeight.get(i));
        }


    }

    public void explodeGrowingAsteroids(int i){
        // i is asteroidRects current index
        // j is laserRects current index
        for (int a = 0; a < 2;a++){
            asteroidIds.add(asteroidIds.get(i));
            randomPosition.add(randomPosition.get(i));

            // finish making the rest of the asteroid
            float randomAngle = (float) (Math.random() * 360 + 1);
            randomAngles.add(randomAngle);
            randomVelocities.add(randomVelocities.get(i));
            asteroidRectsCopy.add(asteroidRects.get(i));
            timesHit.add(1);
            asteroidType.add("growing");
            asteroidScale.add(asteroidScale.get(i)/2);
            PointF newWidthHeights = new PointF(widthHeights.get(i).x * asteroidScale.get(i)/2,
                    widthHeights.get(i).y * asteroidScale.get(i)/2);
            widthHeights.add(newWidthHeights);
            originalWidthHeight.add(originalWidthHeight.get(i));
        }
    }

    public void intersectLasersWithAsteroids(){

            asteroidRectsCopy = asteroidRects;


            for (int i = 0; i < asteroidRects.size(); i++) {
                for (int j = 0; j < lasersRects.size(); j++) {
                    if (asteroidIds.get(i) != null && lasersRects.get(j) != null) {
                        // make sure they both exist


                        if (asteroidRects.get(i).intersect(lasersRects.get(j))) {
                            // make 2 new asteroids of that exact same type
                        if (timesHit.get(i) == 1){
                            deleteAsteroidandLaser(i,j);
                        } else{
                            if (asteroidType.get(i).equals("regular")){
                                explodeRegularAsteroids(i);
                            } else if (asteroidType.get(i).equals("octeroid")){
                            explodeOcteroidAsteroids(i);
                            } else if (asteroidType.get(i).equals("growing")){
                            explodeGrowingAsteroids(i);
                            }
                            deleteAsteroidandLaser(i,j);
                        }
                        }
                    }
                }
            } // last for loop
            asteroidRects = asteroidRectsCopy;
        }

    public void intersectAsteroidWithAsteroid(double elapsedTime){

        for (int i = 0; i < asteroidRects.size();i++){
            if (asteroidIds.get(i)!= null){
                if (asteroidRects.get(i).intersect(aViewPort.getView())){
                    safeTime = true;
                    timer = 0;
                }
            }
        }
        if (timer > 105){
            timer = 0;
            safeTime = false;
        }

        timer++;
    }

    public void drawSafeZone(){

        DrawingHelper.drawFilledCircle( // draw some red circle
                conv.convToVP(model.getCurrentShip().getBodyLocation()), // location
                aViewPort.getBiggestinRect(), // radius
                Color.GREEN,
                100);
    }

    public void drawMiniMap(){

    }

    public void checkIfWon(){

        didWin = true;
        for (int i = 0; i < asteroidIds.size(); i++){
            if (asteroidIds.get(i) != null){
                didWin = false;
            }
        }



        if (didWin) { // clear everything
            backgroundIds.clear();
            asteroidIds.clear();
            randomPosition.clear();
            randomAngles.clear();
            randomVelocities.clear();
            widthHeights.clear();
            laserLocations.clear();
            laserAngles.clear();
            lasersRects.clear();
            asteroidRects.clear();
            asteroidRectsCopy.clear();
            timesHit.clear();
            asteroidScale.clear();
            asteroidType.clear();
            originalWidthHeight.clear();

            timer2++;

            // display message
            System.out.println("DRAWING CENTERED TEXT");
            DrawingHelper.drawCenteredText(
                    model.getCurrentLevel().getTitle(), 50,Color.BLUE);

            if(timer2 > 95){
                timer2 = 0;
                model.incrementLevel();
                loadContent(ContentManager.getInstance());
                didWin = false;
            }
        }

    }


    public void deleteAsteroidandLaser(int i, int j){
        // delete that asteroid & laser
        asteroidIds.set(i, null);
        randomPosition.set(i, null);
        laserLocations.set(j,null);
        lasersRects.set(j,null);
    }

    // collisions


    // 2048
}
