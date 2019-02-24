package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); //instanciação
			ScrollPane scrollPane = loader.load();//carregar a View
			scrollPane.setFitToHeight(true); // ajustando a barra de menu, para ficar até o final da altura
			scrollPane.setFitToWidth(true);// ajustando a barra de menu, para ficar até o final da largura
//---------------------------------------------------------------------------------------------------------------------------------------------------------				
			
			Scene mainScene = new Scene(scrollPane);//cena principal passando como argumento a linha View
			primaryStage.setScene(mainScene);//palco com a cena principal
			primaryStage.setTitle("Aplicação JavaFX de Modelo");//Titulo para o Palco
			primaryStage.show();//mostrar o palco
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
