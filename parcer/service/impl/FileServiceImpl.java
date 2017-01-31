package by.asushenya.parcer.service.impl;

import java.io.BufferedReader;

import java.io.IOException;

import by.asushenya.parcer.dao.factory.DAOFactory;
import by.asushenya.parcer.dao.FileDAO;
import by.asushenya.parcer.dao.exception.DAOException;
import by.asushenya.parcer.service.FileService;
import by.asushenya.parcer.service.exception.ServiceException;

import by.asushenya.parcer.bean.Node;
import by.asushenya.parcer.util.NodeType;

public class FileServiceImpl implements FileService {
	
	private static BufferedReader reader;

	private static Node previousNode;
		
	static{
		previousNode = new Node();
		previousNode.setNodeType(null);
	}
	
	@Override 
	public void setFile(String fileName) throws ServiceException{
		
		DAOFactory daoFactoryObject = DAOFactory.getInstance();
	    FileDAO fileDAO = daoFactoryObject.getFileDAO();
	    
		    try {		    	
				reader = fileDAO.openFile(fileName);
				
			} catch (DAOException e) {
				
				throw new ServiceException("ошибка при открытии файла", e);
			}
	}
	
		@Override
	public Node next() throws ServiceException{			
		 int currentSymbolCode;		 				 
		 StringBuilder nodeContent = new StringBuilder(); // для формирвоания узлов содержащих контент					
								 
		 try {			 
			while((currentSymbolCode = reader.read()) != -1){							
				char currentSymbol = (char)currentSymbolCode;						 
										 
				if(currentSymbol == '<'){	
					
						if(nodeContent.length() != 0){
							
							if(isStringContrinsOnlySpaces(nodeContent)){
								
								nodeContent.setLength(0);										
								reader.reset();										
								continue;	
								
							} else{								
								Node node = new Node();
								node.setNodeContent(nodeContent.toString());
								node.setNodeType(NodeType.CONTENT);
								
								nodeContent.setLength(0);																	
								reader.reset();
								
								return node;
							}																			
						}				
					String currentNode = getCurrentNodeContent(reader);
												
					Node node = makeNodeFromString(currentNode);
					
					return node;
				}
				
				if(currentSymbol != '\t' && //отсекаем не нужные символы
				   currentSymbol != '\n' &&
				   currentSymbol != '\r'){	
					
					nodeContent.append(currentSymbol);
					reader.mark(0);												
					continue;
				}						
			}
		} catch (IOException e) {
			
			throw new ServiceException("Ошибка в ходе анализа",e);
		}				 
		 	return null;
	}
		
	private NodeType getNodeType(String currentNodeContent){
		
		if(currentNodeContent.charAt(0) == '?'){
			
			return NodeType.DECLARATION;
			
		} else  if(currentNodeContent.charAt(currentNodeContent.length()-1) == '/'){
			
			return NodeType.WITHOUT_BODY;
		
		} else if (currentNodeContent.charAt(0) == '/'){
																
			return NodeType.CLOSE;
			
		} else{								
			return NodeType.OPEN;						
		}
	}
	
	 private  String getCurrentNodeContent(BufferedReader reader) throws ServiceException{
		 
		 StringBuilder nodeContent = new StringBuilder();
		 int c;
		 
			 try{
				  while( (char)(c = reader.read()) != '>'){
					  	nodeContent.append((char)c);
				  	}		
			 }   catch(IOException e){
				 
				 	throw new ServiceException("ошибка чтения узла",e);
			 }			 
		 return new String(nodeContent);
	 }
	 
	 private boolean isStringContrinsOnlySpaces(StringBuilder string){
		 
		 int spaceCounter = 0;
		 
		 for(int i = 0;i< string.length();i++){
			 if(string.charAt(i) == ' ') spaceCounter++;
		 }		
		 
		 if(spaceCounter == string.length()){
			 return true;
		 } else {
			 return false;
		 }
	 }
	 
	 private Node makeNodeFromString(String stringNode){
		 Node node = new Node();
		 
		 NodeType nodeType = getNodeType(stringNode);
		 
		 node.setNodeType(nodeType);
	     node.setNodeContent(stringNode);
	     
	     return node;
	 }
}
