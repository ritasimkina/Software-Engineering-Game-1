package Main;

import Exceptions.GameException;
import MessagesBase.HalfMap;
import MessagesBase.PlayerMove;
import MessagesBase.PlayerRegistration;
import MessagesBase.ResponseEnvelope;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.GameState;
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

@Controller
@RequestMapping(value = "/game")
public class ServerEndpoints {
  private static final Logger LOGGER = Logger.getLogger(ServerEndpoints.class.getName());

  /* Please do NOT add all the necessary code in the methods provided below. When following the single
   * Responsibility principle those methods should only contain the bare minimum related to network
   * handling. Such as the converts which convert the objects from/to internal data objects
   * to/from messages. Include the other logic (e.g., new game creation and game id handling) by means
   * of composition (i.e., it should be provided by other classes).
   */

  // below you can find two example endpoints, all the other endpoints can be defined similarly.

  // example for a get request based on game/new
  @RequestMapping(
    value = "/new",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_XML_VALUE
  )
  public @ResponseBody UniqueGameIdentifier newGame() {
    return GameInformation.createInstance();
    // note you will need to include additional logic, e.g., additional classes which create, store,
    // validate, etc. game ids
  }

  // example for a POST request based on game/{gameID}/register
  @RequestMapping(
    value = "/{gameID}/register",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_XML_VALUE,
    produces = MediaType.APPLICATION_XML_VALUE
  )
  public @ResponseBody ResponseEnvelope<UniquePlayerIdentifier> registerPlayer(
      @PathVariable String gameID, @Validated @RequestBody PlayerRegistration playerRegistration) {

    return new ResponseEnvelope<>(GameInformation.getInstance(gameID).register(playerRegistration));
    // note you will need to include additional logic, e.g., additional classes which create, store,
    // validate, etc. player ids and incoming game ids
  }

  @RequestMapping(
    value = "/{gameID}/halfmap",
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_XML_VALUE,
    produces = MediaType.APPLICATION_XML_VALUE
  )
  public @ResponseBody ResponseEnvelope<HalfMap> registerHalfMap(
      @PathVariable String gameID, @Validated @RequestBody HalfMap halfMap) {
    GameInformation.getInstance(gameID).registerHalfMap(halfMap);
    return new ResponseEnvelope<>();
  }

  @RequestMapping(
    value = "/{gameID}/state/{playerID}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_XML_VALUE
  )
  public @ResponseBody ResponseEnvelope<GameState> requestGameStateRaw(
      @PathVariable String gameID, @PathVariable String playerID) throws Exception {
    return new ResponseEnvelope<>(GameInformation.getInstance(gameID).getGameState(playerID));
    // note you will need to include additional logic, e.g., additional classes which create, store,
    // validate, etc. game ids
  }

  @RequestMapping(
    value = "/{gameID}/move",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_XML_VALUE
  )
  public @ResponseBody ResponseEnvelope<GameState> move(
      @PathVariable String gameID, @Validated @RequestBody PlayerMove move) throws Exception {
    GameInformation.getInstance(gameID).move(move);
    return new ResponseEnvelope<>();
  }

  /*Note, this is only the most basic way of handling exceptions in spring (but sufficient for our task)
  it would for example struggle if you use multiple controllers. Add the exception types to the @ExceptionHandler
  which your exception handling should support. For larger projects one would most likely want to use the
  HandlerExceptionResolver, see here https://www.baeldung.com/exception-handling-for-rest-with-spring

  Ask yourself: Why is handling the exceptions in a different method than the endpoint methods a good solution? */
  @ExceptionHandler({GameException.class})
    public @ResponseBody ResponseEnvelope<?> handleException(
            GameException ex, HttpServletResponse response) {
      ResponseEnvelope<?> result = new ResponseEnvelope<>(ex);
      response.setStatus(200);
      return result;
  }
}
