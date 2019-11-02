import java.util.*;
import java.awt.*;
import javax.swing.*;
public class Board
{
	private Tile[][] board;

    public Board()
    {
    	board=new Tile[9][12];
    	int x = 20;
		int y = 40;
		char letter = 'A';
    	int num = 1;
    	for(int r=0;r<board.length;r++)
    	{
    		for(int c=0;c<board[r].length;c++)
    		{
               board[r][c]=new Tile(Color.black,""+num+letter,x,y,r,c);
               x+=55;
              num++;
    		}
    		x=20;
    		y+=55;
    		letter++;
    		num=1;
    	}


    }

    public Tile findTile(String name)
    {
    	for(int x=0;x<board.length;x++)
    		for(int y=0;y<board[x].length;y++)
    			if(board[x][y].getName().equals(name))
    				return board[x][y];
    	return null;
    }
    
    public boolean inBounds(int x , int y)
    {
    	if(x<0||x>8||y<0||y>11)
    		return false;
    	return true;
    }
    
    public boolean isConnected(String name)
    {
    	Tile t = findTile(name);
    	int x=t.getBX();
    	int y=t.getBY();
    	if(inBounds(x, y+1))
	    	if(!(board[x][y+1].getColor()==Color.black))
	    		return true;
	    if(inBounds(x, y-1))
	    	if(!(board[x][y-1].getColor()==Color.black))
	    		return true;
	    if(inBounds(x+1, y))
	    	if(!(board[x+1][y].getColor()==Color.black))
	    		return true;
	    if(inBounds(x-1, y))
	    	if(!(board[x-1][y].getColor()==Color.black))
	    		return true;
    	return false;
    }

    public Tile getConnected(String name)
    {
    	Tile t = findTile(name);
    	int x=t.getBX();
    	int y=t.getBY();
    	if(inBounds(x, y+1))
	    	if(!(board[x][y+1].getColor()==Color.black))
	    		return board[x][y+1];
	    if(inBounds(x, y-1))
	    	if(!(board[x][y-1].getColor()==Color.black))
	    		return board[x][y-1];
	    if(inBounds(x+1, y))
	    	if(!(board[x+1][y].getColor()==Color.black))
	    		return board[x+1][y];
	    if(inBounds(x-1, y))
	    	if(!(board[x-1][y].getColor()==Color.black))
	    		return board[x-1][y];
    	return null;
    }
    
    public Tile getConGray(String name)
    {
    	Tile t = findTile(name);
    	int x=t.getBX();
    	int y=t.getBY();
    	if(inBounds(x, y+1))
	    	if(board[x][y+1].getColor()==Color.gray)
	    		return board[x][y+1];
	    if(inBounds(x, y-1))
	    	if(board[x][y-1].getColor()==Color.gray)
	    		return board[x][y-1];
	    if(inBounds(x+1, y))
	    	if(board[x+1][y].getColor()==Color.gray)
	    		return board[x+1][y];
	    if(inBounds(x-1, y))
	    	if(board[x-1][y].getColor()==Color.gray)
	    		return board[x-1][y];
    	return null;
    }
    
    public void changeTile(String name, Color c)
    {
    	findTile(name).changeColor(c);
    }


    public void paint(Graphics g)
	{
		for(int r = 0; r < 9; r++)
			for(int c = 0; c < 12; c++)
				board[r][c].paint(g);

	}
	public void addTile(Tile t)
	{
		board[t.getX()][t.getY()]=t;
	}
	public boolean equals(Tile t)
	{
	/*	if(getName().equals(t.getName()))
			return true;*/
		return false;


	}

    public String toString()
    {
    	String str="";
    	for(int r=0;r<board.length;r++)
    	{
    		for(int c=0;c<board[r].length;c++)

    			str+=board[r][c]+"\t";
    			str+="\n";

    	}
    	return str;
    }

}