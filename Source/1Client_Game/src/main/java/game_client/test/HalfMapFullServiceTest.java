package game_client.test;

import game_client.service.HalfMapFullService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HalfMapFullServiceTest {
	
	 @Test
	 public void setPlayerPosition_Check() {
		 HalfMapFullService gameMapFull = new HalfMapFullService(8,  8);
		 gameMapFull.setPlayerRow(5);
		 gameMapFull.setPlayerCol(2);
		 
		 assertEquals(5, gameMapFull.getPlayerRow());
		 assertEquals(2, gameMapFull.getPlayerCol());
	 }
	 
	 @Test
	 public void setEnemyTreasurePosition_Check() {
		 HalfMapFullService gameMapFull = new HalfMapFullService(8,  8);
		 gameMapFull.setEnemyTreasureRow(1);
		 gameMapFull.setEnemyTreasureCol(2);
		 
		 assertEquals(1, gameMapFull.getEnemyTreasureRow());
		 assertEquals(2, gameMapFull.getEnemyTreasureCol());
	 }
	 
	 @Test
	 public void setEnemyFortPosition_Check() {
		 HalfMapFullService gameMapFull = new HalfMapFullService(8,  8);
		 gameMapFull.setEnemyFortRow(4);
		 gameMapFull.setEnemyFortCol(4);
		 
		 assertEquals(4, gameMapFull.getEnemyFortRow());
		 assertEquals(4, gameMapFull.getEnemyFortCol());
	 }

}
