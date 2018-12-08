package game_client.messages;

import game_client.service.HalfMapService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "halfMap")
@XmlAccessorType(XmlAccessType.NONE)
public class HalfMap {

    private HalfMapService sourceGameMap;

    @XmlElement(name = "uniquePlayerID")
    private UUID uniquePlayerID;

    @XmlElementWrapper(name = "halfMapNodes")
    @XmlElement(name = "halfMapNode")
    private List<NewMapNode> newMapNodes;

    public HalfMap() {
    }

    public HalfMap(HalfMapService sourceGameMap, List<NewMapNode> newMapNodes, UUID uniquePlayerID) {
        this.sourceGameMap = sourceGameMap;
        this.newMapNodes = newMapNodes;
        this.uniquePlayerID = uniquePlayerID;
    }

    public UUID getUniquePlayerID() {
        return uniquePlayerID;
    }

    @Override
    public String toString() {
        return "HalfMap{" +
                "sourceGameMap=" + "\n" +
                sourceGameMap + "\n" +
                ", uniquePlayerID=" + uniquePlayerID + "\n" +
                ", newMapNodes=" + newMapNodes +
                '}';
    }
}