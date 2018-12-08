package game_client.service;

public class HalfMapFullService extends HalfMapService {

    //TODO make clear with additional tiles

    public final static char PLAYER_CHARACTER = 'O';
    public final static char TREASURE_CHARACTER = '$';
    public final static char ENEMY_FORT_TILE_CHARACTER = 'X';

    private int playerRow;
    private int playerCol;

    private int enemyPlayerRow;
    private int enemyPlayerCol;

    //private int TreasureRow;
    //private int TreasureCol;

    private int enemyTreasureRow;
    private int enemyTreasureCol;

    private int FortRow;
    private int FortCol;

    private int enemyFortRow;
    private int enemyFortCol;

    public HalfMapFullService(int mapRows, int mapCols) {
        super(mapRows, mapCols, false);
    }

    public void setPlayerPosition(int playerRow, int playerCol) {
        this.playerRow = playerRow;
        this.playerCol = playerCol;
    }

    /**
     * @param enemyTreasureRow row Player2 location
     * @param enemyTreasureCol col Player2 location
     */
    public void setEnemyTreasurePosition(int enemyTreasureRow, int enemyTreasureCol) {
        this.enemyTreasureRow = enemyTreasureRow;
        this.enemyTreasureCol = enemyTreasureCol;
    }

    /**
     * @param enemyFortRow row for a Fort
     * @param enemyFortCol col for a Fort
     */
    public void setEnemyFortPosition(int enemyFortRow, int enemyFortCol) {
        this.enemyFortRow = enemyFortRow;
        this.enemyFortCol = enemyFortCol;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    public int getEnemyPlayerRow() {
        return enemyPlayerRow;
    }

    public void setEnemyPlayerRow(int enemyPlayerRow) {
        this.enemyPlayerRow = enemyPlayerRow;
    }

    public int getEnemyPlayerCol() {
        return enemyPlayerCol;
    }

    public void setEnemyPlayerCol(int enemyPlayerCol) {
        this.enemyPlayerCol = enemyPlayerCol;
    }


    public int getEnemyTreasureRow() {
        return enemyTreasureRow;
    }

    public void setEnemyTreasureRow(int enemyTreasureRow) {
        this.enemyTreasureRow = enemyTreasureRow;
    }

    public int getEnemyTreasureCol() {
        return enemyTreasureCol;
    }

    public void setEnemyTreasureCol(int enemyTreasureCol) {
        this.enemyTreasureCol = enemyTreasureCol;
    }

    public int getEnemyFortRow() {
        return enemyFortRow;
    }

    public void setEnemyFortRow(int enemyFortRow) {
        this.enemyFortRow = enemyFortRow;
    }

    public int getEnemyFortCol() {
        return enemyFortCol;
    }

    public void setEnemyFortCol(int enemyFortCol) {
        this.enemyFortCol = enemyFortCol;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < getMapRows(); i++) {
            for (int j = 0; j < getMapCols(); j++) {

                if (i == playerRow && j == playerCol) {
                    result += PLAYER_CHARACTER;
                } else if (i == enemyTreasureRow && j == enemyTreasureCol) {
                    result += TREASURE_CHARACTER;
                }else if (i == enemyFortRow && j == enemyFortCol) {
                    result += ENEMY_FORT_TILE_CHARACTER;
                }else {
                    result += mapCodeToCharacter(gameField[i][j]);
                }
            }
            result += "\r\n";
        }
        return result;
    }
}
