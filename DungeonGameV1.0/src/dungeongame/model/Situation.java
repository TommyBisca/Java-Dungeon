package dungeongame.model;

import java.util.Comparator;


public class Situation implements GameEntity
{
	private String name;
	private int health;

	protected Situation(String name, int health)
	{
		this.name = name;
		this.health = health;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public int getHealth() 
	{
		return this.health;
	}
	
	@Override
	public boolean isAlive() 
	{
		if (this.getHealth() > 0)
		{
			return true;
		}
		return false;
	}
	@Override
	public void reduceHealth(int hp) 
	{
		this.health = this.health - hp;		
	}

	public int spellDamage() 
	{
		return 0;
	}
	
	static class SituationComparer implements Comparator<Situation>
	{

		@Override
		public int compare(Situation sit1, Situation sit2) 
		{
			if (sit1.getName() == sit2.getName())
			{
				return sit1.spellDamage() - sit2.spellDamage();
			}
			else
			{
				return sit2.spellDamage() - sit1.spellDamage();
			}
		}
		
	}
}
