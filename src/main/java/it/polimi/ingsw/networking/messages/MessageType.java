package it.polimi.ingsw.networking.messages;

import it.polimi.ingsw.networking.messages.serverMessage.UpdateViewMessage.UpdatedInHandLeaderCardMessage;

public enum MessageType {
    NEW_GAME,
    UPDATED_MARKET_TRAY,
    JOIN_GAME,
    NICKNAME,
    SELECT_CARD,
    REALLOCATE_RESOURCE,
    SELECT_DECK_FROM_GRID,
    TAKE_FROM_MARKET,
    SWAP_SHELVES,
    SELECT_LEADER_CARD,
    SELECT_LEADER_CARD_TO_DISCARD,
    SELECT_DEVELOPMENT_CARD,
    SELECT_DECK_FROM_PERSONAL,
    CHOOSE_MAIN_ACTION,
    SELECT_RESOURCE,
    TEXT,
    ERROR,
    BUY_DEV_CARD,
    ACTIVATE_PRODUCTION,
    UPDATED_PERSONAL_DEVELOPMENT_BOARD,
    UPDATED_IN_HAND_LEADER_CARD


}
