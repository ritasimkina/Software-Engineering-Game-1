package game_client.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "uniqueGameIdentifier")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameIdentifier {

    @XmlElement(name = "uniqueGameID")
    private String uniqueGameID;

    public String getUniqueGameID() {
        return uniqueGameID;
    }

    @Override
    public String toString() {
        return "GameIdentifier{" +
                "uniqueGameID='" + uniqueGameID + '\'' +
                '}';
    }
}
