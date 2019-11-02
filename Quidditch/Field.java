import java.lang.*;
import java.util.*;

import java.io.*;

public class Field
{
	private Player[][] myField;
  	private Team team, comp;
  	private Snitch s;
  	private Bludger b1,b2;
  	private Quaffle q;
////////////////team file has all postitions
  	public Field(Team one, Team two) throws IOException
  	{
  		myField=new Player[11][21];
  		team = one;
  		comp = two;
  		setField();
  		makeBludgers();
  		makeSnitch();
  		makeQuaffle();
  		makeGoals();
  	}

	public Bludger getBludgerOne()
	{
		return b1;
	}
	
	public Bludger getBludgerTwo()
	{
		return b2;
	}
	
	public Snitch getSnitch()
	{
		return s;
	}
	
	public Quaffle getQuaffle()
	{
		return q;
	}
	
	public void setNull(int x, int y)
	{
		myField[x][y]=null;
	}
	
	public boolean isNull(int x, int y)
	{
		if(myField[x][y]==null)
			return true;
		return false;
	}

	public void makeQuaffle()
	{
		Chaser compC = comp.getFastChaser();
		Chaser teamC = team.getFastChaser();
		if(compC.getSpeed()>teamC.getSpeed())
		{
			q = new Quaffle(compC.getX(),compC.getY());///////////////////////////change so that field doesn't have quaffle only chaser does
			compC.setHas(true);
			System.out.println(compC.getPos() + " " + compC.getName() + " from Team " + comp.getName() + " starts with the Quaffle!");
		}
		else
		{
			q = new Quaffle(teamC.getX(),teamC.getY());
			teamC.setHas(true);
			System.out.println(teamC.getPos() + " " + teamC.getName() + " from Team " + team.getName() + " starts with the Quaffle!");
		}
	}
	
	public Player getGoalTeam()
	{
		return myField[5][20];
	}
	
	public Player getGoalComp()
	{
		return myField[5][0];
	}

	public void makeGoals()
	{
		myField[5][0]=new Player("Goal",comp.getName().substring(0,1) + "'s Goal",5,0);
		myField[5][20]=new Player("Goal",team.getName().substring(0,1) + "'s Goal",5,20);
	}

	public void makeSnitch()
	{
		int x = (int)(Math.random()*11);
  		int y = (int)(Math.random()*21);
  		while(myField[x][y]!=null)
  		{
  			x = (int)(Math.random()*11);
  			y = (int)(Math.random()*21);
  		}
  		s = new Snitch(x,y);
	}

	public void makeBludgers()
	{
		int x = (int)(Math.random()*11);
  		int y = (int)(Math.random()*21);
  		while(myField[x][y]!=null)
  		{
  			x = (int)(Math.random()*11);
  			y = (int)(Math.random()*21);
  		}
  		b1 = new Bludger("Bludger one", x, y);
  		while(myField[x][y]!=null)
  		{
  			x = (int)(Math.random()*11);
  			y = (int)(Math.random()*21);
  		}
  		b2 = new Bludger("Bludger two", x, y);
	}

  	public void setField()
  	{
  		for(int i = 0; i < team.getPlayers().size(); i++)
  		{
  			Player temp = team.getPlayers().get(i);
  			myField[temp.getX()][temp.getY()]=temp;
  		}

  		for(int i = 0; i < comp.getPlayers().size(); i++)
  		{
  			Player temp = comp.getPlayers().get(i);
  			myField[temp.getX()][temp.getY()]=temp;
  		}
  	}

  	public void setField(Player p)
  	{
  		myField[p.getX()][p.getY()] = p;
  	}

	public void scoreboard()
	{
		System.out.println("Team " + team.getName() + " has a total of " + team.getPoints()+ "!");
		System.out.println("Team " + comp.getName() + " has a total of " + comp.getPoints() + "!");
	}

	public void winner()
    {
    	scoreboard();
    	if(team.getPoints()>comp.getPoints())
    		System.out.println("Team " + team.getName()+" wins!");
       	else if(team.getPoints()<comp.getPoints())
    		System.out.println("Team " + comp.getName()+" wins!");
       	else
       		System.out.println("Teams " + team.getName() + " and " + comp.getName() + " tie!");
    }

  	public String toString()
  	{
  		String rString = "";
  		for(int r=0;r<myField.length;r++)
  		{
  			for(int c=0;c<myField[r].length; c++)
  			{
  				rString+= "[" + r + "][" + c + "]";
  				if(myField[r][c]==null)
  					rString += "\t\t\t";
  				else
  				{
  					rString+=myField[r][c].getName();
  					int i =(int)((10-myField[r][c].getName().length())/2);
  					while(i>0)
  					{
  						rString+="\t";
  						--i;
  					}
  				}
  			}
  			rString+="\n";

  		}
  		return rString;
  	}
  	
