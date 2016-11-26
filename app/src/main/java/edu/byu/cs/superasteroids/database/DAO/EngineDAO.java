package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Engine;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class EngineDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public EngineDAO(DBhelper sqldb){this.sqldb = sqldb;}

    // insert
    public void insert(Engine engine){


        ContentValues values = new ContentValues();

        values.put(Contract.Engine.COLUMN_NAME_BASE_SPEED, engine.getBaseSpeed());
        values.put(Contract.Engine.COLUMN_NAME_BASE_TURN_RATE, engine.getBaseTurnRate());
        values.put(Contract.Engine.COLUMN_NAME_ATTACH_POINT, engine.getAttachPoint());
        values.put(Contract.Engine.COLUMN_NAME_IMAGE, engine.getImage());
        values.put(Contract.Engine.COLUMN_NAME_IMAGE_WIDTH, engine.getImageWidth());
        values.put(Contract.Engine.COLUMN_NAME_IMAGE_HEIGHT, engine.getImageHeight());

        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.Engine.TABLE_NAME,
                Contract.Engine.COLUMN_NAME_BASE_SPEED, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
    }


    // query --> this is where
    public void query (){
        String[] projection = {
                Contract.Engine.COLUMN_NAME_BASE_SPEED,
                Contract.Engine.COLUMN_NAME_BASE_TURN_RATE,
                Contract.Engine.COLUMN_NAME_ATTACH_POINT,
                Contract.Engine.COLUMN_NAME_IMAGE,
                Contract.Engine.COLUMN_NAME_IMAGE_WIDTH,
                Contract.Engine.COLUMN_NAME_IMAGE_HEIGHT,

        };



        Cursor c = sqldb.getReadableDatabase().query(
                Contract.Engine.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        Engine anEngine;
        while (c.moveToNext()){

            int baseSpeed = c.getInt(0);
            int baseTurnRate = c.getInt(1);
            String attachPoint = c. getString(2);
            String image = c.getString(3);
            int iWidth = c.getInt(4);
            int iHeight = c.getInt(5);

            anEngine = new Engine(baseSpeed,baseTurnRate,attachPoint,image,iWidth,iHeight);
            aG.insertEngine(anEngine);

        }

        c.close();

    }
}
