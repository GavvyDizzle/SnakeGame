import java.util.ArrayList;

public class Snake {
	
	//Snake Variables
	private int x, y;
	private int nextX, nextY;
	public static boolean isUp = false, isDown = false, isLeft = false, isRight = false;
	
	//ArrayList for Snake
	public static ArrayList<Snake> arr = new ArrayList <Snake>();
	
	
	public Snake(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.nextX = x;
		this.nextY = y;
		arr.add(this);
	}
	
	public int getX(Snake a)
	{
		return this.x;
	}
	public int getY(Snake a)
	{
		return this.y;
	}
	public int getNextX(Snake a)
	{
		return this.nextX;
	}
	public int getNextY(Snake a)
	{
		return this.nextY;
	}
	
	public void updateSnake(Food f) //if snake is in a board spot set Board.bool[i][j] = true
	{
		calcNextPos();
		outOfBounds();
		isCollide();
		if (f.isFoodEaten(arr.get(0))) {
			f.redrawFood(arr);
			newSegment();
		}
		if (!TopClass.isGameOver) {
			updateHead(arr.get(0));
			updateBody();
		}
	}
	
	private void calcNextPos()
	{
		if(isUp)
			arr.get(0).nextY--;
		if(isDown)
			arr.get(0).nextY++;
		if(isRight)
			arr.get(0).nextX++;
		if(isLeft)
			arr.get(0).nextX--;
		
		for (int i = 1; i < arr.size(); i++) { //set nextX/Y equal to the x and y of the previous index
			arr.get(i).nextX = arr.get(i-1).x;
			arr.get(i).nextY = arr.get(i-1).y;
		}
	}
	
	private void updateHead(Snake a)
	{	
		if (arr.size() == 1)
			Board.bool[y][x] = false;
		x = nextX;
		y = nextY;
		Board.bool[y][x] = true;
	}
	
	private void updateBody() //go to position of block of i - 1
	{
		if (arr.size() > 1)
			Board.bool[arr.get(arr.size()-1).y][arr.get(arr.size()-1).x] = false;
		
		for (int i = 1; i < arr.size(); i++) {
			arr.get(i).x = arr.get(i).nextX;
			arr.get(i).y = arr.get(i).nextY;
			
			//arr.set(i, arr.get(i - 1));
			Board.bool[arr.get(i).y][arr.get(i).x] = true;
		}
	}
	
	public static void newSegment()
	{
		new Snake(arr.get(arr.size()-1).x, arr.get(arr.size()-1).y);
	}
	
	private void isCollide()
	{
		for (int i = 4; i < arr.size(); i++)
			if ( arr.get(0).nextX == arr.get(i).nextX && arr.get(0).nextY == arr.get(i).nextY )
					TopClass.isGameOver = true;
	}
	
	public void outOfBounds()
	{
		if ( arr.get(0).nextX >= Board.arr.length || arr.get(0).nextY >= Board.arr[0].length ||
			arr.get(0).nextX < 0 || arr.get(0).nextY < 0 )
		{
			TopClass.isGameOver = true;
		}
	}
	
}
