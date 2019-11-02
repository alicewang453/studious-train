import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class Bank
{
	private ArrayList<Tile>tiles;
	private ArrayList<Corporation>corp;

    public Bank()
    {
    	tiles=new ArrayList<Tile>();

    	char letter='A';
    	int num=1;
        for(int r=0;r<9;r++)
        {
        	for(int c=0;c<12;c++)
        	{
        		tiles.add(new Tile(Color.black,(""+num+letter),r,c,0,0));
                num++;
        	}
        	letter++;
        	num=1;
        }
        corp=new ArrayList<Corporation>();
        corp.add(new Corporation(200,"Sackson",2000,1000,Color.red,700,130));
        corp.add(new Corporation(200,"Zeta",2000,1000,Color.yellow,850,130));
        corp.add(new Corporation(300,"Hydra",3000,1500,Color.orange,1000,130));
        corp.add(new Corporation(300,"Fusion",3000,1500,Color.green,700,270));
        corp.add(new Corporation(300,"America",3000,1500,Color.blue,850,270));
        corp.add(new Corporation(400,"Phoenix",4000,2000,Color.magenta,1000,270));
        corp.add(new Corporation(400,"Quantum",4000,2000,Color.cyan,1150,130));

    }
    
    public Corporation findCorp(Color c)
    {
    	for(int i = 0; i < corp.size(); i++)
    		if(corp.get(i).getColor()==c)
    			return corp.get(i);
    	return null;
    }
    
    public boolean noneOnBoard()
    {
    	if(getAvailable().size()==7)
    		return true;
    	return false;
    }

    public ArrayList<Corporation> getAvailable()
    {
    	ArrayList<Corporation> temp = new ArrayList<Corporation>();
    	for(int i = 0; i < corp.size(); i++)
    		if(!corp.get(i).isOnBoard())
    			temp.add(corp.get(i));
    	return temp;
    }
    
    public Corporation findCorp(String name)
    {
    	for(int i = 0; i < corp.size(); i++)
    		if(corp.get(i).getName().equals(name))
    			return corp.get(i);
    	return null;
    }

    public void paint(Graphics g)
    {
    	for(int x=0;x<corp.size();x++)
        {
        	corp.get(x).paint(g);
        }
    }
    public Tile getRand()
    {
      int rand=(int)(Math.random()*tiles.size());
      return tiles.remove(rand);

    }
    public ArrayList<Tile>getPlayerTiles()
    {
    	ArrayList<Tile>rand=new ArrayList<Tile>();
    	for(int x=0;x<6;x++)
    		rand.add(getRand());
    	return rand;
    }
    public ArrayList<Tile>getInitialTiles()
    {
          ArrayList<Tile>initial=new ArrayList<Tile>();
          for(int x=0;x<4;x++)
          	initial.add(getRand());
          return initial;
    }

    public Corporation getCorp(int x)
    {
       return corp.get(x);
    }
    
    public void drawPlayerTiles(Graphics g)
    {
    	int x=0;
    	int y=50;
    	for(int c=0;x<6;c++)
    	{
    		g.drawRect(x,y,5,5);
    		x++;
    	}


    }


}