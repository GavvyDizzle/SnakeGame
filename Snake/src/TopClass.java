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
	private static final int BOARD_SIZE = 30;
	
	//Game Colors
	public static final Color cellBorder = new Color(0, 51, 102);
	public static final Color cellBG = new Color(0, 0, 0);
	public static final Color snakeHead = new Color(0, 150, 0);
	public static final Color snakeBody = new Color(0, 200, 0);
	public static final Color foodColor = new Color(255,0, 0);
	
	//Booleans
	private final boolean showCellBorder = true;
	public static boolean isGameOver = false;
	
	//Create Game Objects
	Board b = new Board(BOARD_SIZE, BOARD_SIZE);
	Snake s = new Snake( (int)(Math.random() * Board.arr.length), (int)(Math.random() * Board.arr[0].length));
	Food f = new Food();
	
	
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
	
	public Dimension getPreferredSize()
	{
		return new Dimension( (Board.TILE_WIDTH + Board.borderPadding) * Board.XTILES + Board.borderPadding,
							  (Board.TILE_HEIGHT + Board.borderPadding) * Board.YTILES + Board.borderPadding);
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		if (showCellBorder)
			g.setColor(cellBorder);
		else
			g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, (Board.TILE_WIDTH + Board.borderPadding) * Board.XTILES, Board.borderPadding);
		g.fillRect(0, 0, Board.borderPadding, (Board.TILE_HEIGHT + Board.borderPadding) * Board.YTILES);
		g.fillRect(0, 0, (Board.TILE_WIDTH + Board.borderPadding) * Board.XTILES + Board.borderPadding,
				         (Board.TILE_HEIGHT + Board.borderPadding) * Board.YTILES + Board.borderPadding);
		
		b.displayBoard(g, f);
		
		if (isGameOver) { //draws if game is over
			g.setFont(new Font("sansserif", Font.BOLD, 42));
			g.setColor(new Color( (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255) ));
			g.drawString("LOSER", 200, 300);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		s.updateSnake(f);
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
		b = new Board(BOARD_SIZE, BOARD_SIZE);
		Snake.arr = new ArrayList <Snake>();
		s = new Snake( (int)(Math.random() * Board.arr.length), (int)(Math.random() * Board.arr[0].length));
		f = new Food();
		isGameOver = false;
		Snake.isUp = false; Snake.isDown = false; Snake.isLeft = false; Snake.isRight = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}