package edu.byu.cs.superasteroids.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lp1 on 1/27/16.
 */
public class DBhelper extends SQLiteOpenHelper {

     //Contract contract = new Contract();
     // create all


     //@Override
     //public SQLiteDatabase getWritableDatabase() {
       //   return super.getWritableDatabase();
     //}

     private static DBhelper dbhelper;

     public static void init(Context context){
          dbhelper = new DBhelper(context);
     }

     public static SQLiteDatabase getReadable(){
          return dbhelper.getReadableDatabase();
     }

     public static SQLiteDatabase getWriteable(){
          return dbhelper.getWritableDatabase();
     }


     public static DBhelper getDbhelper(){return dbhelper;}

     // * public class FeedReaderDbHelper extends SQLiteOpenHelper {
     // If you change the database schema, you must increment the database version.
     public static final int DATABASE_VERSION = 1;
     public static final String DATABASE_NAME = "database.sqlite";

     public DBhelper(Context context) {
          //onUpgrade(dbhelper.getWritableDatabase(),1,1);
          super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }

     // DBhelper data;
     // @Override
     public void onCreate(SQLiteDatabase db) {

          db.execSQL(Contract.SQL_CREATE_BACKGROUND_OBJECT);
          db.execSQL(Contract.SQL_CREATE_ASTEROID_TYPE);
          db.execSQL(Contract.SQL_CREATE_CANNON);
          db.execSQL(Contract.SQL_CREATE_ENGINE);
          db.execSQL(Contract.SQL_CREATE_EXTRA_PARTS);
          db.execSQL(Contract.SQL_CREATE_LEVEL);
          db.execSQL(Contract.SQL_CREATE_LEVEL_OBJECT);
          db.execSQL(Contract.SQL_CREATE_LEVEL_ASTEROID);
          db.execSQL(Contract.SQL_CREATE_MAIN_BODY);
          db.execSQL(Contract.SQL_CREATE_POWER_CORE);
     }



     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     // This database is only a cache for online data, so its upgrade policy is
     // to simply to discard the data and start over
          db.execSQL(Contract.SQL_DELETE_BACKGROUND_OBJECT);
          db.execSQL(Contract.SQL_DELETE_ASTEROID_TYPE);
          db.execSQL(Contract.SQL_DELETE_CANNON);
          db.execSQL(Contract.SQL_DELETE_ENGINE);
          db.execSQL(Contract.SQL_DELETE_EXTRA_PARTS);
          db.execSQL(Contract.SQL_DELETE_LEVEL);
          db.execSQL(Contract.SQL_DELETE_LEVEL_OBJECT);
          db.execSQL(Contract.SQL_DELETE_LEVEL_ASTEROID);
          db.execSQL(Contract.SQL_DELETE_MAIN_BODY);
          db.execSQL(Contract.SQL_DELETE_POWER_CORE);

          onCreate(db);
     }


     public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          onUpgrade(db, oldVersion, newVersion);


     }


}



