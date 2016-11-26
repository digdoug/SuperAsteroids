package edu.byu.cs.superasteroids.model_classes;

import java.util.ArrayList;

/**
 * Created by lp1 on 1/16/16.
 */
public class Level extends parseCoordinates{



    /**
     * “number”: Integer. The level number.
     “title”: String. The level title.
     “hint”: String. The level hint to be displayed with the title.
     “width”: Integer. The pixel width of the level.
     “height”: Integer. The pixel height of the level.
     “music”: String. The path to the music file to be played with the level. “levelObjects”: An array of Level Objects. Can be empty
     “levelAsteroids”: An array of Level Asteroids. Should not be empty.
     */



    private ArrayList<LevelObject> lObjects = new ArrayList<LevelObject>();
    private ArrayList<LevelAsteroid> lAsteroids = new ArrayList<LevelAsteroid>();
    private int lNumber = 0;
    private String title;
    private String hint;
    private int width = 0;
    private int height = 0;
    private String music;
    private int musicId = -1;



    public Level(ArrayList<LevelObject> lObjects, ArrayList<LevelAsteroid> lAsteroids, int lNumber, String title,
                 String hint, int width, int height, String music) {
        this.lObjects = lObjects;
        this.lAsteroids = lAsteroids;
        this.lNumber = lNumber;
        this.title = title;
        this.hint = hint;
        this.width = width;
        this.height = height;
        this.music = music;
    }

    public Level(){};

    public ArrayList<LevelObject> getlObjects() {
        return lObjects;
    }

    public ArrayList<LevelAsteroid> getlAsteroids() {
        return lAsteroids;
    }

    public int getlNumber() {
        return lNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getHint() {
        return hint;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getMusic() {
        return music;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getMusicId() {
        return musicId;
    }

    public String printLevel(){
        StringBuilder sb = new StringBuilder();
        sb.append(lNumber);
        sb.append(title);
        sb.append(hint);
        sb.append(width);
        sb.append(height);
        sb.append(music);
        sb.append(musicId);

        for (int i = 0; i < lObjects.size();i++){
            sb.append(lObjects.get(i).getPosition());
            sb.append(lObjects.get(i).getObjectId());
            sb.append(lObjects.get(i).getScale());
            sb.append(lObjects.get(i).getLevelMatch());
        }

        for (int i = 0; i < lAsteroids.size();i++){
            sb.append(lAsteroids.get(i).getNumber());
            sb.append(lAsteroids.get(i).getAsteroidId());
            sb.append(lAsteroids.get(i).getLevelMatchid());
        }

        return sb.toString();
    }
    public LevelAsteroid instantiateLevelAsteroid(int number, int asteroidid, int levelMatch){
        LevelAsteroid levelAsteroid = new LevelAsteroid(number,asteroidid,levelMatch);
        return levelAsteroid;
    }

    public LevelObject instantiateLevelObject(String position, int objectId, float scale, int levelMatch){
        LevelObject levelObject = new LevelObject(position,objectId,scale,levelMatch);
        return levelObject;
    }



    public class LevelObject{
        /**
         * “position”: Coordinate String. The position in the level to draw the object.
           “objectId”: Integer. The ID of the object to draw. An ID of 1 corresponds to the first object in the objects array.
           “scale”: Float. The scale to draw the object at.
         */


        private String position;
        private int objectId = 0;
        private float scale = 0;
        private int levelMatchid = 0;

        public LevelObject(String position, int objectId, float scale, int levelMatch) {
            this.position = position;
            this.objectId = objectId;
            this.scale = scale;
            this.levelMatchid = levelMatch;
        }

        public String getPosition() {
            return position;
        }

        public int getPosX(){
            return getX(position);
        }

        public int getPosY(){
            return getY(position);
        }

        public int getObjectId() {
            return objectId;
        }

        public float getScale() {
            return scale;
        }

        public int getLevelMatch() {
            return levelMatchid;
        }
    }

    public class LevelAsteroid{
        /**
         * “number”: Integer. The number of asteroids of this type to generate at the beginning of the level.
           “asteroidId”: Integer. The ID of the asteroid type to generate.
         */

        private int number = 0;
        private int asteroidId = 0;
        private int levelMatchid = 0;

        public LevelAsteroid(int number, int asteroidId, int levelMatch) {
            this.number = number;
            this.asteroidId = asteroidId;
            this.levelMatchid = levelMatch;
        }

        public int getNumber() {
            return number;
        }

        public int getAsteroidId() {
            return asteroidId;
        }

        public int getLevelMatchid() {
            return levelMatchid;
        }
    }


}
