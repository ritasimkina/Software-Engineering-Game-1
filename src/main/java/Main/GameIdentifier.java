package Main;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import MessagesBase.UniqueGameIdentifier;

/**
 * @author Margarita
 * a message for unique Game identifier
 *
 */
@XmlRootElement(name = "GameIdentifier")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameIdentifier implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GameIdentifier(UniqueGameIdentifier gameIdentifier) {
		super();
		this.uniqueGameID = gameIdentifier;
	}

	@XmlElement(name = "UniqueGameID")
	private UniqueGameIdentifier uniqueGameID;

	public UniqueGameIdentifier getUniqueGameID() {
		return uniqueGameID;
	}
	
}
