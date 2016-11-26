package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.AsteroidType;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 1/29/16.
 */


public class AsteroidTypeDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public AsteroidTypeDAO(DBhelper sqldb){this.sqldb = sqldb;}

    // public AsteroidTypeDAO(){};

    // insert
    public void insert(AsteroidType asteroidType){


        ContentValues values = new ContentValues();

        values.put(Contract.AsteroidType.COLUMN_NAME_NAME, asteroidType.getName());
        values.put(Contract.AsteroidType.COLUMN_NAME_IMAGE, asteroidType.getImage());
        values.put(Contract.AsteroidType.COLUMN_NAME_IMAGE_WIDTH, asteroidType.getImageWidth());
        values.put(Contract.AsteroidType.COLUMN_NAME_IMAGE_HEIGHT, asteroidType.getImageHeight());
        values.put(Contract.AsteroidType.COLUMN_NAME_TYPE, asteroidType.getType());

        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
          Contract.AsteroidType.TABLE_NAME,
          Contract.AsteroidType.COLUMN_NAME_NAME, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
    }


    // query --> this is where
    public void query (){
        String[] projection = {

                Contract.AsteroidType.COLUMN_NAME_NAME,
                Contract.AsteroidType.COLUMN_NAME_IMAGE,
                Contract.AsteroidType.COLUMN_NAME_IMAGE_WIDTH,
                Contract.AsteroidType.COLUMN_NAME_IMAGE_HEIGHT,
                Contract.AsteroidType.COLUMN_NAME_TYPE,
        };

        // order by IDs (ascending order should be default)

        //String sortOrder =
          //      Contract.AsteroidType.COLUMN_NAME_ENTRY_ID;

        Cursor c = sqldb.getReadableDatabase().query(
                Contract.AsteroidType.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        AsteroidType aType;
        while (c.moveToNext()){

            String name = c.getString(0);
            String image = c.getString(1);
            int iWidth = c.getInt(2);
            int iHeight = c.getInt(3);
            String type = c.getString(4);

            aType = new AsteroidType(name,image,iWidth,iHeight,type);
            aG.insertAsteroid(aType);

        }

        c.close();

    }



}
