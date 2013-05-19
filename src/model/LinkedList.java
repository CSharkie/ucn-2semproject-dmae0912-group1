package model;

public class LinkedList
{
    private DoubleNode head;
    private DoubleNode tail;
    private int count;

    public LinkedList()
    {
        head= null;
        tail= null;
        count= 0;
    }

    public void add(Object item)
    {
        if(head==null)
        {
            head= new DoubleNode(item,tail,head);
            tail= head;
        }
        else
        {
            tail.setNext(new DoubleNode(item, head, tail));
            tail= tail.getNext();
        }
        count++;
    }

    public void remove(Object item)
    {
        DoubleNode previousItem;
        DoubleNode nextItem;
        DoubleNode curr= head;
        boolean found= false;
        boolean modified=false;
        int i=1;
        while(!found && i<=count)
        {
            Object val= (Object)curr.getValue();
            if(val==item)
                found= true;
            else
            {
                i++;
                curr= curr.getNext();
            }
            
        }
        if(found)
        {
            if(curr.getPrev()!=null && curr.getNext()!=head)
            {
                previousItem=curr.getPrev();
                previousItem.setNext(curr.getNext());
                nextItem=curr.getNext();
                nextItem.setPrev(curr.getPrev());
                count--;
                modified=true;
            }
            if(curr.getPrev()==null && modified==false)
            {
                head=curr.getNext();
                nextItem=curr.getNext();
                nextItem.setPrev(null);
                head.setPrev(null);
                tail.setNext(head);
                count--;
                modified=true;
            }
            if(curr.getNext()==head && modified==false)
            {
                tail=curr.getPrev();
                previousItem=tail.getPrev();
                previousItem.setNext(tail);
                tail.setNext(head);
                count--;
            }
        }
        else
            System.out.println("Not found");
    }

    public int find(Object item)
    {//POST returns the index of the first occurrence of x, 
        //     if x is not in the list, then -1 is returned
        DoubleNode curr= head;
        int i= 0;
        boolean found= false;
        while(!found && curr!=null && i<=count)
        {
            Object val= (Object)curr.getValue();
            if(val==item)
                found= true;
            else
            {
                curr= curr.getNext();
                i++;
            }
        }
        if(found)
            return i;
        else
            return -1;
    }

    //Solution:

    public void print(){
    	DoubleNode curr=null;
        for (int i=1; i<=count; i++) {
        	if(i==1){curr=head;}
            System.out.println(curr.getValue());
            curr.getNext();
        } 
    }

}