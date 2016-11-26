package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.ExtraParts;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class ExtraPartsDAO {


    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public ExtraPartsDAO(DBhelper sqldb){this.sqldb = sqldb;}

    // insert
    public void insert(ExtraParts extraParts){


        ContentValues values = new ContentValues();

        values.put(Contract.ExtraParts.COLUMN_NAME_ATTACH_POINT, extraParts.getAttachPoint());
        values.put(Contract.ExtraParts.COLUMN_NAME_IMAGE, extraParts.getImage());
        values.put(Contract.ExtraParts.COLUMN_NAME_IMAGE_WIDTH, extraParts.getImageWidth());
        values.put(Contract.ExtraParts.COLUMN_NAME_IMAGE_HEIGHT, extraParts.getImageHeight());


        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.ExtraParts.TABLE_NAME,
                Contract.ExtraParts.COLUMN_NAME_ATTACH_POINT, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
    }


    // query --> this is where
    public void query (){
        String[] projection = {
                Contract.ExtraParts.COLUMN_NAME_ATTACH_POINT,
                Contract.ExtraParts.COLUMN_NAME_IMAGE,
                Contract.ExtraParts.COLUMN_NAME_IMAGE_WIDTH,
                Contract.ExtraParts.COLUMN_NAME_IMAGE_HEIGHT,

        };



        Cursor c = sqldb.getReadableDatabase().query(
                Contract.ExtraParts.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        ExtraParts anEPart;
        while (c.moveToNext()){

            String attachPoint = c.getString(0);
            String image = c.getString(1);
            int imageWidth = c. getInt(2);
            int imageHeight = c.getInt(3);

            anEPart = new ExtraParts(attachPoint,image,imageWidth,imageHeight);
            aG.insertExtraParts(anEPart);

        }

        c.close();

    }

}
