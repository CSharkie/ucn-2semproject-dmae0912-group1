package model;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>
{
    private DoubleNode<E> head;
    private DoubleNode<E> tail;
    private int count;

    public LinkedList()
    {
        head= null;
        tail= null;
        count= 0;
    }

    public void add(E item)
    {
        if(head==null)
        {
            head= new DoubleNode<E>(item,tail,head);
            tail= head;
        }
        else
        {
            tail.setNext(new DoubleNode<E>(item, head, tail));
            tail= tail.getNext();
        }
        count++;
    }

    public void remove(E item)
    {
        DoubleNode<E> previousItem;
        DoubleNode<E> nextItem;
        DoubleNode<E> curr= head;
        boolean found= false;
        boolean modified=false;
        int i=1;
        while(!found && i<=count)
        {
            E val= (E)curr.getValue();
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

    public int find(E item)
    {//POST returns the index of the first occurrence of x, 
        //     if x is not in the list, then -1 is returned
        DoubleNode<E> curr= head;
        int i= 0;
        boolean found= false;
        while(!found && curr!=null && i<=count)
        {
            E val= (E) curr.getValue();
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
    public ListIterator<Object> listIterator(int index) {
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<Object> {
        private DoubleNode<E> lastReturned = head;
        private DoubleNode<E> next;
        private int nextIndex;

        ListItr(int index) {
            if (index < 0 || index > count)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+count);
            if (index < (count >> 1)) {
                next = head.getNext();
                for (nextIndex=0; nextIndex<index; nextIndex++)
                    next = next.getNext();
            } else {
                next = head;
                for (nextIndex=count; nextIndex>index; nextIndex--)
                    next = next.getPrev();
            }
        }

        public boolean hasNext() {
            return nextIndex != count;
        }

        public Object next() {
            if (nextIndex == count)
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.getNext();
            nextIndex++;
            return lastReturned.getValue();
        }

        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        public Object previous() {
            if (nextIndex == 0)
                throw new NoSuchElementException();

            lastReturned = next = next.getPrev();
            nextIndex--;
            return lastReturned.getValue();
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex-1;
        }

        public void remove() {
            DoubleNode<E> lastNext = lastReturned.getNext();
            try {
                LinkedList.this.remove(lastReturned.getValue());
            } catch (NoSuchElementException e) {
                throw new IllegalStateException();
            }
            if (next==lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = head;
        }

        public void set(Object e) {
            if (lastReturned == head) throw new IllegalStateException();
            lastReturned.setValue(e);
        }

        public void add(Object e) {
            lastReturned = head;
            add(e);
            nextIndex++;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> iterator() {
		return (Iterator<E>) new ListItr(0);
	}
}