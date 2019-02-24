package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("onMenuItemDepartamentoAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("onMenuItemAboutAction");
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
																		//METODOS DA INTERFACE " Initializable "
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}

}
