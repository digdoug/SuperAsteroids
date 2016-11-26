package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import java.util.ArrayList;

import edu.byu.cs.superasteroids.database.DAO.LevelDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Level;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/14/16.
 */
public class LevelDAOTest extends AndroidTestCase {


    private asteroidsGame model;
    private LevelDAO levelDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        levelDAO = new LevelDAO(DBhelper.getDbhelper());
        Level level = new Level();
        ArrayList<Level.LevelObject> levelObjects1 = new ArrayList<>();
        ArrayList<Level.LevelAsteroid> levelAsteroids1 = new ArrayList<>();

        Level level2 = new Level();
        ArrayList<Level.LevelObject> levelObjects2 = new ArrayList<>();
        ArrayList<Level.LevelAsteroid> levelAsteroids2 = new ArrayList<>();

        Level.LevelObject levelObject = level.instantiateLevelObject("1000,1000",1,1.5f,1);
        levelObjects1.add(levelObject);
        levelObject = level.instantiateLevelObject("2500,2500",2,1.5f,1);
        levelObjects1.add(levelObject);
        levelObject = level.instantiateLevelObject("2000,100",3,1.5f,1);
        levelObjects1.add(levelObject);
        levelObject = level.instantiateLevelObject("100,1000",5,1.5f,1);
        levelObjects1.add(levelObject);

        Level.LevelAsteroid levelAsteroid = level.instantiateLevelAsteroid(7,1,1);
        levelAsteroids1.add(levelAsteroid);
        levelAsteroid = level.instantiateLevelAsteroid(3,3,1);
        levelAsteroids1.add(levelAsteroid);
        levelAsteroid = level.instantiateLevelAsteroid(4,2,1);
        levelAsteroids1.add(levelAsteroid);

        // ----- TEST 1
        level = new Level(levelObjects1,levelAsteroids1,1,"Level 1","Destroy 1 Asteroid",3000,3000,"sounds/SpyHunter.ogg");

        levelDAO.insertAll(level);
        levelDAO.query();

        assertEquals(level.printLevel(), model.getLevels().get(0).printLevel()); // should only have one object


        // ----- TEST 2

        Level.LevelObject levelObject2 = level.instantiateLevelObject("1000,1000",1,1.5f,2);
        levelObjects2.add(levelObject2);
        levelObject2 = level.instantiateLevelObject("500,1000",2,1.5f,2);
        levelObjects2.add(levelObject2);
        levelObject2 = level.instantiateLevelObject("100,2600",9,1.5f,2);
        levelObjects2.add(levelObject2);


        Level.LevelAsteroid levelAsteroid2 = level.instantiateLevelAsteroid(5,3,2);
        levelAsteroids2.add(levelAsteroid2);
        levelAsteroid2 = level.instantiateLevelAsteroid(4,2,2);
        levelAsteroids2.add(levelAsteroid2);
        levelAsteroid2 = level.instantiateLevelAsteroid(8,1,2);
        levelAsteroids2.add(levelAsteroid2);





        level2 = new Level(levelObjects2,levelAsteroids2,2,"Level 2","Destroy 5 Asteroids",
                3000,3000,"sounds/SpyHunter.ogg");


        levelDAO.insertAll(level2);
        levelDAO.query();

        assertEquals(level2.printLevel(), model.getLevels().get(1).printLevel());
}}
