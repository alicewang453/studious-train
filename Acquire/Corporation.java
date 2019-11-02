import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Corporation
{

   	private int price;
	private String corpName;
	private ArrayList<StockCard> stockCards;
	private Color color;
	private int numTiles;
	private boolean isOnBoard;
	private int majority;
	private int minority;
	private int x;
	private int y;

	public Corporation(int p, String cN,int maj,int min,Color c,int xPos,int yPos)
	{
		stockCards=new ArrayList<StockCard>();
		for(int x=0;x<6;x++)
		{
			stockCards.add(new StockCard(p,cN,maj,min,c));
		}
		price=p;
		majority=maj;
		minority=min;
		corpName=cN;
		color=c;
		numTiles=0;
		isOnBoard=false;
		x=xPos;
		y=yPos;
	}
	 public void paint(Graphics g)
    {
    	 g.setColor(getColor());
		 g.fillRect(getX(), getY(), 120, 120);
		 Rectangle rect = new Rectangle(getX(), getY(), 120, 120);
		 g.setColor(Color.black);
		 g.drawRect(getX(), getY(), 120, 120);
		 drawCenteredString(g, "" +getName(),rect, new Font("Helvetica", Font.BOLD, 15));

    }

		public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font)
	{
    	FontMetrics metrics = g.getFontMetrics(font);
    	int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    	int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    	g.setFont(font);
    	g.drawString(text, x, y);
	}
		public int getMaj()
	{
		return majority;
	}
	public Color getColor()
	{
		return color;
	}
	public void changeMajAndMin()
	{
		majority=minority;
		minority=majority;
	}

	public int getMin()
	{
		return minority;
	}

	public void setIsOnBoard(boolean is)
	{
		isOnBoard=is;
	}
	public void changePrice()
	{
		for(StockCard card:stockCards)
			card.setPrice(price);
	}

	public void addTile()
	{
		numTiles++;
	}
	public int getNumTiles()
	{
		return numTiles;
	}

	public void setPrice(int p)
	{
	   price=p;
	}
	public int getPrice()
	{
		return price;
	}
	public StockCard getStockCard(int x)
	{
		return stockCards.get(x);
	}
	public void addStockCard(ArrayList<StockCard>stock)
	{
		stockCards.addAll(stock);
	}
	public void removeStockCard(int quantity)
	{
		int index=stockCards.size()-1;
		for(int x=0;x<quantity;x++)
		{
			stockCards.remove(index);
			x++;
		}
	}
		public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public boolean isOnBoard()
	{
		return isOnBoard;
	}


	public String getName()
	{
		return corpName;
	}
	public String toString()
	{
        String str="";

	    for(int x=0;x<stockCards.size();x++)
	    {
	    	StockCard temp=stockCards.get(x);
	    	str+=temp.getName()+"\n"+"Price:$"+temp.getPrice()+"\nMinority:"+temp.getMin()+"\nMajority:"+temp.getMaj();
	    }
	    return str;
	}
}