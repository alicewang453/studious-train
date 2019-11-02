import java.lang.*;

public class Bludger extends Player
{
	public Bludger(String n, int x, int y)
	{
		super("Bludger",n,x,y);
	}

	public Player chase(Team one, Team two)////findsNearest from either team
  	{
  		Player first = one.getPlayers().get(0);
  		for(int i = 1; i < one.getPlayers().size(); i++)
  		{
  			if(calcDistance(one.getPlayers().get(i))<calcDistance(first))
  				first = one.getPlayers().get(i);
  		}
  		
		Player second = two.getPlayers().get(0);
		for(int i = 1; i < two.getPlayers().size(); i++)
  		{
  			if(calcDistance(two.getPlayers().get(i))<calcDistance(second))
  				second = two.getPlayers().get(i);
  		}
		if(calcDistance(first)<=calcDistance(second))
			return first;
		return second;
  	}
  	
}

/*Player pOne = min;

  		temp = two.getPlayers();
  		min = temp.get(0);
  		for(int i = 1; i < temp.size(); i++)
  		{
  			Player pTemp = temp.get(i);
  			if(calcDistance(pTemp)<calcDistance(min))
  				min = pTemp;
  		}
  		Player pTwo = min;
  		if(calcDistance(pOne) < calcDistance(pTwo))
  			return pOne;
  		return pTwo;*/