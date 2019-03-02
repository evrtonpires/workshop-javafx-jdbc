package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
																			//ITENS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	private Department entity;
	
	private DepartmentService service;
	
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
	public void onBtSalvarAction(ActionEvent event) {
		//salvar departamento no banco de dados
		if(entity == null) {
			throw new IllegalStateException("Entidade esta vazia");
		}
		if(service == null) {
			throw new IllegalStateException("Service esta vazio");
		}
		try {
		entity = getFormData();//vai pegar os dados nas caixas dos formularios e instanciar um departamento
		service.saveOrUpdate(entity);//salva no banco de dados
		Utils.currenStage(event).close();//pegando referencia da janela atual, para fechar a janela após salvar os dados
		}
		catch(DbException e ) {
			Alerts.showAlert("Erro para salvar o Objeto", null, e.getMessage(), AlertType.ERROR);
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//retorna os dados do formulario e cria um novo objeto
	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getText()));//vai converter o String que foi digitado na caixa de texto para inteiro
		obj.setName(txtNome.getText()); // nome do novo departamento
		
		return obj;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currenStage(event).close(); // cancelando a execução do Cadastro
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
	//---------------------------------------------------------------------------------------------------------------------------------------------------------	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
}
