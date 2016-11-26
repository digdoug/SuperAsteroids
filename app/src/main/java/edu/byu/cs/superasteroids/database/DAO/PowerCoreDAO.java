package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.PowerCore;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class PowerCoreDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public PowerCoreDAO(DBhelper sqldb){this.sqldb = sqldb;}

//    public AsteroidTypeDAO(){};

    // insert
    public void insert(PowerCore powerCore){


        ContentValues values = new ContentValues();

        values.put(Contract.PowerCore.COLUMN_NAME_CANNON_BOOST, powerCore.getCannonBoost());
        values.put(Contract.PowerCore.COLUMN_NAME_ENGINE_BOOST, powerCore.getEngineBoost());
        values.put(Contract.PowerCore.COLUMN_NAME_IMAGE, powerCore.getImage());


        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.PowerCore.TABLE_NAME,
                Contract.PowerCore.COLUMN_NAME_CANNON_BOOST, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
        System.out.println(newRowId);
    }


    // query --> this is where
    public void query (){
        String[] projection = {
                //Contract.AsteroidType.COLUMN_NAME_ENTRY_ID,
                Contract.PowerCore.COLUMN_NAME_CANNON_BOOST,
                Contract.PowerCore.COLUMN_NAME_ENGINE_BOOST,
                Contract.PowerCore.COLUMN_NAME_IMAGE,

        };

        // order by IDs (ascending order should be default)

        //String sortOrder =
        //      Contract.AsteroidType.COLUMN_NAME_ENTRY_ID;

        Cursor c = sqldb.getReadableDatabase().query(
                Contract.PowerCore.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        PowerCore pCore;
        while (c.moveToNext()){

            int cannonBoost = c.getInt(0);
            int engineBoost = c.getInt(1);
            String image = c.getString(2);


            pCore = new PowerCore(cannonBoost,engineBoost,image);
            aG.insertPowerCore(pCore);

        }

        c.close();

    }
}
