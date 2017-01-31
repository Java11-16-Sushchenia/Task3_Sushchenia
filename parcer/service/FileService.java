package by.asushenya.parcer.service;

import by.asushenya.parcer.service.exception.ServiceException;

import by.asushenya.parcer.bean.Node;

public interface FileService {
	public Node next() 					 throws ServiceException;
	public void setFile(String fileName) throws ServiceException;
}
