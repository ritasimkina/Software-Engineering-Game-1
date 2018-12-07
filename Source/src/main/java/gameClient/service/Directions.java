package game_client.service;

public class Directions {

    public final static String UP = "Up";
    public final static String DOWN = "Down";
    public final static String LEFT = "Left";
    public final static String RIGHT = "Right";

    public final static String[] allDirections = new String[] {UP, RIGHT, DOWN, LEFT};

    public static int getOffsetRow(String direction) {
        if(direction.equals(UP))
            return -1;

        if(direction.equals(DOWN))
            return 1;

        return 0;
    }

    public static int getOffsetCol(String direction) {
        if(direction.equals(LEFT))
            return -1;

        if(direction.equals(RIGHT))
            return 1;

        return 0;
    }


    /**
     * @param sourceTileType current Tile location
     * @param destTileType destination lTile location
     * @return
     */
    public static int movesNeeded(int sourceTileType, int destTileType) {
        boolean isSourceEasyToPass = sourceTileType != HalfMapService.MOUNTAIN_TILE;
        boolean isTargetEasyToPass = destTileType != HalfMapService.MOUNTAIN_TILE;

        // grass to grass
        if(isSourceEasyToPass && isTargetEasyToPass)
            return 1;

        // grass to mountain
        if(isSourceEasyToPass && !isTargetEasyToPass)
            return 2;

        // mountain to grass
        if(!isSourceEasyToPass && isTargetEasyToPass)
            return 2;

        // mountain to mountain

        return 4;
    }
}
