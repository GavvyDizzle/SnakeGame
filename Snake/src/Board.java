import java.awt.Graphics;

public class Board {
	
	//Constants
	public static final int TILE_WIDTH = 25;
	public static final int TILE_HEIGHT = 25;
	public static final int borderPadding = 3;
	public static int XTILES;
	public static int YTILES;
	
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
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (!bool[i][j]) { //empty spots
					g.setColor(TopClass.cellBG);
					g.fillRect( arr[i][j].getX() + borderPadding, arr[i][j].getY() + borderPadding, arr[i][j].getWidth(), arr[i][j].getHeight() );
				}
				else { //draws snake
					if (i == Snake.arr.get(0).getY(Snake.arr.get(0)) && j == Snake.arr.get(0).getX(Snake.arr.get(0))) //head
						g.setColor(TopClass.snakeHead);
					else
						g.setColor(TopClass.snakeBody); //body
					g.fillRect( arr[i][j].getX() + borderPadding, arr[i][j].getY() + borderPadding, arr[i][j].getWidth(), arr[i][j].getHeight() );
				}
			}
		}
		g.setColor(TopClass.foodColor); //draws food
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

}
