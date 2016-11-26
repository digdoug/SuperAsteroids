package edu.byu.cs.superasteroids.importer;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.Scanner;

import edu.byu.cs.superasteroids.database.Contract;
import edu.byu.cs.superasteroids.database.DAO.AsteroidTypeDAO;
import edu.byu.cs.superasteroids.database.DAO.BackGroundDAO;
import edu.byu.cs.superasteroids.database.DAO.DAOhelper;
import edu.byu.cs.superasteroids.database.DBhelper;
import edu.byu.cs.superasteroids.model_classes.AsteroidType;
import edu.byu.cs.superasteroids.model_classes.asteroidsGame;


/**
 * Created by lp1 on 1/29/16.
 */
public class GameDataImporter implements IGameDataImporter {

    private Context context;
    private DBhelper db;
    private DAOhelper daoHelper;

    GameDataImporter (Context c){
        context = c;
        db = new DBhelper(c);
        //aG = asteroidsGame.getInstance();
    }


    @Override
    public boolean importData(InputStreamReader dataInputReader) {
        db.onUpgrade(DBhelper.getWriteable(),1,1);


        JSONObject json = new JSONObject();


        try {
            Scanner scan = new Scanner (dataInputReader);
            StringBuilder sbuilder = new StringBuilder();
            while (scan.hasNext()){
                sbuilder.append(scan.nextLine());
            }

            String input = sbuilder.toString();
            json = new JSONObject(input);
            daoHelper = new DAOhelper(db);
            daoHelper.doAllQueries(json);

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return false;
    }





}




