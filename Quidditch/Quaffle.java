public class Quaffle extends Player
{
	public Quaffle(int x, int y)
	{
		super("Quaffle","Quaffle",x,y);
	}

	public void resetQuaffle(Team loser)////////////////////team who didn't score
	{
		Chaser temp = loser.getFastChaser();
		temp.setHas(true);
		changePos(temp);
	}

	public void changePos(Player p)/////////////////receive Player to change coordinates to and change speed to same as player
	{
		super.move(p.getX(),p.getY());
	}

	public void setSpeed(Player p)
  	{
  		getStats().set(2,p.getStrength());/////////quaffle's speed is same as player's strength
  	}

}