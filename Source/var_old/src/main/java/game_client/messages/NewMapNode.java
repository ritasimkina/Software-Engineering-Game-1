package game_client.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "halfMapNode")
@XmlAccessorType(XmlAccessType.NONE)
public class NewMapNode {

    @XmlElement(name = "X")
    private int X;

    @XmlElement(name = "Y")
    private int Y;

    @XmlElement(name = "fortPresent")
    private boolean fortPresent;

    @XmlElement(name = "terrain")
    private String terrain;

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

    public boolean isFortPresent() {
        return fortPresent;
    }

    public void setFortPresent(boolean fortPresent) {
        this.fortPresent = fortPresent;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]: %s %s", X, Y, terrain, fortPresent);
    }

}
