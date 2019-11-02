import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
public class Tile implements Comparable<Tile>
{
 	private Color color;
 	private String name;
 	private int x;
 	private int y;
 	private int bX;
 	private int bY;

    public Tile(Color c, String n, int xPos, int yPos,int b,int by)
    {
       color = c;
       name = n;
       x = xPos;
       y = yPos;
       bX=b;
       bY=by;
    }

    public String getName()
    {
    	return name;
    }
    public void changeColor(Color c)
    {
    	color=c;
    }
    public int compareTo(Tile t)
    {
    	if(getName().charAt(1)==t.getName().charAt(1))
    	{
    		int myTile=Integer.parseInt(t.getName().substring(0,1));
    		int other=Integer.parseInt(getName().substring(0,1));
    		if(myTile==other)
    			return 0;
    		else if(myTile>other)
    			return 1;
    		else
    			return -1;

    	}
    	else
    	{
    		int myTile=Integer.parseInt(t.getName().substring(0,1));
    		int other=Integer.parseInt(getName().substring(0,1));
    		if(myTile==other)
    			return 0;
    		else if(myTile>other)
    			return 1;
    		else
    			return -1;
    	}
    }
    public int getBX()
    {
    	return bX;
    }
    public int getBY()
    {
    	return bY;
    }
    
    public int getX()
    {
    	return x;
    }

    public int getY()
    {
    	return y;
    }
    
    public Color getColor()
    {
    	return color;
    }
    
    public void repaint(Graphics g)
    {
    	 g.setColor(getColor());
		 g.fillRect(getX(), getY(), 50, 50);
		 Rectangle rect = new Rectangle(getX(), getY(), 50, 50);
		 g.setColor(Color.black);
		 drawCenteredString(g, ("" +getName()),rect, new Font("Helvetica", Font.BOLD, 15));
    }

    public void paint(Graphics g)
    {
    	 g.setColor(getColor());
		 g.fillRect(getX(), getY(), 50, 50);
		 Rectangle rect = new Rectangle(getX(), getY(), 50, 50);
		 g.setColor(Color.white);
		 drawCenteredString(g, ("" +getName()),rect, new Font("Helvetica", Font.BOLD, 15));
    }
    
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font)
	{
    	FontMetrics metrics = g.getFontMetrics(font);
    	int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    	int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    	g.setFont(font);
    	g.drawString(text, x, y);
	}

}