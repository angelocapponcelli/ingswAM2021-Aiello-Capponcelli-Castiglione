package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;
import java.util.Map;

/**
 * Activate basic production power
 */
public class ActivateBasicProductionMessage extends ClientMessage {
    private final Map<ResourceType, Integer> input;
    private final Map<ResourceType, Integer> output;

    /**
     * Class constructor
     * @param nickname the nickname of the real player
     * @param input map of input production
     * @param output map of output production
     */
    public ActivateBasicProductionMessage(String nickname, Map<ResourceType, Integer> input, Map<ResourceType, Integer> output) {
        super(nickname);
        messageType = MessageType.ACTIVATE_BASIC_PRODUCTION;
        this.input =input;
        this.output = output;
    }

    /**
     *
     * @return map of input production
     */
    public Map<ResourceType, Integer> getInput() {
        return input;
    }

    /**
     *
     * @return map of output production
     */
    public Map<ResourceType, Integer> getOutput() {
        return output;
    }

}
