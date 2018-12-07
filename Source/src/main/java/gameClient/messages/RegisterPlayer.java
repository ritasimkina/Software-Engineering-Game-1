package messages;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement (name = "playerRegistration")
@XmlAccessorType(XmlAccessType.NONE)
public class RegisterPlayer {

	@XmlElement(name="studentID")
	private final String matNr;
	
	@XmlElement(name="studentFirstName")
	private final String firstName;
	
	@XmlElement(name="studentLastName")
	private final String lastName;

	public RegisterPlayer()
	{
		this.matNr = "";
		this.firstName = "";
		this.lastName = "";
	}
	
	public RegisterPlayer(String matNr, String firstName, String lastName)
	{
		this.matNr = matNr;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getMatNr() {
		return matNr;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
