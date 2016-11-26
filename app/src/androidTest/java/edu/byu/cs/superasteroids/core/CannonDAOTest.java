package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DAO.CannonDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Cannon;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/14/16.
 */
public class CannonDAOTest extends AndroidTestCase{


    private asteroidsGame model;
    private CannonDAO cannonDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        cannonDAO = new CannonDAO(DBhelper.getDbhelper());

        // ----- TEST 1
        Cannon cannon = new Cannon("14,240","104,36","images/parts/cannon1.png",
                160,360,"images/parts/laser.png",50,250,"sounds/laser.mp3",1);

        cannonDAO.insert(cannon);
        cannonDAO.query();

        assertEquals(cannon.printCannon(), model.getCannons().get(0).printCannon()); // should only have one object


        // ----- TEST 2
        Cannon cannon2 = new Cannon("19,137","184,21","images/parts/cannon2.png",
                325,386,"images/parts/laser2.png",105,344,"sounds/laser.mp3",2);

        cannonDAO.insert(cannon2);
        cannonDAO.query();

        assertEquals(cannon2.printCannon(), model.getCannons().get(1).printCannon());


    }}
