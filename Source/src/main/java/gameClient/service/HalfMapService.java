package game_client.service;


import game_client.messages.HalfMap;
import game_client.messages.NewMapNode;


import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class HalfMapService {

    private static final Logger LOGGER = Logger.getLogger(HalfMapService.class.getName());

    public static final int GRASS_TILE = 0;
    public static final int WATER_TILE = 1;
    public static final int MOUNTAIN_TILE = 2;

    public static final int FORT_TILE = 3;
    public static final int ENEMY_FORT_TILE = 4;
    public static final int TREASURE_TILE = 5;
    public static final int ENEMY_TREASURE_TILE = 6;
    public static final int PLAYER_TILE = 7;
    public static final int ENEMY_PLAYER_TILE = 8;

    public static final char GRASS_CHARACTER = '_';
    public static final char WATER_CHARACTER = '~';
    public static final char MOUNTAIN_CHARACTER = '^';

    public static final char FORT_CHARACTER = '#';
    public static final char ENEMY_FORT_CHARACTER = 'F';
    public static final char TREASURE_CHARACTER = '$';
    public static final char ENEMY_TREASURE_CHARACTER = 'T';
    public static final char PLAYER_CHARACTER = 'O';
    public static final char ENEMY_PLAYER_CHARACTER = 'P';


    public static final int MIN_MOUNTAIN_TILES = 3;
    public static final int MAX_MOUNTAIN_TILES = 6;

    public static final int MIN_WATER_TILES = 4;
    public static final int MAX_WATER_TILES = 6;

    public final int NUMBER_OF_MAP_ROWS;// = 4;
    public final int NUMBER_OF_MAP_COLS;// = 8;

    protected int[][] gameField;

    public HalfMapService(int mapRows, int mapCols, boolean generateRandomMap) {
        this.NUMBER_OF_MAP_ROWS = mapRows;
        this.NUMBER_OF_MAP_COLS = mapCols;
        gameField = new int[NUMBER_OF_MAP_ROWS][NUMBER_OF_MAP_COLS];
        if (generateRandomMap) {
            generateMap(mapRows, mapCols);
        }
    }

    public void generateMap(int mapRows, int mapCols) {

        gameField = new int[mapRows][mapCols];

        //Random random = new Random();

        List<Tile> freeTiles = new ArrayList<Tile>();
        for(int row = 0; row < mapRows; row++)
            for(int col = 0; col < mapCols; col++)
                freeTiles.add(new Tile(row, col));

        int mountainTileCount = 0;

        //возможно надо убрать аргумент random.
        int totalMountainTileCount = getRandomNumberMinMax(MIN_MOUNTAIN_TILES, MAX_MOUNTAIN_TILES);//число гор от 3 до 6
        //LOGGER.log(Level.INFO, "Now " + totalMountainTileCount + " mountain tiles will be generated.");

        while(mountainTileCount < totalMountainTileCount) {
            Tile randomTile = getNextRandomTile(freeTiles);

            int randomRow = randomTile.row;
            int randomCol = randomTile.col;

            boolean isEdge = randomRow == 0 || randomRow == mapRows-1 || randomCol == 0 || randomCol == mapCols-1;

            if(isEdge) {
                gameField[randomRow][randomCol] = MOUNTAIN_TILE;
                mountainTileCount++;
                freeTiles.remove(randomTile);
            }
        }

        int waterTileCount = 0;
        int totalWaterTileCount = getRandomNumberMinMax(MIN_WATER_TILES, MAX_WATER_TILES);//число гор от 4 до 6
        //LOGGER.log(Level.INFO, "Now " + totalWaterTileCount + " water tiles will be generated.");

        while(waterTileCount < totalWaterTileCount) {
            Tile randomTile = getNextRandomTile(freeTiles);

            int randomRow = randomTile.row;
            int randomCol = randomTile.col;

            boolean isEdge = randomRow == 0 || randomRow == mapRows-1 || randomCol == 0 || randomCol == mapCols-1;

            if(!isEdge) {
                gameField[randomRow][randomCol] = WATER_TILE;
                waterTileCount++;
                freeTiles.remove(randomTile);
            }
        }

        Tile fortTile = getNextRandomTile(freeTiles);

        gameField[fortTile.row][fortTile.col] = FORT_TILE;
        freeTiles.remove(fortTile);





        /*
        //TODO make logic for generation of HalfMap
        for (int row = 0; row < NUMBER_OF_MAP_ROWS; row++) {
            for (int col = 0; col < NUMBER_OF_MAP_COLS; col++) {
                gameField[row][col] = GRASS_TILE;
            }
        }
        gameField[0][5] = WATER_TILE;
        gameField[2][6] = WATER_TILE;
        gameField[2][1] = WATER_TILE;
        gameField[1][7] = WATER_TILE;

        gameField[1][3] = MOUNTAIN_TILE;
        gameField[2][4] = MOUNTAIN_TILE;
        gameField[3][5] = MOUNTAIN_TILE;

        gameField[1][1] = FORT_TILE;
        */
    }


    //public HalfMap generateHalfMapObject(PlayerID playerID) {
    public HalfMap generateHalfMapObject(UUID playerID) {
        List<NewMapNode> newMapNodes = new ArrayList<>();
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                NewMapNode newMapNode = new NewMapNode();
                newMapNode.setX(j);
                newMapNode.setY(i);
                if (gameField[i][j] == FORT_TILE) {
                    newMapNode.setFortPresent(true);
                    gameField[i][j] = GRASS_TILE;
                }
                newMapNode.setTerrain(mapTerrainXMLFieldToTerrainCode(gameField[i][j]));

                newMapNodes.add(newMapNode);
            }
        }

        return new HalfMap(this, newMapNodes, playerID);
    }




    private Tile getNextRandomTile(List<Tile> tiles) {
        Random random = new Random();
        int randomIndex = random.nextInt(tiles.size());
        Tile randomTile = tiles.get(randomIndex);

        return randomTile;
    }

    private int getRandomNumberMinMax(int minBound, int maxBound) {
        Random random = new Random();
        return minBound + random.nextInt(maxBound - minBound + 1);
    }


    //получить игровую клетку. Если за пределами игрового поля, то будет вода.
    public int getTile(int row, int col) {
        if (row < 0 || row >= NUMBER_OF_MAP_ROWS
                || col < 0 || col >= NUMBER_OF_MAP_COLS) {
            return WATER_TILE;
        }
        return gameField[row][col];
    }

    public void setTile(int row, int col, int tileType) {
        gameField[row][col] = tileType;
    }

    public int[][] getGameField() {
        return gameField;
    }

    //получить число колонок. Считаем, что поле прямоугольное.
    public int getMapCols() {
        return gameField[0].length;
    }

    //получить число рядов. Считаем, что поле прямоугольное.
    public int getMapRows() {
        return gameField.length;
    }

    //вывод в консоль изображения игрового поля.
    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < getMapRows(); i++) {
            for (int j = 0; j < getMapCols(); j++) {
                result += mapCodeToCharacter(gameField[i][j]);
            }
            result += "\r\n";
        }

        return result;
    }

    //преобразование цифровых значений игровых клеток в символьные.
    protected char mapCodeToCharacter(int code) {

        switch (code) {
            case GRASS_TILE:
                return GRASS_CHARACTER;
            case WATER_TILE:
                return WATER_CHARACTER;
            case MOUNTAIN_TILE:
                return MOUNTAIN_CHARACTER;

            /*case FORT_TILE:
                return FORT_CHARACTER;
            case ENEMY_FORT_TILE:
                return ENEMY_FORT_CHARACTER;
            case TREASURE_TILE:
                return TREASURE_CHARACTER;
            case ENEMY_TREASURE_TILE:
                return ENEMY_TREASURE_CHARACTER;
            case PLAYER_TILE:
                return PLAYER_CHARACTER;
            case ENEMY_PLAYER_TILE:
                return ENEMY_PLAYER_CHARACTER;
                */
            default:
                return '?';
        }
    }

    public static String mapTerrainXMLFieldToTerrainCode(int i) {
        String result = "";
        if (i == GRASS_TILE) {
            result = "Grass";
        } else if (i == WATER_TILE) {
            result = "Water";
        } else if (i == MOUNTAIN_TILE) {
            result = "Mountain";
        }
        //else {
        //   result = "Grass";
        //}
        return result;
    }


    private class Tile {
        public int row;
        public int col;

        public Tile(int row, int col) {
            this.row = row;
            this.col = col;
        }


        @Override
        public String toString() {
            return "Row: " + row + " Col: " + col;
        }
    }


    public static void main(String[] args) {
        HalfMapService halfMapService = new HalfMapService(4, 8, true);
        System.out.println(halfMapService);
    }

}
