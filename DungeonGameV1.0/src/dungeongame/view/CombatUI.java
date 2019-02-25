package dungeongame.view;

import dungeongame.model.Player;
import dungeongame.model.Situation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CombatUI implements EventHandler<ActionEvent>
{
	private Stage myStage;
	Text playerNameTxt;
	Text playerSpellTxt;
	Text situationNameTxt;
	Text situationSpellTxt;
	final int SITUATION_POINTS = 200;

	Button combatBtn = new Button("Engage");
	Player player;
	Situation situation;

	public CombatUI()

	{
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);

		myStage = new Stage();
		myStage.setTitle("Combat Window");
		myStage.setScene(scene);
		myStage.setWidth(400);
		myStage.setHeight(400);

		playerNameTxt = new Text();
		playerSpellTxt = new Text();
		situationNameTxt = new Text();
		situationSpellTxt = new Text();

		Label label1 = new Label("Player info");
		Label label2 = new Label("Situation info");
		Label label3 = new Label("Player Name: ");
		Label label4 = new Label("Player Spell: ");
		Label label5 = new Label("Situation Name: ");
		Label label6 = new Label("Situation Health: ");

		pane.add(combatBtn, 5, 5);
		pane.add(label3, 0, 3);
		pane.add(label4, 0, 4);
		pane.add(label1, 0, 2);
		pane.add(label2, 8, 2);
		pane.add(label5, 8, 3);
		pane.add(label6, 8, 4);
		pane.add(playerNameTxt, 1, 3);
		pane.add(playerSpellTxt, 1, 4);
		pane.add(situationNameTxt, 9, 3);
		pane.add(situationSpellTxt, 9, 4);

		combatBtn.setOnAction(this);
	}


	public void show(Player player, Situation situation)
	{
		this.player = player;
		this.situation = situation;

		playerNameTxt.setText(this.player.getName());
		playerSpellTxt.setText(this.player.getSpell());
		situationNameTxt.setText(this.situation.getName());
		String enemyHP = Integer.toString(this.situation.getHealth());
		situationSpellTxt.setText(enemyHP);

		myStage.show();
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (event.getSource() == combatBtn)
		{
			if (situation.getName() == "IceWall")
			{
				if (player.getSpell().equals("FireBolt"))
				{
					situation.reduceHealth(200);
					player.addPoints(SITUATION_POINTS);
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Combat Resolution");
					alert1.setHeaderText(null);
					alert1.setContentText("IceWall Melted!  ::::  Your current point total is: " + player.getPoints());
					alert1.showAndWait();
					myStage.close();

				}
				else
				{
					spin();
					myStage.close();
				}
			}
			else if (situation.getName() == "Storm")
			{
				if (player.getSpell().equals("StormShield"))
				{
					situation.reduceHealth(200);
					player.addPoints(SITUATION_POINTS);
					Alert alert3 = new Alert(AlertType.INFORMATION);
					alert3.setTitle("Combat Resolution");
					alert3.setHeaderText(null);
					alert3.setContentText("Storm Survived!  ::::  Your current point total is: " + player.getPoints());
					alert3.showAndWait();	
					myStage.close();

				}
				else
				{
					spin();
					myStage.close();
				}	

			}
			else if (situation.getName() == "Chasm")
			{
				if (player.getSpell().equals("Levitate"))
				{
					situation.reduceHealth(200);
					player.addPoints(SITUATION_POINTS);
					Alert alert7 = new Alert(AlertType.INFORMATION);
					alert7.setTitle("Combat Resolution");
					alert7.setHeaderText(null);
					alert7.setContentText("You Levitated Across!  ::::  Your current point total is: " + player.getPoints());
					alert7.showAndWait();	
					myStage.close();

				}
				else
				{
					spin();
					myStage.close();
				}	

			}
			else
			{
				int pdmg = player.spellDamage();
				situation.reduceHealth(pdmg);
				if (situation.isAlive())
				{
					int sdmg = situation.spellDamage();
					player.reduceHealth(sdmg);
					if (player.isAlive())
					{
						Alert alert5 = new Alert(AlertType.INFORMATION);
						alert5.setTitle("Combat Resolution");
						alert5.setHeaderText(null);
						alert5.setContentText("Your current HP " + player.getHealth() + "  ::::  Enemy current HP " + situation.getHealth());
						alert5.showAndWait();						
					}
					else
					{
						Alert alertFatal = new Alert(AlertType.INFORMATION);
						alertFatal.setTitle("Death Screen");
						alertFatal.setHeaderText("You have Perished!");
						alertFatal.setContentText("You have Died! Your final point total is: " + player.getPoints());
						alertFatal.showAndWait();
						Platform.exit();
					}

				}
				else
				{
					int points;
					if (situation.getName().equals("Draenei"))
					{
						points = 150;
					}
					else if (situation.getName().equals("Dwarf"))
					{
						points = 125;
					}
					else
					{
						points = 100;
					}
					player.addPoints(points);
					Alert alert6 = new Alert(AlertType.INFORMATION);
					alert6.setTitle("Combat Resolution");
					alert6.setHeaderText(null);
					alert6.setContentText("You have killed the " + situation.getName() + "  ::::  Your current point total is: " + player.getPoints());
					alert6.showAndWait();	
					myStage.close();
				}
				if (player.getPoints() > 900)
				{
					Alert alertWin = new Alert(AlertType.INFORMATION);
					alertWin.setTitle("WINNER SCREEN");
					alertWin.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alertWin.setContentText("You have beaten the game, Thou have slaint or surpassed all of the obstacles!");
					alertWin.showAndWait();
					Platform.exit();
				}
			}
		}
	}
	public void spin()
	{
		Alert alert4 = new Alert(AlertType.INFORMATION);
		alert4.setTitle("Combat Resolution");
		alert4.setHeaderText(null);
		alert4.setContentText("Spell Ineffective!");
		alert4.showAndWait();
	}
}
