package edu.byu.cs.superasteroids.ship_builder;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DAO.DAOhelper;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;
import edu.byu.cs.superasteroids.model_classes.theShip;
import edu.byu.cs.superasteroids.viewStuff.viewPort;

/**
 * Created by lp1 on 2/20/16.
 */
public class ShipBuildingController implements IShipBuildingController {

    private IShipBuildingView shipBuildingView;
    private asteroidsGame model = asteroidsGame.getInstance();
    private theShip aShip;
    private ArrayList<Integer> cannonIds = new ArrayList<Integer>();
    private ArrayList<Integer> engineIds = new ArrayList<Integer>();
    private ArrayList<Integer> extraPartsIds = new ArrayList<Integer>();
    private ArrayList<Integer> mainBodyIds = new ArrayList<Integer>();
    private ArrayList<Integer> powerCoreIds = new ArrayList<Integer>();
    private IShipBuildingView.PartSelectionView currentView;
    private int cannonIndex = -1;
    private int engineIndex = -1;
    private int extraPartsIndex = -1;
    private int mainBodyIndex = -1;
    private int powerCoreIndex = -1;
    boolean first = true;
    private viewPort aViewPort;
    private DAOhelper daoHelper;
    private PointF bodyLocation;
    private float scaleFactor = .25f;


    // pass in interface
    ShipBuildingController(IShipBuildingView something){
        this.shipBuildingView = something;
        model = asteroidsGame.getInstance();
        model.populate();
    }


    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {

        currentView = partView;
        if (partView == IShipBuildingView.PartSelectionView.MAIN_BODY){
            System.out.println("main body");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT,true,"engine");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT,true,"extra parts");

