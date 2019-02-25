package dungeongame.model;

public class Player implements Caster
{

	private String name;
	private int health = 200;
	protected int points = 0;
	private String spellName = "FireBolt";
	private int damage;
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getHealth() 
	{
		return this.health;
	}
	
	public boolean isAlive() 
	{
		if (this.getHealth() > 0)
		{
			return true;
		}
		return false;
	}
	
	public void reduceHealth(int hp) 
	{
		this.health = this.health - hp;		
	}
	
	public int getPoints()
	{
		return this.points;
	}
	
	public void addPoints(int points)
	{
		this.points += points;
	}

	@Override
	public String getSpell() 
	{
		return spellName;
	}
	public void setSpell(String spellName)
	{
		this.spellName = spellName;
	}

	@Override
	public int spellDamage() 
	{
		if (this.getSpell().equals("ShadowBolt"))
		{
			damage = 100;
			return damage;
		}
		else if (this.getSpell().equals("FrostBolt"))
		{
			damage = 90;
			return damage;
		}
		else if (this.getSpell().equals("MoonBolt"))
		{
			damage = 80;
			return damage;
		}	
		else if (this.getSpell().equals("FireBolt"))
		{
			damage = 115;
			return damage;
		}
		else 
		{
			damage = 0;
			return damage;
			
		}
	}
}
