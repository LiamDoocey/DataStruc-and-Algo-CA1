package jewel.ca1;

public class MyLinkedList<F> {

    // Node class
    private class node {
        F data;
        node next;

        node(F data) {
            this.data = data;
            this.next = null;
        }
    }

    // Linked List class
    private node head;
    private node tail;
    private int size;

    //get an object at a specific index
    public F get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        //if index is 0, return head
        node current = head;
        //if index is not 0, iterate through list until index is reached
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        //return data at index
        if (current == null) {
            return null;
        }
        return current.data;
    }

    //add an object to the end of the list
    public void add(F e) {
        //create new node
        node n = new node(e);

        //if list is empty, set head and tail to new node
        if (tail != null) {
            tail.next = n;
        }
        tail = n;
        //if list is empty, set head to new node
        if (size == 0) {
            head = n;
        }
        //increase size of list
        size++;
    }

    //remove an object to a specific index
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        //if index is 0, set head to next node
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        node current = head;
        //if index is not 0, iterate through list until index is reached
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        //set current node to next node
        current.next = current.next.next;
        size--;
    }

    //get the size of the list
    public int size() {
        return size;
    }

    //clear the list
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //print the list
    public String toString() {
        node current = head;
        System.out.println("---Printing List---");
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println("---End of List---\n" +
                "Current size of list: " + size + " values");
        return null;
    }
}


