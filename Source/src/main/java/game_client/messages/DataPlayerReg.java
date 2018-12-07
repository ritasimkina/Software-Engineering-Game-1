package game_client.messages;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.NONE)
public class DataPlayerReg {

    @XmlElement(name = "uniquePlayerID")
    public UUID uniquePlayerID;

    public DataPlayerReg() {
    }
}
