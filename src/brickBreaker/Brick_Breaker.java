package brickBreaker;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Brick_Breaker extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBrick = 21;
	
	private Timer timer;  // to set timer for speed of ball
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballX = 120;
	private int ballY = 350;
	
	private int ballDx = -1;
	private int ballDy = -2;
	
	private BrickGenerator brick;
	
	//Constructor
	public Brick_Breaker() {
		brick = new BrickGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer= new Timer(delay,this);
		timer.start();	
	}
	
	//Grapghic object like : ball, bricks, etc
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing bricks
		brick.draw((Graphics2D)g);
		
		//boarders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592); 
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//score		
		g.setFont(new Font("serif" , Font.BOLD, 25));
		g.setColor(Color.red);
		g.drawString("Score :", 510, 25);
		g.setColor(Color.white);
		g.drawString(" "+score, 580, 25);
			
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.red);
		g.fillOval(ballX, ballY, 20, 20);
		
		//End the game
		if(totalBrick <= 0){
			play = false;
			ballDx = 0;
			ballDy = 0;
			
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.setColor(Color.red);
			g.drawString("Game Over", 190, 300);
			g.setColor(Color.white);
			g.drawString("You Won", 210, 325);
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.setColor(Color.blue);
			g.drawString("Press Enter to Restart", 230, 350);	
		}
		
		if(ballY >570){
			play = false;
			ballDx = 0;
			ballDy = 0;
			
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.setColor(Color.red);
			g.drawString("Game Over", 300, 300);
			
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.setColor(Color.blue);
			g.drawString("Press Enter to Restart", 280, 350);			
		}
			
		g.dispose();
	}
	

	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play){
			
			//intersection between ball and paddle
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550,100, 8))){
				ballDy = -ballDy;
			}
			
			//to intersect ball and bricks
			A: for(int i=0;i<brick.bricks.length;i++){
				for(int j=0; j < brick.bricks[0].length;j++){
					if(brick.bricks[i][j]> 0){
						int brickX = j * brick.brickWidth + 80;
						int brickY = i* brick.brickHeight + 50;
						int brickWidth = brick.brickWidth;
						int brickHeight = brick.brickHeight;
						
						Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
						Rectangle ballRect = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)){
							brick.setBrickValue(0,i,j);
							totalBrick--;
							score += 5;
							
							if(ballX + 19 <= brickRect.x || ballX + 1 >= brickRect.x + brickRect.width){
								ballDx = -ballDx;
							}else{
								ballDy = -ballDy;
							}
							break A;
						}
						
					}
				}
			}
			
			//To move ball
			ballX += ballDx;
			ballY += ballDy;
			if(ballX < 0){             //Left border
				ballDx = -ballDx;
			}
			if(ballY < 0){             //Top border
				ballDy = -ballDy;
			}
			if(ballX > 670){            //Right border
				ballDx = -ballDx;
			}
		}
		repaint();
		
	}

	public void keyPressed(KeyEvent arg0) { }
	public void keyTyped(KeyEvent arg0) { }

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(playerX >= 600){
				playerX = 600;
			}
			else{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(playerX < 10){
				playerX = 10;
			}
			else{
				moveLeft();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(!play){
				play = true;
				ballX = 120;
				ballY = 350;
				ballDx = -1;
				ballDy = -2;
				playerX =310;
				score =0;
				totalBrick = 21;
				brick = new BrickGenerator(3,7);
				
				repaint();		
			}
		}
		
	}
	
	public void moveRight(){
		play = true;
		playerX+= 20;
	}
	
	public void moveLeft(){
		play = true;
		playerX-= 20;
	}
	
	
	
	
}
