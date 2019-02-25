package dungeongame.model;

public class Enemy extends Situation implements Caster
{

	private String name;
	private int health;
	private int attackPoints;
	private int damage;
	private String spellName;
	private int spellID;

	public Enemy(String name, int health, int spellID)
	{
		super(name, health);
		this.name = name;
		this.health = health;
		this.spellID = spellID;
	}

	public String getName()
	{
		return this.name;
	}
	
	public boolean isAlive() 
	{
		if (this.getHealth() > 0)
		{
			return true;
		}
		return false;
	}
	
	public int getHealth() 
	{
		return this.health;
	}
	

	public int getAttackPoints()
	{
		return this.attackPoints;
	}

	@Override
	public String getSpell() {
		return this.spellName;
	}

	@Override
	public int spellDamage() 
	{
		if (this.spellID == 1)
		{
			this.damage = 100;
			this.spellName = "ShadowBolt";
		}
		else if (this.spellID == 2)
		{
			this.damage = 90;
			this.spellName = "FrostBolt";
		}
		else if (this.spellID == 3)
		{
			this.damage = 80;
			this.spellName = "MoonBolt";
		}
		else if (this.spellID == 4)
		{
			this.damage = 115;
			this.spellName = "FireBolt";
		}
		else 
		{
			this.damage = 17;
			this.spellName = "Default Spell";
		}
		return this.damage;
	}
	@Override
	public void reduceHealth(int hp) 
	{
		this.health = this.health - hp;		
	}
	
}
