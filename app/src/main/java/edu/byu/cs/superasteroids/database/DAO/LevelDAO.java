package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Level;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class LevelDAO {


    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public LevelDAO(DBhelper sqldb){this.sqldb = sqldb;}

//    public AsteroidTypeDAO(){};

    public void insertAll(Level level){
            insertLevel(level);
            insertLevelObject(level.getlObjects());
            insertLevelAsteroids(level.getlAsteroids());
    }
    // insert
    private void insertLevel(Level level){


        ContentValues values = new ContentValues();

        values.put(Contract.Level.COLUMN_NAME_LEVEL_ID, level.getlNumber());
        values.put(Contract.Level.COLUMN_NAME_TITLE, level.getTitle());
        values.put(Contract.Level.COLUMN_NAME_HINT, level.getHint());
        values.put(Contract.Level.COLUMN_NAME_WIDTH, level.getWidth());
        values.put(Contract.Level.COLUMN_NAME_HEIGHT, level.getHeight());
        values.put(Contract.Level.COLUMN_NAME_MUSIC, level.getMusic());



        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.Level.TABLE_NAME,
                Contract.Level.COLUMN_NAME_LEVEL_ID, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
        System.out.println(newRowId);
    }

    private void insertLevelObject(ArrayList<Level.LevelObject> levelObjects){

        for (int i = 0; i < levelObjects.size();i++){

            ContentValues values = new ContentValues();

            values.put(Contract.LevelObject.COLUMN_NAME_OBJECT_ID, levelObjects.get(i).getObjectId());
            values.put(Contract.LevelObject.COLUMN_NAME_POSITION, levelObjects.get(i).getPosition());
            values.put(Contract.LevelObject.COLUMN_NAME_SCALE, levelObjects.get(i).getScale());
            values.put(Contract.LevelObject.COLUMN_NAME_LEVEL_MATCH_ID, levelObjects.get(i).getLevelMatch());



            long newRowId;
            newRowId = sqldb.getWritableDatabase().insertOrThrow(
                    Contract.LevelObject.TABLE_NAME,
                    Contract.LevelObject.COLUMN_NAME_OBJECT_ID, // THIS IS THE COLUMN THAT WILL
                    values
            );

        }
    }

    private void insertLevelAsteroids(ArrayList<Level.LevelAsteroid> levelAsteroids){

        for (int i = 0; i < levelAsteroids.size();i++){


            ContentValues values = new ContentValues();

            values.put(Contract.LevelAsteroid.COLUMN_NAME_ASTEROID_ID, levelAsteroids.get(i).getAsteroidId());
            values.put(Contract.LevelAsteroid.COLUMN_NAME_NUMBER, levelAsteroids.get(i).getNumber());
            values.put(Contract.LevelAsteroid.COLUMN_NAME_LEVEL_MATCH_ID, levelAsteroids.get(i).getLevelMatchid());


            long newRowId;
            newRowId = sqldb.getWritableDatabase().insertOrThrow(
                    Contract.LevelAsteroid.TABLE_NAME,
                    Contract.LevelAsteroid.COLUMN_NAME_ASTEROID_ID, // THIS IS THE COLUMN THAT WILL
                    // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                    values
            );

        }

    }


    // query --> this is where
    public void query (){

        /// ========= LEVEL
        String[] projection = {
                Contract.Level.COLUMN_NAME_LEVEL_ID,
                Contract.Level.COLUMN_NAME_TITLE,
                Contract.Level.COLUMN_NAME_HINT,
                Contract.Level.COLUMN_NAME_WIDTH,
                Contract.Level.COLUMN_NAME_HEIGHT,
                Contract.Level.COLUMN_NAME_MUSIC,
        };

        Cursor c = sqldb.getReadableDatabase().query(
                Contract.Level.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        /// ======= LEVEL OBJECTS
        String[] projection1 = {
                Contract.LevelObject.COLUMN_NAME_OBJECT_ID,
                Contract.LevelObject.COLUMN_NAME_POSITION,
                Contract.LevelObject.COLUMN_NAME_SCALE,
                Contract.LevelObject.COLUMN_NAME_LEVEL_MATCH_ID,

        };



        ////// ========= LEVEL ASTEROIDS
        String[] projection2 = {
                Contract.LevelAsteroid.COLUMN_NAME_ASTEROID_ID,
                Contract.LevelAsteroid.COLUMN_NAME_NUMBER,
                Contract.LevelAsteroid.COLUMN_NAME_LEVEL_MATCH_ID,

        };




        aG = asteroidsGame.getInstance();
        Level level = new Level();

        while (c.moveToNext()){

            // CURSOR 1 - LEVEL 0BJECTS
            Cursor c1 = sqldb.getReadableDatabase().query(
                    Contract.LevelObject.TABLE_NAME,
                    projection1,
                    null, // selection I don't think this needs to be specified because we're setting up proejction
                    null, // selectionArgs
                    null,
                    null,
                    null
            );

            // CURSOR 2 - LEVEL ASTEROIDS
            Cursor c2 = sqldb.getReadableDatabase().query(
                    Contract.LevelAsteroid.TABLE_NAME,
                    projection2,
                    null, // selection I don't think this needs to be specified because we're setting up proejction
                    null, // selectionArgs
                    null,
                    null,
                    null
            );

            int lNumber = c.getInt(0);
            String title = c.getString(1);
            String hint = c.getString(2);
            int width = c.getInt(3);
            int height = c.getInt(4);
            String music = c.getString(5);
            ArrayList<Level.LevelObject> levelObjects = new ArrayList<Level.LevelObject>();
            ArrayList<Level.LevelAsteroid> levelAsteroids = new ArrayList<Level.LevelAsteroid>();

            while(c1.moveToNext()){
                int levelMatchId = c1.getInt(3);
                if (levelMatchId == lNumber) {// go ahead and make the object
                    String position = c1.getString(1);
                    int objectId = c1.getInt(0);
                    float scale = c1.getFloat(2);
                    Level.LevelObject levelObject = level.instantiateLevelObject(position,
                            objectId,scale,levelMatchId);
                    levelObjects.add(levelObject);
                }
            }

            while(c2.moveToNext()){
                int levelMatchId = c2.getInt(2);
                if (levelMatchId == lNumber) {// go ahead and make the object
                    int number = c2.getInt(1);
                    int asteroidId = c2.getInt(0);
                    Level.LevelAsteroid levelAsteroid = level.instantiateLevelAsteroid(number, asteroidId,
                            levelMatchId);
                    levelAsteroids.add(levelAsteroid);
                }
            }

            level = new Level(levelObjects,levelAsteroids,lNumber,title,hint,width,height,music);
            aG.insertLevel(level);
        }

        c.close();

    }


}
