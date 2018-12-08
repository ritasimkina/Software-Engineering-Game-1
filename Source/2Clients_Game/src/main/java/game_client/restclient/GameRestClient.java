package game_client.restclient;

import java.util.UUID;


import game_client.messages.*;
import org.springframework.web.client.RestTemplate;


public class GameRestClient {

    //public final String SERVER_URI = "http://swe.wst.univie.ac.at:18235";
    String serverUri = "http://swe.wst.univie.ac.at:18235";

    public GameRestClient(String serverUri) {
        this.serverUri = serverUri;
    }

    public GameIdentifier createNewGame() {

        String endpointURI = "/game/new";
        System.out.println("createNewGame: " + serverUri + endpointURI);

        RestTemplate restTemplate = new RestTemplate();
        GameIdentifier gameIdentifier = restTemplate.getForObject(serverUri + endpointURI, GameIdentifier.class);

        return gameIdentifier;
    }

    /**
     * @param game               Game by Id
     * @param playerRegistration registered Player
     * @return server response for registration
     */
    public ResponseEnvelopePlayerReg registerToGame(GameIdentifier game, PlayerRegistration playerRegistration) {
        String endpointURI = "/game/" + game.getUniqueGameID() + "/register";
        System.out.println("registerToGame: " + serverUri + endpointURI);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEnvelopePlayerReg registrationResponse = restTemplate.postForObject(serverUri + endpointURI, playerRegistration, ResponseEnvelopePlayerReg.class);

        return registrationResponse;
    }

    /**
     * @param game    Game by Id
     * @param halfMap generated half of a map
     * @return half map as a message
     */
    public ResponseEnvelope sendHalfOfMap(GameIdentifier game, HalfMap halfMap) {
        String endpointURI = "/game/" + game.getUniqueGameID() + "/halfmap";

        System.out.println("sendHalfOfMap: " + serverUri + endpointURI);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(serverUri + endpointURI, halfMap, ResponseEnvelope.class);
    }


    /**
     * @param game           by Id
     * @param uniquePlayerID Player by Id
     * @return current state and positions
     */

    public GameState getGameState(GameIdentifier game, UUID uniquePlayerID) {
        String endpointURI = "/game/" + game.getUniqueGameID() + "/state/" + uniquePlayerID;

        System.out.println("getGameState: " + serverUri + endpointURI);

        RestTemplate restTemplate = new RestTemplate();
        GameState gameState = restTemplate.getForObject(serverUri + endpointURI, GameState.class);
        return gameState;
    }


    /**
     * @param game           by Id
     * @param uniquePlayerID player id
     * @return move action
     */

    public ResponseEnvelope makeMove(GameIdentifier game, UUID uniquePlayerID, String move) {
        String endpointURI = "/game/" + game.getUniqueGameID() + "/move";
        System.out.println("makeMove: " + serverUri + endpointURI);

        AvatarMove avatarMove = new AvatarMove(uniquePlayerID, move);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEnvelope moveResponse = restTemplate.postForObject(serverUri + endpointURI, avatarMove, ResponseEnvelope.class);
        return moveResponse;
    }


}
