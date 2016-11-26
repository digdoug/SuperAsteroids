package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.MainBody;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class MainBodyDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public MainBodyDAO(DBhelper sqldb){this.sqldb = sqldb;}

    // insert
    public void insert(MainBody mainBody){


        ContentValues values = new ContentValues();

        values.put(Contract.MainBody.COLUMN_NAME_CANNON_ATTACH, mainBody.getCannonAttach());
        values.put(Contract.MainBody.COLUMN_NAME_ENGINE_ATTACH, mainBody.getEngineAttach());
        values.put(Contract.MainBody.COLUMN_NAME_EXTRA_ATTACH, mainBody.getExtraAttach());
        values.put(Contract.MainBody.COLUMN_NAME_IMAGE, mainBody.getImage());
        values.put(Contract.MainBody.COLUMN_NAME_IMAGE_WIDTH, mainBody.getImageWidth());
        values.put(Contract.MainBody.COLUMN_NAME_IMAGE_HEIGHT, mainBody.getImageHeight());



        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.MainBody.TABLE_NAME,
                Contract.MainBody.COLUMN_NAME_CANNON_ATTACH, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
    }


    // query --> this is where
    public void query (){
        String[] projection = {
                Contract.MainBody.COLUMN_NAME_CANNON_ATTACH,
                Contract.MainBody.COLUMN_NAME_ENGINE_ATTACH,
                Contract.MainBody.COLUMN_NAME_EXTRA_ATTACH,
                Contract.MainBody.COLUMN_NAME_IMAGE,
                Contract.MainBody.COLUMN_NAME_IMAGE_WIDTH,
                Contract.MainBody.COLUMN_NAME_IMAGE_HEIGHT,


        };



        Cursor c = sqldb.getReadableDatabase().query(
                Contract.MainBody.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        MainBody anMBody;
        while (c.moveToNext()){

            String cannonAttach = c.getString(0);
            String engineAttach = c.getString(1);
            String extraAttach = c. getString(2);
            String image = c.getString(3);
            int imageWidth = c. getInt(4);
            int imageHeight = c. getInt(5);

            anMBody = new MainBody(cannonAttach,engineAttach,extraAttach,image,imageWidth,imageHeight);
            aG.insertMainBody(anMBody);

        }

        c.close();

    }
}
