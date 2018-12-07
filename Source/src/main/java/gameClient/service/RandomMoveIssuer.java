package game_client.service;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * should be intelligent, but actually not very intelligent player
 */
public class RandomMoveIssuer implements IMoveIssuer {

    private static final Logger LOGGER = Logger.getLogger(RandomMoveIssuer.class.getName());

    private String selectedDir;
    private int stepsLeft;//показывает, сколько осталось шагов (всего 200).


    @Override
    public String getNextMove(HalfMapFullService map) {
        if (stepsLeft > 0) {
            stepsLeft--;
            return selectedDir;
        }

        Random random = new Random();

        while (true) {
            String randomDir = Directions.allDirections[random.nextInt(4)];
            int newRow = map.getPlayerRow() + Directions.getOffsetRow(randomDir);
            int newCol = map.getPlayerCol() + Directions.getOffsetCol(randomDir);

            if (map.getTile(newRow, newCol) == HalfMapService.WATER_TILE)//продолжаем искать новые ячейки, если targetTile это вода.
                continue;

            int sourceTile = map.getTile(map.getPlayerRow(), map.getPlayerCol());
            int targetTile = map.getTile(newRow, newCol);

            LOGGER.log(Level.INFO, "Moving from " + HalfMapService.mapTerrainXMLFieldToTerrainCode(sourceTile) + " to " + HalfMapService.mapTerrainXMLFieldToTerrainCode(targetTile) + " (dir: " + randomDir + ")");

            selectedDir = randomDir;
            stepsLeft = Directions.movesNeeded(sourceTile, targetTile);

            break;
        }
        stepsLeft--;
        return selectedDir;
    }


    @Override
    public String toString() {
        return "RandomMoveIssuer{" +
                "selectedDir='" + selectedDir + '\'' +
                ", stepsLeft=" + stepsLeft +
                '}';
    }
}
