package game_client;

import game_client.messages.*;
import game_client.restclient.GameRestClient;
import game_client.service.*;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public final static int HALF_MAP_ROWS = 4;
    public final static int HALF_MAP_COLS = 8;

    /**
     * Initialization of messaging
     */

    public static void main(String[] args) {

        String serverUri;

        String[] argumentsOfMain = new String[3];
        argumentsOfMain[0] = args[0];
        argumentsOfMain[1] = args[1];
        argumentsOfMain[2] = args[2];

        System.out.println("argumentsOfMain[0]=" + argumentsOfMain[0]);
        System.out.println("argumentsOfMain[1]=" + argumentsOfMain[1]);
        System.out.println("argumentsOfMain[2]=" + argumentsOfMain[2]);

        if (args.length != 0) {
            serverUri = args[1];
        } else {
            serverUri = "http://swe.wst.univie.ac.at:18235/";
        }

        GameRestClient client = new GameRestClient(serverUri);

//creation of new game

        GameIdentifier gameId;
        gameId = client.createNewGame();

        LOGGER.log(Level.INFO, "Created new game, its ID is " + gameId.getUniqueGameID());

        System.out.println("gameId = " + gameId);


//registration of player1 and player2
        PlayerRegistration player1 = new PlayerRegistration("A123456", "Rita", "Simkina");
        ResponseEnvelopePlayerReg player1RegResponse = client.registerToGame(gameId, player1);
        UUID player1ID = player1RegResponse.getUniquePlayerID();

        if (player1ID == null) {
            // error
            return;
        }
        LOGGER.log(Level.INFO, "Player1 got ID " + player1ID.toString());


//        PlayerRegistration player2 = new PlayerRegistration("A654321", "Mr", "Roboto");
//        ResponseEnvelopePlayerReg player2RegResponse = client.registerToGame(gameId, player2);
//        UUID player2ID = player2RegResponse.getUniquePlayerID();

//        if (player2ID == null) {
            // error
//            return;
//        }
//        LOGGER.log(Level.INFO, "Player2 got ID " + player2ID.toString());

//sending of halfMaps of player1 and player2
        HalfMap halfMap1 = new HalfMapService(HALF_MAP_ROWS, HALF_MAP_COLS, true).generateHalfMapObject(player1ID);

        LOGGER.log(Level.INFO, "Generated map for Player1:");
        LOGGER.log(Level.INFO, halfMap1.toString());
        ResponseEnvelope envelope1 = client.sendHalfOfMap(gameId, halfMap1);


        if (!envelope1.isStateOK()) {
            LOGGER.log(Level.SEVERE, "The map for Player 1 has not been accepted by server! Reason: " + envelope1.getExceptionMessage());
            return;
        } else {
            System.out.println("Everything is FINE!(envelope1.isStateOK())");
            LOGGER.log(Level.INFO, "The map for Player 1 has been accepted by server.");
        }
//        HalfMap halfMap2 = new HalfMapService(HALF_MAP_ROWS, HALF_MAP_COLS, true).generateHalfMapObject(player2ID);
//        LOGGER.log(Level.INFO, "Generated map for Player2:");
//        LOGGER.log(Level.INFO, halfMap2.toString());
//        ResponseEnvelope envelope2 = client.sendHalfOfMap(gameId, halfMap2);

//        if (!envelope2.isStateOK()) {
 //           LOGGER.log(Level.SEVERE, "The map for Player 2 has not been accepted by server! Reason: " + envelope1.getExceptionMessage());
//            return;
 //       } else {
 //           System.out.println("Everything is FINE!(envelope2.isStateOK())");
 //           LOGGER.log(Level.INFO, "The map has for Player 2 been accepted by server.");
//        }

        System.out.println("halfMap1:" + "\n" + halfMap1);
//        System.out.println("wwwwwwwwwwwwwwwwwwwwwwww");
//        System.out.println("halfMap2:" + "\n" + halfMap2);

        IMoveIssuer moveIssuer1 = new RandomMoveIssuer();
//        IMoveIssuer moveIssuer2 = new RandomMoveIssuer();

        System.out.println("moveIssuer1=" + moveIssuer1);
//        System.out.println("moveIssuer2=" + moveIssuer2);

        while (true) {
            GameDisplayer displayer = new GameDisplayer();

            GameState gs1 = client.getGameState(gameId, player1ID);
//            GameState gs2 = client.getGameState(gameId, player2ID);

            System.out.println("GameState gs1 =" + gs1);
 //           System.out.println("GameState gs2 =" + gs2);

            if (gs1.getPlayerById(player1ID).getState().equals(Player.PLAYER_WON_STATE)) {
                LOGGER.log(Level.INFO, "Player1 has won the game!");
                break;
            }

//            if (gs2.getPlayerById(player2ID).getState().equals(Player.PLAYER_WON_STATE)) {
//                LOGGER.log(Level.INFO, "Player2 has won the game!");
//                break;
//            }

            HalfMapFullService map1 = gs1.getFullGameMap();
//            HalfMapFullService map2 = gs2.getFullGameMap();

            System.out.println("HalfMapFullService map1 =" + "\n" + map1);
//            System.out.println("HalfMapFullService map2 =" + "\n" + map2);

            System.out.println("-------------------");

            System.out.println("gs1.getPlayerById(player1ID).getState()=" + gs1.getPlayerById(player1ID).getState());
            if (gs1.getPlayerById(player1ID).getState().equals(Player.SHOULD_ACT_NEXT_STATE)) {
                LOGGER.log(Level.INFO, "PLAYER1 IS ON THE MOVE");

                System.out.println("~~~~~~~test~~~~~~~~~~");
                System.out.println("gameId=" + gameId);
                System.out.println("player1ID=" + player1ID);
                System.out.println("moveIssuer1.getNextMove(map1)=" + moveIssuer1.getNextMove(map1));
                System.out.println("~~~~~~~test~~~~~~~~~~");

                ResponseEnvelope response = client.makeMove(gameId, player1ID, moveIssuer1.getNextMove(map1));
                System.out.println(response);
                if (!response.isStateOK()) {
                    LOGGER.log(Level.SEVERE, "Your move has not been accepted! Reason: " + response.getExceptionMessage());
                }
            }

//            if (gs2.getPlayerById(player2ID).getState().equals(Player.SHOULD_ACT_NEXT_STATE)) {
//                LOGGER.log(Level.INFO, "PLAYER2 IS ON THE MOVE");
//                ResponseEnvelope response = client.makeMove(gameId, player2ID, moveIssuer2.getNextMove(map2));
 //               if (!response.isStateOK()) {
//                    LOGGER.log(Level.SEVERE, "Your move has not been accepted! Reason: " + response.getExceptionMessage());
//                }
//            }
            displayer.appendMap(map1);
//            displayer.appendMap(map2);

            //displayer.combineTheWholeMap(map1, map2);
            displayer.displayGame();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
