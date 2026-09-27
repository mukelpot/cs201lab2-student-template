import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (isEmpty()){
            return;
        }

        ArrayList<Node<E>> allNodes = new ArrayList<>();

        Node<E> current = head;

        while (current != null){
            allNodes.add(current);
            current = current.getNext();
        }

        ArrayList<Node<E>> sortedNodes = new ArrayList<>(allNodes);

        sortedNodes.sort((a, b) -> a.getElement().compareTo(b.getElement()));
        
        HashMap<Node<E>,Node<E>> pairs = new HashMap<>();

        for (int i = 0; i < size/2; i++){
            pairs.put(sortedNodes.get(i), sortedNodes.get(size-1-i));
            pairs.put(sortedNodes.get(size-1-i), sortedNodes.get(i));
        }

        for (int i = 0; i < size; i++){
            if (pairs.get(allNodes.get(i)) != null){
                allNodes.set(i, pairs.get(allNodes.get(i)));
            }
        }

        tail = allNodes.get(size-1);
        tail.setNext(null);
        head = allNodes.get(0);

        for (int i = 0; i < size - 1; i++){
            allNodes.get(i).setNext(allNodes.get(i+1));
        }
    }
}

