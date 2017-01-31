package by.asushenya.parcer.bean;

import by.asushenya.parcer.util.NodeType;

public class Node {
	private NodeType nodeType;
	private String nodeContent;
	
	public Node(){}
	
	public void setNodeType(NodeType type){
		this.nodeType = type;
	}
	public NodeType getNodeType(){
		return this.nodeType;
	}
	
	public void setNodeContent(String content){
		this.nodeContent = content;
	}
	public String getNodeContent(){
		return this.nodeContent;
	}
}
