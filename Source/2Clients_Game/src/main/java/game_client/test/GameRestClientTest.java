package game_client.test;

import game_client.messages.PlayerRegistration;
import game_client.restclient.GameRestClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameRestClientTest {
    String serverUri = "http://swe.wst.univie.ac.at:18235";


    @Test
    public void createGameTest(){
        GameRestClient client = new GameRestClient(serverUri);
        assertEquals(5, client.createNewGame().getUniqueGameID().toString().length());
    }


    @Test
    public void registerToGameTest(){
        GameRestClient client = new GameRestClient(serverUri);
        PlayerRegistration player1 = new PlayerRegistration("A123456", "Rita", "Simkina");
        assertEquals(true, client.registerToGame(client.createNewGame(),player1).isStateOK());
    }
}
