package Exceptions;

/* Note, this is just a quick minimal example to show the generic exception handling
 * functionality in spring. Typically I would recommend that you create individual exception types
 * for each unique kind of exception (e.g., individual exceptions for different kinds or rule errors)
 *
 * For simplicity reasons your exceptions could, e.g., inherit from the one given below
 */

import MessagesBase.ResponseEnvelope;

public class GameException extends RuntimeException {
    public static final String GAME_DOES_NOT_EXIST = "GAME_DOES_NOT_EXIST";
    public static final String TOO_MANY_PLAYERS = "TOO_MANY_PLAYERS";
    public static final String INVALID_PLAYER_REGISTRATION = "INVALID_PLAYER_REGISTRATION";

    public static final String DOUBLE_MAP_REGISTRATION = "DOUBLE_MAP_REGISTRATION";
    public static final String IGNORED_MAP_REGISTRATION = "IGNORED_MAP_REGISTRATION";

    public static final String NO_MAP_NODES = "NO_NEW_MAP_NODES";
    public static final String MAP_NODE_OUT_OF_BOUNDS = "NEW_MAP_NODE_OUT_OF_BOUNDS";
    public static final String DUPLICATE_MAP_ENTRY = "DUPLICATE_MAP_ENTRY";
    public static final String INVALID_MAP_TERRAIN = "INVALID_MAP_TERRAIN";
    public static final String MULTIPLE_FORTS_DEFINED = "MULTIPLE_FORTS_DEFINED";
    public static final String NO_FORT_DEFINED = "NO_FORT_DEFINED";
    public static final String MAP_NOT_DEFINED_COMPLETELY = "MAP_NOT_DEFINED_COMPLETELY";
    public static final String WATER_ON_EDGES = "WATER_ON_EDGES";

    public static final String NOT_ENOUGH_GRASS = "NOT_ENOUGH_GRASS";
    public static final String NOT_ENOUGH_WATER = "NOT_ENOUGH_WATER";
    public static final String NOT_ENOUGH_MOUNTAIN = "NOT_ENOUGH_MOUNTAIN";
    public static final String NULL_MAP_NOT_REGISTERED = "MAP_NOT_REGISTERED";
    public static final String UNKOWN_PLAYER = "UNKNOWN_PLAYER";

    private final String errorName;

    public GameException(String errorName, String errorMessage) {
        super(errorMessage);
        this.errorName = errorName;
    }

    public String getErrorName() {
        return errorName;
    }

    public ResponseEnvelope<?> toResponseEnvelope() {
        return new ResponseEnvelope<>(errorName, getMessage());
    }
}
