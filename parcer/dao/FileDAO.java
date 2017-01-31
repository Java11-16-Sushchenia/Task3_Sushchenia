package by.asushenya.parcer.dao;

import java.io.BufferedReader;

import by.asushenya.parcer.dao.exception.DAOException;

public interface FileDAO {
	BufferedReader openFile(String fineName) throws DAOException;
}
