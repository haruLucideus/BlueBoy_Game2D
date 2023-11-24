package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		
		int entityTopWorldRow = entityTopWorldY / gp.tileSize;
		int entityBottomWorldRow = entityBottomWorldY / gp.tileSize;
		int entityLeftWorldCol = entityLeftWorldX / gp.tileSize;
		int entityRightWorldCol = entityRightWorldX / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		
		case "up":
			
			entityTopWorldRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopWorldRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopWorldRow];
			
			if(gp.tileManager.tile[tileNum1].collision == true
			|| gp.tileManager.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		
		case "down":
			
			entityBottomWorldRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomWorldRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomWorldRow];
			
			if(gp.tileManager.tile[tileNum1].collision == true
			|| gp.tileManager.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		
		case "left":
			
			entityLeftWorldCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityTopWorldRow];
			tileNum2 = gp.tileManager.mapTileNum[entityLeftWorldCol][entityBottomWorldRow];
			
			if(gp.tileManager.tile[tileNum1].collision == true
			|| gp.tileManager.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		
		case "right":
			
			entityRightWorldCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[entityRightWorldCol][entityTopWorldRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightWorldCol][entityBottomWorldRow];
			
			if(gp.tileManager.tile[tileNum1].collision == true
			|| gp.tileManager.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		}
	}

}
