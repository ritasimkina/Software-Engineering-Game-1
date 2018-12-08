package game_client.messages;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement (name = "uniquePlayerIdentifier")
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.NONE)
public class Player {
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());

    public static final String SHOULD_ACT_NEXT_STATE = "ShouldActNext";
    public static final String PLAYER_WON_STATE = "Won";
    public static final String PLAYER_SHOULD_WAIT = "ShouldWait";

    public Player() {
    }

    @XmlElement(name = "uniquePlayerID")
    private UUID uniquePlayerID;

    @XmlElement(name = "studentID")
    private String studentID;

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "lastName")
    private String lastName;

    @XmlElement(name = "state")
    private String state;

    @XmlElement(name = "collectedTreasure")
    private boolean collectedTreasure;

    public UUID getUniquePlayerID() {
        return uniquePlayerID;
    }

    public void setUniquePlayerID(UUID uniquePlayerID) {
        if (uniquePlayerID != null) {
            LOGGER.log(Level.INFO, "UniqueID was set");
            this.uniquePlayerID = uniquePlayerID;
        } else
            LOGGER.log(Level.SEVERE, "Wrong insert of UniqueID");
        try {
            throw new Exception("Wrong insert of UniqueID");
        } catch (Exception e) {
            System.out.println("Wrong uniquePlayerID!");
        }
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        if (studentID != null) {
            LOGGER.log(Level.INFO, "StudentID was set");
            this.studentID = studentID;
        } else
            LOGGER.log(Level.SEVERE, "Wrong insert of StundentID");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if ((uniquePlayerID != null) && isValidName(firstName)) {
            LOGGER.log(Level.INFO, "FirstName was set");
            this.firstName = firstName;
        } else
            LOGGER.log(Level.SEVERE, "Wrong insert of FirstName");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if ((uniquePlayerID != null) && isValidName(lastName)) {
            LOGGER.log(Level.INFO, "Lastname was set");
            this.lastName = lastName;
        } else
            LOGGER.log(Level.SEVERE, "Wrong insert of Lastname");
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return if treasure was collected
     */
    public boolean isCollectedTreasure() {
        return collectedTreasure;
    }

    /**
     * @param collectedTreasure collect the treasure
     */
    public void setCollectedTreasure(boolean collectedTreasure) {
        this.collectedTreasure = collectedTreasure;
    }


    /**
     * @param name
     * @return check if name is only letters
     */
    public boolean isValidName(String name) {
        return name.matches(".*[a-zA-Z]+.*");
    }

    @Override
    public String toString() {
        return "Player{" +
                "uniquePlayerID=" + uniquePlayerID +
                ", studentID='" + studentID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", collectedTreasure=" + collectedTreasure +
                '}';
    }
}
