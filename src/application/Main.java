package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); //instanciação
			Parent parent = loader.load();//carregar a View
			Scene mainScene = new Scene(parent);//cena principal passando como argumento a linha View
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
