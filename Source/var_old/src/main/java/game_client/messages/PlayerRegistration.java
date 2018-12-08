package game_client.messages;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "playerRegistration")
@XmlAccessorType(XmlAccessType.NONE)
public class PlayerRegistration {

    private static final Logger LOGGER = Logger.getLogger(PlayerRegistration.class.getName());

    @XmlElement(name = "studentID")
    private String matNr;

    @XmlElement(name = "studentFirstName")
    private String firstName;

    @XmlElement(name = "studentLastName")
    private String lastName;

    public PlayerRegistration() {
        this.matNr = "";
        this.firstName = "";
        this.lastName = "";
    }

    public PlayerRegistration(String matNr, String firstName, String lastName) {
        if (matNr == null || firstName == null || lastName == null || !isValidName(lastName) || !isValidName(firstName)) {
            LOGGER.log(Level.SEVERE, "Incorrect Data set for user");
            try {
                throw new Exception("Incorrect Data set for user");
            } catch (Exception e) {
                System.out.println("Player registration exception!");;
            }
            return;
        }
        this.matNr = matNr;
        this.firstName = firstName;
        this.lastName = lastName;
        LOGGER.log(Level.INFO, "Data for user was set successfully");
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

    public boolean isValidName(String name) {
        return name.matches(".*[a-zA-Z]+.*");
    }
}
