import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Acquire
{

  private AcquireFrame frame;
  private Player user;
  private Bank b;
  private Board board;
 private ArrayList<Player>players;
 private boolean gameOver;
 private ListIterator iter;
  public Acquire(String name)
  {
  	
  	frame=new AcquireFrame();
  	b=frame.getBank();
  	user=new Player(name,b.getPlayerTiles());
  	board=new Board();
  	
  	gameOver=false;
  	players=new ArrayList<Player>();
  	players.add(new Player("Computer 1",b.getPlayerTiles()));
    players.add(new Player("Computer 2",b.getPlayerTiles()));
    players.add(new Player("Computer 3",b.getPlayerTiles()));
    players.add(user);
    assignInitialTiles();
    iter=players.listIterator();
  }
  public void setGameOver(boolean g)
  {
  	gameOver=g;
  }
  public boolean gameOver()
  {
  	return gameOver;
  }
  /*public Player getPlayer()
  {
  	Object p=iter.next();
      if(p.equals(players.get(3)))
      	iter=players.listIterator();
  	return p;

  }*/

  public Board getBoard()
  {
  	return board;
  }
  public Player getUser()
  {
  	return user;
  }

  public void assignInitialTiles()
  {
  	String output = "";
     ArrayList<Tile> temp=b.getInitialTiles();
     for(int x=0;x<temp.size();x++)
     {
     	output+=players.get(x).getName() + " places down the tile " + temp.get(x).getName() +"\n";
     	frame.addTile(temp.get(x));
     }
     output+="Place a tile to start your turn.\n";
     frame.addOutput(output);
  }
  
  public void playerTurnOut()
  {
  	frame.playerTurn();
  }

}