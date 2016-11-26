package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.DAO.AsteroidTypeDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.AsteroidType;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/11/16.
 */
public class AsteroidTypeDAOTest extends AndroidTestCase {

    private asteroidsGame model;
    private AsteroidTypeDAO asteroidTypeDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        asteroidTypeDAO = new AsteroidTypeDAO(DBhelper.getDbhelper());

        // ----- TEST 1
        AsteroidType asteroidType = new AsteroidType("regular","images/asteroids/asteroid.png",
                169,153,"regular");

        asteroidTypeDAO.insert(asteroidType);
        asteroidTypeDAO.query();

        assertEquals(asteroidType.printAsteroid(), model.getAsteroids().get(0).printAsteroid()); // should only have one object


        // ----- TEST 2
        AsteroidType asteroidType2 = new AsteroidType("growing","images/asteroids/blueasteroid.png",
                161,178,"growing");

        asteroidTypeDAO.insert(asteroidType2);
        asteroidTypeDAO.query();

        assertEquals(asteroidType2.printAsteroid(), model.getAsteroids().get(1).printAsteroid());

        // ---- TEST 3
        AsteroidType asteroidType3 = new AsteroidType("octeroid","images/asteroids/asteroid.png",
                169,153,"octeroid");

        asteroidTypeDAO.insert(asteroidType3);
        asteroidTypeDAO.query();

        assertEquals(asteroidType3.printAsteroid(), model.getAsteroids().get(2).printAsteroid());
    }


}
