package it.polimi.ingsw.networking.messages.clientMessages;

public class SelectCardIDClientMessage extends ClientMessage {
    protected final Integer id;

    public SelectCardIDClientMessage(String nickname, Integer id) {
        super(nickname);
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
