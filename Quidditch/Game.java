import java.util.*;
import java.lang.*;
import java.io.*;

public class Game
{
    public static void main(String[]args) throws IOException
    {
    	Scanner kb = new Scanner(System.in);
		Team team = new Team(new File("Team1.dat"));
		Team comp = new Team(new File("computer.dat"));
		Field myField = new Field(team, comp);
		
		System.out.println(team.getName() + " players: \n" + team.toString());
		System.out.println(comp.getName() + " players: \n" + comp.toString());
		
		while(!myField.getSnitch().isFound())
		{
			System.out.println(myField);
			if(team.hasQuaffle())
			{
				System.out.println(team.getName() + " has the Quaffle!");
				System.out.print("Choose a Chaser to play: ");//////////////finding player user wants to manipulate
				String playerName = kb.nextLine();
				Chaser player = team.getMatchingChaser(playerName);
				if(player.hasQuaffle())//////////////if player has quaffle can only pass or shoot	
				{
					System.out.print("\nDo you want to pass or Hail Mary(a.k.a. shoot)? ");
					String choice = kb.nextLine();
					if(choice.equalsIgnoreCase("pass"))////they chose to pass
					{
						System.out.print("Choose a player to pass to: ");
						String pass = kb.next();
						Chaser passTo = team.getMatchingChaser(pass);
						myField.pass(comp,player,passTo);
					}
					else///////////////they chose to shoot
						myField.shoot(player, team, comp);
					
				}
				else//////////////player is going to move;if player doesn't have quaffle
					myField.movePlayer(player);
			}
			
			//////////////////////////computer's turn
			else
			{
				Chaser player = comp.getHasQuaffle();
				if(player.calcDistance(myField.getGoalTeam())>5)/////////////////////////goal not in radius, move other player and pass/intercept
				{
					Chaser passTo = player.findNearestChaser(comp);
					myField.moveToMidPoint(player,myField.getGoalTeam(),passTo);
					
					myField.pass(team, player, passTo);
				}
				else/////////////shoot
					myField.shoot(player,comp,team);
			}/////////////////////////////////chaser seg done
			
			/////////////////bludgers
			System.out.println();
			myField.bludgerTurn(myField.getBludgerOne());
			myField.bludgerTurn(myField.getBludgerTwo());
			
			////////////////snitch
			System.out.println();
			myField.snitchMove();
			
			myField.scoreboard();
		}
		
		myField.winner();
	}
}