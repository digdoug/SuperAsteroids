package edu.byu.cs.superasteroids.database.DAO;

import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.*;

/**
 * Created by lp1 on 1/31/16.
 */
public class DAOhelper {

    private DBhelper db;
    private asteroidsGame aG; //= asteroidsGame.getInstance();
    private AsteroidTypeDAO asteroidTypeDAO;
    private BackGroundDAO bgDAO;
    private LevelDAO levelDAO;
    private MainBodyDAO mainBodyDAO;
    private CannonDAO cannonDAO;
    private ExtraPartsDAO extraPartsDAO;
    private EngineDAO engineDAO;
    private PowerCoreDAO powerCoreDAO;




    public DAOhelper (DBhelper db){
        this.db = db;
    }

    public void onlyDoQueries(){
        asteroidTypeDAO = new AsteroidTypeDAO(db);
        asteroidTypeDAO.query();
        bgDAO = new BackGroundDAO(db);
        bgDAO.query();
        levelDAO = new LevelDAO(db);
        levelDAO.query();
        mainBodyDAO = new MainBodyDAO(db);
        mainBodyDAO.query();
        cannonDAO = new CannonDAO(db);
        cannonDAO.query();
        extraPartsDAO = new ExtraPartsDAO(db);
        extraPartsDAO.query();
        engineDAO = new EngineDAO(db);
        engineDAO.query();
        powerCoreDAO = new PowerCoreDAO(db);
        powerCoreDAO.query();
    }

    public void doAllQueries(JSONObject jsonObject){
        aG = asteroidsGame.getInstance();
        doAsteroid(jsonObject);
        doBackGroundObject(jsonObject);
        doMainBody(jsonObject);
        doCannon(jsonObject);
        doExtraParts(jsonObject);
        doEngine(jsonObject);
        doPowerCore(jsonObject);
        doLevel(jsonObject);
    }



