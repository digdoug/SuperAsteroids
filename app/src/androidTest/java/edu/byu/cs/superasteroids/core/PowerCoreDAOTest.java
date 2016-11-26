package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.DAO.PowerCoreDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.PowerCore;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/14/16.
 */
public class PowerCoreDAOTest extends AndroidTestCase {

    private asteroidsGame model;
    private PowerCoreDAO powerCoreDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        powerCoreDAO = new PowerCoreDAO(DBhelper.getDbhelper());

        // ----- TEST 1
        PowerCore powerCore = new PowerCore(10,10,"images/Ellipse.png");

        powerCoreDAO.insert(powerCore);
        powerCoreDAO.query();

        assertEquals(powerCore.printPowerCore(), model.getPowerCores().get(0).printPowerCore()); // should only have one object


        // ----- TEST 2
        PowerCore powerCore2 = new PowerCore(10,10,"images/Triangle.png");

        powerCoreDAO.insert(powerCore2);
        powerCoreDAO.query();

        assertEquals(powerCore2.printPowerCore(), model.getPowerCores().get(1).printPowerCore());



    }


}
