package messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType (name = "uniquePlayerIdentifier")
@XmlAccessorType(XmlAccessType.NONE)
public class PlayerIdentifier {
	
	@XmlElement(name="uniquePlayerID")
	private final String playerID;

	public PlayerIdentifier() {
		this.playerID = "";
	}
	
	public PlayerIdentifier(String playerID) {
		this.playerID = playerID;
	}
	
	public String getPlayerID() {
		return playerID;
	}
}
