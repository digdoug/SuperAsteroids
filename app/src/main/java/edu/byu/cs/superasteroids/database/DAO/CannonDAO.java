package edu.byu.cs.superasteroids.database.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.Cannon;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 2/25/16.
 */
public class CannonDAO {

    private DBhelper sqldb;
    private asteroidsGame aG;// = asteroidsGame.getInstance();


    public CannonDAO(DBhelper sqldb){this.sqldb = sqldb;}

    // insert
    public void insert(Cannon cannon){


        ContentValues values = new ContentValues();

        values.put(Contract.Cannon.COLUMN_NAME_ATTACH_POINT, cannon.getAttachPoint());
        values.put(Contract.Cannon.COLUMN_NAME_EMIT_POINT, cannon.getEmitPoint());
        values.put(Contract.Cannon.COLUMN_NAME_IMAGE, cannon.getImage());
        values.put(Contract.Cannon.COLUMN_NAME_IMAGE_WIDTH, cannon.getImageWidth());
        values.put(Contract.Cannon.COLUMN_NAME_IMAGE_HEIGHT, cannon.getImageHeight());
        values.put(Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE, cannon.getAttackImage());
        values.put(Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE_WIDTH, cannon.getAttackImageWidth());
        values.put(Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE_HEIGHT, cannon.getAttackImageHeight());
        values.put(Contract.Cannon.COLUMN_NAME_ATTACK_SOUND, cannon.getAttackSound());
        values.put(Contract.Cannon.COLUMN_NAME_DAMAGE, cannon.getDamage());

        long newRowId;
        newRowId = sqldb.getWritableDatabase().insertOrThrow(
                Contract.Cannon.TABLE_NAME,
                Contract.Cannon.COLUMN_NAME_ATTACH_POINT, // THIS IS THE COLUMN THAT WILL
                // BE NULL IN THE CASE OF NO THERE BEING NO TABLE
                values
        );
    }


    // query --> this is where
    public void query (){
        String[] projection = {
                Contract.Cannon.COLUMN_NAME_ATTACH_POINT,
                Contract.Cannon.COLUMN_NAME_EMIT_POINT,
                Contract.Cannon.COLUMN_NAME_IMAGE,
                Contract.Cannon.COLUMN_NAME_IMAGE_WIDTH,
                Contract.Cannon.COLUMN_NAME_IMAGE_HEIGHT,
                Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE,
                Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE_WIDTH,
                Contract.Cannon.COLUMN_NAME_ATTACK_IMAGE_HEIGHT,
                Contract.Cannon.COLUMN_NAME_ATTACK_SOUND,
                Contract.Cannon.COLUMN_NAME_DAMAGE,
        };



        Cursor c = sqldb.getReadableDatabase().query(
                Contract.Cannon.TABLE_NAME,
                projection,
                null, // selection I don't think this needs to be specified because we're setting up proejction
                null, // selectionArgs
                null,
                null,
                null
        );

        aG = asteroidsGame.getInstance();
        Cannon aCannon;
        while (c.moveToNext()){

            String attachPoint = c.getString(0);
            String emitPoint = c.getString(1);
            String image = c. getString(2);
            int iWidth = c.getInt(3);
            int iHeight = c.getInt(4);
            String attackImage = c.getString(5);
            int attackImageWidth = c.getInt(6);
            int attackImageHeight = c.getInt(7);
            String attackSound = c.getString(8);
            int damage = c.getInt(9);

            aCannon = new Cannon(attachPoint,emitPoint,image,iWidth,iHeight,attackImage,attackImageWidth,
                    attackImageHeight,attackSound,damage);
            aG.insertCannon(aCannon);

        }

        c.close();

    }

}
