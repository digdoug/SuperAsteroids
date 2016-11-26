package edu.byu.cs.superasteroids.database;

import android.provider.BaseColumns;

import edu.byu.cs.superasteroids.model_classes.Level;

/**
 * Created by lp1 on 1/29/16.
 */
public class Contract {

    public Contract(){};

    // private static String

    public static abstract class BackGroundObject implements BaseColumns{
        public static final String TABLE_NAME = "backGroundObject";
        public static final String COLUMN_NAME_IMAGE = "image";

    }

    public static abstract class AsteroidType implements BaseColumns{
        public static final String TABLE_NAME = "asteroidType";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_IMAGE_WIDTH = "imageWidth";
        public static final String COLUMN_NAME_IMAGE_HEIGHT = "imageHeight";
        public static final String COLUMN_NAME_TYPE = "type";

    }

    public static abstract class Cannon implements BaseColumns  {
        public static final String TABLE_NAME = "Cannon";
        public static final String COLUMN_NAME_ATTACH_POINT = "attachPoint";
        public static final String COLUMN_NAME_EMIT_POINT = "emitPoint";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_IMAGE_WIDTH = "imageWidth";
        public static final String COLUMN_NAME_IMAGE_HEIGHT = "imageHeight";
        public static final String COLUMN_NAME_ATTACK_IMAGE = "attackImage";
        public static final String COLUMN_NAME_ATTACK_IMAGE_WIDTH = "attackImageWidth";
        public static final String COLUMN_NAME_ATTACK_IMAGE_HEIGHT = "attackImageHeight";
        public static final String COLUMN_NAME_ATTACK_SOUND = "attackSound";
        public static final String COLUMN_NAME_DAMAGE = "damage";
    }

    public static abstract class Engine implements BaseColumns  {
        public static final String TABLE_NAME = "Engine";
        public static final String COLUMN_NAME_BASE_SPEED = "baseSpeed";
        public static final String COLUMN_NAME_BASE_TURN_RATE = "baseTurnRate";
        public static final String COLUMN_NAME_ATTACH_POINT = "attachPoint";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_IMAGE_WIDTH = "imageWidth";
        public static final String COLUMN_NAME_IMAGE_HEIGHT = "imageHeight";

    }

    public static abstract class ExtraParts implements BaseColumns  {
        public static final String TABLE_NAME = "ExrtaParts";
        public static final String COLUMN_NAME_IMAGE_WIDTH = "imageWidth";
        public static final String COLUMN_NAME_IMAGE_HEIGHT = "imageHeight";
        public static final String COLUMN_NAME_ATTACH_POINT = "attachPoint";
        public static final String COLUMN_NAME_IMAGE = "image";

    }

// level
    public static abstract class Level implements BaseColumns  {
        public static final String TABLE_NAME = "Level";
        public static final String COLUMN_NAME_LEVEL_ID = "levelid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_HINT= "hint";
        public static final String COLUMN_NAME_WIDTH = "width";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_MUSIC = "music";

    }


    public static abstract class LevelObject implements BaseColumns  {
        public static final String TABLE_NAME = "LevelObject";
        public static final String COLUMN_NAME_OBJECT_ID = "objectid";
        public static final String COLUMN_NAME_POSITION = "position";
        public static final String COLUMN_NAME_SCALE = "scale";
        public static final String COLUMN_NAME_LEVEL_MATCH_ID = "matchid";

    }

    public static abstract class LevelAsteroid implements BaseColumns  {
        public static final String TABLE_NAME = "LevelAsteroid";
        public static final String COLUMN_NAME_ASTEROID_ID = "asteroidid";
        public static final String COLUMN_NAME_NUMBER = "number";
        public static final String COLUMN_NAME_LEVEL_MATCH_ID = "matchid";

    }

    public static abstract class MainBody implements BaseColumns  {
        public static final String TABLE_NAME = "MainBody";
        public static final String COLUMN_NAME_CANNON_ATTACH = "cannonAttach";
        public static final String COLUMN_NAME_ENGINE_ATTACH = "engineAttach";
        public static final String COLUMN_NAME_EXTRA_ATTACH = "extraAttach";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_IMAGE_WIDTH = "imageWidth";
        public static final String COLUMN_NAME_IMAGE_HEIGHT = "imageHeight";
    }

    public static abstract class PowerCore implements BaseColumns  {
        public static final String TABLE_NAME = "PowerCore";
        public static final String COLUMN_NAME_CANNON_BOOST = "cannonBoost";
        public static final String COLUMN_NAME_ENGINE_BOOST = "engineBoost";
        public static final String COLUMN_NAME_IMAGE = "image";

    }

    // create table concatenates all strings

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_BACKGROUND_OBJECT = "CREATE TABLE " + BackGroundObject.TABLE_NAME + " ("
            + BackGroundObject.COLUMN_NAME_IMAGE + TEXT_TYPE
            + " )";


    public static final String SQL_CREATE_ASTEROID_TYPE = "CREATE TABLE " + AsteroidType.TABLE_NAME + " ("
            + AsteroidType.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
            + AsteroidType.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
            + AsteroidType.COLUMN_NAME_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + AsteroidType.COLUMN_NAME_IMAGE_HEIGHT + INT_TYPE + COMMA_SEP
            + AsteroidType.COLUMN_NAME_TYPE + TEXT_TYPE
            + " )";

    public static final String SQL_CREATE_CANNON = "CREATE TABLE " + Cannon.TABLE_NAME + " ("
            + Cannon.COLUMN_NAME_ATTACH_POINT + TEXT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_EMIT_POINT + TEXT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_IMAGE_HEIGHT + INT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_ATTACK_IMAGE + TEXT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_ATTACK_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_ATTACK_IMAGE_HEIGHT + INT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_ATTACK_SOUND + TEXT_TYPE + COMMA_SEP
            + Cannon.COLUMN_NAME_DAMAGE + INT_TYPE
            + " )";

