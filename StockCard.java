import java.util.*;
import java.awt.*;
import javax.swing.*;
public class StockCard
{


	private int price;
	private String name;
	private int majority;
	private int minority;
    private Color color;
    

   public StockCard(int p, String n, int maj, int min, Color c )
   {
   	price=p;
   	name=n;
   	majority=maj;
   	minority=min;
   	color=c;
   
   }
   public void setPrice(int p)
   {
   	price=p;
   }
   	public int getMaj()
	{
		return majority;
	}
	public void changeMajAndMin()
	{
		majority=minority;
		minority=majority;
	}
	public int getPrice()
	{
		return price;
	}
	public int getMin()
	{
		return minority;
	}
	public String getName()
	{
		return name;
	}
	public Color getColor()
	{
		return color;
	}

/*	public void paint(Graphics g)
	{
	    	int r=700;
   	  	int c=50;
   	  	String numOfStocks[]={"1","2","3"};
   	  	for(int i=0;i<4;i++)
   	  	{
            g.setColor(getColor());
            g.fillRect(r,c,150,150);
            Rectangle rect=new Rectangle(r,c,150,150);
            g.setColor(Color.black);
            g.drawString(getName(),r,c+15);
            g.drawString("Price:$"+getPrice(),r,c+30);
            g.drawString("Minority:$"+getMin(),r,c+45);
            g.drawString("Majority:$"+getMaj(),r,c+60);


   	  		r+=250;
   	  	}

   	  	 int x=700;
   	  	 int y=300;

   	  	for(int a=4;a<7;a++)
   	  	{

   	  	   	g.setColor(getColor());
            g.fillRect(x,y,150,150);
            Rectangle rect=new Rectangle(x,y,150,150);
            g.setColor(Color.black);
           drawCenteredString(g,toString(),rect, new Font("Helvetica", Font.BOLD, 10));

            x+=250;
   	  	}

	}*/
	
     public String toString()
   {
   	return getName()+" "+"Price:$"+getPrice()+" "+"Minority:$"+getMin()+" "+"Majority:$"+getMaj();
   }
	}