  	public boolean isInTeam(Player p)
  	{
  		if(team.getPlayers().contains(p))
  			return true;
  		return false;
  	}
  	
  	public void moveToMidPoint(Player one, Player two, Player three)///////////three is moving
  	{
		int x = (int)((one.getX()+two.getX())/2);
		int y = (int)((one.getY()+two.getY())/2);
		while(!isNull(x,y))
		{
			x+=1;
			y+=1;
		}
		setNull(three.getX(),three.getY());
		three.move(x,y);///////moves player to midpoint etween chaser with uaffle and goal
		setField(three);
  	}
  	
  	public void scored(Team scored, Team oppTeam)
  	{
  		System.out.println("Team " + scored.getName() + " has scored and won 10 points!");
		scored.addPoints(10);
		Chaser opp = oppTeam.getFastChaser();
		System.out.println(opp.getPos() + " " + opp.getName() + " from Team " + oppTeam.getName() + " has the Quaffle now!");
		q.changePos(opp);
		opp.setHas(true);
  	}
  	
  	public void movePlayer(Player p)
  	{
  		Scanner kb = new Scanner(System.in);
  		System.out.println("Where do you want to move?");
		System.out.print("Enter row coordinate: ");
		int r = kb.nextInt();
		System.out.print("Enter column coordinate: ");
		int c = kb.nextInt();
		kb.nextLine();
		while(myField[r][c]!=null)
		{
			System.out.println("Spot is taken. Choose another spot.");
			System.out.println("Where do you want to move?");
			System.out.print("Enter row coordinate: ");
			r = kb.nextInt();
			System.out.print("Enter column coordinate: ");
			c = kb.nextInt();
			kb.nextLine();
		}
		setNull(p.getX(),p.getY());
		p.move(r,c);
		setField(p);
  	}
  	
  	public void pass(Team opp, Chaser player, Chaser passTo)
  	{
  		System.out.println(player.getPos() + " " + player.getName() + " has the Quaffle and is passing to " + passTo.getPos() + " " + passTo.getName() +"!");
  		Chaser intercepter = player.findNearestChaser(opp); 
  		System.out.println(intercepter.getPos() + " " + intercepter.getName() + " from Team " + opp.getName() + " will try to intercept!");
		if(intercepter.intercept(q))/////////////////interecpt is successful
		{
			moveToMidPoint(player, passTo, intercepter);
			intercepter.setHas(true);
			player.setHas(false);
			q.changePos(intercepter);
			System.out.println("Intercept was successful! " + intercepter.getPos() + " " + intercepter.getName() + " has the Quaffle!");
		}
		else///////pass; intercept was not successful
		{
			System.out.println("Intercept was not successful!");
			passTo.setHas(true);
			player.setHas(false);
			q.changePos(passTo);
			System.out.println(passTo.getPos() + " " + passTo.getName() + " has the Quaffle!");
		}
  	}
  	
  	public void keeperPass(Team opp, Keeper player, Chaser passTo)
  	{
  		System.out.println(player.getPos() + " " + player.getName() + " has the Quaffle and is passing to " + passTo.getName() +"!");
  		Chaser intercepter = player.findNearestChaser(opp); 
  		System.out.println(intercepter.getPos() + " " + intercepter.getName() + " will try to intercept!");
		if(intercepter.intercept(q))/////////////////interecpt is successful
		{
			moveToMidPoint(player, passTo, intercepter);
			intercepter.setHas(true);
			q.changePos(intercepter);
			System.out.println("Intercept was successful! " + intercepter.getPos() + " " + intercepter.getName() + " has the Quaffle!");
		}
		else///////pass; intercept was not successful
		{
			System.out.println("Intercept was not successful!");
			passTo.setHas(true);
			q.changePos(passTo);
			System.out.println(passTo.getPos() + " " + passTo.getName() + " has the Quaffle!");
		}
  	}
  	
  	public void shoot(Chaser player, Team mine, Team opp)
  	{
  		System.out.println(player.getPos() + " " + player.getName() + " has tried to shoot!");
  		player.setHas(false);
  		if(opp.getKeeper().save(q))////////if keeper of goal saves
		{
			System.out.println(opp.getKeeper().getPos() + " " + opp.getKeeper().getName() + " has blocked the shot!");
			Chaser passTo = opp.getKeeper().findNearestChaser(opp);
			keeperPass(mine,opp.getKeeper(),passTo);					
		}
		else/////made the goal
			scored(mine,opp);
  	}
  	
