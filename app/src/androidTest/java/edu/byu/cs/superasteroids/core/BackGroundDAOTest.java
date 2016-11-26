package edu.byu.cs.superasteroids.core;

import android.test.AndroidTestCase;

import edu.byu.cs.superasteroids.database.DAO.AsteroidTypeDAO;
import edu.byu.cs.superasteroids.database.DAO.BackGroundDAO;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.BackGroundObject;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/11/16.
 */
public class BackGroundDAOTest extends AndroidTestCase {

    private asteroidsGame model;
    private BackGroundDAO backGroundDAO;

    public void testInsertAndQuery(){
        model = asteroidsGame.getInstance();
        DBhelper.init(getContext());
        backGroundDAO = new BackGroundDAO(DBhelper.getDbhelper());


        BackGroundObject backGroundObject = new BackGroundObject("images/planet1.png");

        backGroundDAO.insert(backGroundObject);
        backGroundDAO.query();

        assertEquals(backGroundObject.printBackGroundObject(), model.getBackGroundObjects().get(0).printBackGroundObject());


        BackGroundObject backGroundObject1 = new BackGroundObject("images/planet2.png");

        backGroundDAO.insert(backGroundObject1);
        backGroundDAO.query();

        assertEquals(backGroundObject1.printBackGroundObject(), model.getBackGroundObjects().get(1).printBackGroundObject());


        BackGroundObject backGroundObject2 = new BackGroundObject("images/planet3.png");

        backGroundDAO.insert(backGroundObject2);
        backGroundDAO.query();

        assertEquals(backGroundObject2.printBackGroundObject(),model.getBackGroundObjects().get(2).printBackGroundObject());


    }
}
