package it.polimi.ingsw.networking.messages.clientMessages;


import it.polimi.ingsw.networking.messages.MessageType;

public class SwapShelvesClientMessage extends ClientMessage {
    private Integer shelves1;
    private Integer shelves2;


    public SwapShelvesClientMessage(String nickname, Integer shelves1, Integer shelves2) {
        super(nickname);
        this.shelves1 = shelves1;
        this.shelves2 = shelves2;
        this.messageType= MessageType.SWAP_SHELVES;
    }

    @Override
    public Boolean check() {
        if (this.shelves1 == this.shelves2) {
            return false;
        }
        if (this.shelves1 < 0 || this.shelves1 > 2) {
            return false;
        }
        if (this.shelves2 < 0 || this.shelves2 > 2) {
            return false;
        } else
            return true;
    }

    public Integer getShelves1() {
        return shelves1;
    }

    public Integer getShelves2() {
        return shelves2;
    }
}
