package Main;

import Exceptions.GameException;
import MessagesBase.HalfMap;
import MessagesBase.HalfMapNode;
import MessagesBase.PlayerMove;
import MessagesBase.PlayerRegistration;
import MessagesBase.Terrain;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.FortState;
import MessagesGameState.FullMap;
import MessagesGameState.FullMapNode;
import MessagesGameState.GameState;
import MessagesGameState.PlayerGameState;
import MessagesGameState.PlayerPositionState;
import MessagesGameState.PlayerState;
import MessagesGameState.TreasureState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.NotNull;

public class GameInformation {

  private static final Logger LOGGER = Logger.getLogger( GameInformation.class.getName() );

  private static final Random RANDOM = new Random();

//  Map to save GameStates
  private static final Map<String, GameInformation> GAMES = new WeakHashMap<>();

  private final List<Player> players = new ArrayList<>(2);

  private int rounds = 0;

  private final Terrain[][] map = new Terrain[8][8];

  //translator is used for a second part of a map (Matrix 4x8 down from a matrix 0x3)
  private static final Position TRANSLATOR = new Position(0, 4);

  private boolean atLeastOneHalfMapReceived = false;

  private String stateId = UUID.randomUUID().toString();

  public UniquePlayerIdentifier register(PlayerRegistration registration) {
    if (players.size() >= 2) {
      throw new IllegalStateException("There already are two players registered!");
    }
    // NOTE: In the beginning, BOTH players should act next.
    // PlayerGameState state = players.isEmpty() ? PlayerGameState.ShouldActNext :
    // PlayerGameState.ShouldWait;

    PlayerGameState state = PlayerGameState.ShouldActNext;
    UniquePlayerIdentifier id = new UniquePlayerIdentifier(UUID.randomUUID().toString());
    PlayerState playerState =
        new PlayerState(
            registration.getStudentFirstName(),
            registration.getStudentLastName(),
            registration.getStudentID(),
            state,
            id,
            false);
    Player player = new Player(playerState);
    player.setIndex(players.size());

    // Connect players
    if (!players.isEmpty()) {
      LOGGER.log(Level.INFO, "Enemy was set");
      player.setEnemy(players.get(0));
      players.get(0).setEnemy(player);
    }

    players.add(player);
    LOGGER.log(Level.INFO, "Player was registered");
    return id;
  }

