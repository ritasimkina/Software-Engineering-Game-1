package Main;

import MessagesBase.HalfMapNode;

public class Position {
  private final int x;
  private final int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public static Position fromHalfMapNode(HalfMapNode node) {
    return new Position(node.getX(), node.getY());
  }
// translating a position to a halfmap conjucted down (each y coordinate is +4 for this halfMap)
  public Position translate(Position other) {
    return new Position(x + other.x, y + other.y);
  }

  @Override
  // addtional check for Objects , if located on a same postion =>same object
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position position = (Position) o;

    if (x != position.x) {
      return false;
    }
    return y == position.y;
  }

  @Override
  //general practice for a hashcode
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}
