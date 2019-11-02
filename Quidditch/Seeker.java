import java.util.*;
import java.lang.Math;

public class Seeker extends Player
{
   	private int vision;
    public Seeker(String p, String name, int x, int y)
    {
      	super(p,name,x,y);
      	vision=(int)(Math.random()*10+1);
    }
    public boolean clutch(Snitch s)////if scan is true, call on clutch; else randomize postiion
    {
    	decEnergy(5);
    	if(getSpeed()>=calcDistance(s))
			if(s.getEnergy()==0)
				return true; 
		return false;
    }

    public void move()
    {
    	int x = (int)(Math.random()*11);
		int y = (int)(Math.random()*21);
		super.move(x,y);
    }

    public boolean scan(Snitch s)
    {
 		if(calcDistance(s)<=5)
 			return true;

	 	else
 			return false;
 	}

}