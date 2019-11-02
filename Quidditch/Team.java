import java.io.*;
import java.util.*;

public class Team
{
    private ArrayList<Beater> beaters;
    private ArrayList<Seeker> seekers;
    private ArrayList<Chaser> chasers;
    private ArrayList<Player> extras;
    private Keeper k;
    private String name;
    private int points;

    public Team(File team) throws IOException
    {
		beaters = new ArrayList<Beater>();
		seekers = new ArrayList<Seeker>();
		chasers = new ArrayList<Chaser>();
    	points=0;
    	Scanner read =new Scanner(team);
		name = read.nextLine();
		extras = new ArrayList<Player>();

		while(read.hasNextLine())
		{
			String[] line = read.nextLine().split(" ");
			if(line[0].equals("Beater"))
				beaters.add(new Beater("Beater ", line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3])));
			else if(line[0].equals("Seeker"))
				seekers.add(new Seeker("Seeker ", line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3])));
			else if(line[0].equals("Chaser"))
				chasers.add(new Chaser("Chaser ", line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3]),false));
			else
				k = new Keeper("Keeper ", line[1], Integer.parseInt(line[2]),Integer.parseInt(line[3]));
		}
		extras.add(beaters.remove(beaters.size()-1));
		extras.add(chasers.remove(chasers.size()-1));
		extras.add(seekers.remove(seekers.size()-1));
	}

	public ArrayList<Beater> getBeaters()
	{
		return beaters;
	}

	public ArrayList<Seeker> getSeekers()
	{
		return seekers;
	}

	public ArrayList<Chaser> getChasers()
	{
		return chasers;
	}

	public Keeper getKeeper()
	{
		return k;
	}

	public ArrayList<Player> getExtras()
	{
		return extras;
	}

	public ArrayList<Player> getPlayers()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		players.addAll(getBeaters());
		players.addAll(getChasers());
		players.addAll(getSeekers());
		players.add(k);
		return players;
	}

   	public String getName()
   	{
   		return name;
   	}

 	public int getPoints()
 	{
 		return points;
 	}
 	
 	public Chaser getHasQuaffle()
 	{
 		for(int i = 0; i < getChasers().size(); i++)
			if(getChasers().get(i).hasQuaffle())
				return getChasers().get(i);
		return null;		
	}
	
	public Chaser getMatchingChaser(String name)
	{
		for(int i = 0; i < getChasers().size(); i++)
			if(getChasers().get(i).getName().equalsIgnoreCase(name))
				 return getChasers().get(i);
		return null;
	}
 	
 	public boolean hasQuaffle()
 	{
 		for(int i = 0; i < getChasers().size(); i++)
			if(getChasers().get(i).hasQuaffle())
				return true;
		return false;
 	}

 	public Chaser getFastChaser()
 	{
 		return getChasers().get(0);
	}

 	public void addPoints(int p)
 	{
 		points+=p;
 	}

 	public String toString()
 	{
 		String rString = "";
 		for(int i = 0; i < getPlayers().size(); i++)
 			rString += getPlayers().get(i).toString() + "\n";
 		return rString;
 	}
}