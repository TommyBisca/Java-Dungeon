package dungeongame.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

import com.sun.prism.MediaFrame;

import dungeongame.model.Player;
import dungeongame.model.Situation;
public class GameUI implements EventHandler<ActionEvent>
{
	private Stage myStage;
	private ComboBox<String> spellsCB = new ComboBox<>();
	private ComboBox<String> sitsCB = new ComboBox<>();
	Button spellBtn = new Button("Presto!");
	Button sitBtn = new Button("Challenge!");
	Label label0 = new Label("Step 2: Choose a Spell");
	Label label1 = new Label("Step 1: Choose a situation or enemy to face");
	Label label2 = new Label("Step 3: Click below to change spells");
	Label label3 = new Label("Step 4: Click below to challenge the situation");
	static Player player = new Player("Tommy"); 

	CombatUI combatUI;

	public GameUI(Stage gameUI)
	{
		FlowPane pane = new FlowPane();
		pane.setOrientation(Orientation.VERTICAL);
		Scene scene = new Scene(pane);

		myStage = gameUI;
		myStage.setTitle("Spell Menu");
		myStage.setScene(scene);
		myStage.setWidth(400);
		myStage.setHeight(400);
		
		pane.getChildren().add(label1);
		pane.getChildren().add(sitsCB);
		pane.getChildren().add(label0);
		pane.getChildren().add(spellsCB);
		pane.getChildren().add(label2);
		pane.getChildren().add(spellBtn);
		pane.getChildren().add(label3);
		pane.getChildren().add(sitBtn);

		spellBtn.setOnAction(this);
		sitBtn.setOnAction(this);
		
		pane.setHgap(10);
		pane.setVgap(5);				
	}

	public void show(List<String> spells, List<String> sits)
	{
		Collections.sort(spells);
		spellsCB.getItems().setAll(spells);
		spellsCB.setValue(spells.get(0));

		Collections.sort(sits);
		sitsCB.getItems().setAll(sits);
		sitsCB.setValue(sits.get(0));
		myStage.show();
	}

	@Override
	public void handle(ActionEvent event) 
	{

		if (event.getSource() == spellBtn)
		{
			String theNewSpell = spellsCB.getValue();
			player.setSpell(theNewSpell);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Spell Chosen");
			alert.setHeaderText(null);
			alert.setContentText(player.getSpell() + " has a damage value of: " + player.spellDamage());
			alert.showAndWait(); 
		}
		else if (event.getSource() == sitBtn)
		{
			Situation sit = Game.getInstance().findSit(sitsCB.getValue());
			CombatUI cui = new CombatUI();
			cui.show(player, sit);
		}
	}
}

