package brickBreaker;

import javax.swing.*;

public class Application {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		Brick_Breaker game=new Brick_Breaker();
		
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Brick Breaker Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(game);
	}

}
