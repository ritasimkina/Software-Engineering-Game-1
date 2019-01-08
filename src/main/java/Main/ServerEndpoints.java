package Main;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Exceptions.GenericExampleException;
import MessagesBase.HalfMap;
import MessagesBase.PlayerRegistration;
import MessagesBase.ResponseEnvelope;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.GameState;

@Controller
@RequestMapping(value = "/game")
public class ServerEndpoints {
	
	private static final Logger LOGGER = Logger.getLogger( ServerEndpoints.class.getName() );

	/* Please do NOT add all the necessary code in the methods provided below. When following the single
	 * Responsibility principle those methods should only contain the bare minimum related to network
	 * handling. Such as the converts which convert the objects from/to internal data objects
	 * to/from messages. Include the other logic (e.g., new game creation and game id handling) by means
	 * of composition (i.e., it should be provided by other classes).
	 */
	
	//below you can find two example endpoints, all the other endpoints can be defined similarly. 
	
	//example for a get request based on game/new
	@RequestMapping(value = "/new", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody UniqueGameIdentifier newGame() {
		
		//set showExeptionHandling to true to test/play around with exception handling (see the method at the bottom)
		boolean showExeptionHandling = false;
		if(showExeptionHandling)
		{
			throw new GenericExampleException("Name: Something", "Message: went totally wrong");
		}
		
		UniqueGameIdentifier gameIdentifier = new UniqueGameIdentifier(UUID.randomUUID().toString());
		GameStateVerification.registerNewGame(gameIdentifier);
		//System.out.println(gameIdentifier);
		
		return gameIdentifier;

		//note you will need to include additional logic, e.g., additional classes which create, store, validate, etc. game ids
	}
	
	
	
	//example for a POST request based on game/{gameID}/register
		@RequestMapping(value = "/{gameID}/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
		public @ResponseBody ResponseEnvelope<UniquePlayerIdentifier> registerPlayer(@PathVariable String gameID,
		      @Validated @RequestBody PlayerRegistration playerRegistration) throws Exception {
			
			//System.out.println(GameStateVerification.gameData.getGameStateId().getUniqueGameID());
			
			if(GameStateVerification.finalData.getGameStateId() == null || !GameStateVerification.finalData.getGameStateId().getUniqueGameID().equals(gameID)) {
				
				throw new GenericExampleException("Game not found", "This game does not exist!");
			}
			
			
			
			UniquePlayerIdentifier newPlayerID = new UniquePlayerIdentifier(UUID.randomUUID().toString());	
			
			String firstName = playerRegistration.getStudentFirstName();
			String lastName = playerRegistration.getStudentLastName();
			String studentID = playerRegistration.getStudentID();
			
			Main.PlayerRegistration translate = new Main.PlayerRegistration(firstName,lastName,studentID);
			
			
			GameStateVerification.registerNextPlayer(translate, newPlayerID);
			ResponseEnvelope<UniquePlayerIdentifier> playerIDMessage = new ResponseEnvelope<>(newPlayerID);
			return playerIDMessage;
			
			//note you will need to include additional logic, e.g., additional classes which create, store, validate, etc. player ids and incoming game ids
		}
	
	@RequestMapping(value = "/{gameID}/halfmap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<HalfMap> registerHalfMap(@PathVariable String gameID,
	      @Validated @RequestBody HalfMap halfMap) throws Exception {
		
		if(GameStateVerification.finalData.getGameStateId() == null || !GameStateVerification.finalData.getGameStateId().getUniqueGameID().equals(gameID)) {
			
			throw new GenericExampleException("Game not found", "This game does not exist!");
		}
		
		halfMap.getUniquePlayerID();
		halfMap.getNodes();
		//System.out.println("fetched ID halfmap: " +halfMap.getUniquePlayerID());
		//System.out.println("fetched ID halfmap: " +halfMap.getNodes());
		
		
		
		Main.ResponseEnvelope returnStatus = GameStateVerification.registerHalfMap(halfMap);
		//System.out.println("Resturn status: " + returnStatus);
		
		if(returnStatus.getExceptionMessage() != null) {
			throw new GenericExampleException("Registration failed", "Please try again");
		}
			
		ResponseEnvelope<HalfMap> playerHMMessage = new ResponseEnvelope<>();
		
		
		return playerHMMessage;
		
	}
	
	
	@RequestMapping(value = "/{gameID}/state/{playerID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody ResponseEnvelope<GameState> requestGameStateRaw(@PathVariable String gameID, @PathVariable String playerID) throws Exception {
		

		if(GameStateVerification.finalData.getGameStateId() == null || !GameStateVerification.finalData.getGameStateId().getUniqueGameID().equals(gameID)) {
			LOGGER.log(Level.INFO,  gameID + "    Game does not exist, lookup:  " + GameStateVerification.finalData.getGameStateId().getUniqueGameID());
			throw new GenericExampleException("Game not found", "This game does not exist!");
		}
		
		
		
		
		GameState envelope = GameStateVerification.fetchGameData();
		
		ResponseEnvelope<GameState> gameStateEnv = new ResponseEnvelope<>(envelope);

		
		LOGGER.log(Level.INFO, "player: " + playerID + " game: " + gameID + "    output of game state  " + gameStateEnv.getData() + "  " + gameStateEnv.getState());
		
		
		return gameStateEnv;

		//note you will need to include additional logic, e.g., additional classes which create, store, validate, etc. game ids
	}
	
	
	
	/*Note, this is only the most basic way of handling exceptions in spring (but sufficient for our task)
	it would for example struggle if you use multiple controllers. Ass the exception types to the @ExceptionHandler
	which your exception handling should support. For larger projects one would most likely want to use the
	HandlerExceptionResolver, see here https://www.baeldung.com/exception-handling-for-rest-with-spring
	
	Ask yourself: Why is handling the exceptions in a different method than the endpoint methods a good solution? */
    @ExceptionHandler({ GenericExampleException.class })
    public @ResponseBody ResponseEnvelope<?> handleException(GenericExampleException ex,HttpServletResponse response) {
    	ResponseEnvelope<?> result = new ResponseEnvelope<>(ex.getErrorName(), ex.getMessage( ));
    	response.setStatus(200);
        return result;
    }
}
