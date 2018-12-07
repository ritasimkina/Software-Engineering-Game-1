package network;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dataobjects.GameID;
import dataobjects.PlayerID;
import dataobjects.PlayerInformation;
import dataobjects.UniqueID;
import messages.GameIdentifier;
import messages.PlayerIdentifier;
import messages.RegisterPlayer;
import messages.ResponseEnvelope;

public class ServerConnection {

	private final URL baseUrl;
	
	public ServerConnection(URL baseUrl)
	{
		this.baseUrl = baseUrl;
	}
	
	public GameID createNewGame() throws Exception
	{
		URL gameNewUrl = new URL(baseUrl, "game/new");
		RestTemplate restTemplate = new RestTemplate();
		GameIdentifier gameIdentifierMessage = restTemplate.getForObject(gameNewUrl.toURI(), GameIdentifier.class);
				 
		return new GameID(gameIdentifierMessage);
	}
	
	public PlayerID registerNewPlayer(GameID gameID, PlayerInformation playerInfo) throws Exception
	{
		RegisterPlayer payload = new RegisterPlayer(playerInfo.getMatnr(), playerInfo.getFirstname(), playerInfo.getLastname());
		
		URL registerPlayerUrl = new URL(baseUrl, "game/"+gameID.getId()+"/register");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEnvelope<PlayerIdentifier> playerIdMessage = restTemplate.postForObject(registerPlayerUrl.toURI(), payload, ResponseEnvelope.class);
		
		return new PlayerID(playerIdMessage.getData());
	}
}
