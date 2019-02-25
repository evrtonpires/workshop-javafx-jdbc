package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	public static Stage currenStage(ActionEvent event) {
		return (Stage)
				((Node) 
				event.getSource())//tipo gerenico, entao faz o downCasting para node ;
				.getScene()//pegando a cena
				.getWindow();//pegando a janela, super classe do Stage, entao se faz um downCasting
	}
}
