package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.BackGroundObject;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/24/16.
 */
public class BackGroundDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();

    public BackGroundDAO(DBhelper sqldb){
        this.sqldb = sqldb;
    }

    public void insert(BackGroundObject bObject){


        ContentValues values = new ContentValues();

        values.put(Contract.BackGroundObject.COLUMN_NAME_IMAGE, bObject.getImage());


        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.BackGroundObject.TABLE_NAME,
                Contract.BackGroundObject.COLUMN_NAME_IMAGE, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
        System.out.println(newRowId);
    }


    public void query (){
        String[] projection = {
                Contract.BackGroundObject.COLUMN_NAME_IMAGE,
        };

        Cursor c = sqldb.getReadableDatabase().query(
                Contract.BackGroundObject.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );
        aG = asteroidsGame.getInstance();
        BackGroundObject backGroundObject;
        while (c.moveToNext()){

            String image = c.getString(0);

            backGroundObject = new BackGroundObject(image);
            aG.insertBObject(backGroundObject);

        }

        c.close();

    }

}
