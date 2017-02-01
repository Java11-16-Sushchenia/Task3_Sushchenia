package by.asushenya.parcer;

import by.asushenya.parcer.bean.Node;
import by.asushenya.parcer.service.FileService;
import by.asushenya.parcer.service.factory.ServiceFactory;
import by.asushenya.parcer.service.exception.ServiceException;

public class Main {

	public static void main(String[] args)  {
	
		ServiceFactory serviceFactoryObject = ServiceFactory.getInstance();		
		FileService fileService = serviceFactoryObject.getFileService();
	
		Node node;
		
		try{
			fileService.setFile("D:\\notes.xml");
			
			while(( node = fileService.next()) != null){
				
				System.out.println(node.getNodeType()+" "+
								   node.getNodeContent());	
				
			}
		} catch(ServiceException e){
			System.out.println("при попытке анализа документа произошла ошибка:"+
																e.getMessage());
		}	
	}

}
