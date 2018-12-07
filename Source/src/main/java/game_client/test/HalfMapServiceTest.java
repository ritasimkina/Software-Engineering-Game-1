package game_client.test;



import game_client.service.HalfMapService;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class HalfMapServiceTest {
	 @Test
	 public void mapGeneration_fulfillsAllRules() {
		 for(int i = 0; i < 100; i++) {
			 HalfMapService map = new HalfMapService(4, 8, true);
			 int grassTileCount = 0;
			 int waterTileCount = 0;
			 int mountainTileCount = 0;
			 
			 int[][] gameField = map.getGameField();
			 for(int row = 0; row < map.getMapRows(); row++) {
				 for(int col = 0; col < map.getMapCols(); col++) {
					 if(gameField[row][col] == HalfMapService.GRASS_TILE)
						grassTileCount++;
					 else if(gameField[row][col] == HalfMapService.WATER_TILE)
						waterTileCount++;
					 else if(gameField[row][col] == HalfMapService.MOUNTAIN_TILE)
						mountainTileCount++;
				 }
			 }
			 
			 assertTrue(grassTileCount >= 15);
			 assertTrue(waterTileCount >= 4);
			 assertTrue(mountainTileCount >= 3);			 
		 }
	 }
	 
	 @Test
	 public void mapFieldToTerrainCodeGrass(){
		 assertEquals("Grass", HalfMapService.mapTerrainXMLFieldToTerrainCode(HalfMapService.GRASS_TILE));
	 }
	 
	 @Test
	 public void mapFieldToTerrainCodeWater(){
		 assertEquals("Water", HalfMapService.mapTerrainXMLFieldToTerrainCode(HalfMapService.WATER_TILE));
	 }
	 
	 @Test
	 public void mapFieldToTerrainCodeMountain(){
		 assertEquals("Mountain", HalfMapService.mapTerrainXMLFieldToTerrainCode(HalfMapService.MOUNTAIN_TILE));
	 }
}