  	public void bludgerTurn(Bludger b)//////beater will try to defend, if fails, dock eneregy from player attacked an move bludger random;if succeeds, beater can attack someone and process starts again
  	{
  		int i = 0;
  		Player nearest = b.chase(team,comp);
  		Team temp = null, opp=null;
  		if(team.getPlayers().contains(nearest))
  		{
  			temp = team;
  			opp = comp;
  		}
  		else
  		{
  			temp = comp;
  			opp = team;
  		}
  		System.out.println(b.getName() +" is attacking " + nearest.getPos() + " " + nearest.getName() + " from Team " + temp.getName() + "!");
  		Beater nearBeater = nearest.findNearestBeater(temp);
  		while(nearBeater.defend(nearest)&&(i<3))
  		{
  			if(nearBeater.getName().equalsIgnoreCase(nearest.getName()))
  				System.out.println(nearBeater.getPos() + " " + nearBeater.getName() + " has defended himself!");
  			else
  				System.out.println(nearBeater.getPos() + " " + nearBeater.getName() + " has defended " + nearest.getName() +"!");
  			moveToMidPoint(b,nearest,nearBeater);
  			b.move(nearBeater.getX(),nearBeater.getY());
  			nearest = nearBeater.findNearestPlayer(opp);
  			System.out.println(nearBeater.getPos() + " " +  nearBeater.getName() + " is now attacking " + nearest.getName() + " from Team " + opp.getName() + "!");
  			nearBeater.decEnergy(5);
  			if(team.getPlayers().contains(nearest))
  			{
  				temp = team;
  				opp = comp;
  			}
  			else
  			{
  				temp = comp;
  				opp = team;
  			}
  			nearBeater = nearest.findNearestBeater(temp);
  			i++;
  		}
  		if(nearBeater.getName().equalsIgnoreCase(nearest.getName()))
  			System.out.println(nearBeater.getPos() + " " + nearBeater.getName() + " was unsuccessful in defending himself!");
  		else
  			System.out.println(nearBeater.getPos() + " " + nearBeater.getName() + " was unsuccessful in defending " + nearest.getName() +"!");
  		nearest.decEnergy(15);
  		moveBludger(b);
  	}
  	
  	public void moveBludger(Player p)
  	{
  		int x = (int)(Math.random()*11);
  		int y = (int)(Math.random()*21);
  		while(myField[x][y]!=null)
  		{
  			x = (int)(Math.random()*11);
  			y = (int)(Math.random()*21);
  		}
  		p.move(x,y);
  	}
  	
  	public void seekerMove(Team temp, Seeker myS)
  	{
  		System.out.println(myS.getPos() + " " + myS.getName() + " from team " + temp.getName() + " has seen the Snitch!");
  		if(myS.clutch(s))
  		{
  			System.out.println(myS.getPos() + " " + myS.getName() + " has clutched the Snitch!");
  			temp.addPoints(150);
  			s.setFound(true);
  			setNull(myS.getX(),myS.getY());
  			myS.move(s.getX(),s.getY());
  			setField(myS);
  		}
  		else
  		{
  			System.out.println(myS.getPos() + " " + myS.getName() + " has failed to clutch the Snitch! :(");
  			s.decEnergy(10);
  			s.move();
  		}
  	}
  	
  	public void snitchMove()
  	{
  		Seeker teamSeeker = team.getSeekers().get(0);
  		Seeker compSeeker = comp.getSeekers().get(0);
  		if(teamSeeker.scan(s)&&(!compSeeker.scan(s)))
	  		seekerMove(team,teamSeeker);
  		else if(compSeeker.scan(s)&&(!teamSeeker.scan(s)))
	  		seekerMove(comp,compSeeker);
	  	else if(compSeeker.scan(s)&&teamSeeker.scan(s))
  		{
  			if(compSeeker.calcDistance(s)<teamSeeker.calcDistance(s))
  				seekerMove(comp,compSeeker);
  			else if(teamSeeker.calcDistance(s)<compSeeker.calcDistance(s))
	  			seekerMove(team,teamSeeker);
	  		else
	  		{
	  			if(compSeeker.getSpeed()>teamSeeker.getSpeed())
	  				seekerMove(comp,compSeeker);
	  			else
	  				seekerMove(team,teamSeeker);
	  		}
  		}
  		else
  		{
  			setNull(teamSeeker.getX(),teamSeeker.getY());
  			setNull(compSeeker.getX(),compSeeker.getY());
  			teamSeeker.move();
  			compSeeker.move();
  			setField(teamSeeker);
  			setField(compSeeker);
  			s.move();
  		}
  			
  	}
}