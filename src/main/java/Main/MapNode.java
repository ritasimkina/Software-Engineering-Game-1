package Main;

public class MapNode {
  private String terrain;

  private String treasureState;

  private String fortState;

  private String Y;

  private String playerPositionState;

  private String X;

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

  public String getY() {
    return Y;
  }

  public void setY(String Y) {
    this.Y = Y;
  }

  public String getPlayerPositionState() {
    return playerPositionState;
  }

  public void setPlayerPositionState(String playerPositionState) {
    this.playerPositionState = playerPositionState;
  }

  public String getX() {
    return X;
  }

  public void setX(String X) {
    this.X = X;
  }

  @Override
  public String toString() {
    return "ClassPojo [terrain = "
        + terrain
        + ", treasureState = "
        + treasureState
        + ", fortState = "
        + fortState
        + ", Y = "
        + Y
        + ", playerPositionState = "
        + playerPositionState
        + ", X = "
        + X
        + "]";
  }
}
