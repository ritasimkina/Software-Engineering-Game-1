package Main;

import java.util.Arrays;

public class Players {
  private Player[] player;

  public Player[] getPlayer() {
    return player;
  }

  public void setPlayer(Player[] player) {
    this.player = player;
  }

  @Override
  public String toString() {
    return "ClassPojo [player = " + Arrays.toString(player) + "]";
  }
}
