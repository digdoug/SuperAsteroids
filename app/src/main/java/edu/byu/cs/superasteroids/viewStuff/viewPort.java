package edu.byu.cs.superasteroids.viewStuff;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;

/**
 * Created by lp1 on 3/3/16.
 */
public class viewPort {


    private int widthOfScreen = 1196;
    private int heightOfScreen = 670;
    private static viewPort instance;

    private viewPort(){
        game = asteroidsGame.getInstance();
        topLeft = new PointF(game.getCurrentLevel().getWidth()/2 - widthOfScreen/2,
                game.getCurrentLevel().getHeight()/2 - heightOfScreen/2);

        dimensions = new PointF(DrawingHelper.getGameViewWidth(),
                DrawingHelper.getGameViewHeight());
        rectView = new RectF();
    }

    public static viewPort getInstance(){
        if (instance == null){
            instance = new viewPort();
        }
        return instance;
    }

    private PointF topLeft;
    private RectF rectView;
    private asteroidsGame game;
    private PointF dimensions;
    private float toTopY;
    private float toBottomY;
    private float toRightX;
    private float toLeftX;
    private float biggestinRect;

    public float getBiggestinRect() {
        return biggestinRect;
    }

    public void recalcViewPort(PointF centerOfShip){
        PointF halfOfScreen = new PointF(widthOfScreen/2,heightOfScreen/2);
        topLeft = GraphicsUtils.subtract(game.getCurrentShip().getBodyLocation(),
                halfOfScreen);


        // viewPort
        if (topLeft.x + widthOfScreen > game.getCurrentLevel().getWidth()){
            topLeft.x = game.getCurrentLevel().getWidth() - widthOfScreen;
        }
        if (topLeft.x < 0){
            topLeft.x = 0;
        }
        if (topLeft.y + heightOfScreen > game.getCurrentLevel().getHeight()){
            topLeft.y = game.getCurrentLevel().getHeight() - heightOfScreen;
        }
        if (topLeft.y < 0){
            topLeft.y = 0;
        }

        // rect View
        // topLeft corner
        float halfOfBodyWidth = game.getCurrentShip().getMainBody().getImageWidth()/2 *
                game.getCurrentShip().getScaleFactor();
        float halfOfBodyHeight = game.getCurrentShip().getMainBody().getImageHeight()/2 *
                game.getCurrentShip().getScaleFactor();

        PointF forUse;
        if (centerOfShip.x - biggestinRect - halfOfBodyWidth < 0){
            forUse = new PointF(biggestinRect + halfOfBodyWidth
                    ,centerOfShip.y);
            game.getCurrentShip().setBodyLocation(forUse);
        }
        if (centerOfShip.x + biggestinRect + halfOfBodyWidth > game.getCurrentLevel().getWidth()){
            forUse = new PointF(game.getCurrentLevel().getWidth() - biggestinRect -
                    halfOfBodyWidth,centerOfShip.y);
            game.getCurrentShip().setBodyLocation(forUse);
        }
        if (centerOfShip.y - biggestinRect - halfOfBodyHeight < 0){
            forUse = new PointF(centerOfShip.x,biggestinRect +
                        halfOfBodyHeight);
            game.getCurrentShip().setBodyLocation(forUse);
        }
        if (centerOfShip.y + biggestinRect + halfOfBodyHeight > game.getCurrentLevel().getHeight()){
            forUse = new PointF(centerOfShip.x,game.getCurrentLevel().getHeight() - biggestinRect -
                    halfOfBodyHeight);
            game.getCurrentShip().setBodyLocation(forUse);
        }

        // left top right bottom
        rectView = new RectF(game.getCurrentShip().getBodyLocation().x - biggestinRect,
                game.getCurrentShip().getBodyLocation().y - biggestinRect,
                game.getCurrentShip().getBodyLocation().x + biggestinRect,
                game.getCurrentShip().getBodyLocation().y + biggestinRect);

    }
    public PointF getTopLeft() {
        return topLeft;
    }

    public RectF getView() {
        return rectView;
    }

    public PointF getDimensions() {
        return dimensions;
    }

    public void setTopLeft(PointF topLeft) {
        this.topLeft = topLeft;
    }

    public void initRect(){

        /**
         * left	float: The X coordinate of the left side of the rectangle
         top	float: The Y coordinate of the top of the rectangle
         right	float: The X coordinate of the right side of the rectangle
         bottom	float: The Y coordinate of the bottom of the rectangle
         */
        toTopY = game.getCurrentShip().getMainBody().getImageHeight()/2 *
                game.getCurrentShip().getScaleFactor();

        toBottomY = (game.getCurrentShip().getMainBody().getImageHeight()/2 +
                game.getCurrentShip().getEngine().getImageHeight()) *
                game.getCurrentShip().getScaleFactor();

        toRightX = (game.getCurrentShip().getMainBody().getImageWidth() /2 +
                game.getCurrentShip().getCannon().getImageWidth()) *
                game.getCurrentShip().getScaleFactor();

        toLeftX = (game.getCurrentShip().getMainBody().getImageWidth()/2 +
                game.getCurrentShip().getExtraParts().getImageWidth()) *
                game.getCurrentShip().getScaleFactor();

        biggestinRect = Math.max(Math.max(Math.max(toTopY,toBottomY),toRightX),toLeftX);

    }
}
