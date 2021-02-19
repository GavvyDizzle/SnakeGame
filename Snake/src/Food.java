import java.awt.Color;
import java.util.ArrayList;

public class Food {

	//Food Variables
	private int x, y;
	
	//Colors
	private static Color food_Color = Color.RED;
	
	
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
	
	public void updateColor() //makes the food pulse red
	{
		if (food_Color.getRed() > 200)
			food_Color = new Color(food_Color.getRed()-10, 0, 0);
		else
			food_Color = Color.RED;
		//food_Color = Color.RED;
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
	
	public Color getColor()
	{
		return food_Color;
	}
	
	public Food getFood(Food f) //returns food object
	{
		return f;
	}
	
	
}
