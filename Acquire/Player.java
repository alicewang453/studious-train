import java.util.*;

public class Player
{
	private String name;
	private Board board;
	private ArrayList<Tile>tiles;
	private ArrayList<StockCard>stockcards;
	private int money;



    public Player(String n, ArrayList<Tile>tile)
    {
      name=n;
      tiles=tile;

      stockcards=new ArrayList<StockCard>();
      money=6000;

    }


    public ArrayList<Tile> getTiles()
    {
    	return tiles;
    }

    public void addTiles(Tile t)
    {
    	tiles.add(t);
    }
    public String getName()
    {
    	return name;
    }
    public int getMoney()
    {
    	return money;
    }
    public void changeMoney(String sign, int amount)
    {
    	if(sign.equals("-"))
    		money-=amount;
    	else
    		money+=amount;
    }
    //////count how many of type stockCard
    public int howManyType(String cardName)
    {
    	int count=0;
    	for(int x=0;x<stockcards.size();x++)
    	{
    		if(stockcards.get(x).getName().equals(cardName))
    			count++;
    	}
    	return count;
    }

    public void placeTile(Tile t)
    {
    	tiles.remove(t);
    	board.addTile(t);

    }
    public ArrayList<StockCard>getStockCards()
    {
    	return stockcards;
    }

public boolean equals(Player p)
  {
  	if(getName().equals(p.getName()))
  		return true;
  	 return false;
  }
}