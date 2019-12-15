import java.util.ArrayList;

public class Food {

	//Food Variables
	private int x, y;
	
	
	public Food()
	{
		x = (int)(Math.random() * Board.arr.length);
		y = (int)(Math.random() * Board.arr[0].length);
	}
	
	public void redrawFood(ArrayList<Snake> arr) //Gives the food a new x and y location that doesn't overlap with the snake
	{
		x = (int)(Math.random() * Board.arr.length);
		y = (int)(Math.random() * Board.arr[0].length);
		
		for (int i = 0; i < Snake.arr.size(); i++)
		{
			if (arr.get(i).getNextX(arr.get(i)) == y && arr.get(i).getNextY(arr.get(i)) == x)
				redrawFood(arr);
		}
	}
	
	public boolean isFoodEaten(Snake a) //Did snake eat the food
	{
		return a.getNextX(a) == y && a.getNextY(a) == x;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public Food getFood(Food f) //returns food object
	{
		return f;
	}
	
	
}
