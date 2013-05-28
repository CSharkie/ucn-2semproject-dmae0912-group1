package model;

public class DoubleNode<E>{
	
	private E value;
	private DoubleNode<E> next;
	private DoubleNode<E> prev;

	/**
	 * Constructors for objects of class DoubleNode
	 */
	public DoubleNode()
	{
		value= null;
		next= null;
		prev= null;
	}

	public DoubleNode(E v, DoubleNode<E> n, DoubleNode<E> p)
	{
		value= v;
		next= n;
		prev= p;
	}
	
	public E getValue()
	{
	    return value;
	}
	public DoubleNode<E> getNext()
	{
	    return next;
	}
	public DoubleNode<E> getPrev()
	{
	    return prev;
	}
	@SuppressWarnings("unchecked")
	public void setValue(Object v)
	{
	    value= (E) v;
	}
	public void setNext(DoubleNode<E> n)
	{
	    next= n;
	}
	public void setPrev(DoubleNode<E> p)
	{
	    prev= p;
	}
	
}
