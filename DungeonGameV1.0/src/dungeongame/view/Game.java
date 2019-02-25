package dungeongame.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import dungeongame.model.Situation;
import dungeongame.model.Storm;
import dungeongame.model.Chasm;
import dungeongame.model.Draenei;
import dungeongame.model.Dwarf;
import dungeongame.model.Gnome;
import dungeongame.model.IceWall;


public class Game 
{
	Scanner console = new Scanner(System.in);
	Random rand = new Random();

	ArrayList<Situation> situations = new ArrayList<>();
	ArrayList<String> spells = new ArrayList<>();

	static private Game instance = new Game();

	private Game()
	{			 }

	static public Game getInstance()
	{
		return instance;
	}

	public ArrayList<String> getAllSpells()
	{
		return spells;
	}
	public ArrayList<String> getAllSituations()
	{
		ArrayList<String> allSits = new ArrayList<>();
		situations.stream().forEach(e -> allSits.add(e.getName()));
		return allSits;		//Still returning an ArrayList
	}

	public Situation findSit(String situationz)
	{
		Situation sitn = null;
		Optional<Situation> match = situations.stream().filter(e -> e.getName().equals(situationz)).findFirst();
		if (match.isPresent()) 
		{
			sitn = match.get();
		}
		return sitn;
	}
	public void loadSpells(String filePath) throws FileNotFoundException
	{
		Scanner input;
		input = new Scanner(new File(filePath));

		while (input.hasNext())
		{
			String line = input.nextLine();
			spells.add(line);
		}
		input.close();
	}

	public void loadSituations(String filename) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File(filename));

		while(input.hasNext())
		{
			String line = input.nextLine();
			String[] fields = line.split(",");

			if (fields[0].equals("Dwarf"))
			{
				int spellID = Integer.parseInt(fields[1]);
				Dwarf dwarf = new Dwarf(spellID);
				situations.add(dwarf);
				dwarf.spellDamage();
			}
			else if (fields[0].equals("Gnome"))
			{
				int spellID = Integer.parseInt(fields[1]);
				Gnome gnome = new Gnome(spellID);
				situations.add(gnome);
				gnome.spellDamage();
			}
			else if (fields[0].equals("Draenei"))
			{
				int spellID = Integer.parseInt(fields[1]);
				Draenei draenei = new Draenei(spellID);
				situations.add(draenei);
				draenei.spellDamage();
			}
			else if (fields[0].equals("IceWall"))
			{
				IceWall icewall = new IceWall();
				situations.add(icewall);
			}
			else if (fields[0].equals("Chasm"))
			{
				Chasm chasm = new Chasm();
				situations.add(chasm);
			}
			else
			{
				Storm storm = new Storm();
				situations.add(storm);
			}
		}
		input.close();	
	}
}






























