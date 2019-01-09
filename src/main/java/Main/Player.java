package Main;

import MessagesGameState.PlayerState;

public class Player {
  private Position position;
  private Position treasure;
  private Position fort;
  private PlayerState state;
  private Player enemy;
  private int index;
  private boolean halfMapRegistered = false;

  public Player(PlayerState state) {
    this.state = state;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Position getTreasure() {
    return treasure;
  }

  public void setTreasure(Position treasure) {
    this.treasure = treasure;
  }

  public Position getFort() {
    return fort;
  }

  public void setFort(Position fort) {
    this.fort = fort;
  }

  public PlayerState getState() {
    return state;
  }

  public void setState(PlayerState state) {
    this.state = state;
  }

  public Player getEnemy() {
    return enemy;
  }

  public void setEnemy(Player enemy) {
    this.enemy = enemy;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean isHalfMapRegistered() {
    return halfMapRegistered;
  }

  public void setHalfMapRegistered() {
    this.halfMapRegistered = true;
  }
}
