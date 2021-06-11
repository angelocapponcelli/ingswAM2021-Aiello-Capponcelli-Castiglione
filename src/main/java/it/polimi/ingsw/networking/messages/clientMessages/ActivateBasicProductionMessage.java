package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;
import java.util.Map;

public class ActivateBasicProductionMessage extends ClientMessage {
    private final Map<ResourceType, Integer> input;
    private final Map<ResourceType, Integer> output;

    public ActivateBasicProductionMessage(String nickname, Map<ResourceType, Integer> input, Map<ResourceType, Integer> output) {
        super(nickname);
        messageType = MessageType.ACTIVATE_BASIC_PRODUCTION;
        this.input =input;
        this.output = output;
    }

    public Map<ResourceType, Integer> getInput() {
        return input;
    }

    public Map<ResourceType, Integer> getOutput() {
        return output;
    }

}
