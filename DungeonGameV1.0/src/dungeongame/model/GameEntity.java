package dungeongame.model;

public interface GameEntity 
{
	String getName();
	int getHealth();
	boolean isAlive();
	void reduceHealth(int hp);
}
