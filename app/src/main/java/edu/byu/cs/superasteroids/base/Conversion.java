package edu.byu.cs.superasteroids.base;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;
import edu.byu.cs.superasteroids.viewStuff.viewPort;

/**
 * Created by lp1 on 3/4/16.
 */
public class Conversion {

    private viewPort aViewPort;
    private asteroidsGame model;
    private PointF world;

    public Conversion(){



    }

    public PointF convToVP(PointF worldPoint){
        return GraphicsUtils.subtract(worldPoint, aViewPort.getTopLeft());
    }

    public PointF convToWolrd(PointF viewPoint){
        return GraphicsUtils.add(viewPoint, world);
    }

    public void setUpConversion(){
        model = asteroidsGame.getInstance();
        model.constructViewPort();
        aViewPort = model.getVP();
        world = new PointF(model.getCurrentLevel().getWidth(),
                model.getCurrentLevel().getHeight());
    }
}
