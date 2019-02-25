package dungeongame.view;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApplication extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		List<String> spellList = Game.getInstance().getAllSpells();
		List<String> situationList = Game.getInstance().getAllSituations();
		GameUI ui = new GameUI(primaryStage);
		ui.show(spellList, situationList);
	}

	public static void main(String[] args)
	{
		Game game = Game.getInstance();

		try 
		{
			game.loadSituations("situations.txt");
			game.loadSpells("spells.txt");
			Application.launch(args);

		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}
}
