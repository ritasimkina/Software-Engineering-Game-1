package game_client.test;


import game_client.messages.HalfMap;
import game_client.service.HalfMapService;
import org.junit.Test;

import java.util.UUID;

import static game_client.service.HalfMapService.*;
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
					 if(gameField[row][col] == GRASS_TILE)
						grassTileCount++;
					 else if(gameField[row][col] == WATER_TILE)
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
		 assertEquals("Grass", HalfMapService.mapTerrainXMLFieldToTerrainCode(GRASS_TILE));
	 }
	 
	 @Test
	 public void mapFieldToTerrainCodeWater(){
		 assertEquals("Water", HalfMapService.mapTerrainXMLFieldToTerrainCode(WATER_TILE));
	 }
	 
	 @Test
	 public void mapFieldToTerrainCodeMountain(){
		 assertEquals("Mountain", HalfMapService.mapTerrainXMLFieldToTerrainCode(HalfMapService.MOUNTAIN_TILE));
	 }



	@Test
	public void setAndGetTileTest(){
		HalfMapService hms = new HalfMapService(4,8,false);
		hms.setTile(0,0,WATER_TILE);
		assertEquals(WATER_TILE,hms.getTile(0,0));
	}


	@Test
	public void generateHalfMapObjectTest(){
		UUID playerID = UUID.fromString("ada369fb-5673-4df0-88f1-dd54d0a109b7");
		HalfMapService hms = new HalfMapService(4,8,false);
		HalfMap hm = hms.generateHalfMapObject(playerID);

		assertEquals(playerID,hm.getUniquePlayerID());
	}






	@Test
	public void mapCodeToCharacterGrass(){
		assertEquals(GRASS_CHARACTER, HalfMapService.mapCodeToCharacter(GRASS_TILE));
	}

	@Test
	public void mapCodeToCharacterWater(){
		assertEquals(WATER_CHARACTER, HalfMapService.mapCodeToCharacter(WATER_TILE));
	}

	@Test
	public void mapCodeToCharacterMountain(){
		assertEquals(MOUNTAIN_CHARACTER, HalfMapService.mapCodeToCharacter(MOUNTAIN_TILE));
	}
}
