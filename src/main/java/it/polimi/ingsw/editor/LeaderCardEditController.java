package it.polimi.ingsw.editor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.server.model.interfaces.Producible;
import it.polimi.ingsw.server.model.interfaces.Requirement;
import it.polimi.ingsw.server.model.misc.Colors;
import it.polimi.ingsw.server.model.resources.*;
import it.polimi.ingsw.server.model.specialAbilities.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LeaderCardEditController {
    public TextField coin;
    public TextField shield;
    public TextField servant;
    public TextField stone;
    public TextField green;
    public TextField blue;
    public TextField purple;
    public TextField yellow;
    public TextField points;
    public TextField greenN;
    public TextField blueN;
    public TextField purpleN;
    public TextField yellowN;
    public TextField id;

    private String resource= Any.getInstance().toString();
    private String specialAbility;
    private Map<String, Integer> costMap= new HashMap<>();


    private int coins;
    private int shields;
    private int stones;
    private int servants;
    private int victoryPoints;


    public void edit(ActionEvent event){
        init();
        makeCostMap();
        writeFile(makeCostMap(), victoryPoints, Integer.parseInt(id.getText()), specialAbility,resource);


        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void discount(ActionEvent event){
        specialAbility= "DISCOUNT";
    }

    public void marble(ActionEvent event){
        specialAbility= "WHITE_MARBLE";
    }

    public void additionalProductionPower(ActionEvent event){
        specialAbility= "PRODUCTION_POWER";
    }

    public void depotSpecial(ActionEvent event){
        specialAbility= "DEPOT";
    }

    public void coinResource(ActionEvent event){
        resource= Coin.getInstance().toString();
    }
    public void shieldResource(ActionEvent event){
        resource= Shield.getInstance().toString();
    }
    public void stoneResource(ActionEvent event){
        resource= Stone.getInstance().toString();
    }
    public void servantResource(ActionEvent event){
        resource= Servant.getInstance().toString();
    }

    public void discard(ActionEvent event){
        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        victoryPoints= Integer.parseInt(points.getText());
    }

    public Map<String, Integer> makeCostMap(){
        if (coins>0){
            costMap.put(ResourceType.COIN.toString(),coins);
        }
        if (shields>0){
            costMap.put(ResourceType.SHIELD.toString(),shields);
        }
        if (stones>0){
            costMap.put(ResourceType.STONE.toString(),stones);
        }
        if (Integer.parseInt(greenN.getText()) >0){
            costMap.put(Colors.GREEN.toString()+ " "+green.getText(),Integer.parseInt(greenN.getText()));
        }
        if (Integer.parseInt(blueN.getText())>0){
            costMap.put(Colors.BLUE.toString()+ " "+blue.getText(),Integer.parseInt(blueN.getText()));
        }
        if(servants>0) {
            costMap.put(ResourceType.SERVANT.toString(), servants);
        }
        if (Integer.parseInt(yellowN.getText())>0){
            costMap.put(Colors.YELLOW.toString()+" "+yellow.getText(),Integer.parseInt(yellowN.getText()));
        }
        if (Integer.parseInt(purpleN.getText())>0){
            costMap.put(Colors.PURPLE.toString()+" "+ purple.getText(),Integer.parseInt(purpleN.getText()));
        }
        return costMap;
    }

    public void writeFile(Map<String, Integer> costs, Integer pointVic, Integer idCard, String special, String resource){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/settings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray listOfCards= jsonObject.getAsJsonArray("LeaderCards");
            JsonObject cardSelected;
            if(idCard<listOfCards.size()-1 && idCard>48) {
                cardSelected = (JsonObject) listOfCards.get(idCard - 49);

                cardSelected.remove("requirements");
            }
            else {
                cardSelected= new JsonObject();
                cardSelected.addProperty("id", idCard);
            }

            JsonObject specials= new JsonObject();
            specials.addProperty("type", special);
            specials.addProperty("resource", resource);
            cardSelected.add("specialAbility", specials);




            JsonObject costAdds= new JsonObject();
            for (String s: costs.keySet()){
                costAdds.addProperty(s, costs.get(s));
            }
            cardSelected.add("requirements", costAdds);



            cardSelected.addProperty("victoryPoints", victoryPoints);

            if (idCard<listOfCards.size()-1 && idCard>48){

            }
            else {
                listOfCards.add(cardSelected);
            }

            FileWriter writer= new FileWriter("src/main/resources/JSONs/settings.json");
            writer.write(jsonObject.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
