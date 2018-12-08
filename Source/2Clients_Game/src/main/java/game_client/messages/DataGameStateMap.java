package game_client.messages;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "map")
@XmlAccessorType(XmlAccessType.NONE)
public class DataGameStateMap {

    @XmlElementWrapper(name="mapNodes")
    @XmlElement(name="mapNode")
    private List<MapNode> mapNodes;

    /**
     * @return the list of all Map Nodes
     */
    public List<MapNode> getMapNodes() {
        return mapNodes;
    }


    @Override
    public String toString() {
        return "DataGameStateMap{" +
                "mapNodes=" + mapNodes +
                '}';
    }
}
