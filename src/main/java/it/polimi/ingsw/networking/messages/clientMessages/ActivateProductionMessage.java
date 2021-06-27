package it.polimi.ingsw.networking.messages.clientMessages;

import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.server.model.resources.ResourceType;

public class ActivateProductionMessage extends ClientMessage {
    private final ResourceType input1;
    private final ResourceType input2;
    private final ResourceType outPut;

    public ActivateProductionMessage(String nickname, ResourceType input1, ResourceType input2, ResourceType outPut) {
        super(nickname);
        messageType = MessageType.ACTIVATE_PRODUCTION;
        this.input1 = input1;
        this.input2 = input2;
        this.outPut = outPut;
    }


    public ResourceType getInput1() {
        return input1;
    }

    public ResourceType getInput2() {
        return input2;
    }

    public ResourceType getOutPut() {
        return outPut;
    }
}
