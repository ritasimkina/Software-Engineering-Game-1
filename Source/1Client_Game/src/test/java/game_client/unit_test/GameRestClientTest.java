package game_client.unit_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import game_client.messages.GameIdentifier;
import game_client.restclient.GameRestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(GameRestClient.class)
public class GameRestClientTest {

    public final String SERVER_URI = "http://swe.wst.univie.ac.at:18235";
    String endpointURI = "/game/new";

    @Autowired
    private GameRestClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        String gameString =
                objectMapper.writeValueAsString(new GameIdentifier("12345"));

        this.server.expect(requestTo(SERVER_URI + endpointURI))
                .andRespond(withSuccess(gameString, MediaType.APPLICATION_XML));
    }

    @Test
    public void whenCallingGetUserDetails_thenClientMakesCorrectCall()
            throws Exception {

        GameIdentifier gameIdentifier = this.client.createNewGame();
        System.out.println(gameIdentifier);
        Assert.assertEquals(gameIdentifier.getUniqueGameID().length(),5);
    }

}
