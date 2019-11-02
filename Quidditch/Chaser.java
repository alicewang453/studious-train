import java.util.*;
import java.lang.Math;

public class Chaser extends Player
{
	private boolean  hasQuaffle;
	public Chaser(String p, String n, int x, int y, boolean q) ////////if bludger hits you and you have quaffle, drop and calls intercept
  	{
  		super(p,n,x,y);
  		hasQuaffle = q;
 	}

/*	public void shoot()/////same as passing to goal
	{
		decEnergy(5);
  		int roll = (int)(Math.random()*10 +1);
  		if(roll>=calcDistance(p))
  			return true;
  		return false;
 	}*/

	public boolean hasQuaffle()
	{
		return hasQuaffle;
	}

	public void setHas(boolean q)//////////in field if hasQuaffle, change quaffle's x and y
	{
		hasQuaffle = q;
	}
	
	public Chaser findNearestChaser(Team myTeam)
	{
		ArrayList<Chaser> temp = new ArrayList<Chaser>();
		temp.addAll(myTeam.getChasers());
		for(int i = temp.size()-1;i>=0;i--)
			if(temp.get(i).getName().equalsIgnoreCase(getName()))
				temp.remove(i);
  		Chaser min = temp.get(0);
  		for(int i = 1; i < temp.size(); i++)
  		{
  			Chaser cTemp = temp.get(i);
  			if(calcDistance(cTemp)<calcDistance(min))
  				min = cTemp;
  		}
  		return min;
	}

   	public boolean intercept(Quaffle q)
   	{
		if(getSpeed() >= q.getSpeed())
			if((int)(Math.random()*10+1) <= getSpeed())
				return true;
		return false;
   	}

  /* 	public boolean recieve()
   	{
		int temp = (int)(Math.random()*10 + 1);
		if(temp > 5)
			return true;
		return false;
   	}

   	 *if pass is true, call on intercept if intercept is false call on recieve if receive is true, change hasQuaffle and quaffle's coordinates
   	 *if pass is true, call on on intercept if intercept is true then change incepter's hasQuaffle and quaffle coordinates
   	 *if pass is true, call on intercept if intercept is false call on receive if receive is false, findNearest player according to recevers' coordinates and goes from there
   	 *if pass is false, findNearest player according to passer's coordinates and goes from there*/
   	 
   	

}