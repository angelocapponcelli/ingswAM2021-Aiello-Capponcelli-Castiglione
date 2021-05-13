package it.polimi.ingsw.networking.ClientMessage;

public class ChooseMainActionClientMessage extends ClientMessage {

    private String string;

    public ChooseMainActionClientMessage(String nickname, String s) {
        super(nickname);
        this.string = s;
    }

    @Override
    public Boolean check() {
        if (this.string != "Take" || this.string != "Buy" || this.string != "Produce") {
            return false;
        }
        return true;
    }
}