    public void doBackGroundObject(JSONObject j){


            bgDAO = new BackGroundDAO(db);


            try {
                JSONObject name = j.getJSONObject("asteroidsGame");
                JSONArray array = name.getJSONArray("objects");
                System.out.println(array.getString(0));
                for (int i = 0; i < array.length();i++) {
                    //JSONObject astName = array.getJSONObject(i);
                    String image = array.getString(i);

                    BackGroundObject aBOject = new BackGroundObject(image);


                    bgDAO.insert(aBOject);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                //  return false;
            }

            bgDAO.query();



    }

    public void doAsteroid(JSONObject j){
        asteroidTypeDAO = new AsteroidTypeDAO(db);


            try {
                JSONObject name = j.getJSONObject("asteroidsGame");
                JSONArray array = name.getJSONArray("asteroids");

                for (int i = 0; i < array.length();i++){
                    JSONObject astName = array.getJSONObject(i);
                    String asteroidName = astName.getString("name");
                    String image = astName.getString("image");
                    int imageWidth = astName.getInt("imageWidth");
                    int imageHeight = astName.getInt("imageHeight");
                    String type = astName.getString("type");

                    AsteroidType anAsteroid = new AsteroidType(asteroidName,
                            image, imageWidth, imageHeight, type);

                    // input into asteroidsGame
                    //asteroidsGame.insertAsteroid(anAsteroid);

                    // input into data base
                    asteroidTypeDAO.insert(anAsteroid);
                }

                //  return true;


            } catch (JSONException e) {
                e.printStackTrace();
                //  return false;
            }

            asteroidTypeDAO.query();

        }

    public void doLevel(JSONObject j){
        levelDAO = new LevelDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray levelsArray = name.getJSONArray("levels");

            for (int i = 0; i < levelsArray.length();i++){
                JSONObject astName = levelsArray.getJSONObject(i);
                int number = astName.getInt("number");
                String title = astName.getString("title");
                String hint = astName.getString("hint");
                int width = astName.getInt("width");
                int height = astName.getInt("height");
                String music = astName.getString("music");
                ArrayList<Level.LevelObject> levelObjects = new ArrayList<Level.LevelObject>();
                ArrayList<Level.LevelAsteroid> levelAsteroids = new ArrayList<Level.LevelAsteroid>();

                Level aLevel = new Level();

                JSONArray objectsArray = astName.getJSONArray("levelObjects");
                for (int k = 0; k< objectsArray.length();k++){
                    JSONObject object = objectsArray.getJSONObject(k);
                    String position = object.getString("position");
                    int objectId = object.getInt("objectId");
                    int scale = object.getInt("scale");

                    // i'm not sure if this is legal in Java
                    Level.LevelObject levelObject = aLevel.instantiateLevelObject(position,objectId,scale,number);
                    levelObjects.add(levelObject);
                }

                JSONArray asteroidsArray = astName.getJSONArray("levelAsteroids");
                for (int l = 0; l < asteroidsArray.length();l++){
                    JSONObject object = asteroidsArray.getJSONObject(l);
                    int num = object.getInt("number");
                    int asteroidId = object.getInt("asteroidId");

                    Level.LevelAsteroid levelAsteroid = aLevel.instantiateLevelAsteroid(num,asteroidId,number);
                    levelAsteroids.add(levelAsteroid);
                }

                aLevel = new Level(levelObjects,levelAsteroids,number,title,hint,
                        width,height,music);

                levelDAO.insertAll(aLevel);
            }



        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        levelDAO.query();

    }

    public void doMainBody(JSONObject j){
        mainBodyDAO = new MainBodyDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray array = name.getJSONArray("mainBodies");

            for (int i = 0; i < array.length();i++){
                JSONObject astName = array.getJSONObject(i);
                String cannonAttach = astName.getString("cannonAttach");
                String engineAttach = astName.getString("engineAttach");
                String extraAttach = astName.getString("extraAttach");
                String image = astName.getString("image");
                int imageWidth = astName.getInt("imageWidth");
                int imageHeight = astName.getInt("imageHeight");

                MainBody mainBody = new MainBody(cannonAttach,engineAttach,extraAttach,
                        image,imageWidth,imageHeight);


                // input into data base
                mainBodyDAO.insert(mainBody);
            }

            //  return true;


        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        mainBodyDAO.query();

    }


    public void doCannon(JSONObject j){
        cannonDAO = new CannonDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray array = name.getJSONArray("cannons");

            for (int i = 0; i < array.length();i++){
                JSONObject astName = array.getJSONObject(i);
                String attachPoint = astName.getString("attachPoint");
                String emitPoint = astName.getString("emitPoint");
                String image = astName.getString("image");
                int imageWidth = astName.getInt("imageWidth");
                int imageHeight = astName.getInt("imageHeight");
                String attackImage = astName.getString("attackImage");
                int attackImageWidth = astName.getInt("attackImageWidth");
                int attackImageHeight = astName.getInt("attackImageHeight");
                String attackSound = astName.getString("attackSound");
                int damage = astName.getInt("damage");

                Cannon cannon = new Cannon(attachPoint,emitPoint,image,imageWidth,imageHeight,attackImage,
                        attackImageWidth,attackImageHeight,attackSound,damage);


                // input into data base
                cannonDAO.insert(cannon);
            }

            //  return true;


        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        cannonDAO.query();

    }

    public void doExtraParts(JSONObject j){
        extraPartsDAO = new ExtraPartsDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray array = name.getJSONArray("extraParts");

            for (int i = 0; i < array.length();i++){
                JSONObject astName = array.getJSONObject(i);
                String attachPoint = astName.getString("attachPoint");
                String image = astName.getString("image");
                int imageWidth = astName.getInt("imageWidth");
                int imageHeight = astName.getInt("imageHeight");

                ExtraParts extraParts = new ExtraParts(attachPoint,image,imageWidth,imageHeight);


                // input into data base
                extraPartsDAO.insert(extraParts);
            }

            //  return true;


        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        extraPartsDAO.query();

    }

    public void doEngine(JSONObject j){
        engineDAO = new EngineDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray array = name.getJSONArray("engines");

            for (int i = 0; i < array.length();i++){
                JSONObject astName = array.getJSONObject(i);
                int baseSpeed = astName.getInt("baseSpeed");
                int baseTurnRate = astName.getInt("baseTurnRate");
                String attachPoint = astName.getString("attachPoint");
                String image = astName.getString("image");
                int imageWidth = astName.getInt("imageWidth");
                int imageHeight = astName.getInt("imageHeight");

                Engine engine = new Engine(baseSpeed,baseTurnRate,attachPoint,image,imageWidth,imageHeight);


                // input into data base
                engineDAO.insert(engine);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        engineDAO.query();

    }


    public void doPowerCore(JSONObject j){
        powerCoreDAO = new PowerCoreDAO(db);


        try {
            JSONObject name = j.getJSONObject("asteroidsGame");
            JSONArray array = name.getJSONArray("powerCores");

            for (int i = 0; i < array.length();i++){
                JSONObject astName = array.getJSONObject(i);
                int cannonBoost = astName.getInt("cannonBoost");
                int engineBoost = astName.getInt("engineBoost");
                String image = astName.getString("image");

                PowerCore powerCore = new PowerCore(cannonBoost,engineBoost,image);


                // input into data base
                powerCoreDAO.insert(powerCore);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            //  return false;
        }

        powerCoreDAO.query();

    }

}
