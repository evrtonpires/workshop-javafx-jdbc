package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
																		//ITENS DE CONTROLES DE TELA
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	@FXML
	private MenuItem menuItemVendedor;
	
	@FXML
	private MenuItem menuItemDepartamento;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
																		//METODOS DE CONTROLES DE TELA
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("onMenuItemVendedorAction");
	}
	@FXML
	public void onMenuItemDepartamentoAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
	loadView("/gui/About.fxml");
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
																		//METODOS DA INTERFACE " Initializable "
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
																		//METODOS DA INTERFACE SECUNDARIA, CRIANDO OUTRA TELA.
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	private synchronized void loadView(String absoluteName) {// synchronized -  garantir que todo o processo seja interrompido durante o multtreding
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));//instanciação
			
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();//referencia CENA
		
			VBox mainVBox =(VBox)//Casting para referencia do VBOX Principal = Classe MainViewController
				((ScrollPane) //Casting para referencia do ScrollPane = Classe MainView
				mainScene.getRoot())//pega o primeiro elemento da View = ScrollPane
				.getContent();//referencia para o que tiver dentro do ScrollPane
		
		
		//refencia para o menu
		
		Node mainMenu = mainVBox.getChildren().get(0);// pegando o primeiro filho do VBOX da janela principal
		
		mainVBox.getChildren().clear();// limpar todos os filhos do mainVBOX
		
		mainVBox.getChildren().add(mainMenu);// adicionando o Menu
		mainVBox.getChildren().addAll(newVBox.getChildren());//adicionando os filhos do NewVBOX
		
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Erro Carregando a Pagina", e.getMessage(), AlertType.ERROR);
		}
	}

}
