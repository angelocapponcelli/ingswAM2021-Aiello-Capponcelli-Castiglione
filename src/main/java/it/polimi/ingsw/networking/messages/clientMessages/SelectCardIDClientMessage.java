package it.polimi.ingsw.networking.messages.clientMessages;

public class SelectCardIDClientMessage extends ClientMessage {
    protected Integer id;

    /**
     * o posizione
     */

    public SelectCardIDClientMessage(String nickname, Integer id) {
        super(nickname);
        this.id = id;
    }

    @Override
    public Boolean check() {
        return true;
    }

    public Integer getId() {
        return id;
    }
}
