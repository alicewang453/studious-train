import java.util.*;

public class Player 
{
  	private String position, name;
  	private ArrayList<Integer> stats;
  	private int xPos,yPos;

  	public Player(String p, String n, int x, int y)
  	{
  		position = p;
  		name = n;
  		xPos = x;
  		yPos = y;
  	 	stats = new ArrayList<Integer>();
  	 	randomStats();
 	}

	public void randomStats()
	{
		for(int i = 0; i <3; i++)
			stats.add((int)(Math.random()*10+1));
		stats.add(100);
	}

	public String getName()
	{
		return name;
	}
	
	public String getPos()
	{
		return position;
	}

	public void move(int x, int y)//calls Energy
	{
		decEnergy(calcDistance(y,y));
  		xPos = x;
  		yPos = y;
  		
  	}

  	public int getStrength()
  	{
  		return stats.get(0);
  	}

  	public int getEnergy()
  	{
  		return stats.get(3);
  	}

  	public int getSpeed()
  	{
  		return stats.get(2);
  	}

	public int getEvade()
	{
		return stats.get(1);
	}

	public ArrayList<Integer> getStats()
	{
		return stats;
	}

    public void skip()
    {
    	stats.set(1,stats.get(1)+15);
    }

	public boolean avoid()////////////////return if avoided successfully
	{
		return true;
	}

	public void decEnergy(int dec)
	{
		stats.set(1,stats.get(1)-dec);
	}

/*	public boolean pass(Player p)/////if pass is successful, change quaffle coordinates////////////if pass fails, can try to call on intercept(); if intercept fails, quaffle goes to closest chaser?
  	{
  		decEnergy(5);
  		int roll = (int)(Math.random()*10 +1);
  		if(roll>=calcDistance(p))
  			return true;
  		return false;
  	}*/

  	public Chaser findNearestChaser(Team myTeam)//////////main will be able to findNearest or specify
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
  	}

	public int getX()
	{
 		return xPos;
	}

 	public int getY()
 	{
 		return yPos;
	}

 	public int calcDistance(Player p)
 	{
 		return (int)(Math.sqrt((Math.pow(getX()-p.getX(),2))+Math.pow(getY()-p.getY(),2)));

 	}
 	
 	public int calcDistance(int a, int b)
 	{
 		return (int)(Math.sqrt((Math.pow(getX()-a,2))+Math.pow(getY()-b,2)));
 	}

/*	public int compareTo(Player other)
	{
		if(getSpeed()>other.getSpeed())
			return -1;
		else if(getSpeed()<other.getSpeed())
			return 1;
		else
			return 0;
	}*/
	
	public Beater findNearestBeater(Team myTeam)
	{
		if(calcDistance(myTeam.getBeaters().get(0))<calcDistance(myTeam.getBeaters().get(1)))
			return myTeam.getBeaters().get(0);
		else
			return myTeam.getBeaters().get(1);
	}	

	public String toString()
	{
		String rString = "";
		rString += getPos() + " " + getName() + "\t";
		for(int i = 0; i < getStats().size(); i++)
		{
			if(i==0)
				rString += "Strength: " + getStrength() + "\t";
			else if(i==1)
				rString += "Evade: " + getEvade() + "\t";
			else if(i==2)
				rString += "Speed: " + getSpeed() + "\t";
			else
				rString += "Energy: " + getEnergy() + "\t";
		}
		return rString;
	}

}