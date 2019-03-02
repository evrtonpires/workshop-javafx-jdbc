package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	//validando o formulario
	private static final long serialVersionUID = 1L;
	
	//carrega a colecao com todos os erros possiveis
	private Map<String , String> errors = new HashMap<>();
	
	//guardar os erros de cada campo do formulario
	public ValidationException(String msg) {
		super(msg);
	}
	
	//retorna os erros
	public Map<String , String> getErrors(){
		return errors;
	}
	
	//permitir adicionar um elemento a colecao	
	public void addError(String nomeDoCampo, String mensagemDeErro) {
		errors.put(nomeDoCampo, mensagemDeErro);
	}
}
