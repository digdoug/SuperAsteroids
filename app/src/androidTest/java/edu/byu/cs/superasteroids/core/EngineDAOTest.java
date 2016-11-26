package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.DAO.EngineDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Engine;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/14/16.
 */
public class EngineDAOTest extends AndroidTestCase {

    private asteroidsGame model;
    private EngineDAO engineDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        engineDAO = new EngineDAO(DBhelper.getDbhelper());

        // ----- TEST 1
        Engine engine = new Engine(350,270,"106,6","images/parts/engine1.png",
                220,160);

        engineDAO.insert(engine);
        engineDAO.query();

        System.out.println(engine.printEngine());
        System.out.println(model.getEngines().get(0).printEngine());

        assertEquals(engine.printEngine(), model.getEngines().get(0).printEngine()); // should only have one object


        // ----- TEST 2
        Engine engine2 = new Engine(350,270,"106,6","images/parts/engine2.png",
                220,160);


        engineDAO.insert(engine2);
        engineDAO.query();

        assertEquals(engine2.printEngine(), model.getEngines().get(1).printEngine());
}}
