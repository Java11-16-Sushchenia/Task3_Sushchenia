package by.asushenya.parcer.dao.impl;

import by.asushenya.parcer.dao.FileDAO;

import by.asushenya.parcer.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileDAOImpl implements FileDAO, AutoCloseable {	
	
	private BufferedReader reader;
	
	@Override
	public BufferedReader openFile(String fileName) throws DAOException{ 
		
		try {
			reader = new BufferedReader(
						new InputStreamReader(
							new FileInputStream(fileName), Charset.forName("UTF-8")));
			
			return reader;
		} catch (FileNotFoundException e) {	
			
			throw new DAOException ("file not found",e);
		}	
	}

	@Override
	public void close() throws DAOException {
		try {
			reader.close();
			
		} catch (IOException e) {
			throw new DAOException("closing error",e);
		}
		
		
	}
}
