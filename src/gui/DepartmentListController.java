package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener {

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// ITENS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;

	@FXML
	private TableColumn<Department, String> tableColumnNome;

	@FXML
	private TableColumn<Department, Department> tableColumnEDIT;
	
	@FXML
	TableColumn<Department, Department> tableColumnREMOVE;

	@FXML
	private Button btNovoCadastro;

	@FXML
	private DepartmentService service;

	// lista para carregar os departamentos
	private ObservableList<Department> obsList;
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONTROLES DE TELA
//---------------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	public void onBtNovoCadastroAction(ActionEvent event) {
		Stage parentStage = Utils.currenStage(event);// referencia com Stage atual
		Department obg = new Department();
		createDialogForm(obg, "/gui/DepartmentForm.fxml", parentStage);// passando a referencia para criar a janela do
																		// formulario
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS DA INTERFACE " Initializable "
//---------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();// iniciar componente na tela

	}

	private void initializeNodes() {
		// comportamento das colunas na tela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));// padrao do JAVAFX para iniciar o
																			// comportamento das colunas
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("name"));// padrao do JAVAFX para iniciar o
																				// comportamento das colunas

		// referencia para o Stage
		Stage stage = (Stage) // downCasting para Stage, pois Window eh sua super classe
		Main.getMainScene()// acessando a cena
				.getWindow();// pega a referencia para a janela, ele eh uma super classe do Stage

		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // comando para a tableViewDepartment
																				// acompanhar a altura da janela
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
//invertendo o controle, ao inves de dar NEW direto na classe
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// responsavel por acessar o servi�o , carregar os departamentos, e jogar os
	// departamentos na observableList, que ser� associado ao tableView,e ai entao
	// os departamentos irao aparecer na tela
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service esta Nulo");
		}
		List<Department> list = service.findAll();// recuperar os departamentos do servi�os

		obsList = FXCollections.observableArrayList(list);// instacia o observableList,pegando os dados originais da
															// lista
		tableViewDepartment.setItems(obsList);
		initEditButtons();//acrescenta um novo botao , com o texto edit, em cada linha da tabela, e quando clicar, ira abrir um 
		//formulario de edicao usando o createDialogForm();
		
		//remove os departamentos
		initRemoveButtons();
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {// janela de dialogo
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));// instancia��o
			Pane pane = loader.load(); // carregamento painel

			DepartmentFormController controller = loader.getController();// pegou o controlador da tela que foi
																			// carregada
			controller.setDepartment(obj);
			controller.setDepartmentService(new DepartmentService());// inje��o de dependencia para salvar no banco
			controller.subscribeDataChangeListener(this);// esta se inscrevendo para receber o evento, que quando for
															// disparado, executara """" public void onDataChanged()
															// """"
			controller.updateFormData();// carregar os dados do objeto no formulario

			Stage dialogStage = new Stage();

			dialogStage.setTitle("Dados do Departamento");// titulo da janela
			dialogStage.setScene(new Scene(pane));// nova cena do palco
			dialogStage.setResizable(false);// diz se a janela pode ou nao ser redimencionada
			dialogStage.initOwner(parentStage);// Stage pai dessa janela "parentStage"
			dialogStage.initModality(Modality.WINDOW_MODAL);// janela fica travada enquanto a janela estiver aberta, nao
															// acessa a anterior
			dialogStage.showAndWait();// funcao para carregar a janela do formulario, para preencher um novo
										// departamento

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro Carregamento de Tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		// quando receber a notifica��o que os dados foram alterados, ir� atualizar os
		// dados da tabela
		// usando o metodo updateTableView();
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Department, Department>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Department obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/DepartmentForm.fxml", Utils.currenStage(event)));
			}
		});
	}
	//cria um botao chamado Remover
	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Department, Department>() {
		 private final Button button = new Button("Remover");
		 @Override
		 protected void updateItem(Department obj, boolean empty) {
		 super.updateItem(obj, empty);
		 if (obj == null) {
		 setGraphic(null);
		 return;
		 }
		 setGraphic(button);
		 button.setOnAction(event -> removeEntity(obj));
		 }
		 });
		}

	//remocao de um departamento
	private void removeEntity(Department obj) {		
		Optional<ButtonType> resultado = Alerts.Confirmacao("Confirma��o", "Deseja realmente Remover ?");
		
		if(resultado.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Servi�o esta vazio");
			}
			try {
			service.remove(obj);
			updateTableView();
			}
			catch(DbIntegrityException e ) {
				Alerts.showAlert("Erro Remo��o do Objeto", null, e.getMessage(), AlertType.ERROR);
			}
			
		}
		
	}
}
