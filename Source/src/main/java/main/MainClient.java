package main;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.web.client.RestClientException;

import dataobjects.GameID;
import dataobjects.PlayerInformation;
import network.ServerConnection;

public class MainClient {

	public static void main(String[] args) throws Exception {
		
		/* Bis zur Vorstellung von Teilaufgabe 2 ist hier noch der Server aus dem
		 * Vorsemester zu finden. Es kann leichte Änderungen im Verhalten geben, 
		 * das Netzwerkprotokoll ist aber identisch. 
		 */
		URL baseServerURL = new URL("http://swe.wst.univie.ac.at:18235/");
		
		ServerConnection server = new ServerConnection(baseServerURL);
		GameID gameID = server.createNewGame();
		
		server.registerNewPlayer(gameID, new PlayerInformation());
		server.registerNewPlayer(gameID, new PlayerInformation());
		//server.registerNewPlayer(gameID, new PlayerInformation());
	}

}
