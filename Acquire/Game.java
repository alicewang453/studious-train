import java.util.*;

public class Game
{

   public static void main(String[]args)
   {
   	  Scanner kb=new Scanner(System.in);
   	  System.out.print("Welcome to Acquire!\nPlease enter your name: ");
   	  String name=kb.next();
   	  Acquire obj=new Acquire(name);
	//  obj.playerTurnOut();

   	/*  if(!obj.isGameOver())
   	  {
   	  	Player now=obj.getPlayer();
   	  	if(now.equals(obj).getUser())
   	  	{
   	  		//places tile
   	  		if(obj.getBoard().isConnected())


   	  	}
   	  }*/

   }

}