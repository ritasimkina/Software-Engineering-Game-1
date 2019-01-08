package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import MessagesBase.HalfMap;
import MessagesBase.HalfMapNode;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.FullMap;
import MessagesGameState.GameState;
import MessagesGameState.PlayerState;




public class GameStateVerification {
	
	private static final Logger LOGGER = Logger.getLogger( GameStateVerification.class.getName() );
	
	public static GameIdentifier gameID;
	
	public static PlayerRegistration playerRegistration0;
	public static PlayerRegistration playerRegistration1;
	public static ResponseEnvelope playerRegistrationResponse0;
	public static ResponseEnvelope playerRegistrationResponse1;
	
	public static HalfMap playerHalfMap0;
	public static HalfMap playerHalfMap1;
	
	static List<PlayerState> players = new ArrayList<PlayerState>();
	static Data finalData = new Data();
	
	public static PlayerState player;

	
	public static void reset() { 
		gameID = null;
		playerRegistration0 = null;
		playerRegistration1 = null;
		playerRegistrationResponse0 = null;
		playerRegistrationResponse1 = null;
		playerHalfMap0 = null;
		playerHalfMap1 = null;
		
	}
	
	public static void registerNewGame(UniqueGameIdentifier gameIdentifier) {
		LOGGER.log(Level.INFO,  gameIdentifier.getUniqueGameID() + "    Game registered");
		Data gameData = new Data();
		gameData.setGameStateId(gameIdentifier);
		finalData = gameData;
	}
	
	public static ResponseEnvelope registerNextPlayer(PlayerRegistration registration, UniquePlayerIdentifier newPlayerID) {
		//System.out.println("received UniquePlayerIdentifier Value :" + newPlayerID );
		//System.out.println("received registration Value :" + registration );
		
		if(playerRegistration0 == null) {
			if(!registration.isRegistrationValid()) {
				LOGGER.log(Level.WARNING, "Invalid player registration has been attempted! 1  " + newPlayerID.getUniquePlayerID());
				return new ResponseEnvelope(Exceptions.INVALID_PLAYER_REGISTRATION, "Invalid player registration!", States.ERROR);
			}
			playerRegistration0 = registration;
			MessagesGameState.PlayerGameState state = MessagesGameState.PlayerGameState.Won;
			boolean collectedTreasure = true;
			PlayerState player = new PlayerState(registration.getStudentFirstName(),registration.getStudentLastName(),registration.getStudentID(),state,newPlayerID,collectedTreasure);
			players.add(player);
			LOGGER.log(Level.INFO,  player.getUniquePlayerID() + "    Registered first player.");			
			return playerRegistrationResponse0 = new ResponseEnvelope(null, null, States.OK);
		}
		else if(playerRegistration1 == null) {
			if(!registration.isRegistrationValid()) {
				LOGGER.log(Level.WARNING, "Invalid player registration has been attempted! 2  "  + newPlayerID.getUniquePlayerID());
				return new ResponseEnvelope(Exceptions.INVALID_PLAYER_REGISTRATION, "Invalid player registration!", States.ERROR);
			}
			
			playerRegistration1 = registration;
			MessagesGameState.PlayerGameState state = MessagesGameState.PlayerGameState.Won;	
			boolean collectedTreasure = true;
			PlayerState player = new PlayerState(registration.getStudentFirstName(),registration.getStudentLastName(),registration.getStudentID(),state,newPlayerID,collectedTreasure);
			players.add(player);
			LOGGER.log(Level.INFO,  player.getUniquePlayerID() + "    Registered second player.");	
			return playerRegistrationResponse1 = new ResponseEnvelope(null, null, States.OK);
		}
		else {
			LOGGER.log(Level.WARNING, "Registration to full game has occured!");
			return new ResponseEnvelope(Exceptions.TOO_MANY_PLAYERS, "Game has already enough (two) players!", States.ERROR);
		}
		
	}
	
	public static ResponseEnvelope registerHalfMap(HalfMap halfMap) {
		if(halfMap.getUniquePlayerID() == null)
			return new ResponseEnvelope(Exceptions.NULL_MAP_NOT_REGISTERED, "Could not register null half map!", States.ERROR);
		
		if(playerRegistration0 != null && player.getUniquePlayerID().equals(halfMap.getUniquePlayerID()))
		{
			if(playerHalfMap0 != null) {
				LOGGER.log(Level.INFO, player.getUniquePlayerID()+ "  Map already registered. 1");	
				return new ResponseEnvelope(Exceptions.DOUBLE_MAP_REGISTRATION, "Two map registrations are not allowed!", States.ERROR); // Second registration is not allowed
			}
				
				playerHalfMap0 = halfMap;
				LOGGER.log(Level.INFO, "Registered map for first player.");	
				
				
				//dataRegistrationUtil.setSavedMap(halfMap);
		
			return new ResponseEnvelope(null, null, States.OK);
		}
		
		if(playerRegistration1 != null && player.getUniquePlayerID().equals(halfMap.getUniquePlayerID()))
		{
			if(playerHalfMap1 != null) {
				LOGGER.log(Level.INFO, player.getUniquePlayerID()+ "  Map already registered. 2");
				return new ResponseEnvelope(Exceptions.DOUBLE_MAP_REGISTRATION, "Two map registrations are not allowed!", States.ERROR); // Second registration is not allowed
			}

				LOGGER.log(Level.INFO, "Registered map for second player.");	
				playerHalfMap1 = halfMap;
				//dataRegistrationUtil.setSavedMap(halfMap);
			return new ResponseEnvelope(null, null, States.OK);
		}
		
		LOGGER.log(Level.INFO, halfMap.getUniquePlayerID()+ "  Ignored map registration for unknown player");
		return new ResponseEnvelope(Exceptions.IGNORED_MAP_REGISTRATION, "Ignored map registration for unknown player!", States.ERROR); // Second registration is not allowed;
	}

	public static GameState fetchGameData() {
		FullMap fullmap = new FullMap();
		Optional<FullMap> optional = Optional.of(fullmap);
		HalfMap map = finalData.getMap();
		
		
		
		GameState gameState = new GameState(optional,players,finalData.getGameStateId().getUniqueGameID());
		//LOGGER.log(Level.INFO, "gameState" + gameState);	
		return gameState;
	}

}
