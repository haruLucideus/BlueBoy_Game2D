package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTING
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16, maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol,
					 screenHeight = tileSize * maxScreenRow;
	
	//WORLD SETTING
	public final int maxWorldCol = 50, maxWorldRow = 50;
	public final int worldWidth = maxScreenCol * tileSize,
					 worldHeight = maxScreenRow * tileSize;
	
	//FPS
	final int FPS = 60;
	
	TileManager tileManager = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    
    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Setting width height of panel
		this.setBackground(Color.white); // background color
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//	public void run() {
//		while(gameThread != null) {
//			
//			double drawInterval = 1000000000/FPS; // vẽ mỗi 0.0167s để có thể vẽ 60 lần mỗi 1s
//			double nextDrawTime = System.nanoTime() + drawInterval; //System.nanoTime(): trả về thời gian hiện tại
//																	//drawInterval: thời gian cho mỗi lần vẽ
//																	//Nếu lần vẽ đầu tiên là hiện tại(System.nanoTime()) thì lần vẽ kế tiếp là sau 0.0167s
//			//1.UPDATE: update information such as character position
//			update();
//			//2.DRAW: draw the screen with the updated information
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime /= 1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				nextDrawTime += drawInterval;
//			
//				Thread.sleep((long) remainingTime);
//			}
//			catch(InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			//delta: là thời gian trôi qua hoặc nói cách khác là thời gian trôi qua đã đủ cho một lần draw chưa?
			//khi delta = 1 thì đó là lúc thời gian trôi qua đã đủ cho một lần vẽ trong số 60 lần (FPS)
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileManager.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
}
