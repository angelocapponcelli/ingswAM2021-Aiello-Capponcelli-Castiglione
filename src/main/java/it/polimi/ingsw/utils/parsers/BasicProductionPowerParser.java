package it.polimi.ingsw.utils.parsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.model.productionPower.ProductionPower;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerInput;
import it.polimi.ingsw.server.model.productionPower.ProductionPowerOutput;
import it.polimi.ingsw.server.model.resources.ResourceType;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class BasicProductionPowerParser {

    public static ProductionPower getBasicProductionPower() throws FileNotFoundException {

        JsonObject jsonObject = JsonParser.parseReader(new FileReader("src/main/resources/BasicProductionPower.json")).getAsJsonObject();
        JsonArray tmpInput = jsonObject.getAsJsonArray("productionPowerInput");
        JsonArray tmpOutput = jsonObject.getAsJsonArray("productionPowerOutput");

        ProductionPowerInput input = new ProductionPowerInput();
        ProductionPowerOutput output = new ProductionPowerOutput();

        for(int i = 0; i < tmpInput.size(); i++){
            switch (tmpInput.get(i).getAsJsonObject().get("resourceType").getAsString()){
                case "ANY":
                    input.add(ResourceType.getResourceClass(ResourceType.ANY), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "COIN":
                    input.add(ResourceType.getResourceClass(ResourceType.COIN), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "STONE":
                    input.add(ResourceType.getResourceClass(ResourceType.STONE), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SHIELD":
                    input.add(ResourceType.getResourceClass(ResourceType.SHIELD), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SERVANT":
                    input.add(ResourceType.getResourceClass(ResourceType.SERVANT), tmpInput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
            }
        }

        for(int i = 0; i < tmpOutput.size(); i++){
            String s = tmpOutput.get(i).getAsJsonObject().get("resourceType").getAsString();
            switch (s){
                case "ANY":
                    output.add(ResourceType.ANY, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "COIN":
                    output.add(ResourceType.COIN, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "STONE":
                    output.add(ResourceType.STONE, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SHIELD":
                    output.add(ResourceType.SHIELD, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
                case "SERVANT":
                    output.add(ResourceType.SERVANT, tmpOutput.get(i).getAsJsonObject().get("multiplicity").getAsInt());
                    break;
            }

        }


        return new ProductionPower(input,output);



    }
}

