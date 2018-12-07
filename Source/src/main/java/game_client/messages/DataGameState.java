package game_client.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "data")
@XmlAccessorType(XmlAccessType.NONE)
public class DataGameState {

    @XmlElementWrapper(name="players")
    @XmlElement(name="player")
    private List<Player> players;

    @XmlElement(name="map")
    private DataGameStateMap map;

    @XmlElement(name="gameStateId")
    private String gameStateId;

    /**
     * @return a state of a Game as a message
     */
    public DataGameStateMap getMap() {
        return map;
    }

    /**
     * @return players taking part in a Game
     */
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "DataGameState{" +
                "players=" + players +
                ", map=" + map +
                '}';
    }
}
