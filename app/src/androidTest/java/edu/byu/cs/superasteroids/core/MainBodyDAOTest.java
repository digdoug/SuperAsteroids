package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.DAO.MainBodyDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.MainBody;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/14/16.
 */
public class MainBodyDAOTest extends AndroidTestCase {



    private asteroidsGame model;
    private MainBodyDAO mainBodyDAO;

    public void testInsertAndQuery(){
        // create artifical AsteroidType

        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        mainBodyDAO = new MainBodyDAO(DBhelper.getDbhelper());

        // ----- TEST 1
        MainBody mainBody = new MainBody("190,227","102,392","6,253","images/parts/mainbody1.png",
                200,400);

        mainBodyDAO.insert(mainBody);
        mainBodyDAO.query();

        assertEquals(mainBody.printMainBody(), model.getMainBodies().get(0).printMainBody()); // should only have one object


        // ----- TEST 2
        MainBody mainBody2 = new MainBody("143,323","85,459","26,323","images/parts/mainbody2.png",
                156,459);

        mainBodyDAO.insert(mainBody2);
        mainBodyDAO.query();

        assertEquals(mainBody2.printMainBody(), model.getMainBodies().get(1).printMainBody());


    }
}
