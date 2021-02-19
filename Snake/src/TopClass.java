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
	private static final int BOARD_SIZE = 10; //size of game grid
	
	//Game Colors
	private static int bodyColor_counter = 0; //body color is set by a method below
	
	//Booleans
	private final boolean showCellBorder = true;
	public static boolean isGameOver = false;
	
	//Create Game Objects
	Board board = new Board(BOARD_SIZE, BOARD_SIZE);
	Snake snake = new Snake( (int)(Math.random() * Board.arr.length), (int)(Math.random() * Board.arr[0].length));
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
		if (showCellBorder)
			g.setColor(board.getCellBorderColor());
		else
			g.setColor(board.getCellBackgroundColor());
		
		food.updateColor();
		board.displayBoard(g, food);
		
		if (isGameOver) { //draws if game is over
			g.setFont(new Font("sansserif", Font.BOLD, 42));
			g.setColor(new Color( (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255) ));
			g.drawString("LOSER", Board.TILE_WIDTH * Board.XTILES/2 - 62, Board.TILE_HEIGHT * Board.YTILES/2 + 26);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		snake.updateSnake(food);
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) //Takes user input
	{
		if ( e.getKeyCode() == KeyEvent.VK_UP && !Snake.isDown ) { //-y
			Snake.isUp = true; Snake.isDown = false; Snake.isRight = false; Snake.isLeft = false;
		}
		if ( e.getKeyCode() == KeyEvent.VK_DOWN && !Snake.isUp ) { //+y
			Snake.isUp = false; Snake.isDown = true; Snake.isRight = false; Snake.isLeft = false;
		}
		if ( e.getKeyCode() == KeyEvent.VK_RIGHT && !Snake.isLeft ) { //+x
			Snake.isUp = false; Snake.isDown = false; Snake.isRight = true; Snake.isLeft = false;
		}
		if ( e.getKeyCode() == KeyEvent.VK_LEFT && !Snake.isRight) { //-x
			Snake.isUp = false; Snake.isDown = false; Snake.isRight = false; Snake.isLeft = true;
		}
		
		if ( e.getKeyCode() == KeyEvent.VK_SPACE && isGameOver) {
			resetGame();
		}
		repaint();
	}
	
	private void resetGame()
	{
		board = new Board(BOARD_SIZE, BOARD_SIZE);
		Snake.arr = new ArrayList <Snake>();
		snake = new Snake( (int)(Math.random() * Board.arr.length), (int)(Math.random() * Board.arr[0].length));
		food = new Food();
		isGameOver = false;
		Snake.isUp = false; Snake.isDown = false; Snake.isLeft = false; Snake.isRight = false;
	}
	
	public static Color bodyColor() //makes snake body different colors
	{
		bodyColor_counter++;
		switch (bodyColor_counter % 7)
		{
		  case 1 :
			return new Color(0, 170, 0);
		  case 2 :
			return new Color(0, 175, 0);
		  case 3 :
			return new Color(0, 180, 0);
		  case 4 :
			return new Color(0, 185, 0);
		  case 5 :
			return new Color(0, 190, 0);
		  case 6 :
			return new Color(0, 195, 0);
		  default :
			return new Color(0, 200, 0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
