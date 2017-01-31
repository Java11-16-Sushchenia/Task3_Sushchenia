package by.asushenya.parcer.service.factory;

import by.asushenya.parcer.service.FileService;
import by.asushenya.parcer.service.impl.FileServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private FileService fileService = new FileServiceImpl();
	
	private ServiceFactory(){}
	
	public FileService getFileService(){
		return fileService;
	}
	
	public static ServiceFactory getInstance(){
		return instance;
	}
}
