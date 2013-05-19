package model;

public class DoubleNode{
	
	private Object value;
	private DoubleNode next;
	private DoubleNode prev;

	/**
	 * Constructors for objects of class DoubleNode
	 */
	public DoubleNode()
	{
		value= null;
		next= null;
		prev= null;
	}

	public DoubleNode(Object v, DoubleNode n, DoubleNode p)
	{
		value= v;
		next= n;
		prev= p;
	}
	
	public Object getValue()
	{
	    return value;
	}
	public DoubleNode getNext()
	{
	    return next;
	}
	public DoubleNode getPrev()
	{
	    return prev;
	}
	public void setValue(Object v)
	{
	    value= v;
	}
	public void setNext(DoubleNode n)
	{
	    next= n;
	}
	public void setPrev(DoubleNode p)
	{
	    prev= p;
	}
	
}
