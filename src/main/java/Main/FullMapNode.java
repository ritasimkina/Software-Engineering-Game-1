package Main;

import MessagesBase.Terrain;
import MessagesGameState.FortState;
import MessagesGameState.PlayerPositionState;
import MessagesGameState.TreasureState;
import Validation.NotNegative;
import Validation.NotNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "mapNode")
@XmlAccessorType(value = XmlAccessType.NONE)
public final class FullMapNode {
  @XmlElement(name = "playerPositionState", required = true)
  private final PlayerPositionState playerPositionState;

  @XmlElement(name = "terrain", required = true)
  private final Terrain terrain;

  @XmlElement(name = "treasureState", required = true)
  private final TreasureState treasureState;

  @XmlElement(name = "fortState", required = true)
  private final FortState fortState;

  @XmlElement(name = "X", required = true)
  @Min(value = 0L)
  @Max(value = 7L)
  private final int X;

  @XmlElement(name = "Y", required = true)
  @Min(value = 0L)
  @Max(value = 7L)
  private final int Y;

  public FullMapNode() {
    this.terrain = null;
    this.X = 0;
    this.Y = 0;
    this.playerPositionState = null;
    this.treasureState = null;
    this.fortState = null;
  }

  public FullMapNode(
      Terrain terrain,
      PlayerPositionState playerPos,
      TreasureState treasure,
      FortState fort,
      int X,
      int Y) {
    this.terrain = NotNull.check(terrain, "Terrain must not be null");
    this.X = NotNegative.check(X, "X must not be negative");
    this.Y = NotNegative.check(Y, "Y must not be negative");
    this.playerPositionState = NotNull.check(playerPos, "PlayerPositionState must not be null");
    this.treasureState = NotNull.check(treasure, "TreasureState must not be null");
    this.fortState = NotNull.check(fort, "FortState must not be null");
  }

  public int getX() {
    return this.X;
  }

  public int getY() {
    return this.Y;
  }

  public PlayerPositionState getPlayerPositionState() {
    return this.playerPositionState;
  }

  public Terrain getTerrain() {
    return this.terrain;
  }

  public TreasureState getTreasureState() {
    return this.treasureState;
  }

  public FortState getFortState() {
    return this.fortState;
  }
}
