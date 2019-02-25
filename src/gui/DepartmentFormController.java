package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable{
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
																			//ITENS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	private Department entity;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private Label labelErroNome;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
//---------------------------------------------------------------------------------------------------------------------------------------------------------
																			//METODOS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	public void setDepartment(Department entity) {//controlador tem uma instancia do departamento
		this.entity = entity;
	}
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("onBtSalvarAction");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("onBtCancelarAction");
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);// ide só aceita numero inteiro
		Constraints.setTextFieldMaxLength(txtNome, 30); // nome tera no maximo 30 caracteres 
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Valor da Entidade é Nulo");
		}
		txtId.setText(String.valueOf(entity.getId()));// a caixa de texto trabalha como string. entao converte o valor do id da entidade que é inteiro para String
		txtId.setText(String.valueOf(entity.getName()));
		
	}
	

}
