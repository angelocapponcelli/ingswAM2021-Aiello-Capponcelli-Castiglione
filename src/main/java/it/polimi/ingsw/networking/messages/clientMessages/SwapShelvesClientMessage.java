package it.polimi.ingsw.networking.messages.clientMessages;


import it.polimi.ingsw.networking.messages.MessageType;

/**
 * Swap shelves message
 */
public class SwapShelvesClientMessage extends ClientMessage {
    private final Integer shelves1;
    private final Integer shelves2;


    public SwapShelvesClientMessage(String nickname, Integer shelves1, Integer shelves2) {
        super(nickname);
        this.shelves1 = shelves1;
        this.shelves2 = shelves2;
        this.messageType = MessageType.SWAP_SHELVES;
    }


    public Integer getShelves1() {
        return shelves1;
    }

    public Integer getShelves2() {
        return shelves2;
    }
}
