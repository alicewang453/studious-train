import java.util.*;
import java.lang.Math.*;

public class Keeper extends Player
{
	private int save;

	public Keeper(String p,String n, int x, int y)
  	{
  		super(p,n,x,y);
  		save = (int)(Math.random()*10+1);
 	}

 	public int getSave()
 	{
 		return save;
 	}


  	public boolean save(Quaffle q)////if keeper saves, pass
  	{
  		int roll = (int)(Math.random()*10 +1);
		if(getSave()>=q.getSpeed())///////if save is bigger than speed, save quaffle call pass in other class; else up points of opposite team and give quaffle to other team			
			if(roll<=getSave())
				return true;
		return false;
		
  	}

  /*	public boolean pass(Player p)/////if pass is successful, change quaffle coordinates
  	{
  		int roll = (int)(Math.random()*10 +1);
  		if(roll>=calcDistance(p))
  			return true;
  		return false;

  	}

  	public Chaser findNearest(Team myTeam)//////////main will be able to findNearest or specify
  	{
  		ArrayList<Chaser> temp = myTeam.getChasers();
  		Chaser min = temp.get(0);
  		for(int i = 1; i < temp.size(); i++)
  		{
  			Chaser cTemp = temp.get(i);
  			if(calcDistance(cTemp)<calcDistance(min))
  				min = cTemp;
  		}
  		return min;
  	}*/
}