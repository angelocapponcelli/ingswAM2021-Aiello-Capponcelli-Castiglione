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

/**
 * This class is used by the client to edit the leader card.
 * he can create a new leader card with a different id. Or he can change the parameter of an existing leader card.
 * The type of resource , the special ability, the victory points and the requirements can be edited.
 */
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

    /**
     * It is called when the ok button is clicked. It calls the other methods that save the changes on the file json
     * @param event the click on the ok button
     */
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

    /**
     * sets the ability on discount
     * @param event the discount button clicked
     */
    public void discount(ActionEvent event){
        specialAbility= "DISCOUNT";
    }

    /**
     * sets the ability on marble
     * @param event the click of the marble button
     */
    public void marble(ActionEvent event){
        specialAbility= "WHITE_MARBLE";
    }

    /**
     * sets the ability on production power
     * @param event the click of the production button
     */
    public void additionalProductionPower(ActionEvent event){
        specialAbility= "PRODUCTION_POWER";
    }
    /**
     * sets the ability on depot
     * @param event the click of the depot button
     */
    public void depotSpecial(ActionEvent event){
        specialAbility= "DEPOT";
    }

    /**
     * sets the resource on coin
     * @param event the click of the coin button
     */
    public void coinResource(ActionEvent event){
        resource= Coin.getInstance().toString();
    }

    /**
     * sets the resource on shield
     * @param event the click of the shield button
     */
    public void shieldResource(ActionEvent event){
        resource= Shield.getInstance().toString();
    }

    /**
     * sets the resource on stone
     * @param event the click of the stone button
     */
    public void stoneResource(ActionEvent event){
        resource= Stone.getInstance().toString();
    }

    /**
     * sets the resource on servant
     * @param event the click of the servant button
     */
    public void servantResource(ActionEvent event){
        resource= Servant.getInstance().toString();
    }

    /**
     * Goes back to the main stage to start to edit again doesn't save the changes
     * @param event the click on the discard button
     */
    public void discard(ActionEvent event){
        try {
            Editor.setRoot("editor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts text value in integers
     */
    public void init(){
        coins= Integer.parseInt(coin.getText());
        shields= Integer.parseInt(shield.getText());
        servants= Integer.parseInt(servant.getText());
        stones= Integer.parseInt(stone.getText());
        victoryPoints= Integer.parseInt(points.getText());
    }

    /**
     * Makes the map of cost selecting the only requirements with more than 0 as a value
     * @return requirement map
     */
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


    /**
     * Writes the changes on the json file. It adds a new leader card if the id isn't present already otherwise it
     * changes an existing leader card
     * @param costs requirements map
     * @param pointVic victory points
     * @param idCard the id of the card
     * @param special the ability of the card
     * @param resource the resource of the card
     */
    public void writeFile(Map<String, Integer> costs, Integer pointVic, Integer idCard, String special, String resource){
        Gson gson= new Gson();
        try {
            FileReader reader= new FileReader("src/main/resources/JSONs/editedSettings.json");
            JsonObject jsonObject= gson.fromJson(reader,JsonObject.class);
            JsonArray listOfCards= jsonObject.getAsJsonArray("LeaderCards");
            JsonObject cardSelected;
            if(idCard<listOfCards.size()+48 && idCard>48) {
                cardSelected = (JsonObject) listOfCards.get(idCard - 49);

                cardSelected.remove("requirements");
            }
            else {
                cardSelected= new JsonObject();
                cardSelected.addProperty("id", idCard);
                listOfCards.add(cardSelected);
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



            FileWriter writer= new FileWriter("ssrc/main/resources/JSONs/editedSettings.json");
            writer.write(jsonObject.toString());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
