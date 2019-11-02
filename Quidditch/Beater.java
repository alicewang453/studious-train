import java.util.*;
import java.lang.*;

public class Beater extends Player
{
	public Beater(String p,String n, int x, int y)
  	{
  		super(p,n,x,y);
 	}
   	
   	public Player findNearestPlayer(Team oppTeam)
   	{
   		Player temp = oppTeam.getPlayers().get(0);
   		for(int i = 1; i < oppTeam.getPlayers().size(); i++)
  		{
  			if(calcDistance(oppTeam.getPlayers().get(i))<calcDistance(temp))
  				temp = oppTeam.getPlayers().get(i);
  		}
  		return temp;
   	}

   	public boolean defend(Player p)/////passed in player that needs to be rescued; if true, call attack if false dec health of player passed in and set bludgers postion to player beater moved to space next to player
   	{
   		decEnergy(5);
   		int roll = (int)(Math.random()*10+1);
		if(getSpeed()>=calcDistance(p))
			if(roll<=getSpeed())
				return true;
		return false;
   	}
}