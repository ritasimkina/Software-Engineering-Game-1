package game_client.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement (name = "mapNode")
@XmlAccessorType(XmlAccessType.NONE)
public class MapNode {

    public MapNode() {}

    @XmlElement(name="playerPositionState")
    private String playerPositionState;

    @XmlElement(name="terrain")
    private String terrain;

    @XmlElement(name="treasureState")
    private String treasureState;

    @XmlElement(name="fortState")
    private String fortState;

    @XmlElement(name="X")
    private int X;

    @XmlElement(name="Y")
    private int Y;

    public String getPlayerPositionState() {
        return playerPositionState;
    }

    public void setPlayerPositionState(String playerPositionState) {
        this.playerPositionState = playerPositionState;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getTreasureState() {
        return treasureState;
    }

    public void setTreasureState(String treasureState) {
        this.treasureState = treasureState;
    }

    public String getFortState() {
        return fortState;
    }

    public void setFortState(String fortState) {
        this.fortState = fortState;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "MapNode{" +
                "playerPositionState='" + playerPositionState + '\'' +
                ", terrain='" + terrain + '\'' +
                ", treasureState='" + treasureState + '\'' +
                ", fortState='" + fortState + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                '}';
    }
}
