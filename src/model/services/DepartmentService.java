package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();// criando a dependencia : dao ---- e injetando a dependencia DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
			
	}
	//inserir ou atualizar um novo departamento
	public void saveOrUpdate(Department obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}

}
