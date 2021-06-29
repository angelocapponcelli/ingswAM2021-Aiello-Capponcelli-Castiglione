package it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage;

import it.polimi.ingsw.client.view.reducedGameModel.ReducedVaticanReportSection;
import it.polimi.ingsw.networking.messages.MessageType;
import it.polimi.ingsw.networking.messages.serverMessage.ServerMessage;

import java.util.List;

public class UpdatedVaticanReportMessage extends ServerMessage {
    private final List<ReducedVaticanReportSection> updatedVaticanReportSections;

    public UpdatedVaticanReportMessage(List<ReducedVaticanReportSection> justFlipped) {
        messageType = MessageType.UPDATED_VATICAN_REPORT_SECTION;
        this.updatedVaticanReportSections = justFlipped;
    }

    public List<ReducedVaticanReportSection> getUpdatedVaticanReportSections() {
        return updatedVaticanReportSections;
    }
}
