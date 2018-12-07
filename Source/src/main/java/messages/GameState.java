package game_client.messages;

import game_client.service.HalfMapFullService;
import game_client.service.HalfMapService;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "responseEnvelope")
@XmlAccessorType(XmlAccessType.NONE)
public class GameState {

    @XmlElement(name = "exceptionName")
    private String exceptionName;

    @XmlElement(name = "exceptionMessage")
    private String exceptionMessage;

    @XmlElement(name = "state")
    private String state;

    @XmlElement(name = "data")
    private DataGameState data;

    public GameState() {
    }

    public boolean isStateOK() {
        //return state != null && state.equals("OK");
        return state != null && state.equals("Okay");
    }

    /**
     * Location on a full map not only terrain, but also treasures, forts and players
     */
    public HalfMapFullService getFullGameMap() {
        HalfMapFullService result = new HalfMapFullService(8, 8);

        for (MapNode mn : data.getMap().getMapNodes()) {
            if (mn.getPlayerPositionState().equals("MyPosition") || mn.getPlayerPositionState().equals("BothPlayerPosition")) {
                result.setPlayerPosition(mn.getY(), mn.getX());
            }

            if (mn.getTerrain().equals("Water")) {
                result.setTile(mn.getY(), mn.getX(), HalfMapService.WATER_TILE);
            } else if (mn.getTerrain().equals("Mountain")) {
                result.setTile(mn.getY(), mn.getX(), HalfMapService.MOUNTAIN_TILE);
            }

            //if(mn.getTreasureState().equals("MyTresureIsPlacedHere")) {
            if (mn.getTreasureState().equals("MyTreasureIsPlacedHere")) {
                result.setEnemyTreasurePosition(mn.getY(), mn.getX());
            }
//MyTreasureIsPlacedHere
            if (mn.getFortState().equals("EnemyFortPresent")) {
                result.setEnemyFortPosition(mn.getY(), mn.getX());
            }
        }
        return result;
    }

    public Player getPlayerById(UUID uniquePlayerID) {
        List<Player> players = data.getPlayers();
        for (Player player : players) {
            if (player.getUniquePlayerID() == null)
                continue;
            if (player.getUniquePlayerID().equals(uniquePlayerID))
                return player;
        }
        return players.get(1);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "exceptionName='" + exceptionName + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }
}
