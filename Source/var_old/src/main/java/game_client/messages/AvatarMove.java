package game_client.messages;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//этот класс сейчас не используется.

@XmlRootElement(name = "playerMove")
@XmlAccessorType(XmlAccessType.NONE)
public class AvatarMove {
    /**
     * Message for a client move
     */
    @XmlElement(name = "uniquePlayerID")
    private UUID uniquePlayerID;

    @XmlElement(name = "move")
    private String move;

    public AvatarMove() {
    }

    public AvatarMove(UUID uniquePlayerID, String move) {
        this.uniquePlayerID = uniquePlayerID;
        this.move = move;
    }

    @Override
    public String toString() {
        return "AvatarMove{" +
                "uniquePlayerID=" + uniquePlayerID +
                ", move='" + move + '\'' +
                '}';
    }
}
