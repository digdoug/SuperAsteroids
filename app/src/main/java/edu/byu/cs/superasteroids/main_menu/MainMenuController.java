package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.database.DAO.DAOhelper;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;
import edu.byu.cs.superasteroids.model_classes.theShip;

/**
 * Created by lp1 on 3/3/16.
 */
public class MainMenuController implements IMainMenuController {

    private MainActivity activity;
    private asteroidsGame model;
    private theShip aShip = new theShip();
    private DAOhelper daoHelper;


    public MainMenuController(MainActivity someActivity){
        this.activity = someActivity;
    }
    @Override
    public void onQuickPlayPressed() {
        model = asteroidsGame.getInstance();
        model.populate();
        aShip.createRandomShip();
        model.setCurrentShip(aShip);
        activity.startGame();
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