  // HalfMap registration
  public void registerHalfMap(HalfMap halfMap) {
    Player playerIndex = findPlayer(halfMap);

    if (playerIndex.isHalfMapRegistered()) {
      throw new GameException(GameException.DOUBLE_MAP_REGISTRATION, "Halfmap already registered");
    }

    atLeastOneHalfMapReceived = true;
    playerIndex.setHalfMapRegistered();
    LOGGER.log(Level.INFO, "Player was assigned a halfmap");

    // business rules checks
    long waterCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Water)).count();
    long grassCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Grass)).count();
    long mountainCount =
        halfMap.getNodes().stream().map(x -> x.getTerrain().equals(Terrain.Mountain)).count();
    long nodesCount = halfMap.getNodes().stream().count();


    if(waterCount<4){
      throw new GameException(GameException.NOT_ENOUGH_WATER, "More water nodes should be generated");
    }

    if(grassCount<15){
      throw new GameException(GameException.NOT_ENOUGH_GRASS, "More grass nodes should be generated");
    }

    if(mountainCount<3){
      throw new GameException(GameException.NOT_ENOUGH_WATER, "More mountain nodes should be generated");
    }
    if(nodesCount!=32){
      throw new GameException(GameException.MAP_NOT_DEFINED_COMPLETELY, "wrong nodes number");
    }

    // TODO: Check that counts are within limits and throw exceptions


    // checking for a fort to place a Player there
    Optional<HalfMapNode> fort =
        halfMap.getNodes().stream().filter(HalfMapNode::isFortPresent).findAny();
    if (!fort.isPresent()) {
      throw new GameException(GameException.NO_FORT_DEFINED, "No fort");
    }
    // checking for a Grass Terrain to place a treasure
    Optional<HalfMapNode> treasure =
        halfMap
            .getNodes()
            .stream()
            .filter(x -> x.getTerrain().equals(Terrain.Grass))
            .filter(x -> !x.isFortPresent())
            .findAny();

    // no place for a treasure

    if (!treasure.isPresent()) {
      throw new GameException(GameException.NOT_ENOUGH_GRASS, "No grass!");
    }

    halfMap
        .getNodes()
        .forEach(
            x -> {
              Position position = Position.fromHalfMapNode(x);
              if (playerIndex.getIndex() == 1) {
                position = position.translate(TRANSLATOR);
              }
              map[position.getX()][position.getY()] = x.getTerrain();
            });

    // getting a fort from a Halfmap, placing a player on a Fort

    Position position = Position.fromHalfMapNode(fort.get());
    if (playerIndex.getIndex() == 1) {
      position = position.translate(TRANSLATOR);
    }
    playerIndex.setFort(position);
    playerIndex.setPosition(position);

    // placing a treasure on both Halfmaps
    position = Position.fromHalfMapNode(treasure.get());
    if (playerIndex.getIndex() == 1) {
      position = position.translate(TRANSLATOR);
    }
    playerIndex.setTreasure(position);
    stateChange();
  }
  // state consisting from fullmap, playerState and state ID
  public GameState getGameState(String playerId) {
    final Player player = findPlayer(playerId);
    return new GameState(getFullMap(player), hideOther(player), stateId);
  }

  //PlayerState should be returned even if only one player was registered
  private List<PlayerState> hideOther(Player player) {
    List<PlayerState> result = new ArrayList<>(2);
    result.add(player.getState());
    if (player.getEnemy() == null) {
      return result;
    }

    // getting enemy state and hiding its ID
    PlayerState original = player.getEnemy().getState();
    result.add(
        new PlayerState(
            original.getFirstName(),
            original.getLastName(),
            original.getStudentID(),
            original.getState(),
            // NOTE: Hide player ID of the enemy player.
            new UniquePlayerIdentifier(""),
            original.hasCollectedTreasure()));
    return result;
  }

  // empty optional if no map was registered
  private Optional<FullMap> getFullMap(Player player) {
    if (!atLeastOneHalfMapReceived) {
      LOGGER.log(Level.WARNING, "NO MAP was RECIEVED");
      return Optional.empty();
    }

    Position fakeEnemyPosition =
        new Position(RANDOM.nextInt(map.length), RANDOM.nextInt(map.length));

    List<FullMapNode> nodes = new ArrayList<>(8 * 4 * 2);
    for (int x = 0; x < map.length; x++) {
      for (int y = 0; y < map[x].length; y++) {
        if (map[x][y] == null) {
          continue;
        }
        boolean fakeEnemyPositionHere =
            (rounds <= 10) && fakeEnemyPosition.equals(new Position(x, y));
        FullMapNode fullMapNode = view(player, new Position(x, y), fakeEnemyPositionHere);
        nodes.add(fullMapNode);
      }
    }
    return Optional.of(new FullMap(nodes));
  }

  // finding a player from a list of players
  @NotNull
  private Player findPlayer(String playerId) {
    return players
        .stream()
        .filter(x -> x.getState().getUniquePlayerID().equals(playerId))
        .findAny()
        .orElseThrow(() -> new GameException(GameException.UNKOWN_PLAYER, "Unkown player"));
  }

  @NotNull
  private Player findPlayer(UniquePlayerIdentifier id) {
    return findPlayer(id.getUniquePlayerID());
  }

  // returning each single node
  private FullMapNode view(Player player, Position position, boolean fakeEnemyHere) {

      // default for non treasure/player/fort node
      TreasureState treasureState = TreasureState.NoOrUnknownTreasureState;
      FortState fortState = FortState.NoOrUnknownFortState;
      PlayerPositionState playerPositionState = PlayerPositionState.NoPlayerPresent;


      // cases for presence of treasure/player/fort node
      if (position.equals(player.getPosition())) {
        playerPositionState = PlayerPositionState.MyPosition;
      }

      if (position.equals(player.getFort())) {
        fortState = FortState.MyFortPresent;
      }

      // initial check of rounds (2 turns) if move was implemented so that enemy position becomes clear after 10 rounds
      if ((rounds > 10 && position.equals(player.getEnemy().getPosition())) || fakeEnemyHere) {
        if (playerPositionState == PlayerPositionState.MyPosition) {
          playerPositionState = PlayerPositionState.BothPlayerPosition;
        } else {
          playerPositionState = PlayerPositionState.EnemyPlayerPosition;
        }
      }

      if (rounds > 10) {
      if (position.equals(player.getEnemy().getFort())) {
        fortState = FortState.EnemyFortPresent;
    }

      if (!player.getState().hasCollectedTreasure()) {
        if (position.equals(player.getTreasure())) {
          treasureState = TreasureState.MyTreasureIsPlacedHere;
        }
      }
    }

    return new FullMapNode(
        map[position.getX()][position.getY()],
        playerPositionState,
        treasureState,
        fortState,
        position.getX(),
        position.getY());
  }

  public void move(PlayerMove move) {
    Player player = findPlayer(move);
    // TODO: Check whether this move is possible.
    stateChange();
  }

  private void stateChange() {
    stateId = UUID.randomUUID().toString();
  }

  public static GameInformation getInstance(UniqueGameIdentifier id) {
    return getInstance(id.getUniqueGameID());
  }
  // Ggetting a game state - GameInformation
  public static GameInformation getInstance(String gameId) {
    GameInformation result = GAMES.get(gameId);
    if (result == null) {
      throw new GameException(GameException.GAME_DOES_NOT_EXIST, "Game does not exist");
    }
    return result;
  }

  // creating an instandce for a gameState - GameInformation and saving it in GAMES
  public static UniqueGameIdentifier createInstance() {
    // defensive mechanism for generation
    int tries = 0;
    while (tries++ < 100) {
      String id = UUID.randomUUID().toString();
      if (GAMES.containsKey(id)) {
        continue;
      }
      GAMES.put(id, new GameInformation());
      LOGGER.log(Level.INFO, "State was saved into GAMES");
      return new UniqueGameIdentifier(id);
    }
    throw new RuntimeException("Could not generate a unique ID");
  }
}
