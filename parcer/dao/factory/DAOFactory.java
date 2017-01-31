package by.asushenya.parcer.dao.factory;

import by.asushenya.parcer.dao.FileDAO;
import by.asushenya.parcer.dao.impl.FileDAOImpl;

public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();
	
	private DAOFactory(){}
	
	private FileDAO fileDAO = new FileDAOImpl();
	
	public FileDAO getFileDAO(){
		return fileDAO;
	}
	
	public static DAOFactory getInstance(){
		return daoFactory;
	}
	
}
	
	