            shipBuildingView.setPartViewImageList(partView, mainBodyIds);

        } else if (partView == IShipBuildingView.PartSelectionView.ENGINE){
            System.out.println("engine");

            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT,true,"cannon");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT,true,"main body");

            shipBuildingView.setPartViewImageList(partView, engineIds);
        }  else if (partView == IShipBuildingView.PartSelectionView.CANNON){
            System.out.println("cannon");

            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT,true,"power core");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT,true,"engine");

            shipBuildingView.setPartViewImageList(partView, cannonIds);
        }  else if (partView == IShipBuildingView.PartSelectionView.POWER_CORE){
            System.out.println("power core");

            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT,true,"extra parts");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT,true,"cannon");

            shipBuildingView.setPartViewImageList(partView, powerCoreIds);
        } else if (partView == IShipBuildingView.PartSelectionView.EXTRA_PART){
            System.out.println("extra part");

            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.DOWN,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.UP,false,"");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.LEFT,true,"main body");
            shipBuildingView.setArrow(partView, IShipBuildingView.ViewDirection.RIGHT,true,"power core");

            shipBuildingView.setPartViewImageList(partView, extraPartsIds);
        }

    }

    @Override
    public void update(double elapsedTime) {

        // setStartGameButton(bool);


        // thisShipBuilderMath();
    }

    @Override
    public void loadContent(ContentManager content) {
        // get all your stuff from models and put them in the content manager stuff
        //boolean first = true;

        if (first) {

            // do levels for the heck of it
            /*for (int i = 0; i < model.getLevels().size(); i++) {
                cannonIds.add(content.loadImage(model.getCannons().get(i).getImage()));
            }*/

            //daoHelper.onlyDoQueries();
            // cannons
            for (int i = 0; i < model.getCannons().size(); i++) {
                cannonIds.add(content.loadImage(model.getCannons().get(i).getImage()));
            }
            // engines
            for (int i = 0; i < model.getEngines().size(); i++) {
                engineIds.add(content.loadImage(model.getEngines().get(i).getImage()));
            }
            // extra parts
            for (int i = 0; i < model.getExtraParts().size(); i++) {
                extraPartsIds.add(content.loadImage(model.getExtraParts().get(i).getImage()));
            }
            // main body
            for (int i = 0; i < model.getMainBodies().size(); i++) {
                mainBodyIds.add(content.loadImage(model.getMainBodies().get(i).getImage()));
            }
            // power core
            for (int i = 0; i < model.getPowerCores().size(); i++) {
                powerCoreIds.add(content.loadImage(model.getPowerCores().get(i).getImage()));
            }

            System.out.println("THERE ARE " + model.getLevels().size() + " NUMBER OF LEVELS");

            //model.getCurrentShip().setBodyLocation(null);
            //aViewPort = viewPort.getInstance();
            //a


            first = false;
        }

    }

    @Override
    public void unloadContent(ContentManager content) {

    }

    @Override
    public void draw() {

        if (mainBodyIndex == 1){
            drawMainBodyduringShipBuilder();
        }
        if (cannonIndex == 1 && mainBodyIndex ==1){
            drawCannon();
        }
        if (engineIndex == 1 && mainBodyIndex == 1){
            drawEngine();
        }
        if (extraPartsIndex == 1 && mainBodyIndex == 1){
            drawExtraParts();
        }

    }

    // I think this just sets the currentView
    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {

        // gives you the direction they're swiping
        // view.animateToview(powerCore,opposite of direction);

        // switch statments on your view
         if (currentView == IShipBuildingView.PartSelectionView.MAIN_BODY){
            if (direction == IShipBuildingView.ViewDirection.LEFT){
                shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.EXTRA_PART,
                        IShipBuildingView.ViewDirection.RIGHT);
            } else if (direction == IShipBuildingView.ViewDirection.RIGHT){
                shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.ENGINE,
                        IShipBuildingView.ViewDirection.LEFT);
            }
        // partView = ENGINE;
         } else if (currentView == IShipBuildingView.PartSelectionView.ENGINE){
             if (direction == IShipBuildingView.ViewDirection.LEFT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY,
                         IShipBuildingView.ViewDirection.RIGHT);
             } else if (direction == IShipBuildingView.ViewDirection.RIGHT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.CANNON,
                         IShipBuildingView.ViewDirection.LEFT);
             }
         } else if (currentView == IShipBuildingView.PartSelectionView.CANNON){
             if (direction == IShipBuildingView.ViewDirection.LEFT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.ENGINE,
                         IShipBuildingView.ViewDirection.RIGHT);
             } else if (direction == IShipBuildingView.ViewDirection.RIGHT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.POWER_CORE,
                         IShipBuildingView.ViewDirection.LEFT);
             }
         } else if (currentView == IShipBuildingView.PartSelectionView.POWER_CORE){
             if (direction == IShipBuildingView.ViewDirection.LEFT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.CANNON,
                         IShipBuildingView.ViewDirection.RIGHT);
             } else if (direction == IShipBuildingView.ViewDirection.RIGHT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.EXTRA_PART,
                         IShipBuildingView.ViewDirection.LEFT);
             }
         } else if (currentView == IShipBuildingView.PartSelectionView.EXTRA_PART){
             if (direction == IShipBuildingView.ViewDirection.LEFT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.POWER_CORE,
                         IShipBuildingView.ViewDirection.RIGHT);
             } else if (direction == IShipBuildingView.ViewDirection.RIGHT){
                 shipBuildingView.animateToView(IShipBuildingView.PartSelectionView.MAIN_BODY,
                         IShipBuildingView.ViewDirection.LEFT);
             }
         }

    }

    @Override
    public void onPartSelected(int index) {


        if (currentView == IShipBuildingView.PartSelectionView.MAIN_BODY){
            mainBodyIndex = 1;
            model.getCurrentShip().setMainBody(model.getMainBodies().get(index), mainBodyIds.get(index));
        } else if (currentView == IShipBuildingView.PartSelectionView.ENGINE){
            engineIndex = 1;
            model.getCurrentShip().setEngine(model.getEngines().get(index), engineIds.get(index));
        } else if (currentView == IShipBuildingView.PartSelectionView.CANNON){
            cannonIndex = 1;
            model.getCurrentShip().setCannon(model.getCannons().get(index), cannonIds.get(index));
        } else if (currentView == IShipBuildingView.PartSelectionView.POWER_CORE){
            powerCoreIndex = 1;
            model.getCurrentShip().setPowerCore(model.getPowerCores().get(index), powerCoreIds.get(index));
        } else if (currentView == IShipBuildingView.PartSelectionView.EXTRA_PART){
            extraPartsIndex = 1;
            model.getCurrentShip().setExtraParts(model.getExtraParts().get(index), extraPartsIds.get(index));
        }

        // if all parts have been selected put this in the asteroids game
        if (mainBodyIndex == 1 && engineIndex == 1 &&
                cannonIndex == 1 && powerCoreIndex == 1 &&
                extraPartsIndex == 1){

            //model.setCurrentShip(aShip);
            model.getCurrentShip().setBodyLocation(null);
            shipBuildingView.setStartGameButton(true);
        }

    }

    @Override
    public void onStartGamePressed() {
        shipBuildingView.startGame();
    }

    @Override
    public void onResume() {

    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }

    public void drawMainBodyduringShipBuilder(){
        model = asteroidsGame.getInstance();
        bodyLocation = new PointF(DrawingHelper.getGameViewWidth()/2,
                DrawingHelper.getGameViewHeight()/2);

        DrawingHelper.drawImage(model.getCurrentShip().getMainBody().getImageId(),
                bodyLocation.x,
                bodyLocation.y, 0,
                scaleFactor, scaleFactor, 255);
    }

    public void drawCannon(){

        // part offset = (bodyattach - bodycenter) + (partcenter - partAttach)
        // part location = bodyLocation + partOffset

        // for part attachment
        float X = ((model.getCurrentShip().getMainBody().getCannonAttachX() -
                model.getCurrentShip().getMainBody().getImageWidth()/2)
                + ((model.getCurrentShip().getCannon().getImageWidth()/2) -
                model.getCurrentShip().getCannon().getAttachPointX())) * scaleFactor;

        float Y = ((model.getCurrentShip().getMainBody().getCannonAttachY() -
                model.getCurrentShip().getMainBody().getImageHeight()/2)
                + (model.getCurrentShip().getCannon().getImageHeight()/2) -
                model.getCurrentShip().getCannon().getAttachPointY()) * scaleFactor;



        DrawingHelper.drawImage(model.getCurrentShip().getCannon().getImageId(),
                bodyLocation.x + X,
                bodyLocation.y + Y,
                0,scaleFactor,scaleFactor,255);

    }

    public void drawEngine(){

        float X = (model.getCurrentShip().getMainBody().getEngineAttachX() -
                model.getCurrentShip().getMainBody().getImageWidth()/2)
                + ((model.getCurrentShip().getEngine().getImageWidth()/2) -
                model.getCurrentShip().getEngine().getAttachPointX()) * scaleFactor;

        float Y = ((model.getCurrentShip().getMainBody().getEngineAttachY() -
                model.getCurrentShip().getMainBody().getImageHeight()/2)
                + (model.getCurrentShip().getEngine().getImageHeight()/2) -
                model.getCurrentShip().getEngine().getAttachPointY()) * scaleFactor;


        DrawingHelper.drawImage(model.getCurrentShip().getEngine().getImageId(),
                bodyLocation.x + X,
                bodyLocation.y + Y,
                0, scaleFactor, scaleFactor, 255);
    }

    public void drawExtraParts(){
        float X = ((model.getCurrentShip().getMainBody().getExtraAttachX() -
                model.getCurrentShip().getMainBody().getImageWidth()/2)
                + ((model.getCurrentShip().getExtraParts().getImageWidth()/2) -
                model.getCurrentShip().getExtraParts().getAttachPointX())) * scaleFactor;

        float Y = ((model.getCurrentShip().getMainBody().getExtraAttachY() -
                model.getCurrentShip().getMainBody().getImageHeight()/2)
                + (model.getCurrentShip().getExtraParts().getImageHeight()/2) -
                model.getCurrentShip().getExtraParts().getAttachPointY()) * scaleFactor;


        DrawingHelper.drawImage(model.getCurrentShip().getExtraParts().getImageId(),
                bodyLocation.x + X,
                bodyLocation.y + Y,
                0,scaleFactor,scaleFactor,255);
    }
}
