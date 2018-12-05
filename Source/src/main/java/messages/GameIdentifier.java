package messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement (name = "GameIdentifier")
@XmlRootElement(name = "uniqueGameIdentifier")
@XmlAccessorType(XmlAccessType.NONE)
public class GameIdentifier {

	//@XmlElement(name="UniqueGameID")
	@XmlElement(name="uniqueGameID")
	private final String gameID;

	public GameIdentifier() {
		this.gameID = "";
	}
	
	public GameIdentifier(String gameID) {
		this.gameID = gameID;
	}

	public String getGameID() {
		return gameID;
	}
}
