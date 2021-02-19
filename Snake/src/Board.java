import java.awt.Color;
import java.awt.Graphics;

public class Board {
	
	//Constants
	public static final int TILE_WIDTH = 20;
	public static final int TILE_HEIGHT = 20;
	public static final int borderPadding = 2;
	public static int XTILES;
	public static int YTILES;
	
	//Colors
	private static final Color cellBorder_Color = new Color(0, 51, 102);
	private static final Color cellBackground_Color = new Color(0, 0, 0);	
	private static final Color snakeHead_Color = new Color(0, 125, 0);
	
	//Board Arrays
	public static Rectangle[][] arr;
	public static Boolean[][] bool;
	
	
	public Board(int x, int y) 
	{
		XTILES = x;
		YTILES = y;
		arr = new Rectangle[x][y];
		bool = new Boolean[x][y];
		createBoard();
	}
	
	private void createBoard()
	{
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = new Rectangle( j * (TILE_HEIGHT + borderPadding), i * (TILE_HEIGHT + borderPadding), TILE_WIDTH, TILE_HEIGHT);
				bool[i][j] = false;
			}
		}
	}
	
	public void displayBoard(Graphics g, Food f) //Draws all game objects to the screen
	{
		//g.fillRect(0, 0, (TILE_WIDTH + borderPadding) * XTILES, borderPadding);
		//g.fillRect(0, 0, borderPadding, (TILE_HEIGHT + borderPadding) * YTILES);
		//g.fillRect(0, 0, (TILE_WIDTH + borderPadding) * XTILES + borderPadding, (TILE_HEIGHT + borderPadding) * YTILES + borderPadding);
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (!bool[i][j]) { //empty spots
					//g.setColor(cellBackground_Color);
					//g.fillRect( arr[i][j].getX() + borderPadding, arr[i][j].getY() + borderPadding, arr[i][j].getWidth(), arr[i][j].getHeight() );
				}
				else { //draws snake
					if (i == Snake.arr.get(0).getY(Snake.arr.get(0)) && j == Snake.arr.get(0).getX(Snake.arr.get(0))) //head
						g.setColor(snakeHead_Color);
					else
						g.setColor(TopClass.bodyColor()); //body
					g.fillRect( arr[i][j].getX() + borderPadding, arr[i][j].getY() + borderPadding, arr[i][j].getWidth(), arr[i][j].getHeight() );
				}
			}
		}
		g.setColor(f.getColor()); //draws food
		g.fillRect( arr[f.getX()][f.getY()].getX() + borderPadding, arr[f.getX()][f.getY()].getY() + borderPadding, arr[f.getX()][f.getY()].getWidth(), arr[f.getX()][f.getY()].getHeight() );
		//g.fillRect( arr[f.getX()][f.getY()].getX() + borderPadding + 2, arr[f.getX()][f.getY()].getY() + borderPadding + 2, arr[f.getX()][f.getY()].getWidth() - 4, arr[f.getX()][f.getY()].getHeight() - 4 );
	}
	
	public int getX(int j)
	{
		return j * 20;
	}
	
	public int getY(int i)
	{
		return i * 20;
	}
	
	public Color getCellBorderColor()
	{
		return cellBorder_Color;
	}
	public Color getCellBackgroundColor()
	{
		return cellBackground_Color;
	}

	

}