    public static final String SQL_CREATE_ENGINE = "CREATE TABLE " + Engine.TABLE_NAME + " ("
            + Engine.COLUMN_NAME_BASE_SPEED + INT_TYPE + COMMA_SEP
            + Engine.COLUMN_NAME_BASE_TURN_RATE + INT_TYPE + COMMA_SEP
            + Engine.COLUMN_NAME_ATTACH_POINT + TEXT_TYPE + COMMA_SEP
            + Engine.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
            + Engine.COLUMN_NAME_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + Engine.COLUMN_NAME_IMAGE_HEIGHT + INT_TYPE
            + " )";

    public static final String SQL_CREATE_EXTRA_PARTS = "CREATE TABLE " + ExtraParts.TABLE_NAME + " ("
            + ExtraParts.COLUMN_NAME_ATTACH_POINT + TEXT_TYPE + COMMA_SEP
            + ExtraParts.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
            + ExtraParts.COLUMN_NAME_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + ExtraParts.COLUMN_NAME_IMAGE_HEIGHT + INT_TYPE
            + " )";

    public static final String SQL_CREATE_LEVEL = "CREATE TABLE " + Level.TABLE_NAME + " ("
            + Level.COLUMN_NAME_LEVEL_ID + INT_TYPE + COMMA_SEP
            + Level.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP
            + Level.COLUMN_NAME_HINT + TEXT_TYPE + COMMA_SEP
            + Level.COLUMN_NAME_WIDTH + INT_TYPE + COMMA_SEP
            + Level.COLUMN_NAME_HEIGHT + INT_TYPE + COMMA_SEP
            + Level.COLUMN_NAME_MUSIC + TEXT_TYPE
            + " )";

    public static final String SQL_CREATE_LEVEL_OBJECT = "CREATE TABLE " + LevelObject.TABLE_NAME + " ("
            + LevelObject.COLUMN_NAME_OBJECT_ID + TEXT_TYPE + COMMA_SEP
            + LevelObject.COLUMN_NAME_POSITION + INT_TYPE + COMMA_SEP
            + LevelObject.COLUMN_NAME_SCALE + FLOAT_TYPE + COMMA_SEP
            + LevelObject.COLUMN_NAME_LEVEL_MATCH_ID + INT_TYPE

            + " )";

    public static final String SQL_CREATE_LEVEL_ASTEROID = "CREATE TABLE " + LevelAsteroid.TABLE_NAME + " ("
            + LevelAsteroid.COLUMN_NAME_ASTEROID_ID + INT_TYPE + COMMA_SEP
            + LevelAsteroid.COLUMN_NAME_NUMBER + INT_TYPE + COMMA_SEP
            + LevelAsteroid.COLUMN_NAME_LEVEL_MATCH_ID + INT_TYPE
            + " )";

    public static final String SQL_CREATE_MAIN_BODY = "CREATE TABLE " + MainBody.TABLE_NAME + " ("
            + MainBody.COLUMN_NAME_CANNON_ATTACH + TEXT_TYPE + COMMA_SEP
            + MainBody.COLUMN_NAME_ENGINE_ATTACH + TEXT_TYPE + COMMA_SEP
            + MainBody.COLUMN_NAME_EXTRA_ATTACH + TEXT_TYPE + COMMA_SEP
            + MainBody.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP
            + MainBody.COLUMN_NAME_IMAGE_WIDTH + INT_TYPE + COMMA_SEP
            + MainBody.COLUMN_NAME_IMAGE_HEIGHT + INT_TYPE
            + " )";

    public static final String SQL_CREATE_POWER_CORE = "CREATE TABLE " + PowerCore.TABLE_NAME + " ("
            + PowerCore.COLUMN_NAME_CANNON_BOOST + INT_TYPE + COMMA_SEP
            + PowerCore.COLUMN_NAME_ENGINE_BOOST + INT_TYPE + COMMA_SEP
            + PowerCore.COLUMN_NAME_IMAGE + TEXT_TYPE
            + " )";


    // DROP TABLES

    public static final String SQL_DELETE_BACKGROUND_OBJECT =
            "DROP TABLE IF EXISTS " + BackGroundObject.TABLE_NAME;

    public static final String SQL_DELETE_ASTEROID_TYPE =
            "DROP TABLE IF EXISTS " + AsteroidType.TABLE_NAME;

    public static final String SQL_DELETE_CANNON =
            "DROP TABLE IF EXISTS " + Cannon.TABLE_NAME;

    public static final String SQL_DELETE_ENGINE =
            "DROP TABLE IF EXISTS " + Engine.TABLE_NAME;

    public static final String SQL_DELETE_EXTRA_PARTS =
            "DROP TABLE IF EXISTS " + ExtraParts.TABLE_NAME;

    public static final String SQL_DELETE_LEVEL =
            "DROP TABLE IF EXISTS " + Level.TABLE_NAME;

    public static final String SQL_DELETE_LEVEL_OBJECT =
            "DROP TABLE IF EXISTS " + LevelObject.TABLE_NAME;

    public static final String SQL_DELETE_LEVEL_ASTEROID =
            "DROP TABLE IF EXISTS " + LevelAsteroid.TABLE_NAME;

    public static final String SQL_DELETE_MAIN_BODY =
            "DROP TABLE IF EXISTS " + MainBody.TABLE_NAME;

    public static final String SQL_DELETE_POWER_CORE =
            "DROP TABLE IF EXISTS " + PowerCore.TABLE_NAME;




}
