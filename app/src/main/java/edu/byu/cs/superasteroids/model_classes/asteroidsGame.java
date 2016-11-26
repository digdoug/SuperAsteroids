package edu.byu.cs.superasteroids.model_classes;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.database.DAO.DAOhelper;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.viewStuff.viewPort;

/**
 * Created by lp1 on 1/16/16.
 */
public class asteroidsGame {


    private static asteroidsGame instance;

    private asteroidsGame(){
        daoHelper = new DAOhelper(DBhelper.getDbhelper());
 //       daoHelper.onlyDoQueries();
    }

    public static asteroidsGame getInstance(){
        if (instance == null){
            instance = new asteroidsGame();
        }
        return instance;
    }

    // all arrays
    private ArrayList<BackGroundObject> backGroundObjects = new ArrayList<>();
    private ArrayList<AsteroidType> asteroids = new ArrayList<>();
    private ArrayList<Level> levels = new ArrayList<>();
    private ArrayList<MainBody> mainBodies = new ArrayList<>();
    private ArrayList<Cannon> cannons = new ArrayList<>();
    private ArrayList<ExtraParts> extraParts = new ArrayList<>();
    private ArrayList<Engine> engines = new ArrayList<>();
    private ArrayList<PowerCore> powerCores = new ArrayList<>();
    private theShip currentShip = new theShip();
    private int currentLevel = 0;
    private viewPort VP;
    private DAOhelper daoHelper;


    public void insertBObject(BackGroundObject bObj){
        backGroundObjects.add(bObj);
    }

    public void insertAsteroid(AsteroidType ast){asteroids.add(ast);}

    public void printAsteroids(){
        for (int i = 0; i < asteroids.size();i++){
            asteroids.get(i).printAsteroid();
        }
    }
    public void insertLevel(Level level){
        levels.add(level);
    }

    public void insertMainBody(MainBody mainBody){
        mainBodies.add(mainBody);
    }

    public void insertCannon(Cannon cannon){
        cannons.add(cannon);
    }

    public void insertExtraParts(ExtraParts eParts){
        extraParts.add(eParts);
    }

    public void insertEngine(Engine engine){
        engines.add(engine);
    }

    public void insertPowerCore(PowerCore powerCore){
        powerCores.add(powerCore);
    }

    public theShip getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(theShip currentShip) {
        this.currentShip = currentShip;
    }

    public ArrayList<BackGroundObject> getBackGroundObjects() {
        return backGroundObjects;
    }

    public ArrayList<AsteroidType> getAsteroids() {
        return asteroids;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public Level getCurrentLevel(){
        return levels.get(currentLevel);
    }

    public void incrementLevel(){
        currentLevel++;
    }

    public ArrayList<MainBody> getMainBodies() {
        return mainBodies;
    }

    public ArrayList<Cannon> getCannons() {
        return cannons;
    }

    public ArrayList<ExtraParts> getExtraParts() {
        return extraParts;
    }

    public ArrayList<Engine> getEngines() {
        return engines;
    }

    public ArrayList<PowerCore> getPowerCores() {
        return powerCores;
    }

    public void constructViewPort(){
        VP = viewPort.getInstance();
        //currentShip.getConversion().setUpConversion();
    }

    public viewPort getVP() {
        return VP;
    }

    public void populate(){
        daoHelper = new DAOhelper(DBhelper.getDbhelper());
        daoHelper.onlyDoQueries();
    }
}
