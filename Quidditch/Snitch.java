import java.lang.Math;

public class Snitch extends Player
{
	private boolean isFound;

	public Snitch(int x, int y)
	{
		super("Snitch","Snitch",x,y);
		isFound = false;
	}

	public void move()
	{
		int x = (int)(Math.random()*11);
		int y = (int)(Math.random()*21);
		super.move(x,y);
	}

	public boolean isFound()
	{
		return isFound;
	}

	public void setFound(boolean b)
	{
		isFound = b;
	}

}