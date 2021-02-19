import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class TopClass extends JComponent implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	//Global Static Constants
	private static final int FPS = 10; //movements per second
	private static final int BOARD_HEIGHT = 20; //size of game grid
	private static final int BOARD_WIDTH = 20; //size of game grid
	
	//Booleans
	private boolean hasMovedThisFrame = false;
	public static boolean gameOver = false;
	
	//Game Objects
	Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
	Snake snake = new Snake( (int)(Math.random() * BOARD_WIDTH), (int)(Math.random() * BOARD_HEIGHT));
	Food food = new Food();
	
	
	public static void main(String[] args) 
	{
		JFrame window = new JFrame("Snake");
		TopClass game = new TopClass();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		Timer t = new Timer((int)(1000 / FPS), game);
		t.start();
		
		window.addKeyListener(game);
		
	}
	
	public Dimension getPreferredSize() //sets the size of the window based on the size of the game grid
	{
		return new Dimension( (Board.TILE_WIDTH + Board.borderPadding) * Board.XTILES + Board.borderPadding,
							  (Board.TILE_HEIGHT + Board.borderPadding) * Board.YTILES + Board.borderPadding);
	}
	
	@Override
	protected void paintComponent(Graphics g) //draws all relevant objects to the screen
	{
		board.displayBoard(g, food, snake);
		
		if (gameOver) { //draws if game is over
			g.setFont(new Font("sansserif", Font.BOLD, 42));
			g.setColor(new Color( (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255) ));
			g.drawString("LOSER", Board.TILE_WIDTH * Board.XTILES/2 - 62, Board.TILE_HEIGHT * Board.YTILES/2 + 26);
		}
	}
	
	private void checkGameOver()
	{
		gameOver = snake.outOfBounds() || snake.isCollidingWithSelf();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) //runs every frame
	{
		if (!gameOver) {
			hasMovedThisFrame = false;
			snake.calcNextPos();
			checkGameOver();
		}
		if (!gameOver) { //2 if statements because of update order
			snake.updateSnake(food);
			food.updateColor();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) //Takes user input
	{
		if (!hasMovedThisFrame) {
			if ( e.getKeyCode() == KeyEvent.VK_UP && !Snake.isDown ) { //-y
				Snake.isUp = true; Snake.isDown = false; Snake.isRight = false; Snake.isLeft = false;
				hasMovedThisFrame = true;
			}
			if ( e.getKeyCode() == KeyEvent.VK_DOWN && !Snake.isUp ) { //+y
				Snake.isUp = false; Snake.isDown = true; Snake.isRight = false; Snake.isLeft = false;
				hasMovedThisFrame = true;
			}
			if ( e.getKeyCode() == KeyEvent.VK_RIGHT && !Snake.isLeft ) { //+x
				Snake.isUp = false; Snake.isDown = false; Snake.isRight = true; Snake.isLeft = false;
				hasMovedThisFrame = true;
			}
			if ( e.getKeyCode() == KeyEvent.VK_LEFT && !Snake.isRight) { //-x
				Snake.isUp = false; Snake.isDown = false; Snake.isRight = false; Snake.isLeft = true;
				hasMovedThisFrame = true;
			}
		}
		
		if ( e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
			resetGame();
		}
	}
	
	private void resetGame()
	{
		board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		Snake.arr = new ArrayList <Snake>();
		snake = new Snake( (int)(Math.random() * BOARD_WIDTH), (int)(Math.random() * BOARD_HEIGHT));
		food = new Food();
		gameOver = false;
		Snake.isUp = false; Snake.isDown = false; Snake.isLeft = false; Snake.isRight = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
