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
        //count= 0;
    }

    public void add(Object item)
    {
        if(head==null)
        {
            head= new DoubleNode(item,null,null);
            tail= head;
        }
        else
        {
            tail.setNext(new DoubleNode(item,null,tail));
            tail= tail.getNext();
        }
        count++;
    }

    public void remove(int value)
    {
        DoubleNode previousItem;
        DoubleNode nextItem;
        DoubleNode curr= head;
        boolean found= false;
        while(!found && curr!=null)
        {
            int val= (Integer)curr.getValue();
            if(val==value)
                found= true;
            else
            {
                curr= curr.getNext();
            }
        }
        if(found)
        {
            if(curr.getPrev()!=null && curr.getNext()!=null)
            {
                previousItem=curr.getPrev();
                previousItem.setNext(curr.getNext());
                nextItem=curr.getNext();
                nextItem.setPrev(curr.getPrev());
                count--;
            }
            if(curr.getPrev()==null)
            {
                head=curr.getNext();
                nextItem=curr.getNext();
                nextItem.setPrev(head);
                head.setPrev(null);
                count--;
            }
            if(curr.getNext()==null)
            {
                tail=curr.getPrev();
                previousItem=tail.getPrev();
                previousItem.setNext(tail);
                tail.setNext(null);
                count--;
            }
        }
        else
            System.out.println("Not found");
    }

    public int find(int x)
    {//POST returns the index of the first occurrence of x, 
        //     if x is not in the list, then -1 is returned
        DoubleNode curr= head;
        int i= 0;
        boolean found= false;
        while(!found && curr!=null && i<=count)
        {
            int val= (Integer)curr.getValue();
            if(val==x)
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
        for (DoubleNode curr = head; curr != null; curr = curr.getNext()) {
            System.out.println(curr.getValue());
        } 
    }

}