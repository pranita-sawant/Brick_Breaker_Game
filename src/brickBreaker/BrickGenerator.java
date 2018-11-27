package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickGenerator {
	public int bricks[][];  //contains no of bricks
	public int brickWidth;
	public int brickHeight;
	
	public BrickGenerator(int row, int col){
		bricks =new int [row][col];
		for(int i=0; i < bricks.length; i++){
			for(int j=0; j< bricks[0].length; j++){
				bricks[i][j] = 1;    //1 will detect that this brick is NOT intersected with ball
			}
		}
		
		brickWidth=540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g){
		for(int i=0; i < bricks.length; i++){
			for(int j=0; j< bricks[0].length; j++){
				if(bricks[i][j] > 0){
					g.setColor(Color.white);
					g.fillRect(j*brickWidth + 80, i*brickHeight +50 , brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j  * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col){
		bricks[row][col]= value;
	}
	
	
}
