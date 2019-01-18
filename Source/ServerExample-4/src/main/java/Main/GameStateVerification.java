package Main;

import Exceptions.GameException;
import MessagesBase.HalfMap;
import MessagesBase.HalfMapNode;
import MessagesBase.PlayerRegistration;
import MessagesBase.ResponseEnvelope;
import MessagesBase.Terrain;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.FullMap;
import MessagesGameState.PlayerState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Deprecated
public class GameStateVerification {

  private static final Logger LOGGER = Logger.getLogger(GameStateVerification.class.getName());

  public static UniqueGameIdentifier gameID;

  public static PlayerRegistration playerRegistration0;
  public static PlayerRegistration playerRegistration1;
  public static ResponseEnvelope playerRegistrationResponse0;
  public static ResponseEnvelope playerRegistrationResponse1;

  public static HalfMap playerHalfMap0;
  public static HalfMap playerHalfMap1;

  static List<PlayerState> players = new ArrayList<>();

  public static ResponseEnvelope registerNextPlayer(
      PlayerRegistration registration, UniquePlayerIdentifier newPlayerID) {
    // System.out.println("received UniquePlayerIdentifier Value :" + newPlayerID );
    // System.out.println("received registration Value :" + registration );

    if (playerRegistration0 == null) {
      /**
       * if (!registration.isRegistrationValid()) { "Invalid player registration has been attempted!
       * 1 " + newPlayerID.getUniquePlayerID()); return new ResponseEnvelope(
       * GameException.INVALID_PLAYER_REGISTRATION, "Invalid player registration!"); }
       */
      playerRegistration0 = registration;
      MessagesGameState.PlayerGameState state = MessagesGameState.PlayerGameState.Won;
      PlayerState player =
          new PlayerState(
              registration.getStudentFirstName(),
              registration.getStudentLastName(),
              registration.getStudentID(),
              state,
              newPlayerID,
              true);
      players.add(player);
      LOGGER.log(Level.INFO, player.getUniquePlayerID() + "    Registered first player.");
      return playerRegistrationResponse0 = new ResponseEnvelope();
    } else if (playerRegistration1 == null) {
      /*
      if (!registration.isRegistrationValid()) {
        LOGGER.log(
            Level.WARNING,
            "Invalid player registration has been attempted! 2  "
                + newPlayerID.getUniquePlayerID());
        return new ResponseEnvelope(
            GameException.INVALID_PLAYER_REGISTRATION, "Invalid player registration!");
      }
      */

      playerRegistration1 = registration;
      MessagesGameState.PlayerGameState state = MessagesGameState.PlayerGameState.Won;
      PlayerState player =
          new PlayerState(
              registration.getStudentFirstName(),
              registration.getStudentLastName(),
              registration.getStudentID(),
              state,
              newPlayerID,
              true);
      players.add(player);
      LOGGER.log(Level.INFO, player.getUniquePlayerID() + "    Registered second player.");
      return playerRegistrationResponse1 = new ResponseEnvelope();
    } else {
      LOGGER.log(Level.WARNING, "Registration to full game has occurred!");
      return new ResponseEnvelope(
          GameException.TOO_MANY_PLAYERS, "Game has already enough (two) players!");
    }
  }

  public static boolean f(HalfMapNode x) {
    return x.getTerrain().equals(Terrain.Water);
  }

  public static ResponseEnvelope<HalfMap> registerHalfMap(HalfMap halfMap) {
    if (halfMap.getUniquePlayerID() == null)
      return new ResponseEnvelope<>(
          GameException.NULL_MAP_NOT_REGISTERED, "Could not register null half map!");

    long waterCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Water)).count();
    long grassCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Grass)).count();
    long mountainCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Mountain)).count();

    if (waterCount < 4 || grassCount < 15 || mountainCount < 3) {
      return new ResponseEnvelope<>("Bad Map", "Given half map is invalid!");
    }

    if (playerRegistration0 != null
        && players.get(0).getUniquePlayerID().equals(halfMap.getUniquePlayerID())) {
      if (playerHalfMap0 != null) {
        LOGGER.log(Level.INFO, players.get(0).getUniquePlayerID() + "  Map already registered. 1");
        return new ResponseEnvelope<>(
            GameException.DOUBLE_MAP_REGISTRATION,
            "Two map registrations are not allowed!"); // Second registration is not allowed
      }

      playerHalfMap0 = halfMap;
      LOGGER.log(Level.INFO, "Registered map for first player.");

      // dataRegistrationUtil.setSavedMap(halfMap);

      return new ResponseEnvelope<>();
    }

    if (playerRegistration1 != null
        && players.get(1).getUniquePlayerID().equals(halfMap.getUniquePlayerID())) {
      if (playerHalfMap1 != null) {
        LOGGER.log(Level.INFO, players.get(1).getUniquePlayerID() + "  Map already registered. 2");
        return new ResponseEnvelope<>(
            GameException.DOUBLE_MAP_REGISTRATION,
            "Two map registrations are not allowed!"); // Second registration is not allowed
      }

      LOGGER.log(Level.INFO, "Registered map for second player.");
      playerHalfMap1 = halfMap;
      // dataRegistrationUtil.setSavedMap(halfMap);
      return new ResponseEnvelope<>();
    }

    LOGGER.log(
        Level.INFO, halfMap.getUniquePlayerID() + "  Ignored map registration for unknown player");
    return new ResponseEnvelope<>(
        GameException.IGNORED_MAP_REGISTRATION,
        "Ignored map registration for unknown peayer!"); // Second registration is not allowed;
  }

  public static void fetchGameData() {
    FullMap fullmap = new FullMap();
    Optional<FullMap> optional = Optional.of(fullmap);

    // LOGGER.log(Level.INFO, "gameState" + gameState);
  }
}
