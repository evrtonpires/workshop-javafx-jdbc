package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
																//ITENS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnNome;
	
	@FXML
	private Button btNovoCadastro;
	
	@FXML
	private DepartmentService service;
	
	//lista para carregar os departamentos
	private ObservableList<Department> obsList;
//---------------------------------------------------------------------------------------------------------------------------------------------------------
																//METODOS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@FXML
	public void onBtNovoCadastroAction() {
		System.out.println("onBtNovoCadastroAction");
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------
															//METODOS DA INTERFACE " Initializable "
//---------------------------------------------------------------------------------------------------------------------------------------------------------
@Override
public void initialize(URL url, ResourceBundle rb) {
	initializeNodes();//iniciar componente na tela

}

private void initializeNodes() {
	//comportamento das colunas na tela
	tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));// padrao do JAVAFX para iniciar o comportamento das colunas
	tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));// padrao do JAVAFX para iniciar o comportamento das colunas
	
	//referencia para o Stage
	Stage stage = (Stage)//downCasting para Stage, pois Window eh sua super classe
			Main.getMainScene()//acessando a cena
			.getWindow();//pega a referencia para a janela, ele eh uma super classe do Stage
	
	tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // comando para a tableViewDepartment acompanhar a altura da janela
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//invertendo o controle, ao inves de dar NEW direto na classe
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// responsavel por acessar o serviço , carregar os departamentos, e jogar os departamentos na observableList, que será associado ao tableView,e ai entao os departamentos irao aparecer na tela
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service esta Nulo");
		}
		List<Department> list = service.findAll();//recuperar os departamentos do serviços
		
		obsList = FXCollections.observableArrayList(list);//instacia o observableList,pegando os dados originais da lista
		tableViewDepartment.setItems(obsList);
				
	}

}
